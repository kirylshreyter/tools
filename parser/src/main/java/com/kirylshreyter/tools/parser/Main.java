package com.kirylshreyter.tools.parser;

import java.io.FileNotFoundException;

import com.kirylshreyter.tools.parser.impl.IParserJsonImpl;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
		IParser parser = new IParserJsonImpl();
		parser.parse(
				"D:/java/workspace/training_2017/hometasks/src/main/java/com/kirylshreyter/training_2017/hometasks/parser/sample.json");

	}

}
