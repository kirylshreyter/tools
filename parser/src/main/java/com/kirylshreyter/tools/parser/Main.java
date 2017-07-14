package com.kirylshreyter.tools.parser;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

import com.kirylshreyter.tools.parser.impl.IParserJsonImpl;

public class Main {

	public static void main(String[] args) throws InstantiationException, IllegalAccessException,
			IllegalArgumentException, InvocationTargetException, IOException {
		IParser parser = new IParserJsonImpl();
		TestObject1 object1 = new TestObject1();
		object1 = (TestObject1) parser.fromJson(
				"D:/biglion/java/workspace/tools/parser/src/main/java/com/kirylshreyter/tools/parser/sample.json",
				TestObject1.class);
		parser.toJson("D:/biglion/java/workspace/tools/parser/src/main/java/com/kirylshreyter/tools/parser/out.json",
				object1);
	}

}
