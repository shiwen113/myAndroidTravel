package gem.day6;

public class People {
	//方法：人在观看动物表演
	public void watch(Animal animal) {
		animal.show();
	}
	
	//传统
//	public void watch(Object obj) {
//		//不同类型动物的表演
//		if(obj instanceof Dog) {
//			Dog dog = (Dog) obj;
//			dog.show();
//		}else if(obj instanceof Dolphin) {
//			Dolphin dp = (Dolphin) obj;
//			dp.show();
//		}else {
//			//.......
//		}
//		
//	}
	
	
	public void watchDog(Dog dog) {
		dog.show();
	}
	
	public void watchDolphin(Dolphin dolphin) {
		dolphin.show();
	}
	
	//获得一个动物对象,工厂factory method方法的设计模式
	//i=1:返回Dog对象
	//i=2：返回Dolphin对象
	//i=3:返回parrot对象
	//注意：方法的返回类型与return的值要一致
	public static Animal getAinmal(int i) {
		if(i==1) {
			//Dog是Animal子类
			return new Dog();
		}else if(i==2) {
			return new Dolphin();
		}else if(i==3) {
			return new Parrot();
		}
		//抛出一个异常
		return null;
	}
	
	
	
	
}
