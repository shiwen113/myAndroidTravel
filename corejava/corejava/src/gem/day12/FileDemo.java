package gem.day12;

import java.io.File;

public class FileDemo {

	public static void main(String[] args) {
		// File的一些常用方法
		//创建File对象，new File(文件所在的路径：)
		//文件的路径：相对、绝对
		File file = new File("E:\\ptcwork\\ja2015112m\\workspace\\"+
				"corejava\\src\\gem\\day12");
		//文件是否存在
		System.out.println(file.exists());
		//是否是文件或目录
		System.out.println(file.isFile()+","+file.isDirectory());
		//文件名
		System.out.println(file.getName()+","+file.getAbsolutePath());
		System.out.println("========================");
		//获得目录下的文件及目录
		String[] fn = file.list();
		//只显示Java源文件
		for(String s:fn) {
			if(s.endsWith("java")) {
				System.out.println(s);
			}
		}
		File[] fs = file.listFiles();
		System.out.println("========================");
		for(File f:fs) {
			System.out.println(f.getPath());
		}
		
		
		
	}

}
