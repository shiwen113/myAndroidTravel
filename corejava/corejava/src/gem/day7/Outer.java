package gem.day7;
//说明内部类的一些语法
//top level类
public class Outer {
	private static int i = 100;
	private String s = "java";	//实例变量
	//内部类是类的一个成员
	//内部类的访问控制符可以是：public package protected private
	//也可以用static
	class Inner {
		//可以写,实例变量与实例方法
		String s = "android";	//可以定义实例变量
		//内部类不可以定义静态成员
		///static int score = 100;
		//定义实例方法
		public void show() {
			String s = "JSP";
			//无条件外部类的成员
			System.out.println(s+","+this.s+","+Outer.this.s+","+Outer.i);
		}
	};
	
	
	public static void main(String[] args) {
		//创建内部类的语法
		//先创建外部类对象
		Outer out = new Outer();
		//创建内部类的语法:out.new Inner();
		Inner in = out.new Inner();
		//可以调用内部对象上的方法
		in.show();
	}

}
//不是内部类
class C {}