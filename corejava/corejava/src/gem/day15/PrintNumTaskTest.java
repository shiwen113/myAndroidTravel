package gem.day15;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.TimeUnit;

public class PrintNumTaskTest {

	public static void main(String[] args) throws InterruptedException {
		// 创建ForkJoinPool对象
		ForkJoinPool pool = new ForkJoinPool(6);
		//提交任务
		//输入1到30
		//看有几个线程，做这一个事情
		pool.submit(new PrintNumTask(1,31));
		//
		pool.awaitTermination(2, TimeUnit.SECONDS);
		//down线程池
		pool.shutdown();
	}

}
