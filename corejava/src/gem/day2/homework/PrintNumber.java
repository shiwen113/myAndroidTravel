package gem.day2.homework;

public class PrintNumber {
	/**
	 * 
	 *输出以下数字形式
	 */

	public static void main(String[] args) {
		int n = 9;
		for(int row = 1;row <= n;row++){
			//输出数字之前输出的空格
			for(int j = 1;j <= (n - row) * 2;j++){
				System.out.print(" ");
			}
			//前一半
			for(int j = 1;j <= row;j++){
				System.out.print(j+" ");
			}
			//另一半
			for(int j = row - 1;j > 0;j--){
				System.out.print(j+" ");
			}
			System.out.println();
		}
	}

}
