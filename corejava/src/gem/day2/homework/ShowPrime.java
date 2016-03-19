package gem.day2.homework;

public class ShowPrime {

	/*
	 * 输出100以内的素数
	 */
	public static void main(String[] args) {
		boolean flag ;
		System.out.print("2 ");
		//n是要判断的数
		for(int n = 3;n < 100;n += 2){
			flag = true;//假设n是素数
			for(int i = 2;i <= n / 2;i++){
				if(n % i == 0){
					flag = false;//说明n不是素数
					break;
				}
			}
			//检测flag
			if(flag){
				System.out.print(n+" ");
			}
		}
	}

}
