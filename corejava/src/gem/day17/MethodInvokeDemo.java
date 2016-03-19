package gem.day17;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MethodInvokeDemo {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 示例说明通过Mehtod对象上的invoke方法来调用一个方法
		// 任何一个类中的任何一个方法都可以用一个Method对象表示
		// 正常
		String str = "i love reflection!!!";
		int pos = str.indexOf("love", 10);
		System.out.println(pos);
		//反射技术调用indexOf方法
		//获得String的Class对象
		Class<String> c = String.class;
		//获得这个方法的Method对象
		//m这个对象  == 字符串上的indexOf方法，带两个参数
		Method m = c.getMethod("indexOf", String.class,int.class);
		//再用Method对象上的invoke方法
		//在str对象上调用方法m
		int pos1 = (Integer)m.invoke(str, "tion",0);
		System.out.println(pos1);
	}

}
