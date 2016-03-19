package gem.day7;
//Season:类已继承Enum， extends Enum
//只能有固定数目的对象  
public enum Season   {
	//SPRING:变量，对象，类型是Season
	//SPRIN,SUMMER,AUTUMN,WINTER是Seanson的类型对象
	//public static final
	SPRING("春天") {
		@Override
		public void info() {
			System.out.println("春暖花开，正好踏青");
		}
		
	},
	SUMMER("夏天") {

		@Override
		public void info() {
			System.out.println("夏日炎炎，适合游泳");
		}
		
	},
	AUTUMN("秋天") {

		@Override
		public void info() {
			// TODO Auto-generated method stub
			
		}
		
	},
	WINTER("冬天") {

		@Override
		public void info() {
			// TODO Auto-generated method stub
			
		}
		
	};
	//成员变量
	String name;
	//默认的访问控制符是private
	Season(String name){
		this.name = name;
	}
	//可以写方法
	public String getName() {
		return name;
	}
	//定义一个抽象方法
	public  abstract void info();
}









//模拟一个enum
class Season1 {
	public static final Season1 SPRING = new Season1();
	public static final Season1 SUMMER = new Season1();
	
	private Season1(){};
}


enum Singleton {
	SINGLETON;
}