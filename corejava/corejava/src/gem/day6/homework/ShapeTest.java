package gem.day6.homework;
public class ShapeTest {
	//3、（* 抽象类 多态）继上题
	//A、将Shape定义为抽象类，Shape中的方法area及girth定义为抽象方法，并增加一个抽象方法draw。
	//B、在子类中实现上面的draw方法，以打印给定的图形。
	//C、创建一个TestShape类，在main方法中创建一个元素类型为Shape，长度为3 的数组，里面有三个不同类型的对象，循环打印这三个对象图形。
	//D、在TestShape中，写一个方法，接受一个图形（Shape）作为参数，并打印出该图形。
	static void draw(Shape s) {
		s.draw();
	}
	
	
	public static void main(String[] args) {
		//C、创建一个TestShape类，在main方法中创建一个元素类型为Shape，
		//长度为3 的数组，里面有三个不同类型的对象，循环打印这三个对象图形。
		Shape[] ss = new Shape[3];
		ss[0] = new Circle(10);
		ss[1] = new Rect(1,2);
		ss[2] = new Square(2);
		for(Shape s:ss) {
			s.draw();
		}
		
		draw(new Circle(10));
		draw(new Rect(1,2));
		draw(new Square(2));
		
		

	}

}
//2. *（封装、继承）有以下几个类，根据下面的继承关系，用Java 代码实现。

//A、 Circle 类（圆形），属性：半径；方法：求周长、求面积
//B、 Rect 类（矩形），属性：长、宽；方法：求周长、求面积
//C、 Square 类（正方形），属性：边长；方法：求周长、求面积
//提示：
//1） 这三个类均具有求周长和面积的方法；
//2） 正方形是特殊的矩形；

abstract class Shape {
	public abstract double area();//求面积
	public abstract double girth();//求周长
	public abstract void draw();//画出图形
}

class Rect  extends Shape {
	private double length;
	private double width;
	public Rect(double length,double width) {
		this.length = length;
		this.width = width;
	}
	public double calculatePerimeter() {
		return 2 * (this.length + this.width);
	}
	@Override
	public double area() {
		// TODO Auto-generated method stub
		return this.length * this.width;
	}
	@Override
	public double girth() {
		// TODO Auto-generated method stub
		return 2 * (this.length + this.width);
	}
	@Override
	public void draw() {
		System.out.println("draw Rect");
	}
}

class Circle extends Shape {
	private double radius;
	public Circle(double radius) {
		this.radius = radius;
	}
	
	@Override
	public double area() {
		// TODO Auto-generated method stub
		return  Math.PI * this.radius * this.radius;
	}
	@Override
	public double girth() {
		// TODO Auto-generated method stub
		return 2 * Math.PI * this.radius;
	}
	public void draw() {
		System.out.println("draw Circle");
	}

}
//正方形是特殊的矩形；宽与高相同
class Square extends Rect {
	//private double width;
	public Square(double width) {
		super(width,width);
	}
}






