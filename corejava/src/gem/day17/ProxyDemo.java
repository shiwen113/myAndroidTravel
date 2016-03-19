package gem.day17;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyDemo {

	public static void main(String[] args) {
		Person person = new PersonImpl();
		
		//用匿名类，实现了InvocationHandler接口
		InvocationHandler handler = new InvocationHandler(){
			@Override
			public Object invoke(Object proxy, 
					Method method, 
					Object[] args)
					throws Throwable {
				//method对象，对应eating方法,args调用该方法的参数
				System.out.println("喝茶");
				//person.eating();
				//反射
				method.invoke(person);
				System.out.println("抽烟");
				return null;
			}
			
		};		
	
		//实现了Person接口的代理对象
		Person person1 =  (Person)Proxy.newProxyInstance(
				ProxyDemo.class.getClassLoader(),
				new Class[]{Person.class},
				handler);
		//System.out.println(person);
		System.out.println(person1.getClass());
		person1.eating();
	}

	

}
