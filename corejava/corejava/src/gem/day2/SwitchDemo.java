package gem.day2;

public class SwitchDemo {

	public static void main(String[] args) {
		//switch-case的用法
		//switch中可以是整型变量,也可以是自动转换为int的类型 
		//long不可以
		//字符串：JDK1.7之后，
		int score = 3;
		//switch-case的运行流程
		switch(score) {
		case 5:
			System.out.println("优");
			break; //
		case 4:
			System.out.println("良");
			break; //
		case 3:
			System.out.println("中");
			//break; //
		case 2:
			System.out.println("一般");
			//break; //
		case 1:
			System.out.println("差");
			break; //
		default:   
			System.out.println("???");
		
		}
		
		

	}

}
