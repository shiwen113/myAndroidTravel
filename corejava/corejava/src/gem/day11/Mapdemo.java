package gem.day11;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Mapdemo {

	public static void main(String[] args) {
		//说明Map的基本用法
		/*8、（Map）已知某学校的教学课程内容安排如下：
		老师 课程
		Tom CoreJava
		John Oracle
		Susan Oracle
		Jerry JDBC
		Jim Unix
		Kevin JSP
		Lucy JSP
		完成下列要求：
		1） 使用一个Map，以老师的名字作为键，以老师教授的课程名作为值，表示上述课程安排。
		2） 增加了一位新老师Allen 教JDBC
		3） Lucy 改为教CoreJava
		4） 遍历Map，输出所有的老师及老师教授的课程
		5） *利用Map，输出所有教JSP 的老师。
		*/
		//创建一个Map对象，接口：Map，实现类：HashMap
		Map<String,String>  courses = 
				new HashMap<String,String>();
		//加入数据put，修改、删除数据
		courses.put("Tom", "CoreJava");
		courses.put("John", "Oracle");
		courses.put("Susan", "Oracle");
		courses.put("Jerry", "JDBC");
		courses.put("Jim", "Unix");
		courses.put("Kevin", "JSP");
		courses.put("Lucy", "JSP");
		courses.put("Li4", null);
		int size = courses.size();
		System.out.println("size="+size);
		
		//2） 增加了一位新老师Allen 教JDBC
		courses.put("Allen", "JDBC");
		//3） Lucy 改为教CoreJava,修改值也用put方法
		//courses.put("Lucy", "CoreJava");
		System.out.println(courses);
		//取出数据，根据key获得对应的值，
		//根据老师的名字获得所教的课程,问Kevin所教的课程
		String c = courses.get("Kevin");
		System.out.println(c);
		//遍历集合中的数据,主 要有两种方法,
		//方法1:先获得键的集合,对键所在的集合遍历，通过键获得对应的值
		Set<String> teachers = courses.keySet();
	
		for(String t:teachers) {
			//t：键，教师的姓名,取出t对应的值，教的课程
			String course = courses.get(t);
			System.out.println(t+"="+course);
		}		
		//方法2:Map.Entry遍历,这个集合Set中的元素是Map.Entry
		//Map.Entry是一个接口，是key和value的抽象
		
		Set<Map.Entry<String,String>> tc = courses.entrySet();
		for(Map.Entry<String, String> entry :tc) {
			//entry代表Map.Entry对象
			String key = entry.getKey();
			String value = entry.getValue();
			System.out.println(key+","+value);
		}
		// *利用Map，输出所有教JSP 的老师。
		System.out.println("教JSP课程的老师:");
		for(String t:teachers) {
			//获得教师教的课程
			String course = courses.get(t);
			if(course.equals("JSP")) {
				System.out.println(t);
			}
		}
	}
}
