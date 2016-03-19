package gem.day9;

public class GenericMethod {
	//用范型<>方法来写
	public static <T> void swap(T x,T y) {
		T temp = x;
		x = y;
		y = temp;
	}
	
	

	public static void main(String[] args) {
		swap("java","ios");
		swap(10,20);//T:Integer
		//T:Girl
		swap(new Girl("小王"),new Girl("小李"));
	}

}
class Girl {
	String name;
	public Girl(String name) {
		this.name = name;
	}
}