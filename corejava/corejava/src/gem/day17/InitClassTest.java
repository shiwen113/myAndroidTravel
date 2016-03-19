package gem.day17;

public class InitClassTest {

	public static void main(String[] args) throws ClassNotFoundException {
		// TODO Auto-generated method stub
		//new InitClass();
		//访问类的静态变量时，会初始化类
		//int i = InitClass.i;
		//System.out.println(i);
		//Class.forName("gem.day17.InitClass");
		//String s = SubInitClass.str;
		//System.out.println(s);
		//不初始化类的三种情形
		//1、访问静态常量,常量在编译时能确定，此时不初始化类
		//System.out.println(InitClass.i);
		//2、通过子类名访问父类的静态变量，初始化父类，子类没有初始化
		//System.out.println(SubInitClass.j);
		//3、创建数组对象时,不会
		InitClass[] arr = new InitClass[10];
		System.out.println("ok");
	}

}
