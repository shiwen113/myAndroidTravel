package gem.day11;

import java.util.LinkedList;
import java.util.Queue;

public class QueueDemo {

	public static void main(String[] args) {
		//说明Queue的用法,创建队列对象
		Queue<String> q = new LinkedList<String>();
		//队列中加入元素
		q.add("java");
		q.offer("c++");
		q.offer("ios");
		System.out.println(q);
		//取队列中的元素.离开这个队列
		String s1 = q.remove();
		//取出队列中的元素，但还保留在队列中
		String s = q.element();		
		String s2 = q.remove();
		String s3 = q.poll();
		System.out.println(s1+","+s+s2+","+s3);
		System.out.println(q);
		//队列中没有元素时会抛出异常
		//String s4 = q.remove();
		//队列中没有元素时，会抛出特殊值null
		String s4 = q.poll();
		System.out.println(s4);
		

	}

}
