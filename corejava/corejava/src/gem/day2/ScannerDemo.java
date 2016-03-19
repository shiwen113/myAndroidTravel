package gem.day2;

import java.util.Scanner;

public class ScannerDemo {

	public static void main(String[] args) {
		//说明用Scanner读取数据
		//创建Scanner对象，
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入姓名:");
		//读数据,nextXxx
		String name = scanner.next();
		//
		System.out.println("请输入年龄：");
		int age = scanner.nextInt();
		System.out.println("姓名是:"+name+",年龄是："+age);
		

	}

}
