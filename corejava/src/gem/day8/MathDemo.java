package gem.day8;

public class MathDemo {

	public static void main(String[] args) {
		double d = -5.6;
		//[-6,-5]
		System.out.println(Math.floor(d));
		System.out.println(Math.ceil(d));
		//四舍五入:(int)Math.floor(a + 0.5f)
		System.out.println(Math.round(d));
		System.out.println(Math.random());
		//平方根
		Math.sqrt(100);

	}

}
