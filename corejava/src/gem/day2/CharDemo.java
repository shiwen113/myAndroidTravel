package gem.day2;

public class CharDemo {

	public static void main(String[] args) {
		//&&与&的区别
		if(10!=10 && 10/0==1) {
			System.out.println(".....");
		}
		System.out.println("ok");
		
		
		
		//说明字符的用法,字符==》数值  字符编码集
		char c = '汉';	//97:ascii
		//汉的unicode值
		System.out.println((int)c);
		System.out.println((char)27721);
		
		//boolean bool = 0;  //error
		//if(1)				//ERROR
		
		short s = 1;
		s = (short)(s+1);
		//int Integer ：字符串 ==>整数
		int age = Integer.parseInt("123");
		//double Double
		//字符串==》浮点数
		Double.parseDouble("123.45");
	}

}
