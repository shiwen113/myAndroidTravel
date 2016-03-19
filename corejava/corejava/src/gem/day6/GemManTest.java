package gem.day6;

public class GemManTest {
	
	

	public static void main(String[] args) {
		GemMan man = new GemMan();
		//同一个对象有不同的类型 
		Friend f = new GemMan();
		Husband h = new GemMan();
		Teacher t = new GemMan();
		
		boolean b = man instanceof Friend;
		System.out.println(b);
	}

}


