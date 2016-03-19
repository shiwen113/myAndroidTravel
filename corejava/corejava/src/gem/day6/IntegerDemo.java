package gem.day6;

public class IntegerDemo {

	public static void main(String[] args) {
		// 基本数据类型int的封装（包装）类Inetger
		//创建Integer对象的两种方法
		Integer i1 = new Integer(100);
		Integer i2 = new Integer("101");
		//方法 xxxValue：获得对应类型的值
		short s = i1.shortValue();
		long l = i1.longValue();
		//自动装箱boxing,自动拆箱，集合框架中的元素是引用类型
		Integer i = 100;	//int ==> Integer（自动）,1.5
		int j = i;			//Integer ==> int（自动）
		//字符符==>int,logn,float,double,静态方法:parseXxx
		int x = Integer.parseInt("100");
		double d = Double.parseDouble("100.10");
		System.out.println(x+","+d);
		//其它方法:toXxxString：转换为x进制数的方法
		String s1 = Integer.toBinaryString(100);	//2进制
		String s2 = Integer.toHexString(100);		//16进制
		String s3 = Integer.toOctalString(100);		//8进制
		//144:8*8+4*8+4=64+32+4
		System.out.println(s1+","+s2+","+s3);
		//将-128到127的Integer对象放在缓存池中，
		Integer x1 = Integer.valueOf(127);
		Integer x2 = Integer.valueOf(127);
		System.out.println(x1 == x2);
		
		
		
		
		
		
		
		
	}

}
