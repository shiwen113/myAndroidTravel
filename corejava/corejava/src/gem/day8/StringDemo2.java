package gem.day8;

public class StringDemo2 {

	public static void main(String[] args) {
		//字符串的不变性(难点)
		String s = "java";
		String s1 = s.toUpperCase();//转为大写
		String s2 = s.substring(2);//取子字符串
		String s3 = s + s1;
		
		System.out.println(s+","+s1+","+s2);
	}

}
