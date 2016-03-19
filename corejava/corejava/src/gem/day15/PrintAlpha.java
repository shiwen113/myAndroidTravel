package gem.day15;

public class PrintAlpha {
	// 定义一个标志变量，轮到谁
	boolean flag = false; // 轮到输出大写

	// 两种方法
	// 1、传统方法：synchronized wait notifyAll
	// 2、新方法: Lock Condition await signalAll
	// 输出大写的字符，锁加在this对象上
	public synchronized void printUpper(char ch) {
		// 是否是轮到我了,while
		while (!flag) {//!flag ==>true  while(true)
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
		System.out.print(ch);
		// 反标志位
		flag = !flag;
		// 通知在this对象上等待的线程对象，去争取this对象的锁
		this.notifyAll();

	}

	// 输出一个小写的字符
	public synchronized void printLower(char ch) {
		// 是否是轮到我了
		// 没有轮到我，等
		// 如果轮到我，打
		// 是否是轮到我了,while
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
		//
		System.out.print(ch);
		// 反标志位
		flag = !flag;
		// 通知在this对象上等待的线程对象，去争取this对象的锁
		this.notifyAll();
	}
}
