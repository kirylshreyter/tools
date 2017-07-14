package com.kirylshreyter.tools.parser;

import java.util.LinkedHashMap;
import java.util.Map.Entry;

import com.kirylshreyter.tools.parser.converters.JsonConverters;
import com.kirylshreyter.tools.parser.utils.JsonUtils;
import com.kirylshreyter.tools.parser.validation.JsonValidation;

public class Sample {

	public static int recursy(int n) {

		if (n <= 0) {
			return 0;
		}
		return n + recursy(n - 1);
	}

	public static void main(String[] args) {
		System.out.println(recursy(10));
		LinkedHashMap<Integer, String> hashMap = new LinkedHashMap<>();
		hashMap.put(1, "one");
		hashMap.put(2, "two");
		hashMap.put(4, "four");
		hashMap.put(3, "three");

		for (Entry<Integer, String> entry : hashMap.entrySet()) {
			System.out.println(entry.getKey() + " : " + entry.getValue());
		}
		JsonConverters converters = new JsonConverters();
		JsonValidation validation = new JsonValidation();
		JsonUtils utils = new JsonUtils();
		String notEditedJsonString = converters.getJsonStringArrayFromJsonFile(
				"D:/biglion/java/workspace/tools/parser/src/main/java/com/kirylshreyter/tools/parser/sample.json");
		validation.checkIfJsonStringIsValid(notEditedJsonString);
		String editedJsonString = utils.removeLiteralsFromJsonString(notEditedJsonString);
	}

}
