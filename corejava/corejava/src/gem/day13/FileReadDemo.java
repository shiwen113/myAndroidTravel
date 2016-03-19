package gem.day13;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;

public class FileReadDemo {

	public static void main(String[] args) throws IOException {
		//通过FileReader读取文件中的数据
		//Reader: 关注read方法
		//FileReader:创建对象的方法
		//new FileReader(File对象或字符串表示的文件路径)
		File f = new File("src\\gem\\day13\\FileReadDemo.javasdf");
		Reader reader = new FileReader("src\\gem\\day13\\FileReadDemo.java");
		//读出数据,一个字符一个字符的读
		int ch = reader.read();
		System.out.println((char)ch);
		ch = reader.read();
		System.out.println((char)ch);
		//读到一个字符数组中
		char[] cbuf = new char[10];
		int len = reader.read(cbuf);
		//
		char[] cbuf1 = new char[16];
		//读到数组的某部份
		reader.read(cbuf1, 5, 9);
		System.out.println(Arrays.toString(cbuf1));
		System.out.println(len);
		
		//关闭流
		reader.close();
	}

}
