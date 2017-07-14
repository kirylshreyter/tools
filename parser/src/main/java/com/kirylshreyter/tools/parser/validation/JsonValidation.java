package com.kirylshreyter.tools.parser.validation;

import com.kirylshreyter.tools.parser.exeptions.NotValidJsonFileToParseExeption;

public class JsonValidation {

	public void checkIfJsonStringIsValid(String jsonString) {
		
		if ((jsonString.indexOf('{') > jsonString.indexOf('}')) | (jsonString.indexOf('[') > jsonString.indexOf(']'))) {
			throw new NotValidJsonFileToParseExeption();
		}
		int bracket_couter = 0;
		int brace_couter = 0;
		for (int i = 0; i < jsonString.length(); i++) {
			if (jsonString.charAt(i) == '[' | jsonString.charAt(i) == ']') {
				bracket_couter++;
			} else if (jsonString.charAt(i) == '{' | jsonString.charAt(i) == '}') {
				brace_couter++;
			}
		}
		int index_of_bracket = 0;
		if (jsonString.indexOf('[') == -1) {
			index_of_bracket = 0;
		} else {
			index_of_bracket = jsonString.indexOf('[');
		}
		if ((jsonString.indexOf('{') <= index_of_bracket) && jsonString.lastIndexOf('}') > jsonString.lastIndexOf(']')) {
			if (!(bracket_couter % 2 == 0 && brace_couter % 2 == 0)) {
				throw new NotValidJsonFileToParseExeption();
			} 
		}
	}

}
