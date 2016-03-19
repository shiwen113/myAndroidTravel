package gem.day14;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
public class CallableDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		// 使用Callable、FutureTask来创建线程对象
		// 2、创建一个FutureTask对象
		FutureTask<Integer> task = new FutureTask<Integer>(new Computer());
		//3、FutureTask是实现了Runnable接口,以该对象创建一个Thread对象
		new Thread(task).start();
		//获得运算结果
		Integer i = task.get();//阻塞方法
		System.out.println("i="+i);
		for(int j=1;j<10;j++) {
			Thread.sleep(100);
			System.out.print((char)(j+'a'));
		}
	}

}

//1、写一个类实现Callable接口
class  Computer implements Callable<Integer> {
	@Override
	public Integer call() throws Exception {
		//线程体
		//1-20的和
		int sum = 0;
		for(int i=1;i<=20;i++) {
			sum += i;
			System.out.print(i);
			//思考一下
			Thread.sleep(100);
		}
		return sum;
	}
}