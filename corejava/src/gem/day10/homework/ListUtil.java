package gem.day10.homework;

import java.util.ArrayList;
import java.util.List;

public class ListUtil {
	//4、（*List）写一个函数reverseList，该函数能够接受一个List，然后把该List 倒序排列。并完成测试代码，例如：
	//List list = new ArrayList();
	//list.add(“Hello”);
	//list.add(“World”);
	//list.add(“Learn”); //此时list 为Hello World Learn
	//reverseList(list);
	//调用reverseList 方法之后，list 为Learn World Hello
	@SuppressWarnings("unchecked")
	public static void reverseList(List lists) {
		for(int i=0;i<lists.size()/2;i++) {
			Object tmp = lists.get(i);
			lists.set(i, lists.get(lists.size()-1-i));
			lists.set(lists.size()-1-i,tmp);

		}
		
	}
	public static void main(String[] args) {
		List list = new ArrayList();
		list.add("Hello");
		list.add("World");
		list.add("Learn"); //此时list 为Hello World Learn
		list.add("Java");
		System.out.println(list);
		reverseList(list);
		System.out.println(list);

	}

}
