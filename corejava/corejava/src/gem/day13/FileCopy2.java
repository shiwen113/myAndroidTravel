package gem.day13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileCopy2 {
	//命令行参数 java gem.day13.FileCopy3 源文件名 目标文件名
	public static void main(String[] args) throws IOException {
		//args[0] ??
		//字节文件的拷贝，car1.jpg ==> car2.jpg
		//InputStream   FileInputStream
		InputStream in = new FileInputStream(
				"src\\gem\\day13\\car1.jpg");
		//OutputStream  FileOutputStream
		OutputStream out = new FileOutputStream(
				"src\\gem\\day13\\car2.jpg");
		//读一块，写一块
		int len = 0;
		byte[] cbuf = new byte[512];
		while((len=in.read(cbuf))!=-1) {
			out.write(cbuf, 0, len);
		}
		
	
		
		//关闭流
		in.close();
		out.close();
	}

}
