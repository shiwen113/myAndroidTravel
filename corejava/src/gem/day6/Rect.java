package gem.day6;
//语法：类继承一个抽象类：必须实现抽象类中的抽象方法
//或者 将这个类也定义为抽象类
public  class Rect extends Shape {
	//长方形：
	private double width;
	private double height;
	//定义一个构造方法，
	public Rect(double width,double height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public double area() {
		// TODO Auto-generated method stub
		return this.width * this.height;
	}

	@Override
	public double girth() {
		// TODO Auto-generated method stub
		return 2*(this.width + this.height);
	}

	@Override
	public void draw() {
		// TODO Auto-generated method stub
		System.out.println("正在画一个宽为:"+this.width+
				",高为："+this.height+"长方型");
	}
	//get/set方法
	
	

}
