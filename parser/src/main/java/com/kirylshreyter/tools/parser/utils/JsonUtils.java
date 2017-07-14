package com.kirylshreyter.tools.parser.utils;

public class JsonUtils {

	public String removeLiteralsFromJsonString(String jsonString) {
		String regex = "[\t\n]+";
		String wanted = jsonString.replaceAll(regex, "");
		return wanted;
	}
	
	/**
	 * Creates an object of the class passed in the parameters.
	 * 
	 * @param clazz desired class.
	 * @throws ClassNotFoundException
	 * @throws IllegalAccessException
	 * @throws InstantiationException
	 */
	public <T> Object createInstanceOfClass(Class<?> clazz) {
		Object obj = null;

		try {
			obj = clazz.newInstance();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}
		return obj;
	}

}
