package gem.day17;

public class InitClass {
	static int j = 20;
	final static int i = 100+j;
	{//实例初始化块
		System.out.println("实例初始化块");
	}
	static {
		System.out.println("静态初始化块:"+i);
	}
	
	public static void main(String[] args) {
		System.out.println("in main");
	}

}
class SubInitClass extends InitClass {
	static String str = "java";
	static {
		System.out.println("子类的静态初始化块:"+str);
	}
}



