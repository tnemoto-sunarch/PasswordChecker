package jp.co.sunarch.pwchecker.controller;

import java.lang.reflect.Method;
import java.util.Date;
import java.util.Set;

import org.reflections.Reflections;
import org.reflections.scanners.MethodAnnotationsScanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import jp.co.sunarch.pwchecker.annotation.ResultPoint;
import jp.co.sunarch.pwchecker.data.PasswdService;
import jp.co.sunarch.pwchecker.data.TCommonPassEntity;
import jp.co.sunarch.pwchecker.dto.JsonRequest;
import jp.co.sunarch.pwchecker.dto.JsonResponse;

@Controller
@ResponseBody
public class PasswordCheckerController {

	@Autowired
	private AutowireCapableBeanFactory factory;

	@Autowired
	private PasswdService passwdService;

	/**
	 * パスワードチェックAPI
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/passwdcheck", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> passwdcheck(@RequestBody JsonRequest req) {
		JsonResponse res = new JsonResponse();
		int responseCode = 200;
		int result = 0;
		String message = null;
		try {
			if(req.getValue() != null) {
				// @ResultPoint をもつメソッドを全て実行する
				// アノテーションの検索
				Reflections ref = new Reflections("", new MethodAnnotationsScanner());
				Set<Method> plusMethods = ref.getMethodsAnnotatedWith(ResultPoint.class);
				// メソッドの実行
				for(Method method : plusMethods) {
					// SpringDIに対応するためにSpringによるインスタンス化
					Object obj = factory.createBean(method.getDeclaringClass());
					// 実行
					int point = (int)method.invoke(obj, req.getValue());
					// 結果反映
					result += point;
				}
			}
		} catch(Exception e) {
			responseCode = 500;
			message = e.getMessage();
			e.printStackTrace();
		}

		res.setResponseCode(responseCode);
		if(result < 0) {
			res.setResult(0);
		} else {
			res.setResult(result);
		}
		res.setExecTime(new Date());
		res.setMessage(message);

		return ResponseEntity.ok(res);
	}

	/**
	 * パスワード登録API
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/passwdreg", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> passwdreg(@RequestBody JsonRequest req) {
		JsonResponse res = new JsonResponse();
		int responseCode = 200;
		int result = 0;
		String message = null;
		try {
			TCommonPassEntity en = passwdService.find(req.getValue());
			if(en != null) {
				en.setCount(en.getCount() + 1);
			} else {
				en = new TCommonPassEntity();
				en.setPassword(req.getValue());
				en.setCount(1);
			}
			passwdService.save(en);
			message = "パスワードを登録しました";
		} catch(Exception e) {
			responseCode = 500;
			message = e.getMessage();
			e.printStackTrace();
		}

		res.setResponseCode(responseCode);
		res.setResult(result);
		res.setExecTime(new Date());
		res.setMessage(message);

		return ResponseEntity.ok(res);
	}

	/**
	 * パスワード全削除API
	 * @param req
	 * @return
	 */
	@RequestMapping(value = "/passwddelall", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonResponse> passwddelall(@RequestBody JsonRequest req) {
		JsonResponse res = new JsonResponse();
		int responseCode = 200;
		int result = 0;
		String message = null;
		try {
			passwdService.deleteAll();
			message = "登録されているパスワードを削除しました";
		} catch(Exception e) {
			responseCode = 500;
			message = e.getMessage();
			e.printStackTrace();
		}

		res.setResponseCode(responseCode);
		res.setResult(result);
		res.setExecTime(new Date());
		res.setMessage(message);

		return ResponseEntity.ok(res);
	}
}
