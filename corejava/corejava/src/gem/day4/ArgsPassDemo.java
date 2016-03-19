package gem.day4;

public class ArgsPassDemo {
	//方法参数传递机制
	//针对基本数据类型
	public void inc(int i) {
		i++;
	}
	//针对引用类型
	public void inc(Value value) {
		value.v++;
	}
	//字符串:引用类型
	public void inc(String s) {
		System.out.println(s);
		s = s+"java";
	}
	public static void main(String[] args) {
		// 创建对象
		ArgsPassDemo apd = new ArgsPassDemo();
		int x = 10;
		apd.inc(x);
		System.out.println("x="+x);//10
		Value v = new Value();
		//
		System.out.println(v.v);
		apd.inc(v);
		System.out.println(v.v);	//11
		//字符串的不变性
		String s = new String("android");
		apd.inc(s);
		System.out.println(s);//?
		
	}

}

class Value {
	int v=10;
}


