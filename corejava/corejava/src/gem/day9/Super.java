package gem.day9;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Super {
	public Number m() throws IOException {
		//Number 是父类 ，Integer是子类
		return new Integer(1);
	}
	

}

class Sub extends Super {
	//子类的方法的返回类型要小（返回类型可以是父类方法的返回类型或其子类）
	//子类的方法可以不声明父类方法声明的异常，
	//子类的方法声明抛出的异常可以比父类方法声明抛出的异常（小）
	@Override
	public Integer m() throws FileNotFoundException  {
		return new Integer(2);
	}
}