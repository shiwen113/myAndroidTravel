package gem.day5;

public class SingletonTest {

	public static void main(String[] args) {
		Singleton s1 = Singleton.getInsatnce();
		Singleton s2 = Singleton.getInsatnce();
		System.out.println(s1 == s2);//true

	}

}
