package gem.day10.homework;
//1、有一个Employee类，其中有如下属性
//private int age;
//private String name;
//private double salary;
//实现Employee类，要求
//1）具有无参的构造函数
//2）具有Employee(int age，String name,double salary)构造函数
//3）重写toString方法，显示员工的姓名、年龄、薪水

//4、（***Set，Comparable 接口）在前面的Employee 类基础上，
//为Employee类添加相应的代码，使得Employee对象能正确放入TreeSet 中。
//并编写相应的测试代码。
//注：比较时，先比较员工年龄大小，年龄小的排在前面。如果两个员工年龄相同，则再
//比较其收入，收入少的排前面。如果年龄和收入都相同，则根据字典顺序比较员工姓名。


public class Employee implements Comparable<Employee> {
	private int age;
	private String name;
	private double salary;

	//无参构造函数
	public Employee() {
		
	}
	//具有Employee(int age，String name,double salary)构造函数
	public Employee(String name,int age,double salary){
		this.name = name;
		this.age = age;
		this.salary = salary;
		
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getSalary() {
		return salary;
	}
	public void setSalary(double salary) {
		this.salary = salary;
	}
	
	//使得姓名相同并且年龄也相同的员工为逻辑上相等（值）的员工
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + age;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Employee other = (Employee) obj;
		if (age != other.age)
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
	//toString方法
	@Override
	public String toString() {
		return "Employee [age=" + age + ", name=" + name + ", salary=" + salary
				+ "]";
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	//排序规则
	//为Employee类添加相应的代码，使得Employee对象能正确放入TreeSet 中。
	//并编写相应的测试代码。
	//注：比较时，先比较员工年龄大小，年龄小的排在前面。如果两个员工年龄相同，则再
	//比较其收入，收入少的排前面。如果年龄和收入都相同，则根据字典顺序比较员工姓名。
	@Override
	public int compareTo(Employee o) {
		if(this.age != o.age) {
			return this.age - o.age;
		}else if(this.salary != o.salary) {
			return (int)(this.salary - o.salary);
		}else {
			return this.name.compareTo(o.name);
		}
	}

}
