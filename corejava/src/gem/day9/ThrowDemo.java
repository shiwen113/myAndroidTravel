package gem.day9;

public class ThrowDemo {
	//说明throw关键字的用法及其含义
	public static int method(int i)  {
		//i大于0;
		if(i>0) {
			System.out.println("i="+i);
			return i+100;  //调用方法的人,正常终止
			//System.out.println("ok");
		}
		//i<=0，不会法，抛出一个异常
		//throw 异常对象
		//运行期异常
		RuntimeException rex = new RuntimeException("这是一个运行期异常");
		//编译期异常
		Exception e = new Exception();
		//throw:可以是Throwable的对象
		//throw和return类比
		throw  rex;	//终止程序的运行,catch语句块用
		//System.out.println("adfad");
	}

	public static void main(String[] args) {
		//rex扔给了JVM
		int i = method(-1);
		System.out.println("ok,i="+i);
	}

}
