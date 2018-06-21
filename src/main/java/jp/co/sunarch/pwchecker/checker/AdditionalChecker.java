package jp.co.sunarch.pwchecker.checker;

import java.util.NoSuchElementException;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;

import jp.co.future.uroborosql.SqlAgent;
import jp.co.future.uroborosql.UroboroSQL;
import jp.co.future.uroborosql.config.SqlConfig;
import jp.co.sunarch.pwchecker.annotation.ResultPoint;
import jp.co.sunarch.pwchecker.data.TCommonPassEntity;
import jp.co.sunarch.pwchecker.service.DejizoService;
import jp.co.sunarch.pwchecker.service.DejizoServiceResponse;

public class AdditionalChecker {

	@Autowired
	private DejizoService dejizoService;

	@Autowired
	private DataSource dataSource;

	private static char[][] KEYBOARD = new char[][] {
	/*0*/	{'1','2','3','4','5','6','7','8','9','0','-','^','\\'},
	/*1*/	{'q','w','e','r','t','y','u','i','o','p','@','['},
	/*2*/	{'a','s','d','f','g','h','j','k','l',';',':',']'},
	/*3*/	{'z','x','c','v','b','n','m',',','.','/','\\'}
	};

	/**
	 * L33T対応コンバート
	 * 置き換え文字を元に戻す
	 * @param value
	 * @return
	 */
	private String convertL33T(String value) {
		value = value.replace("@", "a");
		value = value.replace("$", "s");
		value = value.replace("0", "o");
		value = value.replace("3", "e");
		return value;
	}

	/**
	 * よく使われるパスワード
	 * @param value
	 * @return
	 */
	@ResultPoint
	public int commonWords(String value) {
		SqlConfig config = UroboroSQL.builder(dataSource).build();
		try (SqlAgent agent = config.agent()){
			Optional<TCommonPassEntity> entity = agent.query("pass_select").param("value", value).findFirst(TCommonPassEntity.class);
			TCommonPassEntity e = entity.get();
			if(e != null) {
				return -3;
			}
		} catch(NoSuchElementException e) {
			//見つからない場合・・・？
		}
		return 0;
	}

	/**
	 * 辞書単語
	 * @param value
	 * @return
	 */
	@ResultPoint
	public int dictionaryWords(String value) {
		DejizoServiceResponse dejires = dejizoService.service(value);

		if(dejires.getItemCount() > 0) {
			return -2;
		} else {
			value = convertL33T(value);
			value = value.toLowerCase();
			dejires = dejizoService.service(value);
			if(dejires.getItemCount() > 0) {
				return -1;
			} else {
				return 0;
			}
		}
	}

	/**
	 * JISキーボード配列チェック
	 * @param value
	 * @return
	 */
	@ResultPoint
	public int keybordWords(String value) {
		String lowercase = value.toLowerCase();
		char[] chars = lowercase.toCharArray();
		int count = 0;
		char cc = 0;
		for(char ch : chars) {
			if(cc != 0) {
				// ccの場所特定
				int y = 0, x = 0;
				boolean isFound = false;
				Loop:for(;y<KEYBOARD.length;y++) {
					for(x = 0;x<KEYBOARD[y].length;x++) {
						if(cc == KEYBOARD[y][x]) {
							isFound = true;
							break Loop;
						}
					}
				}
				if(isFound) {
					// ccの上下左右のどれか
					if(	(y > 0 && ch == KEYBOARD[y-1][x]) ||
						(y < KEYBOARD.length - 1 && ch == KEYBOARD[y+1][x]) ||
						(x > 0 && ch == KEYBOARD[y][x-1])||
						(x < KEYBOARD[y].length - 1  && ch == KEYBOARD[y][x+1])
						) {
						count ++;
					} else {
						count = 0;
					}
				} else {
					count = 0;
				}
			}
			if(count > 2) {
				return -1;
			}
			cc = ch;
		}
		return 0;
	}

	/**
	 * 繰り返し文字列
	 * @param value
	 * @return
	 */
	@ResultPoint
	public int repeatWords(String value) {
		char[] chars = value.toCharArray();
		int count = 0;
		char cc = 0;
		for(char ch : chars) {
			if(cc != 0) {
				if(ch == cc) {
					count ++;
				} else {
					count = 0;
				}
			}
			if(count > 2) {
				return -1;
			}
			cc = ch;
		}
		return 0;
	}

	/**
	 * 順序文字列
	 * ※文字の順序性のみをチェック
	 * @param value
	 * @return
	 */
	@ResultPoint
	public int sequencialWords(String value) {
		String lowercase = value.toLowerCase();
		char[] chars = lowercase.toCharArray();
		int count = 0;
		char cc = 0;
		for(char ch : chars) {
			if(cc != 0) {
				if(ch >= 97 && ch <= 122 && cc + 1 == ch) {
					count ++;
				} else {
					count = 0;
				}
			}
			if(count > 2) {
				return -1;
			}
			cc = ch;
		}
		return 0;
	}
}
