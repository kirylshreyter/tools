package com.kirylshreyter.tools.parser;

public class Sample {

	public static int recursy(int n) {

		if (n <= 0) {
			return 0;
		}
		return n + recursy(n - 1);
	}

	public static void main(String[] args) {
		System.out.println(recursy(10));

	}

}
