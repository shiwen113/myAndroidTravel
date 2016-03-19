package gem.day14;

public class JoinDemo {
	static Thread t2;
	public static void main(String[] args) {
		//局部变量==》(全局)变量
		// 示例说明线程join方法
		// t2线程，PrintLetter,输出小写字母
		t2 = new PrintLetter();
		t2.setDaemon(true);//后台
		t2.start();
		
		new Thread(new Thread1()).start();
		
		new Thread(new Thread2(t2)).start();
		
		// 创建自已的线程，匿名类实现
//		new Thread(new Runnable() {
//			@Override
//			public void run() {
//				for (int i = 1; i <= 10; i++) {
//					System.out.print(i);
//					try {
//						if (i == 5) {
//							t2.join();//理解join方法的含义
//						}
//						Thread.sleep(10);
//					} catch (InterruptedException e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//
//			}
//		}).start();

	}
	
	//内部类
	static class  Thread1 implements Runnable {
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				t2.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
//普通类
class Thread2 implements Runnable {
	private Thread t2;
	public Thread2(Thread t2) {
		this.t2 = t2;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			t2.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}

