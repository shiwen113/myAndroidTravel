package gem.day5;
//单例设计模式：保证一个类只有一个对象
public class Singleton {
	//定义一个静态变量,静态变量初始化仅一仅
	//lazy创建对象
	//private static Singleton singleton = new Singleton();
	private static Singleton singleton = null;
	
	//不能让外面的用户创建这个对象,让构造方法私有化
	private Singleton() {
		
	}
	//让外面的用户可以获得这个对象,提供一个静态方法
	public static Singleton   getInsatnce() {
		if(singleton == null) {
			singleton = new Singleton();
		}
		
		return singleton;
	}
	

}
