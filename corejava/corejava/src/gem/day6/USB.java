package gem.day6;
//定义了一个名字为USB的接口
public interface USB {
	//可以定义静态常量,默认的：公开的、静态、常量
	public final static String s = "Java";
	//方法：公开的抽象的方法,public abstract
	public abstract void read();	//read数据
	//默认的不是package,public 
	void write();					//写数据
	
}
