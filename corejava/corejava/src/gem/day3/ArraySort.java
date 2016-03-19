package gem.day3;

import java.util.Arrays;

public class ArraySort {

	public static void main(String[] args) {
		//定义一个有10个元素的整型数组
		int[] arr = new int[10];
		//随机生成10个1-100的整数初始化这个数组
		for(int i=0;i<arr.length;i++) {
			int x = (int)(Math.random()*100) + 1;
			arr[i] = x;
		}
		//输出数组中的数,for-each
		for(int x:arr) {
			System.out.print(x+",");
		}		
		//求出这个数组中的最大值
		//第一个数是最大值，之后，将这个最大值和后面的数依次比较，
		//大的作为新的最大值
		int max = arr[0];
		for(int i=1;i<arr.length;i++) {
			if(arr[i] > max) {
				max = arr[i];
			}
		}
		System.out.println("该数组的最大值是:"+max);
		//求最小值		
		//将这个数组排序,冒泡排序
		//每一轮时，求出最大数，最大值放在最右边
		//18 23 15 30 63 12       18 23 15 30 12 63
		//18 15 23 30 12 63
		for(int j=arr.length-1;j>0;j--) {
			for (int i = 0; i < j; i++) {
				// 两个数比较，前面的数比后面的数大的话，后移
				if (arr[i] > arr[i + 1]) {
					int temp = arr[i + 1];
					arr[i + 1] = arr[i];
					arr[i] = temp;
				}
			}
		}
		//Arrays：实用工具类
		System.out.println(Arrays.toString(arr));	
		//折半查找
		int key = 14;
		//二分查找的基本算法是，对排序的数组，
		//首先，假设表中元素是按升序排列，将表中间位置记录的关键字与查找关键字比较，
		//如果两者相等，则查找成功；否则利用中间位置记录将表分成前、后两个子表，
		//如果中间位置记录的关键字大于查找关键字，则进一步查找前一子表，
		//否则进一步查找后一子表。重复以上过程，直到找到满足条件的记录，使查找成功，
		//或直到子表不存在为止，此时查找不成功。
		

	}

}
