package gem.day5;

public class StaticDemo {
	//成员变量
	int age = 2;			//实例变量
	static int nums = 4;	//腿的个数
	
	//实例方法与静态方法
	//实例方法,访问实例方法，通过对象来访问
	//实例方法可以访问实例变量和静态变量
	public void m1() {
		age = age+1;
		nums = nums + 1;
	}
	//静态方法,通过类名来访问
	public static void m2() {
		//静态方法中只能访问静态变量，也可以调用静态方法
		//age = age+1;
		nums = nums + 1;
	}
	
	
	public static void main(String[] args) {
		//静态变量通过类名来访问
		int n = StaticDemo.nums;
		System.out.println(n);
		//访问m2方法：
		StaticDemo.m2();
		
		
		//访问实例变量，须先创建对象，通过对象访问
		StaticDemo sd1 = new StaticDemo();
		sd1.age++;
		int a1 = sd1.age;	//3
		//访问m1方法:
		sd1.m1();
		
		StaticDemo sd2 = new StaticDemo();
		int a2 = sd2.age;	//2
		System.out.println(a1+","+a2);
		//静态变量也可以用对象来访问
		sd1.nums--;	//StaticDemo.nums
		System.out.println(sd2.nums);	//3
	}

}
