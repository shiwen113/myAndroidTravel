package gem.day10;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class EmployeeSet {

	public static void main(String[] args) {
		//创建一个放Employyee对象的Set集合
		Set<Employee> emps = new HashSet<Employee>();
		//加入一些员工对象
		//110    zhang3   5000
		Employee e1 = new Employee("110","zhang3",5000);
		emps.add(e1);
		//111    li4      5500
		emps.add(new Employee("111","li4",5500));
		//112    wang5    6000
		emps.add(new Employee("112","wang5",6000));
		//110    zhang3   5000
		Employee e4 = new Employee("110","zhang3",5000);
		emps.add(e4);
		//?有几个元素,有e1.equals(e4)==true
		System.out.println(e1.equals(e4));
		System.out.println(emps.size());
		//for-each语句遍历
		for(Employee e:emps) {
			System.out.println(e.toString());
		}
		//Iterator遍历
		Iterator<Employee> iter = emps.iterator();
		while(iter.hasNext()) {
			Employee e = iter.next();
			//e ==> e.toString
			System.out.println(e);
		}
	}

}
