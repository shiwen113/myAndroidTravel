package gem.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo {

	public static void main(String[] args) throws IOException {
		// 创建一个URL对象，学习一些方法
		URL url = new URL("http://www.csdn.net/");
		// 常见的方法
		System.out.println(url.getHost()+","+url.getPath()+","+url.getPort());
		//获得这个资源，在控制台输出
		InputStream in = url.openStream();
		//字符流，字节流==》InputStreamReader 字符流 ==>    BuffereedReader
		//从网络获取数据，从输入流中读数据
		//向网络上发送数据，将数据写到输出流中
		BufferedReader br = 
				new BufferedReader(new InputStreamReader(in));
		//读一行，输出一行
		String s = null;
		while((s = br.readLine())!=null) {
			System.out.println(s);
		}
		br.close();
	}

}
