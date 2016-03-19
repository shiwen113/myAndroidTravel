package gem.day17.homework;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

// 通过反射技术创建Student对象
public class ReflectDemo {
	
	public static void main(String[] args) throws NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		// 获取Student的Class对象
		Class<Student> clazz = Student.class;
		//getDecalaredMehod("方法名"，方法的参数类型，)

		// 获得Student的构造器
		Constructor<Student> ctor = clazz.getDeclaredConstructor(
				int.class, String.class, int.class);
		// 获得Student实例
		Object obj = ctor.newInstance(1001, "张三", 22);
		//相当于
        // new Student(1001,"张三",22）;
		System.out.println(obj);
	}
}


// 学生类
class Student{
	private int id;
	private String name;
	private int age;
	public Student(){}
	public Student(int id, String name, int age) {
		super();
		this.id = id;
		this.name = name;
		this.age = age;
	}
	
	@Override
	public String toString() {
		return "ID：" + id + ", 姓名：" + name + ", 年龄：" + age;
	}
	
	
}
