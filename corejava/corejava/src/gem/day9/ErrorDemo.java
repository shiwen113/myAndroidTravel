package gem.day9;

public class ErrorDemo {
	public static void m() {
		m();
	}
	

	public static void main(String[] args) {
		// OutOfMemoryError：
		int [] arr = new int[10000000];//
		m();
		System.out.println("ok:"+arr.length);

	}

}
