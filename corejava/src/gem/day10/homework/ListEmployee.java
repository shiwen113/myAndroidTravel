package gem.day10.homework;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListEmployee {

	//（List）在上题的基础上
	//1) 创建一个List，在List 中增加如下员工对象，基本信息如下：
	//	姓名 年龄 工资
	//	zhang3 18 3000
	//	li4 25 3500
	//	wang5 22 3200
    //	wang5 22 6200
	//2) 在li4 之前插入一个工人，信息为：姓名：zhao6，年龄：24，工资3300
	//3) 删除wang5 的信息
	//4) 利用for-each 循环遍历，打印List 中所有员工的信息
	//5) 利用一般for 循环遍历，打印List 中所有员工的信息
	//6) 利用迭代（Iterator）遍历，打印List 中所有员工的信息。

	public static void main(String[] args) {
		//1
		List<Employee> es = new ArrayList<Employee>();
		Employee e1 = new Employee("zhnag3",18,3000);
		Employee e2 = new Employee("li4",25,3500);
		Employee e3 = new Employee("wang5",22,3200);
		Employee e4 = new Employee("wang5",22,6200);
		es.add(e1);
		es.add(e2);
		es.add(e3);
		es.add(e4);
		System.out.println(es);
		Employee e5 = new Employee("zho6",24,3300);
		//2
		es.add(1, e5);
		System.out.println(es);
		es.remove(3);
		//3
		System.out.println(es);
		//4) 利用for-each 循环遍历，打印List 中所有员工的信息
		for(Employee e:es) {
			System.out.println(e);
		}
		//5) 利用一般for 循环遍历，打印List 中所有员工的信息
		for(int i=0;i<es.size();i++) {
			System.out.println(es.get(i));
		}
		//6) 利用迭代（Iterator）遍历，打印List 中所有员工的信息。
		Iterator<Employee> iter = es.iterator();
		while(iter.hasNext()) {
			Employee e = iter.next();
			System.out.println(e);
		}
		

	}

}
