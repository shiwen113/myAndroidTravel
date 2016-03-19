package gem.day6;
//图型:抽象概念
//抽象类：是不能实例化的类，new 抽象类名(error)
public abstract class Shape {
	//抽象类与抽象方法的关系
	//抽象类中可以有具体方法,也可以有抽象方法
	//接口：只有抽象方法
	//如果类中有抽象方法，则这个类必须为抽象类
	//抽象方法,具体方法（有实现的方法)
	//抽象方法：即没有实现的方法
	//签名 + 实现
	//用abstract说明是一个抽象方法
	//求面积
	public abstract double area(); 
	public abstract double girth();//周长
	public abstract void draw();	
	
	
	
}
