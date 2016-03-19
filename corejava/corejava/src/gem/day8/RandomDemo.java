package gem.day8;

import java.util.Random;

public class RandomDemo {

	public static void main(String[] args) {
		// Random的用法
		Random random = new Random();
		//随机生成10个数
		for(int i=0;i<10;i++) {
			System.out.print(random.nextInt()+",");
			//[0,100)的整数
			System.out.print(random.nextInt(100)+",");
			//[0,1)之间的小数
			System.out.println(random.nextDouble());
		}

	}

}
