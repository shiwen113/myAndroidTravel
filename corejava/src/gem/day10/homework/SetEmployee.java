package gem.day10.homework;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class SetEmployee {

	//3、(Set)在1题的基础上，为Employee增加equals方法与hashCode方法，使得姓名相同并且年龄也相同的员工为逻辑上相等（值）的员工。并完成下面的代码
	//1) 创建一个Set，在Set 中增加如下员工对象，基本信息如下：
	//	姓名 年龄 工资
	//	zhang3 18 3000
	//	li4 25 3500
	//	wang5 22 3200
	//	wang5 22 6200
	//2) Set集合中有几个元素，为什么？
	//3) 利用for-each 循环遍历，打印Set中所有员工的信息
	//4) 利用迭代遍历，打印Set中所有员工的信息

	public static void main(String[] args) {
		//1
		Set<Employee> es = new HashSet<Employee>();
		Employee e1 = new Employee("zhnag3",18,3000);
		Employee e2 = new Employee("li4",25,3500);
		Employee e3 = new Employee("wang5",22,3200);
		Employee e4 = new Employee("wang5",22,6200);
		es.add(e1);
		es.add(e2);
		es.add(e3);
		es.add(e4);
		System.out.println(es.size());	//3
		//3) 利用for-each 循环遍历，打印Set中所有员工的信息
		for(Employee e:es) {
			System.out.println(e);
		}
		
		//4) 利用迭代遍历，打印Set中所有员工的信息
		Iterator<Employee> iter = es.iterator();
		while(iter.hasNext()) {
			Employee e = iter.next();
			System.out.println(e);
		}
		


	}

}
