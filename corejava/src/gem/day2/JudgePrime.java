package gem.day2;

import java.util.Scanner;

public class JudgePrime {
	int i = 10;//成员变量
	
	

	public static void main(String[] args) {
		Math.random();//double [0,1) ==> 1,10的整数
		
		// 输入一个整数，判断它是否是质数
		// 质数是只能被1和自已整除的数
		System.out.println("请输入一个整数：");
		//n%3 == 0
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		//n 能否被2,3,4,...n-1中某个数整除，如果能，则这个数不是质数
		//否则是质数
		boolean flag = true;//是质数，false不是质数
		for(int i=2;i<=Math.sqrt(n);i++) {
			if(n%i == 0) {
				flag = false;
			}
		}
		if(flag) {
			System.out.println(n+"是质数");
		}else {
			//这个数不是质数
			System.out.println(n+"不是质数");
		}
		
		{
			int i = 10;
		}
		
		//System.out.println(i);
	}
}
