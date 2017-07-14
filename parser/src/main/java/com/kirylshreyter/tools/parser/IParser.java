package com.kirylshreyter.tools.parser;

import java.io.FileNotFoundException;

public interface IParser {
	
	<T> Object fromJson(String sourceFile, Class<?> clazz) throws FileNotFoundException;
	
	boolean toJson(String fileName, Object objectToSave);

}
