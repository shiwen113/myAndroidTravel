package gem.day15;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

//使用JDK1.5新特性，写生产者-消费者
public class PrintAlpah1 {
	//替换synchronized的Lock
	Lock lock = new ReentrantLock();
	//获得Condition对象，替换wait与notifyAll方法
	Condition cond = lock.newCondition();
	
	//标志位
	boolean flag = true;
	//输出大写字母
	public void printUpper(char ch) {
		//加锁
		try {
			lock.lock();
			//轮到我，没有，等
			while(!flag) {
				try {
					//Thread.currentThread对象在等
					cond.await();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			//输出字符
			System.out.print(ch);
			flag = !flag;	
			cond.signalAll();
			//到我，干活，换醒
		}finally {
			lock.unlock();
		}
	}
	
	
	
	
	
	
	
}
