package gem.day17.homework;
/*
 * 1、（Method）通过命令行输入指定的类名(如：输入java.lang,String)，
以输出该类（如String）的所有构造方法签名
 */
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ConstractionTest {

	public static void main(String[] args) throws ClassNotFoundException {
		System.out.println("请输入类名：");
		Scanner input = new Scanner(System.in);
		
		String classname = input.next();
		//通过命令行获得类名
		//classname = args[0];
		//加载类
		Class clazz = Class.forName(classname);
		//获得构造方法
		Constructor[] con = clazz.getDeclaredConstructors();
		//遍历构造方法
		for(Constructor cons:con){
			System.out.println(cons.toGenericString());
		}

	}

}
