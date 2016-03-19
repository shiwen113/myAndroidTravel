package gem.day13;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;

public class SerializableDemo {

	public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
		//对象的序列化与反序列化
		//String，Date
		//序列化主要使用ObjectOutputStream
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("src\\gem\\day13\\p.dat"));
//		//序列化一个字符串
//		oos.writeObject("i love android");
//		//序列化一个日期对象
//		oos.writeObject(new Date());
//		//关闭流
//		oos.close();
		
		//自定义类，序列化 Person对象
		Person p = new Person("tom","1234");
		oos.writeObject(p);
		oos.close();
		
		//反序列化
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("src\\gem\\day13\\p.dat"));
//		//读出字符串
//		String s = (String)ois.readObject();
//		Date d = (Date) ois.readObject();
//		System.out.println(s+","+d.getTime()+","+new Date().getTime());
//	
		//反序列化人对象
		Person p1 = (Person) ois.readObject();
		System.out.println(p1.getName()+","+p1.getPwd());
		ois.close();
		
		

	}

}
