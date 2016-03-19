package gem.day5.homework;

/**
 * 
6、（静态方法、重载）编写一个程序，要求提供三个静态方法，方法名统一为max，要求如下：
a)	第一个方法提供两个整型参数，求最大整数
b)	第二个方法提供两个双精度数，求最大双精度数
c)	第三个方法提供三个双精度数，求最大双精度数
d)	在main方法中分别调用这三个方法，在控制台输出三个方法的返回值
 *
 */

public class Max {
	public static int max(int x,int y) {
		return (x>y)?x:y;
	}
	
	public static double max(double x,double y) {
		return (x>y)?x:y;
	}
	
	public static double max(double x,double y,double z) {
		double tmp = (x>y)?x:y;
		return (tmp>z)?tmp:z;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println(max(10,20));
		System.out.println(max(11.1,22.2));
		System.out.println(max(11.1,22.2,10));

	}
	

}
