package gem.day5;

public class SmallDogTest {

	public static void main(String[] args) {
		//javac 
		//dog：编译期类型  :Dog,能调什么方法
		//    运行期类型: Dog,实际调用什么方法
		Dog dog = new Dog("小白");
		dog.yaff();
		//dog1:编译期类型：Dog
		//     运行期类型是:SmallDog
		//     编译期类型是运行期类型的父类
		Dog dog1 = new SmallDog();
		//难难:
		dog1.yaff();
	}

}
