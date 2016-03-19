package gem.day7;

public class ObjectDemo {

	public static void main(String[] args) throws CloneNotSupportedException {
		int[] arr = {1,2,3,4};
		int len = arr.length;
		Object obj = arr;
		//创建复数对象
		Complex c1 = new Complex(10,20);
		Complex c2 = new Complex(10,20);
		//""+c1 ==> "" + c1.toString(),默认的实现是Object上的实现
		System.out.println(c1);
		//equals方法、hashCode方法
		System.out.println(c1 == c2); //false
		//c1.equals方法:Object类上实现的equals方法
		//equals方法的默认实现：==
		//equals比较应该是值的比较，逻辑上的比较
		//重写equals方法，面试题，重写了equasl方法，也要重写hashCode方法
		//相等对象:o1.equals(o2) == true 
		System.out.println(c1.equals(c2));	//false
		//通过clone方法clone出一个对象
		Complex c3 = (Complex) c1.clone();
		System.out.println(c3);    //10+20i

	}

}
