package gem.day9.homework;

import java.util.Scanner;

//2、（自定义异常）在上一题的基础上，把下面代码补充完整。
public class TestMyException{
		public static void main(String args[]){
			int n = 1;
			System.out.println("请读入数n:");
			//读入n
			Scanner scanner = new Scanner(System.in);
			n = scanner.nextInt();
			try{
				m(n);
			}catch(MyException1 ex1){
				//输出ex1 详细的方法调用栈信息
				ex1.printStackTrace();
			}catch(MyException2 ex2){
				//输出ex2 的详细信息
				ex2.printStackTrace();
				//并把ex2 重新抛出
				throw ex2;
			}
		}
		//声明抛出MyException1
		public static void m(int n) throws MyException1 { 
			if (n == 1) {
				//抛出MyException1
				//并设定其详细信息为“n == 1”
				throw new MyException1("n==1");
			}else {
				//抛出MyException2
				//并设定其详细信息为“n == 2”
				throw new MyException1("n==2");
		}
	}
}



