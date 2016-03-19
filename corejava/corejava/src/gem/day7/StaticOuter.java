package gem.day7;
//示例说明静态内部类
public class StaticOuter {
	int score = 100;
	static String str = "android";
	
	//定义一个静态内部类
	static class StaticInner {
		//可以写什么,实例变量,静态变量
		int i = 100;
		static String s = "java";
		//定义一个实例方法
		void show() {
			//不能访问外部类的实例成员，可以访问外部类的静态成员
			System.out.println(i+","+s+","+str);
		}
	}
	//在类中定义内部接口
	
	public static void main(String[] args) {
		// 使用静态内部类,创建了一个静态内部类的对象
		StaticInner si = new StaticOuter.StaticInner();
		si.show();

	}

}
