package gem.day9;

public class RuntimeExceptionDemo {

	public static void main(String[] args) {
		// 常见的运行异常
		//NullPointerException:空指针异常
//		String s = null;
//		int len = s.length();//
//		System.out.println("len="+len);
		//数组越界异常：ArrayIndexOutOfBoundsException
		//int[] arr = {1,2,3};
		//int a = arr[arr.length];
		//System.out.println("a="+a);
		//数字格式化异常：NumberFormatException
		int i = Integer.parseInt("123a45");
		System.out.println("i="+i);
		
		
	}

}
