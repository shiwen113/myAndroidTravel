package gem.day8;

public class StringDemo1 {

	public static void main(String[] args) {
		// 字符串的比较，请用equals方法
		String s = "va";
		String s1 = "java";		//字符串常量池
		String s2 = "ja"+"va";	//编译器：java
		//面试题：创建了几个对象2
		String s3 = new String("java");
		System.out.println(s1 == s2);
		System.out.println(s1.equals(s2));//true
		
		System.out.println(s1 == s3);	//false
		System.out.println(s1.equals(s3));//true

	}

}
