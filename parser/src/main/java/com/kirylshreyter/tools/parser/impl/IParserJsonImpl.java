package com.kirylshreyter.tools.parser.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map.Entry;

import com.kirylshreyter.tools.parser.IParser;
import com.kirylshreyter.tools.parser.converters.JsonConverters;
import com.kirylshreyter.tools.parser.exeptions.NotValidJsonFileToParseExeption;
import com.kirylshreyter.tools.parser.utils.JsonUtils;
import com.kirylshreyter.tools.parser.validation.JsonValidation;

public class IParserJsonImpl implements IParser {
	JsonValidation validation = new JsonValidation();
	JsonConverters converters = new JsonConverters();
	JsonUtils utils = new JsonUtils();

	@Override
	public <T> Object fromJson(String sourceFile, Class<?> clazz) throws FileNotFoundException, InstantiationException, IllegalAccessException {
		String notEditedJsonString = converters.getJsonStringArrayFromJsonFile(sourceFile);
		validation.checkIfJsonStringIsValid(notEditedJsonString);
		String editedJsonString = utils.removeLiteralsFromJsonString(notEditedJsonString);
		String string = converters.getStringObjectsArrayFromJsonString(editedJsonString);
		Object convertedObject = null;
		HashMap<LinkedList<Object>, LinkedList<Object>> hashMap = new HashMap<>();
		converters.splitStringObjectToStringFieldsMap(string, hashMap);
		convertedObject = utils.createInstanceOfClass(clazz);
		Method methods[] = convertedObject.getClass().getDeclaredMethods();

		Iterator<Entry<LinkedList<Object>, LinkedList<Object>>> iterator = hashMap.entrySet().iterator();
		LinkedList<Object> keySet = null;
		LinkedList<Object> valueSet = null;

		if (iterator.hasNext()) {
			Entry<LinkedList<Object>, LinkedList<Object>> pair = iterator.next();
			keySet = pair.getKey();
			valueSet = pair.getValue();

			for (int j = 0; j < keySet.size(); j++) {
				String str = keySet.get(j).toString();
				for (int x = 0; x < methods.length; x++) {
					if (methods[x].getName().toLowerCase().contains(str)
							& methods[x].getName().substring(0, 3).contains("set")) {
						try {
							methods[x].invoke(convertedObject, valueSet.get(j));
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalArgumentException e) {
							throw new NotValidJsonFileToParseExeption();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
		return convertedObject;
	}

	@Override
	public boolean toJson(String fileName, Object objectToSave) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		File file = new File(fileName);
		if (!file.exists()){
			file.createNewFile();
		}
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("{\n");
		Method methods[] = objectToSave.getClass().getDeclaredMethods();
		for (Method method : methods) {
			String fieldName = null, fieldValue = null;
			if(method.getName().substring(0, 3).contains("get")){
				fieldName = method.getName().substring(3,method.getName().length());
				System.out.println(fieldName);
				fieldValue = method.invoke(objectToSave).toString();
				stringBuilder.append("\t\"");
				stringBuilder.append(fieldName);
				stringBuilder.append("\":\"");
				stringBuilder.append(fieldValue);
				stringBuilder.append("\",\n");
			}
			if(method.getName().substring(0, 2).contains("is")){
				fieldName = method.getName().substring(2,method.getName().length());
				System.out.println(fieldName);
				fieldValue = method.invoke(objectToSave).toString();
				stringBuilder.append("\t\"");
				stringBuilder.append(fieldName);
				stringBuilder.append("\":\"");
				stringBuilder.append(fieldValue);
				stringBuilder.append("\",\n");
			}
			
		}
		stringBuilder.delete(stringBuilder.length()-2, stringBuilder.length());
		stringBuilder.append("\n}");
		try(BufferedWriter out = new BufferedWriter(new FileWriter(file))){
			out.write(stringBuilder.toString());
		}catch (Exception e) {
			return false;
		}
		
		return true;
	}

}
