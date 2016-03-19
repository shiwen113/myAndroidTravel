package gem.day4.homework;

public class ComplexTest {

	public static void main(String[] args) {
		//测试代码
		Complex c1 = new Complex(3,5);	//3+5i
		Complex c2 = new Complex(7,8);	//7+8i
		Complex c3 = c1.add(c2);		//10+13i
		System.out.println(c3.getReal()+"+"+
				c3.getIm()+"i");

	}

}
