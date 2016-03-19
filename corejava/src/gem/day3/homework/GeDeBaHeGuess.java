package gem.day3.homework;

import java.util.Arrays;

public class GeDeBaHeGuess {
	//验证哥德巴赫猜想：大于6的偶数能够表示成两个素数之和
	public static void main(String[] args) {
		//最大的整数
		final int MAX = 1000;
		//求出MAX之内的所有质数，将之放在一个数组中,这个数组最长为MAX/2
		int[]  primes = new int[MAX/2];
		//遍历3-MAX，找到一个质数，就将这个数放到数组primes
		int i = 0;	
		for(int j=3;j<=MAX;j++) {
			boolean flag = true;	//是否是质数的标志
			//判断j是否是质数
			for(int k=2;k<=Math.sqrt(j);k++) {
				if(j%k==0) {//成立则j不是质数
					flag=false;
					break;
				}
			}
			if(flag) {//是质数,j放入数组
				primes[i++] = j;
			}
		}
		//可以测试一下
		System.out.println(Arrays.toString(primes));
		//验证从6开始到MAX之内的任一偶数均可表示为两个质数之和
		for(int j = 6;j<=MAX;j=j+2) {
			boolean flag = false;
			exit:
			for(int m=0;m<primes.length;m++) {
				for(int n=0;n<primes.length;n++) {
					if(primes[m] + primes[n] == j) {
						//则找到两个质数
						System.out.println(j+"="+primes[m]+"+"+primes[n]);
						flag = true;
						//退出验证
						break exit;
					}
				}
			}
			if(!flag) {
				System.out.println(j+"没有找到两个对应的质数!!!");
			}
		}

	}

}
