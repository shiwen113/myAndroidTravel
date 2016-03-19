package gem.day9;

import java.io.IOException;

public class ExceptionStackDemo {
	public static void  f3() throws IOException {
		throw new IOException();
	}
	public static void f2() throws IOException {
		f3();
	}
	
	public static void f1() throws IOException {
		f2();
	}
	
	//方法调用：main==>f1==> f2 ==> f3 (抛出异常)
	//异常:

	public static void main(String[] args) throws IOException {
		f1();
	}

}
