package com.kirylshreyter.tools.parser;

import java.io.FileNotFoundException;

import com.kirylshreyter.tools.parser.impl.IParserJsonImpl;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		IParser parser = new IParserJsonImpl();
		TestObject1 object1 = new TestObject1();
		object1 = (TestObject1) parser.fromJson("E:/java/workspaces/tools/parser/src/main/java/com/kirylshreyter/tools/parser/sample.json", TestObject1.class);
		System.out.println(object1.toString());
	}

}
