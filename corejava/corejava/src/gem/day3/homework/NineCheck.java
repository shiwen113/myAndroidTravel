package gem.day3.homework;

import java.util.Scanner;

public class NineCheck {

	public static void main(String[] args) {
		// 输入一个数，3,5,7...
		System.out.println("请输入一个数：");
		Scanner scanner = new Scanner(System.in);
		int n = scanner.nextInt();
		// 定义一个二维数组放这些数
		int[][] arr = new int[n][n];
		int v = 1;	//初值
		int x = 0;  //第几行
		int y = n/2;  //第几列
		// 初始值，v=1,放在第一行的中间位置
		while(v <= n*n) {
			arr[x][y] = v;
			v = v+1;
			// 实现算法
			// 放在右上方：
			x = x - 1;
			y = y + 1;
			if(x<0 && y > (n-1)) {
				// 3、行列均越界,放在这个数的下边
				x = x + 2;
				y = y - 1;
			}else if(y > (n-1)) {
				// 1、列越界  y > (n-1)，放在行的最左边
				y = 0;
			}else if( x < 0) {
				// 2、行越界 x < 0 ，放在列的最下边
				x = n-1;
			}else if(arr[x][y]>0) {
				// 4、右上方已有值arr[x][y]>0 ，放在这个数的下边
				x = x + 2;
				y = y - 1;
				
			}
		}
		// 输出二维数组中数
		for(int i=0;i<arr.length;i++) {
			for(int j=0;j<arr[i].length;j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();//换行
		}
	}

}
