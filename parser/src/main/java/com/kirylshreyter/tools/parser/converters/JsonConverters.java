package com.kirylshreyter.tools.parser.converters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.kirylshreyter.tools.parser.exeptions.NotValidJsonFileToParseExeption;

public class JsonConverters {

	/**
	 * Method reads file line by line in list of strings. Each entry of list is
	 * a line of given file.
	 * 
	 * @param sourceFile
	 *            Path to file which needed to parse.
	 * @return List of strings.
	 */
	public List<String> getJsonStringArrayFromJsonFile(String sourceFile) {
		List<String> strings = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
			String tempLine = "";
			while ((tempLine = br.readLine()) != null) {
				strings.add(tempLine);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return strings;
	}

	/**
	 * Method create one string object of a few strings from list.
	 * 
	 * @param strings
	 *            List of strings which is need to concatenate.
	 * @return String object.
	 */
	public String getJsonStringFromJsonStringArray(List<String> strings) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < strings.size(); i++) {
			builder.append(strings.get(i));
		}
		return builder.toString();
	}

	public String getStringObjectsArrayFromJsonString(String fullJsonString) {
		int start_index = fullJsonString.indexOf('{');
		if (start_index == -1) {
			throw new NotValidJsonFileToParseExeption();
		}
		int end_index = 0;
		for (int i = start_index + 1, brace_counter = 1; i < fullJsonString.length() && brace_counter > 0; i++) {
			if (fullJsonString.charAt(i) == '{') {
				brace_counter++;
			}
			if (fullJsonString.charAt(i) == '}') {
				brace_counter--;
			}
			end_index = i + 1;
		}
		String stringToAdd = fullJsonString.substring(start_index, end_index);
		stringToAdd = stringToAdd.substring(stringToAdd.indexOf('{') + 1, stringToAdd.lastIndexOf('}'));
		fullJsonString = fullJsonString.substring(end_index, fullJsonString.length());
		if (fullJsonString.indexOf('{') != -1 || fullJsonString.indexOf('}') != -1) {
			throw new NotValidJsonFileToParseExeption();
		}
		return stringToAdd;
	}

	public HashMap<LinkedList<Object>, LinkedList<Object>> splitStringObjectToStringFieldsMap(String string,
			HashMap<LinkedList<Object>, LinkedList<Object>> map) {
		if (getEndKeyIndex(string) == -1) {
			throw new NotValidJsonFileToParseExeption();
		}
		String keyString = string.substring(getStartKeyIndex(), getEndKeyIndex(string));
		keyString = clear(keyString).toString();
		string = string.substring(getEndKeyIndex(string), string.length());
		String valueString = string.substring(getStartValueIndex(string), getEndValueIndex(string));
		Object valueObject = clear(valueString);
		string = string.substring(getEndValueIndex(string), string.length());
		LinkedList<Object> keyList = null;
		LinkedList<Object> valueList = null;
		Map.Entry<LinkedList<Object>, LinkedList<Object>> entry;
		Iterator<Map.Entry<LinkedList<Object>, LinkedList<Object>>> it = map.entrySet().iterator();
		if (it.hasNext()) {
			entry = it.next();
			keyList = entry.getKey();
			valueList = entry.getValue();
			keyList.add(keyString);
			valueList.add(valueObject);
			map.clear();
		} else {
			keyList = new LinkedList<Object>();
			keyList.add(keyString);
			valueList = new LinkedList<Object>();
			valueList.add(valueObject);
		}
		map.put(keyList, valueList);

		if (string.indexOf(",") != -1) {
			splitStringObjectToStringFieldsMap(string, map);
		} else {
			return map;
		}
		return map;
	}

	private int getStartKeyIndex() {
		return 0;
	}

	private int getEndKeyIndex(String string) {
		return string.indexOf(":");
	}

	private int getStartValueIndex(String string) {
		return string.indexOf(":");
	}

	private int getEndValueIndex(String string) {
		if (string.indexOf(",") != -1) {
			return string.indexOf(",");
		} else {
			return string.length();
		}
	}

	private Object clear(String string) {
		String compared = string.replaceAll("\\s+", "");
		compared = compared.replaceAll(":", "");
		if (compared.contentEquals("true")) {
			return true;
		} else if (compared.contentEquals("false")) {
			return false;
		}
		if (compared.contentEquals("null")) {
			return new String("null");
		}
		if (string.indexOf("\"") != -1) {
			string = string.substring(string.indexOf("\"") + 1, string.lastIndexOf("\""));
			return string;
		} else {
			string = string.replaceAll("\\s+", "");
			string = string.replaceAll(":", "");
			if (string.matches("\\d+")) {
				string = string.replaceAll("[^0-9]", "");
				return Long.valueOf(string);
			} else if (string.matches("[-+]?[0-9]*\\.?[0-9]+([eE][-+]?[0-9]+)?")) {
				return Double.parseDouble(string);
			}
			return string;
		}
	}

}
