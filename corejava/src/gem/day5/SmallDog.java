package gem.day5;
//SmallDog继承Dog
public class SmallDog  extends Dog{
	
	public String getName() {
		//可以访问protected成员		
		return this.name;
	}
	
	//定义一个yaff方法，这个方法就是一个重写方法
	//@Override是Eclipse用，判断重写方法是否正确
	@Override
	public void yaff() {
		//调用父类的yaff方法,指父类对象
		//super.yaff();
		
		System.out.println("哼哼，哼哼哼，。。。。");
	}
	//默认的构造方法
	public  SmallDog() {
		//调用父类的无参的构造方法
		super("小红");
	}
	
	
	
	public static void main(String[] args) {
		//调用SmallDog的yaff方法
		SmallDog sd = new SmallDog();
		sd.yaff();
	}

}
