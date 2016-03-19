package gem.day2.homework;

public class NineMultyNine {
	/*
	 * 输出9x9的乘法表
	 */
	public static void main(String[] args) {
		for(int i = 1;i <= 9;i ++){
			//每一行输出多少个数  i
			System.out.print(i+" | ");
			for(int j = 1;j <= i;j++){
				System.out.print(i+"*"+j+"="+(i * j < 10?i * j+ "    ":i * j+"   "));
			}
			System.out.println();
		}
	}

}
