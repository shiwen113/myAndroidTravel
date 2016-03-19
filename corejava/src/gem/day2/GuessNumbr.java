package gem.day2;

import java.util.Scanner;

public class GuessNumbr {
	/*
	 *  输入一个1到10的整数，系统随机生成一个1到10的整数，
若玩家输入的数比随机数小，则提示玩家数输小了，让玩家重新输入，
若玩家输入的数比随机数大，则提示玩家数输大了，让玩家重新输入，
若这两个数相等，则本轮游戏结束，若输入的次数小于3，则提示说玩家太牛了，
若输入的次数小于5，则提示说玩家还行，否则提示说玩家太差了。
询问玩家是否还要继续，若继续，则重新开如一轮新游戏，否则游戏结束。
	 */
	public static void main(String[] args) {
		//
		Scanner scanner = new Scanner(System.in);
		while(true) {
			//玩一轮游戏[0,1)
			int r = (int)(Math.random()*10) + 1;
			//变量，记住猜的次数
			int count = 0;
			System.out.println("请输入一个数：");
			while(true) {
				int n = scanner.nextInt();
				count++;
				if(n == r) {
					//退出的条件是：输入的数和随机数相等
					break;
				}else if(n > r) {
					//输的数大了
					System.out.println("输入的数大了，请重新输入：");
				}else if(n < r) {
					System.out.println("输入的数小了，请重新输入：");
				}
			}
			//点评
			if(count < 3) {
				System.out.println("很牛。。。。");
			}else if(count < 5 ) {
				System.out.println("还行。。。。");
			}else {
				System.out.println("差。。。。");
			}//这轮游戏结束
			System.out.println("继续吗y/n？");
			String yn = scanner.next();
			//如果输入的是y，则继续玩游戏，否则退出
			//字符串判断相等，不用==,用equals方法
			if( !yn.equals("y")) {
				//退出
				break;
			}
		}
		System.out.println("欢迎下次。。。。。。。");
	}

}
