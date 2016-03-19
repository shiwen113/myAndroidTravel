package gem.day15;

public class ThreadLocalDemo {

	public static void main(String[] args) {
		// 说明ThreadLocal变量的含义
		// 创建两个线程，调用inc方法，在线程中观察i的状况
		Value v = new Value();
		// 一个线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					//v.inc();
					v.inc2();//对ThreadLocal变量t中的值加一
					System.out.println(Thread.currentThread().getName() + ":"
							+ v.t.get());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();
		// 另一个线程
		new Thread(new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 10; i++) {
					//v.inc();
					v.inc2();
					System.out.println(Thread.currentThread().getName() + ":"
							+ v.t.get());
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}

		}).start();
		//这两个线程看到是不是同一个v.i???

	}

}
