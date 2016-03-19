package gem.day4;
//定义类：关键字class Dog是类名
//{前的部分：类的声明
//class修饰符：访问控制符:public pacakge(默认)
public class Dog {
	//类体:class body
	//设计：   实现
	//属性，成员变量,表示对象的状态,
	//语法格式：[修饰符]  类型  属性名
	//访问控制符
	String name ;
	//方法：表示对象的行为
	//构造方法：初始化对象的状态
	
	//狗在叫
	//方法的签名  修饰符  方法三要素：返回类型 方法名 形参
	//修饰符：访问控制符:public package protected private 
	public  void yaff() {
		//方法体，方法实现
		System.out.println("汪汪，汪汪汪，。。。。");
		return  ;
	}
	public void Dog() {}
	
	//加法，方法名：功能,加两个整数
//	public int   add(int a,int y) {
//		int z  = a+y;
//		return z;	//值或一个对象
//		//功记：80%，不要写System.out,(仅是测试代码)
//		//System.out.println(x+"+"+y +"=" + z);
//	}
	//可以加两个浮点数
	public float add(float x,float y) {
		return x + y;
	}
	//可以加三个整数
//	public int add(int x,int y,int z) {
//		return x + y + z;
//	}  
	//可变参数
	public int add(int ... x) {
		//x是一个int类型的数组
		int sum = 0;
		for(int i=0;i<x.length;i++) {
			sum += x[i];
		}
		return sum;
	}
	
	
	
	
	public Dog newInsatnce() {
		return new Dog();
	}
	
	
	
	
}
class Cat {}