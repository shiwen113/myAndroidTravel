package gem.day10;
//类型：字符串、数值、日期,实体类
//让这个类实现Compareable接口，这里的排序规则，称为自然排序
//目的：是当将Employee对象加入到TreeSet集合中时，
//可以让TreeSet将集合中的Employee对象进行排序，
public class Employee implements Comparable<Employee> {
	private String eno;		//员工号
	private String name;	//姓名
	private int salary;		//工资
	
	//构造方法
	public Employee(String eno,String name,int salary) {
		this.eno = eno;
		this.name = name;
		this.salary = salary;
	}
	//get/set方法
	public String getEno() {
		return eno;
	}
	public void setEno(String eno) {
		this.eno = eno;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}
	//toString方法
	@Override
	public String toString() {
		return "Employee [eno=" + eno + ", name=" + name +
				", salary=" + salary
				+ "]";
	}
	//哈希码
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((eno == null) ? 0 : eno.hashCode());
		
		return result;
	}
	//重写equals方法和hashCode:
	//设计：什么样的两个员工是"相等",我认为：两个员工的工号相等，
	//则认为是同一个员工
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (eno == null) {
			if (other.eno != null)
				return false;
		} else if (!eno.equals(other.eno))
			return false;
		return true;
	}
	@Override
	public int compareTo(Employee o) {
		//代码中验证o是Employee类型
		//比较的是当前对象this 与传过来的对象o比较
		//this < o  返回 负数   -1
		//this = o  相等           0
		// this.equals(o) == true
		//this > o  正数           1
		//确定：排序规则，只能按员工编号
		return this.getEno().compareTo(o.getEno());
	}
	
	
	
	
	
	
	
	
	
	
	
}
