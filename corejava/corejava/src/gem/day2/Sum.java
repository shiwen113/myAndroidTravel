package gem.day2;

import java.util.Scanner;

public class Sum {

	public static void main(String[] args) {
		//输入一个整数，用三种方法，求出1到这个整数的和，并输出
		System.out.println("请输入一个整数：");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		//求1到n的和,for
		int sum = 0;
		for(int i=1 ;i<=n ;i++) {
			sum += i;
		}
		//System.out.println(i);
		System.out.println("1到"+n+"的和是："+sum);
		//另两种循环方法:do-while,while
		//for(;;);	//死循环
		int j = 0;
		for(;j<10;) {
			j++;
		}
		System.out.println(j);
	}
	
}
