package jp.co.sunarch.pwchecker.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

public class JsonResponse implements Serializable{

	public static final long serialVersionUID = 2L;

	private int responseCode = -1;

	private int result = -1;

	private String message;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss.SSS")
	private Date execTime;

	public int getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(int responseCode) {
		this.responseCode = responseCode;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getExecTime() {
		return execTime;
	}

	public void setExecTime(Date execTime) {
		this.execTime = execTime;
	}
}
