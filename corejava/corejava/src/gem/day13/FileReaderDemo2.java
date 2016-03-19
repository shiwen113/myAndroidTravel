package gem.day13;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class FileReaderDemo2 {

	public static void main(String[] args) throws IOException {
		// 将这个文件读出，在控制台显示
		//创建一个FileReader对象
		Reader reader = 
				new FileReader("src\\gem\\day13\\FileReaderDemo2.java");
		//1、读一个字符，显示一个字符,ch=-1，流中已无数据
		/*
		int ch = 0;
		while((ch = reader.read()) != -1) {
			//写到输出流
			System.out.print((char)ch);
		}
		reader.close();*/
		//2、一块一块的读,读到一个字符数组
		char[] cbuf = new char[512];
		int len = reader.read(cbuf);
		while(len != -1) {
			//显示字符数组中的字符
			//new String(cbuf);  error
			//new String(cbuf,0,len);  ok
			for(int i=0;i<len;i++) {
				System.out.print(cbuf[i]);
			}
			//再读
			len = reader.read(cbuf);
		}
		reader.close();
	}

}
