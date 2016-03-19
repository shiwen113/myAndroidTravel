package gem.day6;

public class PeopleTest {

	public static void main(String[] args) {
		// 测试watch方法
		People p = new People();
		Dog d = new Dog();
		p.watch(d);	//看Dog对象的表演
		Dolphin dp = new Dolphin();
		p.watch(dp);	//看Dolphin对象的表演
		
		Parrot parrot = new Parrot();
		p.watch(parrot);
		//定义一个数组，动物类型的数组，放三个对象,Dog,Dolphin,Parrot
		//遍历这个数组，调用其的show方法
		Animal[] as = new Animal[]{
				new Dog(),new Dolphin(),new Parrot()
		};
		as[0] = new Dog();
		for(Animal a:as) {
			a.show();
		}
		
	}

}
