package com.kirylshreyter.tools.parser.utils;

public class JsonUtils {

	public String removeLiteralsFromJsonString(String jsonString) {
		String regex = "[\t\n\r\b\f]+";
		jsonString = jsonString.replaceAll(regex, "");
		return jsonString;
	}

	/**
	 * Creates an object of the class passed in the parameters.
	 * @param clazz desired class.
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 */
	public <T> Object createInstanceOfClass(Class<?> clazz) throws InstantiationException, IllegalAccessException {
		Object obj = null;
		obj = clazz.newInstance();
		return obj;
	}

}
