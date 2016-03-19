package gem.day6;

public class ShapeTest {

	public static void main(String[] args) {
		//父类  类型名 = new 子类();
		Shape s = new Rect(10,6);
		//s = new Circle();
		double a = s.area();
		System.out.println("面积:"+a);
		
		s = new Square(100);
		a = s.area();	//长方形的面积
		a = s.area();	//园的面积
		//
		
		
		System.out.println("面积："+a);
	}

}
