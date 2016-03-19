package gem.day6.homework;

public class HourlyEmployees extends Employee {
	private double hourlySalary;//每小时工资
	private int hours;			//工作的小时数
	
	public HourlyEmployees(String name,int month,
			double hourlySalary,int hours) {
		//调用父类的构造方法
		super(name,month);
		this.hourlySalary = hourlySalary;
		this.hours = hours;
	}
	//get/set方法

	public double getHourlySalary() {
		return hourlySalary;
	}

	public void setHourlySalary(double hourlySalary) {
		this.hourlySalary = hourlySalary;
	}

	public int getHours() {
		return hours;
	}

	public void setHours(int hours) {
		this.hours = hours;
	}

	@Override
	public double getSalary() {
		if(hours < 160) {
			return hourlySalary * hours;
		}else {
			return hourlySalary * 160 + 
					(hours - 160) * 1.5*hourlySalary;
		}
	}
	
}
