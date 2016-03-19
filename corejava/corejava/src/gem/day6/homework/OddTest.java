package gem.day6.homework;


public class OddTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Odd o1 = new Odd(3);
		OddPrint.print(o1);
		Odd o2 = new Odd(4);
		OddPrint.print(o2);

	}

}
//判断一个整型数是奇数还是偶数，要求如下：
//a)编写一个接口OddInterface，提供两个方法：isOddNumber方法返回一个boolean值，判断一个数是否是奇数，
//getOdd方法返回一个整型数，返回被判断的数值。
//b)编写一个类Odd 实现OddInterface，提供一个int类型的odd属性表示要被判断的数，提供一个有参的构造方法
//；实现OddInterface中定义的方法。
//c)编写一个OddPrint类，打印整型数是奇数还是偶数。提供一个静态的print方法，
//方法的形参是OddInterface型的参数
//d)编写OddTest测试类，在main方法中创建一个Odd对象，并调用OddPrint的静态print方法打印判断结果

interface OddInterface {
	public boolean isOddNumber();
	public int getOdd();
}

class Odd implements OddInterface{
	private int data;
	public Odd(int data) {
		this.data = data;
	}
	public boolean isOddNumber() {
		return (this.data%2==0)?false:true;
	}
	public int getOdd() {
		return this.data;
	}
}
class OddPrint {
	static void print(OddInterface odd) {
		if(odd.isOddNumber()) {
			System.out.println(odd.getOdd() + "是奇数");
		}else {
			System.out.println(odd.getOdd() + "是偶数");
		}
	}
}
