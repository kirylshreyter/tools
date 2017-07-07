package com.kirylshreyter.tools.parser.impl;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

import com.kirylshreyter.tools.parser.IParser;
import com.kirylshreyter.tools.parser.exeptions.NotValidJsonFileToParseExeption;

public class IParserJsonImpl implements IParser {

	public <T> Object parse(String sourceFile) throws FileNotFoundException {
		List<String> list = getStringArrayOfJsonFile(sourceFile);
		String fullString = getJsonStringFromJsonStringArray(list);

		if (checkIfJsonStringIsValid(fullString)) {

		} else {
			throw new NotValidJsonFileToParseExeption("Provided JSON file is NOT correct file for parsing.");
		}
		;

		return null;
	}

	private String getJsonStringFromJsonStringArray(List<String> list) {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < list.size(); i++) {
			builder.append(list.get(i));
		}
		return builder.toString();
	}

	private List<String> getStringArrayOfJsonFile (String sourceFile) throws FileNotFoundException {
		List<String> list = new ArrayList<String>();
		try (BufferedReader br = new BufferedReader(new FileReader(sourceFile))) {
			String tempotyBufferString;
			while ((tempotyBufferString = br.readLine()) != null) {
				list.add(tempotyBufferString);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	private boolean checkIfJsonStringIsValid(String jsonString) {
		int bracket_couter = 0;
		int brace_couter = 0;
		for (int i = 0; i < jsonString.length(); i++) {
			if (jsonString.charAt(i) == '[' | jsonString.charAt(i) == ']') {
				bracket_couter++;
			} else if (jsonString.charAt(i) == '{' | jsonString.charAt(i) == '}') {
				brace_couter++;
			}
		}
		if ((bracket_couter % 2 == 0) && (brace_couter % 2 == 0)) {
			return true;
		} else {
			return false;
		}
	}
}
