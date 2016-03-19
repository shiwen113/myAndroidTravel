package gem.day8;

public class StringBufferDemo {

	public static void main(String[] args) {
		StringBuffer sbuf = new StringBuffer("java");
		sbuf.append("android");
		sbuf.append("IOS");
		String s1 = "java";
		s1 = s1+ "android";//创建新的字符串对象
		s1 = s1 + "ios";	//创建新的字符串对象
		//不断重复的创建新的字符对象，要用StringBuffer
		//说明两个性能之间的比较
		long start = System.currentTimeMillis();
		for(int i=0;i<10000;i++) {
			s1 += i;//创建新的对象
		}
		long end = System.currentTimeMillis();
		System.out.println("String花的时间："+(end - start));
		//StringBuffer
		start = System.currentTimeMillis();
		for(int i=0;i<10000;i++) {
			sbuf.append(i);
		}
		end = System.currentTimeMillis();
		System.out.println("StringBuffer花的时间："+(end - start));
		
		
		
		
		
		System.out.println(sbuf.toString());

	}

}
