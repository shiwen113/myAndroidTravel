package gem.day14;

import java.util.concurrent.locks.ReentrantLock;

//帐户
public class Account {
	//创建一个锁对象
	private final ReentrantLock lock = new ReentrantLock();
	
	int balance = 10000; // 存款

	// 方法：取钱，同步
	// 线程安全类：Vector,HashTable,StringBuffer,String...
	//
	// 线程安全，线程不安全,
	// 哪个对象上加锁，同步方法
	public  void getMoney(int money) {
		//不需同步的代码
		// 同步块
		synchronized (this) {
			//同步块
			if (money <= balance) {

				// 输出：谁取走了我的Money
				System.out.println(Thread.currentThread().getName() + "取走了"
						+ money + ",余额是" + (this.balance - money));
				//
				this.balance = this.balance - money;
			} else {
				System.out.println("............");
			}
		}
		//
	}
	public  void getMoney1(int money) {
		//Lock实现加锁
		lock.lock();
		try {
			//同步块
			if (money <= balance) {

				// 输出：谁取走了我的Money
				System.out.println(Thread.currentThread().getName() + "取走了"
						+ money + ",余额是" + (this.balance - money));
				//
				this.balance = this.balance - money;
			} else {
				System.out.println("............");
			}
		}finally {
			//释放锁
			lock.unlock();
		}
	}
	
	
	//不同步的方法,线程调用这个方法时，需要不需锁？
	public  void method1() {
		getMoney(100);
	}
	
	//synchronized transient implements 
	//能否在静态方法中加synchronized,能
	//哪个对象上加锁：Account.class
	public synchronized static void method() {
		Class c = Account.class;
	}
	
	
	
	

}
