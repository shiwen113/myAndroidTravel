package gem.day8.homework;

import java.util.Scanner;

//判断一个数是否是回文数字(通过String来处理)
public class Rotor {

	public static void main(String[] args) {
		System.out.println("请输入一个整数：");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		String s = String.valueOf(n);
		System.out.println(n+"有"+s.length()+"位数");
		StringBuilder sb = new StringBuilder();
		for(int i = s.length() - 1;i >= 0;i --){
			sb.append(s.charAt(i));
		}
		if(s.equals(sb.toString())){
			System.out.println(n+"是回文数字");
		}else{
			System.out.println(n+"不是回文数字");
		}
	}

}
