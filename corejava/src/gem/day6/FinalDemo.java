package gem.day6;
//类定义为final类说明这个类不能被继承
public   class FinalDemo {
	//静态变理
	final static  double PI ;	// = 3.14;
	static {//静态初始化块
		PI = 3.1415;
	}
	final static double d = PI+1;
	//成员变量,final变量只能赋值一次
	final String s ;
	{	//实例初始化块
		//s = "ios";
	}
	
	public FinalDemo(String s) {
		this.s = s;
	}
	//写一个方法,final是其子类不能重写该方法
	public final int earn() {
		return 100000;
	}
	

	public static void main(String[] args) {
		// final：修饰局部变量，表示这个变量是一个常量
		final int i = 100;	//i是常量
		//i = 101;
		//引用变量类型 
		final Value value = new Value();
		value.v = 101;//???
		int v = value.v;
		System.out.println(v);
		//value = new Value(); error
		
	}
}
class Value {
	int v = 100;
}









