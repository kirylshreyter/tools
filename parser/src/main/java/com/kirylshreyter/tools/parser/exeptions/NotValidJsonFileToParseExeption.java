package com.kirylshreyter.tools.parser.exeptions;

public class NotValidJsonFileToParseExeption extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public NotValidJsonFileToParseExeption() {
        super();
    }
    public NotValidJsonFileToParseExeption(String s) {
        super(s);
    }

}
