package gem.day13;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Reader;

public class FileCopy3 {

	public static void main(String[] args) throws IOException {
		//用BufferedReader和BufferedWriter写一个文本文件的拷贝
		Reader reader = new FileReader("src\\gem\\day13\\FileCopy3.java");
		BufferedReader br = new BufferedReader(reader);
		BufferedWriter bw = new BufferedWriter(
				new FileWriter("src\\gem\\day13\\FileCopy3.txt"));
		//读一行文本，写一行文本
		String s = null;
		while((s=br.readLine())!=null) {
			bw.write(s);
			bw.newLine();
		}
		bw.flush();
		br.close();
		bw.close();
	}

}
