package gem.day17;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInvocation implements InvocationHandler {
	final Person p = new PersonImpl();

	public static void main(String[] args) {
		Person p = (Person) Proxy.newProxyInstance(
				MyInvocation.class.getClassLoader(),
				new Class[] { Person.class }, new MyInvocation());
		p.eating();
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println(proxy instanceof Person);
		System.out.println("begin");
		method.invoke(p);
		// method.invoke(proxy);
		System.out.println("end");
		return proxy;
	}

}
