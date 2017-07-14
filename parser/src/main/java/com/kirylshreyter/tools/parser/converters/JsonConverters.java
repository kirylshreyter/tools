package com.kirylshreyter.tools.parser.converters;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;

import com.kirylshreyter.tools.parser.exeptions.NotValidJsonFileToParseExeption;

public class JsonConverters {

	/**
	 * Method reads file line by line in string.
	 * @param sourceFile Fullpath to file which needed to parse.
	 */
	public String getJsonStringArrayFromJsonFile(String sourceFile) {
		StringBuilder builder = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
			String tempString;
			while ((tempString = br.readLine()) != null) {
				builder.append(tempString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return builder.toString();
	}

	public String getStringObjectsArrayFromJsonString(String editedJsonString) {
		int start_index = editedJsonString.indexOf('{');
		if (start_index == -1) {
			throw new NotValidJsonFileToParseExeption();
		}
		int end_index = 0;
		for (int i = start_index + 1, brace_counter = 1; i < editedJsonString.length() && brace_counter > 0; i++) {
			if (editedJsonString.charAt(i) == '{') {
				brace_counter++;
			}
			if (editedJsonString.charAt(i) == '}') {
				brace_counter--;
			}
			end_index = i + 1;
		}
		String stringToAdd = editedJsonString.substring(start_index, end_index);
		stringToAdd = stringToAdd.substring(stringToAdd.indexOf('{') + 1, stringToAdd.lastIndexOf('}'));
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
