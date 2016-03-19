package gem.day16;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLEncoderDemo {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// 说明URLEncoder,URLDecoder的用法
		String str = "http://localhsot:8080/web/A?name=中文";
		//用utf-8字符编码集对字符串str进行编码 
		String str1 = URLEncoder.encode(str, "utf-8");
		System.out.println(str1);
		//用utf-8对字符串str1解码
		String str2 = URLDecoder.decode(str1, "utf-8");
		System.out.println(str2);
	}

}
