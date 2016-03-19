package gem.day7.homework;
//编写一个表示颜色的枚举类，要求如下：
//a)提供一个char类型的私有属性color
//b)提供一个有参的私有构造方法，在构造方法中为color属性赋值
//c)为枚举类提供三个枚举值：红，绿，黄
//d)提供toString方法，返回相应颜色的字符串
//e)编写一个测试类，提供一个静态的driver方法，形参为颜色的枚举类型，
//根据传进来的枚举值打印不同的语句“红灯停”，“绿灯行”，“黄灯慢行”。
//在main方法中调用driver方法，传不同的枚举值观察打印的结果。
public class EnumColorTest {
	public static void driver(Color c) {
		if(c == Color.RED) System.out.println("红灯停");
		if(c == Color.GREEN) System.out.println("绿灯行");
		if(c == Color.YELLOW) System.out.println("黄灯慢行");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		driver(Color.RED);
	}

}
enum Color {
	RED('r'),GREEN('g'),YELLOW('y');
	private char c;
	private Color(char c) {
		this.c = c;
	}
	@Override
	public String toString() {
		return c=='r'?"红色":
				((c=='g')?"绿色":"黄色");
	}
	
}