package gem.day17;

public class ClassDemo {

	public static void main(String[] args) throws ClassNotFoundException {
		// 获得Class对象的三种方法
		//方法1:类名.class
		Class<ClassDemo> c1 = ClassDemo.class;
		Class<Integer> ci = Integer.class;
		//int.class
		//方法2:通过对象上的getClass方法，也能获得对应的类对象
		ClassDemo cd = new ClassDemo();
		Class c2 = cd.getClass();
		String str = "java";
		//str.getClass();
		//3、类的字符串，Class.forName，获得类对象
		Class c3 = Class.forName("gem.day17.ClassDemo");
		//?:加载类，只加载一次，初始化类，执行静态初始化块
		System.out.println(c1==c2);
		System.out.println(c1==c3);
		
	}

}
