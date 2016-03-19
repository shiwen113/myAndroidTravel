package gem.day11;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

public class MapNullTest {

	public static void main(String[] args) {
		//HashMap中key,value可以是null
		Map<String,String> m1 = new HashMap<String,String>();
		m1.put(null, "Java");
		m1.put("tom", null);
		System.out.println(m1.size());
		System.out.println(m1);
		//HashTable中不可以
		Map<String,String> m2 = new Hashtable<String,String>();
		m2.put("tom",null);
		
	}

}
