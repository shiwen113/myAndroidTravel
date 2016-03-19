package gem.day13;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Echo {

	public static void main(String[] args) throws IOException {
		//从键盘上读一行数据，System.in   InputStream
		//字节流 ==》字符流 ==>带缓存的字符流
		//     InputStreamReader 字节输入流 ==》 字符输入流
		// ==> 带缓存的字符流
		Reader reader = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(
				new InputStreamReader(System.in));
		//while循环，当输入exit或quit退出这个循环
		//从流中读出一行数据  ： 字符 
		String s = br.readLine();
		//输出这行数据
		System.out.println(s);

	}

}
