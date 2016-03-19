package gem.day12;

import java.io.File;
import java.io.FilenameFilter;

public class ListFileDemo {

	public static void main(String[] args) {
		//创建一个File对象
		File dir = new File("src\\gem\\day12");
		String[] names = dir.list(new FilenameFilter(){

			@Override
			public boolean accept(File dir, String name) {
				//只显示java源文件
				return name.endsWith("txt");
			}
			
		});
		for(String n:names) {
			System.out.println(n);
		}
		System.out.println("end");
	}

}
