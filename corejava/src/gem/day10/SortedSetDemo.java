package gem.day10;

import java.util.SortedSet;
import java.util.TreeSet;

public class SortedSetDemo {

	public static void main(String[] args) {
		// 说明SortedSet集合的用法
		// SortedSet是Set的子接口，它有Set的所有的特性
		SortedSet<String> ss = new TreeSet<String>();
		//在ss集合中加入一些字符串
		ss.add("Java");
		ss.add("Android");
		ss.add("Servlet");
		ss.add("JSP");
		ss.add("MySQL");
		System.out.println(ss);
		//String实现了Compareable接口，规定了排序的规则

	}

}
