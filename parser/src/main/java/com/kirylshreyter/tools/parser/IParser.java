package com.kirylshreyter.tools.parser;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public interface IParser {
	
	<T> Object fromJson(String sourceFile, Class<?> clazz) throws FileNotFoundException, InstantiationException, IllegalAccessException;
	
	boolean toJson(String fileName, Object objectToSave) throws IOException, IllegalAccessException, IllegalArgumentException, InvocationTargetException;

}
