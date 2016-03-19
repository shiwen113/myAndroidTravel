package gem.day2;

public class ContinueDemo {

	public static void main(String[] args) {
		//continue的使用
		for(int i=1;i<10;i++) {
			if(i==6) {//这步不运行，
				//continue;
				//从方法中返回
				return;
			}
			System.out.print(i+",");
		}
		System.out.println("end!!!");
	}

}
