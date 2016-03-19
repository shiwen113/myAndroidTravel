package gem.day5;

public class DogTest {

	public static void main(String[] args) {
		// 定义了一个Dog类，定义了一个新的类型（引用类型）
		//父类类型 对象名 = new 子类（）；  自动
		//子类类型 变量名 =  （子类类型）  父类类型变量  强制类型转换
		Dog dog = new Dog("小白");
		SmallDog sdog = new SmallDog();
		//向上转型，自动
		dog = sdog;
		Dog dog1 = new SmallDog();
		//SmallDog sdog1 = (SmallDog)new Dog("小白");
		SmallDog sdog1 = (SmallDog) dog1;	//ok
		//instance of 判断一个对象是否某一个类型
		// 对象  instanceof 类型  
		System.out.println((dog1 instanceof Dog));
		System.out.println((dog1 instanceof SmallDog));
		System.out.println((dog1 instanceof Object));
		//一个对象多个不同的身份
	}

}
