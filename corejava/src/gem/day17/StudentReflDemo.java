package gem.day17;

import java.lang.reflect.Method;

public class StudentReflDemo {

	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException {
		// 说明获得Method对象的几个方法
		//getMethods()           public    含父类的方法
		//getDeclaredMethods()   与权限无关     不含父类的方法
		//先获得Class对象
		Class<Student> cs = Student.class;
		//可以用Class对象创建一个Student对象,
		//要求是Student类有无参的构造方法
		Student s = cs.newInstance();
		
		
		
		Method[] ms = cs.getMethods();
		System.out.println(ms.length);
		//输出
		for(Method m:ms) {
			System.out.println(m.toGenericString());
		}
		//
		System.out.println("====================================");
		Method[] ms1 = cs.getDeclaredMethods();
		for(Method m:ms1) {
			System.out.println(m.toGenericString());
		}
		//获得指定的方法
		Method m = cs.getMethod("setName",String.class);
		System.out.println(m);
		
		

	}

}
