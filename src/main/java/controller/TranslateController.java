package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import util.Translate;

@RestController
public class TranslateController {
	@RequestMapping(value= "/translate",  produces="application/json;charset=UTF-8")
	@ResponseBody
	public String translate(String key) {
		return Translate.getResult(key);
	}
}
