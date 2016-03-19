package gem.day9;
//自定义异常
//编译期异常，用法与系统定义的异常一样
public class MyException extends Exception{
	//定义两个构造方法
	public MyException() {
	}
	
	public MyException(String message){
		super(message);
	}

	public static void main(String[] args) throws MyException {
		throw new MyException();
	}
}
//自定义运行异常,定义一个类继承RuntimeExcepion
class MyRuntimeException extends RuntimeException {
	public MyRuntimeException() {
		
	}
	public MyRuntimeException(String message) {
		super(message);
	}
	
}