package jp.co.sunarch.pwchecker.service;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "SearchDicItemResult", namespace = "http://btonic.est.co.jp/NetDic/NetDicV09")
public class DejizoServiceResponse {

	@JsonProperty("ErrorMessage")
	private String errorMessage;

	@JsonProperty("TotalHitCount")
	private int totalHitCount;

	@JsonProperty("ItemCount")
	private int itemCount;

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public int getTotalHitCount() {
		return totalHitCount;
	}

	public void setTotalHitCount(int totalHitCount) {
		this.totalHitCount = totalHitCount;
	}

	public int getItemCount() {
		return itemCount;
	}

	public void setItemCount(int itemCount) {
		this.itemCount = itemCount;
	}
}
