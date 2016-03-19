package gem.day13;

import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class WriterDemo1 {

	public static void main(String[] args) throws IOException {
		// 创建一个文件输出流FileWriter，写入一些字符
		Writer writer = 
				new FileWriter("src\\gem\\day13\\WriterDemo1.txt",true);
		//写入数据
		//一个字符一个字符写
		writer.write('j');
		writer.write('a');
		//一块一块的写
		char[] cbuf = {'v','a',',','a','n','d','r','o','i','d'};
		writer.write(cbuf);
		//写数组中的部份
		writer.write(cbuf,3, 5);
		//写字符串
		writer.write("abcd");
		writer.flush();//
		writer.close();
		
	}

}
