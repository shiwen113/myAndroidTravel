package gem.day4;

public class Primer {
	//写一个方法，判断一个数是否是素数
	public   boolean  isPrimer(int n) {
		//System.out.println();
		for(int i=2;i<n-1;i++) {
			if(n%i == 0) {
				return false;
			}
		}
		return  true;
	}
}
