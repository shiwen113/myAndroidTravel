package gem.day7;

public class SeasonTest {

	public static void main(String[] args) {
		//Season s = new Season();
		Season s = Season.SUMMER;
		System.out.println(s.getName());
		//枚举类的其它方法：
		int i = s.ordinal();
		System.out.println(i);
		//seasons放在Season对象，4个
		Season[] seasons = Season.values();
		for(Season sea:seasons) {
			System.out.print(sea.getName()+",");
		}
		//switch语句中可以用枚举类型型
		switch(s) {
		case SPRING:
			s.info();
			break;
		case SUMMER:
			s.info();
			break;
		case AUTUMN:
			s.info();
			break;
		case WINTER:
			s.info();
			break;
		}
		
		
		
		
	}

}
