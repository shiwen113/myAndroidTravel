package gem.day15;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadPoolDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 示例说明线程池的用法
		//创建了一个固定数目的线程池对象
		ExecutorService service = Executors.newFixedThreadPool(6);
		//提交任务，Runnable，Callable
		//提交Runnable任务
		service.submit(new Runnable() {
			@Override
			public void run() {
				for(int i=0;i<10;i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
				
			}});
		//提交一个Callable任务
		
		Future<Integer> f = service.submit(new Callable<Integer>(){

			@Override
			public Integer call() throws Exception {
				for(int i=0;i<10;i++) {
					System.out.println(Thread.currentThread().getName()+":"+i);
				}
				return 100;
			}
		});
		//获得call方法的返回值
		Integer i = f.get();
		System.out.println("i="+i);
		
		
		
		//关闭池
		service.shutdown();
		
		
	}

}
