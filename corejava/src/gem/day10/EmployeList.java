package gem.day10;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class EmployeList {

	public static void main(String[] args) {
		//创建一个放Employyee对象的List集合
		List<Employee> emps = new ArrayList<Employee>();
		//加入一些员工对象
		//110    zhang3   5000
		//111    li4      5500
		//112    wang5    6000
		//110    zhang3   5000
		emps.add(new Employee("110","zhang3",5000));
		emps.add(new Employee("111","li4",5500));
		emps.add(new Employee("112","wang5",6000));
		emps.add(new Employee("110","zhang3",5000));
		//用三种方法遍历集合中的元素
		//普通for  for-each  Iterator  ListIterator
		Iterator<Employee> iter = emps.iterator();
		while(iter.hasNext()) {
			Employee e = iter.next();
			System.out.println(e);
		}
		//普通
		for(int i=0;i<emps.size();i++) {
			Employee e = emps.get(i);
			System.out.println(e);
		}
	}

}
