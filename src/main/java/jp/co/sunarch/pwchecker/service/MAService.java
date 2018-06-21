package jp.co.sunarch.pwchecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 形態素解析サービス
 *
 * yahoo API
 * ただし、スペースのない英単語を区切ってくれる訳ではないので、だめ
 * @author lusyfur
 *
 */
@Service
public class MAService {

	@Autowired
	@Qualifier("maServiceRestTemplate")
	RestTemplate restTemplate;

	private static final String URL = "https://jlp.yahooapis.jp/MAService/V1/parse?appid={appid}&sentence={sentence}&results=ma";

	public MAServiceResponse service(String value) {
		String appid = "dj00aiZpPXVFdllJaEhhV1ZHVCZzPWNvbnN1bWVyc2VjcmV0Jng9MzQ-";
		String sentence = value;
		return restTemplate.getForObject(URL, MAServiceResponse.class, appid, sentence);
	}
}
