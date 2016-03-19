package gem.day9;
//范型类，定义一个范型变量<T>
public class GenericClassDemo<T> {
	//成员变量
	T t;
	public GenericClassDemo(T t) {
		this.t = t;
	}
	
	//方法get 这个t
	public T get() {
		return t;
	}
	//set方法
	public void set(T t) {
		this.t = t;
	}

	public static void main(String[] args) {
		//int ==> Integer
		GenericClassDemo<String> gcd = 
				new GenericClassDemo<String>("java");
		String s = gcd.get();
		//T:Girl
		GenericClassDemo<Girl> gcd1 = new
				GenericClassDemo<Girl>(new Girl("alice"));
		

	}

}
