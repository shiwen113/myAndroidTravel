package gem.day4;

public class PrimerTest {

	public static void main(String[] args) {
		int x = 1998;
		//创建对象
		Primer p = new Primer();
		boolean flag = p.isPrimer(x);
		if(flag) {
			System.out.println(x+"是素数");
		}else {
			System.out.println(x+"不是素数");
		}

	}

}
