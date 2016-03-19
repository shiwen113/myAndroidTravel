package gem.day4;

public class PersonTest {

	public static void main(String[] args) {
		Person p = new Person("小二",18);
		System.out.println(p.getName()+","+p.getAge());
		Person p3 = new Person();
		System.out.println(p3.getName()+","+p3.getAge());
		{//代码块
			String name = "";
		}
		//System.out.println(name);
		
	
	}

}
