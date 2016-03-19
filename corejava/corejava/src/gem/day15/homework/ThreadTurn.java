package gem.day15.homework;

public class ThreadTurn {
	boolean flag = false;
	
	public synchronized void print1(){
		while (!flag) {
			// 没有轮到我，等
			try {
				// 在哪个对象(this)上等,当前线程Thread.currentThread()，进入this对象等待队列
				// 释放所有的锁
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 如果轮到我，打
		for(int j =1;j<=10;j++){
			System.out.println(Thread.currentThread().getName()+"输出"+j);
		}
		// 反标志位
		flag = !flag;
		// 通知在this对象上等待的线程对象，去争取this对象的锁
		this.notifyAll();

	}
	public synchronized void print2(){
		while (flag) {
			// 没有轮到我，等
			try {
				// 在哪个对象(this)上等,当前线程Thread.currentThread()，进入this对象等待队列
				// 释放所有的锁
				this.wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		// 如果轮到我，打
		for(int j =1;j<=20;j++){
			System.out.println(Thread.currentThread().getName()+"输出"+j);
		}
		// 反标志位
		flag = !flag;
		// 通知在this对象上等待的线程对象，去争取this对象的锁
		this.notifyAll();
	}
	public static void main(String[] args) {
		final ThreadTurn print = new ThreadTurn();
		Thread thread1 = new Thread(new Runnable() {
			
			@Override
			public void run() {
			for(int i = 0;i<20;i++){
				print.print1();
			}
				
			}
		},"线程1");
		
		Thread thread2 = new Thread(new Runnable() {
			
			@Override
			public void run() {
				for(int i = 0;i<20;i++){
					print.print2();
				}
				
			}
		},"线程2");
		
		thread1.start();
		thread2.start();
		
		

	}

}
