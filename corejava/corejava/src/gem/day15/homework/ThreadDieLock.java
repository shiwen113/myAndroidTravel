package gem.day15.homework;

public class ThreadDieLock implements Runnable{

	private boolean flag = false;
	Object a = new Object();
	Object b = new Object();
	
	@Override
	public void run() {
		if(!flag){
			synchronized (a){
				System.out.println("已锁定a，休息0.3秒去锁定b");
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			    synchronized (b) {
                    System.out.println("b已锁定");
                }
			}
		}
		
		
		if(flag){
			synchronized (b) {
				System.out.println("已锁定b，休息0.3秒去锁定a");
				try {
					Thread.sleep(300);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				synchronized (a) {
					System.out.println("a已锁定");
				}
			}
		}
		
	}
	
	
	public static void main(String[] args) {
		ThreadDieLock thread1 = new ThreadDieLock();
		ThreadDieLock thread2 = new ThreadDieLock();
		thread1.flag = true;
		thread2.flag = false;
		 new Thread(thread1).start();
		 new Thread(thread2).start();

	}
}
