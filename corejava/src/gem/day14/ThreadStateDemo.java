package gem.day14;

public class ThreadStateDemo {

	public static void main(String[] args) throws InterruptedException {
		// 说明线程的状态
		//创建线程对象
		Thread t = new Thread(new PrintLetterTask());
		System.out.println("1:"+t.getState());   //NEW 初始状态
		//
		t.start();
		System.out.println("2:"+t.getState());   //可运行状态
		Thread.sleep(3000);		//t:end 
		System.out.println("5:"+t.getState());  

	}

}

//线程，类实现Runnable接口
class PrintLetterTask implements Runnable {
	@Override
	public void run() {
		//当前线程的状态
		System.out.println("3:"+Thread.currentThread().getState());
		for(char ch='A';ch<='Z';ch++) {
			System.out.print(ch);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//当前线程的状态
		System.out.println("4:"+Thread.currentThread().getState());
	}
}
