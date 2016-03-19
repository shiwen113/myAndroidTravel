package gem.day13;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class DisplayTxtFile {

	public static void main(String[] args) {
		// 将文本文件中的内容在控制台输出，文件名从命令行参数中获得。
		if(args.length < 1) {
			System.out.println("请正确使用");
			return;
		}
		//
		File file = new File(args[0]);
		if(!file.exists() || file.isDirectory()) {
			System.out.println("文件不存在，或者不是文件");
			return;
		}//
		BufferedReader reader = null;
		try {
			reader = 
					new BufferedReader(new FileReader(file));
			//读一行，显示一行
			String s = null;
			while((s=reader.readLine())!=null) {
				System.out.println(s);
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
			//转换异常,比如抛出一个运行期异常
		}finally {
			//关闭流,放在finally语句块
			if(reader!=null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
