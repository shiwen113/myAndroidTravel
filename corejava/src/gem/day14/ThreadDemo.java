package gem.day14;

public class ThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		// 说明Thread的常见方法
		//获得当前线程对象
		Thread t = Thread.currentThread();
		//获得这个线程的相关信息，id,name,....
		long id = t.getId();
		String name = t.getName();
		System.out.println("id="+id+",name="+name);
		//当前线程sleep
		Thread.sleep(3000);
		System.out.println("=======end===========");
	}

}
