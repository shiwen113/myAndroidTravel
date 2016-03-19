package gem.day11;

import java.util.PriorityQueue;
import java.util.Queue;

public class PriorityQueueDemo {

	public static void main(String[] args) {
		// 说明带优先级的队列
		Queue<String> q = new PriorityQueue<String>();
		//按自然顺序排，String类上实现了Comparable接口中的方法
		//compareTo方法
		q.add("java");
		q.add("c++");
		q.add("ios");
		//注意与普通队列的区别
		System.out.println(q);
		
		//加入学生对象,自然排序，自定义排序
		Queue<Student> sq = new PriorityQueue<Student>();
		sq.add(new Student("110",new java.util.Date(),100));
		System.out.println(sq);
		sq.add(new Student("111",new java.util.Date(),99));
		System.out.println(sq);
	}

}
