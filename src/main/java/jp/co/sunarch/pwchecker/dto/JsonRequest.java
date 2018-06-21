package jp.co.sunarch.pwchecker.dto;

import java.io.Serializable;

public class JsonRequest implements Serializable{

	public static final long serialVersionUID = 1L;

	private String value;

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}
}
