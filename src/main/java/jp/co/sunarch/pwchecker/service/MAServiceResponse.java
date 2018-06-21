package jp.co.sunarch.pwchecker.service;

import java.util.List;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "ResultSet")
public class MAServiceResponse {

	private MaResult ma_result;

	public static class MaResult {
		private int total_count;

		private int filtered_count;

		private List<Word> word_list;

		public int getTotal_count() {
			return total_count;
		}

		public void setTotal_count(int total_count) {
			this.total_count = total_count;
		}

		public int getFiltered_count() {
			return filtered_count;
		}

		public void setFiltered_count(int filtered_count) {
			this.filtered_count = filtered_count;
		}

		public List<Word> getWord_list() {
			return word_list;
		}

		public void setWord_list(List<Word> word_list) {
			this.word_list = word_list;
		}
	}

	public static class Word {
		private String surface;
		private String reading;
		private String pos;
		private String baseform;
		public String getSurface() {
			return surface;
		}
		public void setSurface(String surface) {
			this.surface = surface;
		}
		public String getReading() {
			return reading;
		}
		public void setReading(String reading) {
			this.reading = reading;
		}
		public String getPos() {
			return pos;
		}
		public void setPos(String pos) {
			this.pos = pos;
		}
		public String getBaseform() {
			return baseform;
		}
		public void setBaseform(String baseform) {
			this.baseform = baseform;
		}
	}

	public MaResult getMa_result() {
		return ma_result;
	}

	public void setMa_result(MaResult ma_result) {
		this.ma_result = ma_result;
	}

}
