package gem.day10;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class SetDemo {

	public static void main(String[] args) {
		//说明Set集合的用法
		//定义Set集合：接口名  变量名 = new 实现类（）；
		//范型定义集合中只能放某中类型对象  <String>
		Set<String> set = new HashSet<String>();
		//加一些元素
		set.add("java");
		set.add("Servlet");	//13:int==>Integer
		set.add("JSP");
		set.add("IOS");
		set.add("Android");
		//Set集合中元素不重复，
		//o1.equals(o2)==true,o1与o2重复
		set.add("java");
		//去掉IOS
		set.remove("IOS");
		//集合中有几个元素
		System.out.println("集合中元素的个数："+set.size());
		System.out.println("集合中的元素："+set);
		//迭代方法，两种方法，for-each,Iterator
		for(String s:set) {
			System.out.print(s+",");
		}
		System.out.println();
		Iterator<String> iter = set.iterator();
		while(iter.hasNext()) {
			//获得元素
			String s = iter.next();
			System.out.print(s+",");
		}
	}

}
class Dog {};
