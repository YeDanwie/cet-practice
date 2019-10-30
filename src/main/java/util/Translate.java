package util;

import java.io.UnsupportedEncodingException;

import com.baidu.translate.demo.Translated;

public class Translate {
	public static String getResult(String key) {
		String result = null;
		try {
			result = Translated.getResult(key);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
	}
}
