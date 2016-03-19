package gem.day2;

public class BitOperationDemo {

	public static void main(String[] args) {
		// 位运算符示例：数==》二进制数
		// 位
		int i = 13;
		int j = 25;
		// i & j
		// 13 = 8 + 4 + 1
		// 25 = 16 + 8 + 1
		//13   0     0000 1101           
		//25         0001 1001
        //&          0000 1001   ==> 9   并
		//|          0001 1101   ==> 29     或
		//^          0001 0100   ==> 20  异或
		System.out.println(i+"&"+j+"="+(i&j));
		System.out.println(i+"|"+j+"="+(i|j));
		System.out.println(i+"^"+j+"="+(i^j));
		
		System.out.println(false & true);
		
		//>> >>>的区别是什么？
		//>> 最高位不变，>>>最高位=0
		String s  = (i>j)?"10":"20";
		System.out.println("s="+s);
		//+运算符，可以作为字符串联接
		System.out.println(1 + 1 + "1");
		
		
		
		
		
		
		
		
	}

}
