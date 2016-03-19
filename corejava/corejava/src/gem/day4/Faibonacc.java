package gem.day4;

public class Faibonacc {
	//二分查找，方法，递归
	
	// 写一个方法，获得斐波纳契数列的第n项值
	public 	int	fib(int n) {
		//终止条件，如果n=1或n=2，返回1
		if(n==1 || n==2) {
			return 1;
		}
		//方法中调用了自已
		return fib(n-1) + fib(n-2);
	}

	public static void main(String[] args) {
		Faibonacc f = new Faibonacc();
		int v = f.fib(6);
		//1  1  2  3  5  8 13  21 34 ..... 
		System.out.println("数列第8项对应的值是："+v);
		

	}

}
