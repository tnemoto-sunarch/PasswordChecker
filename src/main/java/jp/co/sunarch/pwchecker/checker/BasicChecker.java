package jp.co.sunarch.pwchecker.checker;

import jp.co.sunarch.pwchecker.annotation.ResultPoint;

public class BasicChecker {

	@ResultPoint
	public int uppercaseLetter(String value) {
		if(value.matches(".*[A-Z]+.*")) {
			return 1;
		} else {
			return 0;
		}
	}
	@ResultPoint
	public int lowerLetter(String value) {
		if(value.matches(".*[a-z]+.*")) {
			return 1;
		} else {
			return 0;
		}
	}
	@ResultPoint
	public int numberLetter(String value) {
		if(value.matches(".*[0-9]+.*")) {
			return 1;
		} else {
			return 0;
		}
	}
	@ResultPoint
	public int symbolLetter(String value) {
		if(value.matches(".*([^\\w]|_)+.*")) {
			return 1;
		} else {
			return 0;
		}
	}
	@ResultPoint
	public int size(String value) {
		if(value.length() > 7) {
			return 1;
		} else {
			return 0;
		}
	}
}
