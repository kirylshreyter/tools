package com.kirylshreyter.tools.parser.impl;

import java.io.FileNotFoundException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
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
	public <T> Object fromJson(String sourceFile, Class<?> clazz) throws FileNotFoundException {
		List<String> list = converters.getJsonStringArrayFromJsonFile(sourceFile);
		String fullString = utils.removeLiteralsFromJsonString(converters.getJsonStringFromJsonStringArray(list));

		validation.checkIfJsonStringIsValid(fullString);

		String string = converters.getStringObjectsArrayFromJsonString(fullString);

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
							& methods[x].getName().toLowerCase().contains("set")) {
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
	public boolean toJson(String fileName, Object objectToSave) {
		// TODO Auto-generated method stub
		return false;
	}

}
