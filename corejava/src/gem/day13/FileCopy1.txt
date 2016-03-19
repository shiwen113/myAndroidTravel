package gem.day13;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;

public class FileCopy1 {

	public static void main(String[] args) throws IOException {
		//将当前文件FileCopy1.java ==> FileCopy1.txt
		//创建FileReader对象
		Reader reader = new FileReader("src\\gem\\day13\\FileCopy1.java");
		//创建FileWriter对象
		Writer writer = new FileWriter("src\\gem\\day13\\FileCopy1.txt");
		//方法1:读一个字符，写一个字符
//		int ch = 0;
//		while((ch=reader.read())!=-1) {
//			writer.write(ch);
//		}
		//方法2:读一块，写一块
		char[] cbuf = new char[512];
		int len = 0;
		while((len = reader.read(cbuf))!=-1) {
			writer.write(cbuf,0,len);
		}
		//关闭流
		reader.close();
		writer.close();
		
		
	}

}
