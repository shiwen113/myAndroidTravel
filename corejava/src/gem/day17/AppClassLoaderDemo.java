package gem.day17;

public class AppClassLoaderDemo {

	public static void main(String[] args) {
		//哪个类加载器加载了当前这个类
		//获得类AppClassLoaderDemo的类加载器对象
		ClassLoader cl = AppClassLoaderDemo.class.getClassLoader();
		System.out.println(cl);
		//系统类加载器
		ClassLoader cl1 = ClassLoader.getSystemClassLoader();
		System.out.println(cl1);
		//系统类加载器的父加载器，扩展类加载器
		ClassLoader cl2 = cl.getParent();
		System.out.println(cl2);
		//得到根类加载器,根加载器是c/c++实现
		ClassLoader cl3 = cl2.getParent();
		System.out.println(cl3);
	}

}
