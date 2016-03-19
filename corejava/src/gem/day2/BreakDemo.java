package gem.day2;

public class BreakDemo {

	public static void main(String[] args) {
		// break语句的作用
		exit: 
		for (int j = 0; j < 10; j++) {
			for (int i = 1; i < 10; i++) {
				System.out.print(i + ",");
				if (i == 6) {
					//break +标签语句
					break exit;
				}
			}
			System.out.println();
		}

	}

}
