package gem.day8.homework;

import java.util.regex.Matcher;
//写一个方法，判断给定的字符串是否是手机号码，并测试
import java.util.regex.Pattern;

public class RegexPhoneNumber {
	public static boolean isPhoneNo(String phone) {
		Pattern pattern = Pattern.compile("^((13[0-9])|(15[^4\\D])|(18[0,5-9]))\\d{8}$");
		Matcher matcher = pattern.matcher(phone);
		return matcher.matches();
	}

	public static void main(String[] args) {
		String phone = "13862402427";
		if(isPhoneNo(phone)) {
			System.out.println(phone+"是电话号码");
		}else {
			System.out.println(phone+"不是电话号码");
		}
	}

}
