package gem.day5;
//对象生命周期简单的流程
public class ObjectInit {
	//定义一个静态变量
	static String str = "java";
	//定义一个实例变量
	int  i = 100;
	//静态初始化块
	static {
		System.out.println("str="+str);
	}
	{//初始化块,代码
		System.out.println("i="+i);
	}
	public ObjectInit(int i) {
		System.out.println(i);
		this.i = i;
	}	
	public static void main(String[] args) {
		//类加载，执行静态初始化块,仅一次
		//先初始化块，再构造
		new ObjectInit(10);
		new ObjectInit(20);
		
		//数组:长方，园
		
		
	}
}
