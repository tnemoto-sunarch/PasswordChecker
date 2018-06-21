package jp.co.sunarch.pwchecker.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

/**
 * 辞書検索サービス
 * デ辞蔵APIにアクセスする
 * @author lusyfur
 *
 */
@Service
public class DejizoService {

	@Autowired
	@Qualifier("dejizoServiceRestTemplate")
	RestTemplate restTemplate;

	private static final String URL = "http://public.dejizo.jp/NetDicV09.asmx/SearchDicItemLite?Dic=EJdict&Word={word}&Scope=HEADWORD&Match=EXACT&Merge=AND&Prof=XHTML&PageSize=1&PageIndex=0";

	public DejizoServiceResponse service(String value) {
		String word = value;
		return restTemplate.getForObject(URL, DejizoServiceResponse.class, word);
	}

}
