package com.kirylshreyter.tools.parser;

import java.io.FileNotFoundException;

public interface IParser {
	
	<T> Object parse(String sourceFile) throws FileNotFoundException;

}
