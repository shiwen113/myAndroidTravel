package gem.day5;
//Super是Sub的父类
public class Sub extends Super  {
	static {
		System.out.println("in static sub ");
	}
	{
		System.out.println("in sub");
	}
}
