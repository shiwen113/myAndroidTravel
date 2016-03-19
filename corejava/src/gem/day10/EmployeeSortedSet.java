package gem.day10;

import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

public class EmployeeSortedSet {

	public static void main(String[] args) {
		// 了解
		// 创建一个SortedSet集合，放Employee对象
		SortedSet<Employee> emps = new TreeSet<Employee>(
				new Comparator<Employee>(){
					@Override
					public int compare(Employee o1, Employee o2) {
						return o2.getSalary() - o1.getSalary();
					}
				});
		//如果想将员工对象按其它规则进行排序？
		//自定义排序规则
		//按工资大小排序
		//加一些员工对象
		emps.add(new Employee("112","wang5",6000));
		emps.add(new Employee("110","zhang3",5000));
		emps.add(new Employee("111","li4",5500));
		emps.add(new Employee("113","obama",2500));
		
		System.out.println(emps);
	}
}
//自定义排序规则，写一个类实现Comparator接口，
//在接口的compare方法中定义排序规则（大小关系）
class EmployeeComparator implements Comparator<Employee> {
	@Override
	public int compare(Employee o1, Employee o2) {
		// 定义排序规则，按工资比较
		return o1.getSalary() - o2.getSalary();
	}
	
}





