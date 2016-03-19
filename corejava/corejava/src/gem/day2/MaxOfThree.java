package gem.day2;

import java.util.Scanner;

public class MaxOfThree {

	public static void main(String[] args) {
		// 读入三个整数，输出这三个整数中最大的一个
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入第一个整数：");
		int n1= scanner.nextInt();
		System.out.println("请输入第二个整数：");
		int n2= scanner.nextInt();
		System.out.println("请输入第三个整数：");
		int n3= scanner.nextInt();
//		int max = n1;
//		if(n2 > n1) {
//			max = n2;
//		}
//		//?:
//		
		int max = n1>n2?n1:n2;
		max = (n3>max)?n3:max;
		//
		System.out.println(n1+","+n2+","+n3+"最大值是："+max);
	}

}
