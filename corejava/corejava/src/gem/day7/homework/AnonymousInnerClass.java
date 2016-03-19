package gem.day7.homework;


public class AnonymousInnerClass {
	
	
	

	public static void aMethod(IA ia) {
		ia.test();
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		aMethod(new IA() {
			public void test() {
				System.out.println("匿名类");
			}
		});

	}

}
//接口的匿名内部类实现，要求如下：
//a)定义一个接口名为IA，提供一个test方法
//b)编写一个测试类，提供一个方法method，参数为IA类型
//c)在main方法中，调用method方法，同时实参以匿名内部类的方式构建

interface IA {
	void test();
}
