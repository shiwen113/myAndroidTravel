package gem.day8;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternDemo {

	public static void main(String[] args) {
		// 写一个验证QQ号码的正则表达式(难)
		// 第一个是数字1-9，几位4-10位的数字
		String regx = "^[1-9]\\d{4,10}$";
		//获得Pattern对象
		Pattern pattern = Pattern.compile(regx);
		//一些需要验证的字符串
		String in = "12345678";
		Matcher m = pattern.matcher(in);
		//验证一个字符串，是否是符合这个正则表达式的QQ号码
		System.out.println(m.matches());
		//替换 *** XXX
		//字符串也有正则表达式相关的方法
		//字符串 ==> 正则表达式
		boolean flag = in.matches(regx);
		System.out.println(flag);
		//替换
		String s = "i love java,Love android too";
		//love ==> hate
		String s1 = s.replaceAll("love|Love", "hate");
		System.out.println(s1);
		//将字符串分割为一个字符串数组的方法:（重要） split
		s = "java#jsp#servlet#android";
		String[] arr = s.split("#");
		System.out.println(Arrays.toString(arr));
		
		
	}

}
