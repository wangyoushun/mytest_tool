/**
 * 
 */
package cn.six.googlecode;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.junit.Test;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber.PhoneNumber;
import com.google.i18n.phonenumbers.geocoding.PhoneNumberOfflineGeocoder;

import cn.six.utils.FileTool;


/**
 * @author : wangyoushun
 * @createTime : 2017年5月24日 下午4:11:30
 * @version : 1.0
 * @description
 */
public class PhoneNumCity {

	private PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
	private PhoneNumberOfflineGeocoder phoneNumberOfflineGeocoder = PhoneNumberOfflineGeocoder.getInstance();
	private String language = "CN";

	@Test
	public void main() {
		PhoneNumber referencePhonenumber = null;
		String phoneNum = "18256587160";
		try {
			referencePhonenumber = phoneUtil.parse(phoneNum, language);
		} catch (NumberParseException e) {
			e.printStackTrace();
		}
		// 手机号码归属城市 city
		String city = phoneNumberOfflineGeocoder.getDescriptionForNumber(referencePhonenumber, Locale.CHINA);
		System.out.println(city);
	}

	// 查用户号码归属地
	@Test
	public void queryPhone() throws Exception {
		String filePath = "F:\\userAccount.txt";
		String newf = "F:\\new.txt";
		List<String> lines = FileTool.lines(new File(filePath));
		List<String> list = new ArrayList<String>();
		for (String string : lines) {
			String[] split = string.split(",");
			String json = getCityByphonenumber(split[0]);
			if (json.contains("广州") || json.contains("绍兴")) {
				System.out.println(json);
				string += "," + json;
				list.add(string);
			}
		}
		FileTool.writeAppend(new File(newf), list);
	}

	private String getCityByphonenumber(String phoneNum) {
		PhoneNumber referencePhonenumber = null;
		try {
			referencePhonenumber = phoneUtil.parse(phoneNum, language);
		} catch (NumberParseException e) {
			e.printStackTrace();
		}
		// 手机号码归属城市 city
		String city = phoneNumberOfflineGeocoder.getDescriptionForNumber(referencePhonenumber, Locale.CHINA);
		System.out.println(city);
		return city;
	}
}
