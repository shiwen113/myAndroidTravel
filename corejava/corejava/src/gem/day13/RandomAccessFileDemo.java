package gem.day13;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RandomAccessFileDemo {

	public static void main(String[] args) throws IOException {

		
		//创建一个以读写模式，的RandomAccessFile对象,文件名data.dat
		RandomAccessFile raf = 
				new RandomAccessFile("src\\gem\\day13\\data.dat","rw");
//		//写一些数据
//		//写一个整数
//		raf.writeInt(100);
//		raf.writeFloat(123.45F);
//		raf.writeUTF("android");
//		//再从这个文件中读出这些数据
//		raf.close();
		
		//移动指针
		raf.seek(4);
		
		//从文件data.dat中读出数据
		//int i = raf.readInt();
		float f = raf.readFloat();
		String s = raf.readUTF();
		raf.close();
		System.out.println(","+f+","+s);
		

	}

}
