package gem.day17;
//代理人吃饭,静态代理,23设计模式，
public class PersonProxy {
	//要代理的对象
	Person p = new PersonImpl();
	
	//提供一个和eating方法一样签名的方法
	public void eating() {
		//
		System.out.println("喝口水....");
		//p.eating();
		System.out.println("抽支烟.....");
		//加加价
	}
	
	

	public static void main(String[] args) {
		PersonProxy proxy = new PersonProxy();
		proxy.eating();
	}

}
