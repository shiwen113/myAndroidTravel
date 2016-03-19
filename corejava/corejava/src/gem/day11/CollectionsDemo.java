package gem.day11;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

public class CollectionsDemo {

	public static void main(String[] args) throws ParseException {
		//创建一个List对象，放Student
		List<Student> students = new ArrayList<Student>();
		//String == Date
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//在students中加入几个学生对象
		students.add(new Student("110",sdf.parse("1990-1-1"),100));
		students.add(new Student("111",sdf.parse("1992-11-11"),90));
		students.add(new Student("110",sdf.parse("1991-12-12"),95));
		students.add(new Student("112",sdf.parse("1992-10-10"),88));
		System.out.println(students);
		//Collections方法:
		//reverse：反转
		Collections.reverse(students);
		System.out.println(students);
		//shuffle：乱序
		Collections.shuffle(students);
		System.out.println(students);
		//排序:sort方法
		Collections.sort(students);
		System.out.println(students);
		//包装线程不安全的集合对象为线程安全的集合对象
		List<Student> sstudents = Collections.synchronizedList(students);

	}

}
