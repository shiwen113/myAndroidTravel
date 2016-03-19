package gem.day10;

import java.util.ArrayList;
import java.util.List;
import java.util.ListIterator;

public class ListDemo {

	public static void main(String[] args) {
		// 示例说明List的用法:元素可以重复
		//定义一个List集合，放String类型的元素，实现类用ArrayList
		List<String> lists = new ArrayList<String>();
		//加入一些字符串
		lists.add("Java");
		lists.add("Android");
		lists.add("Spring");
		lists.add("Hibernate");
		lists.add("Struts2");
		lists.add("Java");
		lists.add(3,"JDBC");
		//获得后面的哪个Java,获得索引是6的元素
		String s = lists.get(6);
		lists.remove("Java");
		System.out.println("s="+s);
		System.out.println("元素个数:"+lists.size());
		System.out.println(lists);
		
		//List集合的遍历,4种方法
		//for-each  Iterator
		//普通for语句
		for(int i=0;i<lists.size();i++) {
			String str = lists.get(i);
			System.out.print(str+",");
		}
		System.out.println();
		//ListIterator(*),从后向前迭代
		ListIterator<String> liter = 
				lists.listIterator(lists.size());
		while(liter.hasPrevious()) {
			String str = liter.previous();
			System.out.print(str+",");
		}
		
		
		

	}

}
