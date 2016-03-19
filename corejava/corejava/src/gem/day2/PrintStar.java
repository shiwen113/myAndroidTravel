package gem.day2;

import java.util.Scanner;

public class PrintStar {

	public static void main(String[] args) {
		//（循环*）读入一个整数n，输出如下图形
		//	n = 3
		//		*
		//		***
		//		*****
		//		n = 4
		//		*
		//		***
		//		*****
		//		*******
		//		思路：读入的整数n，就是外层循环的循环次数。
		//		对于每一行，要做的事情：1. 输出若干个星；2. 换行。
		System.out.println("请输入一个整数");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		for(int i=0;i<n;i++) {
			//？？？？ 2i+1
			for(int j=0;j<2*i+1;j++) {
				System.out.print("*");
			}
			System.out.println();
		}
	}

}
