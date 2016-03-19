package gem.day13;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class NIOCopy {

	public static void main(String[] args) throws IOException {
		// NIO实现文件的拷贝，NIOCopy.java ==> NIOCopy.txt
		File f = new File("src\\gem\\day13\\NIOCopy.java");
		//获得要读出的文件的一个FileChannel
		FileInputStream in = new FileInputStream(f);
		FileChannel fc1 = in.getChannel();
		//与内存区域建立一个映射  map 
		MappedByteBuffer mbb = fc1.map(MapMode.READ_ONLY, 0, f.length());
		//获得要写入的文件的一个FileChannel
		FileOutputStream out = 
				new FileOutputStream("src\\gem\\day13\\NIOCopy.txt");
		FileChannel fc2 = out.getChannel();
		//通过这个通道写入内存中的数据
		fc2.write(mbb);
		//关闭
		in.close();
		out.close();
		fc1.close();
		fc2.close();
		
	}

}
