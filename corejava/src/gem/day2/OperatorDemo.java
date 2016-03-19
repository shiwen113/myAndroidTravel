package gem.day2;

public class OperatorDemo {

	public static void main(String[] args) {
		//先++与后++的区别,有付作用的运算符
		int i = 10;
		int j = ++i;	//i=i+1;j=i
		System.out.println(j+","+i);
		j = i++;		//j = i; i=i+1
		System.out.println(j+","+i);
		//栈  堆
		i = i + 1;
		
	}

}
