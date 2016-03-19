package gem.day4;

public class DogTest {

	public static void main(String[] args) {
		int i = 10;
		//创建Dog对象
		//Dog是类名，表示一个类型,这是一个引用类型
		//dog：是一个变量名，代码一个对象
		// new 是运算符，用来创建一个对象，系统会在内存的堆空间中分配一个
		//区域放这个对象
		//Dog()：是构造方法，用来初始化对象的成员变量
		Dog dog = new Dog();
		//访问对象的成员：属性，调用方法
		//访问对象的成员运算符是：.
		String name = dog.name;
		System.out.println(name);
		//调用方法
		dog.yaff();
		//调用add方法，变量z与方法add的返回类型要一致
		float x = 10;
		int y = 20;
		int u = 30;
		//y:int ==> float
		float z = dog.add(x,y) + 1;
		//可以用可变参数
		int a = dog.add(1, 2,3,4,5,6,7,8);
		System.out.println(a);
		
		System.out.printf("",x);

	}

}
