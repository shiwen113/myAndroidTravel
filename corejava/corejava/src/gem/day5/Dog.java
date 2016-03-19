package gem.day5;

public class Dog {
	//
	protected String name = "小白";
	
	public void yaff() {
		System.out.println(name+"汪汪，汪汪汪，。。。。。。");
	}
	public void yaff(int i) {
		System.out.println(name+"汪汪，汪汪汪，。。。。。。");
	}
	//public Dog() {};
	public Dog(String name) {
		this.name = name;
	}
}
