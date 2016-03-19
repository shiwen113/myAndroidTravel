package gem.day4;
//实体类
public class Person {
	private String name;	//名字
	private int age;		//年龄
	//写一个有参数的构造方法
	//构造方法可以有参数
	public  Person(String name,int age) {
		//就近原则,this表示当前对象
		this.name = name;
		this.age = age;
	}
	public Person() {
		//调用带参数的构造方法
		this("小三",20);
		//this.name = "小三";
		//this.age = 20;
	}
	//get/set方法
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	
	
	//有一个不带参数的构造方法
	//public Person() {}
	
	
}
