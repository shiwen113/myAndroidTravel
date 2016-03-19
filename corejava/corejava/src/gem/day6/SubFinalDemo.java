package gem.day6;
//是FinalDemo的子类
public class SubFinalDemo extends FinalDemo {
	public SubFinalDemo() {
		super("Android");
	}
	//重写父类的方法
	//@Override
	//重载方法
	public int earn(int i) {
		return 1000;
	}
	
	public static void main(String[] args) {
		SubFinalDemo sd = new SubFinalDemo();
		
	}
}
