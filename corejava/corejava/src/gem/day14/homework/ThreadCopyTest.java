package gem.day14.homework;

import java.io.File;

public class ThreadCopyTest {

	public static void main(String[] args) {
		//原文件==》目标文件
		File src = new File("d:\\春.mp3");
		File dest = new File("d:\\spring.mp3");
		//分为几段,每段用一个线程对象拷贝
		int num = 3;
		//获得源文件的长度,计算一下每段的长度
		long len = src.length();
		int size = (int)(len/num);	//每段长度
		int start = 0;
		int end = size;
		for(int i=0;i<num;i++) {
			new Thread(new ThreadCopy(start,end,src,dest)).start();
			start = end;
			end = start + size;
		}

	}

}
