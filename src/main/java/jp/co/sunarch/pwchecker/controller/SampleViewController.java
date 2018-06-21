package jp.co.sunarch.pwchecker.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SampleViewController {

	@RequestMapping("/")
	public String geodeSearch(Model model) {
		return "index";
	}

}
