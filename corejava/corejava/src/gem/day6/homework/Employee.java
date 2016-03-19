package gem.day6.homework;
/*
3、（**封装、继承、super）某公司的雇员分为以下若干类：
Employee：这是所有员工总的父类，属性：员工的姓名,员工的生日月份。		
方法：getSalary(int month) 根据参数月份来确定工资，如果该月员工过生日，则公司会额外奖励100 元。
SalariedEmployee：Employee 的子类，拿固定工资的员工。属性：月薪
HourlyEmployee：Employee 的子类，按小时拿工资的员工，每月工作超出160 小时的部分按照1.5 倍工资发放。属性：每小时的工资、每月工作的小时数
SalesEmployee：Employee 的子类，销售人员，工资由月销售额和提成率决定。属性：月销售额、提成率
BasePlusSalesEmployee：SalesEmployee 的子类，有固定底薪的销售人员，工资由底薪加上销售提成部分。属性：底薪。
根据要求创建SalariedEmployee、HourlyEmployees、SaleEmployee 和BasePlusSalesEmployee四个类的对象各一个，并计算某个月这四个对象的工资。
注意：要求把每个类都做成完全封装，不允许非私有化属性。
类图如下：*/
public abstract class Employee {
	private String name;	//姓名
	private int month;		//出生月份
	//构造方法
	public Employee(String name,int month) {
		this.name = name;
		this.month = month;
	}
	//调用子类的计算工资的方法,模版template方法
	public double getSalary(int month) {
		if(month == this.month) {
			return getSalary() + 100;
		}else {
			return getSalary();
		}
	}
	//
	protected abstract double getSalary(); 
		

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getMonth() {
		return month;
	}

	public void setMonth(int month) {
		this.month = month;
	}
	
	//get/set方法
	
	
	
	
	//toString方法
	//equals和hashCode方法
	
	
}
