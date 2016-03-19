package gem.day17.homework;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/*
 * 2、（Method）通过反射技术调用一个String对象上的
   1)lastIndexOf(String str,int fromIndex)
 */
public class RefectTest {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		//加载类
		Class clazz = String.class;
		//定义一个字符串
		String str = "java android ios";
		//获取这个方法
		Method med = clazz.getDeclaredMethod("lastIndexOf", String.class,int.class);
		//启动这个方法
		int i = (int) med.invoke(str, "an",str.length());
		//正常调用：
		//str.lastIndexOf("an",str.length)
		System.out.println(i);
	}

}
