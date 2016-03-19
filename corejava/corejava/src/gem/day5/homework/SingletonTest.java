package gem.day5.homework;

public class SingletonTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Singleton s1 = Singleton.getInstance();
		Singleton s2 = Singleton.getInstance();
		System.out.println(s1 == s2);//true
		
		
		
	}

}

// 实现单例模式
class Singleton {
	private static Singleton singleton = null;

	private Singleton() {
	}

	public static Singleton getInstance() {
		if (singleton == null) {
			singleton = new Singleton();
		}
		return singleton;
	}

}