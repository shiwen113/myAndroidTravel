package gem.day14;

public class CreateThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		//启动线程
		//new PrintLetter().start();;
		//new Thread(new PrintNum(),"PrintNum").start();
		//匿名内部类，创建线程对象(重要)
		new Thread("PrintLetter"){
			public void run() {
				//输出当前线程的名字
				System.out.print(Thread.currentThread().getName());
				
				//输出小写字母
				for(char ch='a';ch<='z';ch++) {
					System.out.print(ch);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			};
		}.start();
		//
		new Thread(new Runnable(){
			@Override
			public void run() {
				System.out.print(Thread.currentThread().getName());
				for(int i=1;i<=26;i++) {
					System.out.print(i);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		},"PrintNum").start();
		System.out.print(Thread.currentThread().getName());
		//输出大写的字母
		for(char ch='A';ch<='Z';ch++) {
			System.out.print(ch);
			Thread.sleep(100);
		}
		
		
	}
}

//创建线程中的两种方法，
//写一个继承Thread的类，重写run方法，输出a-z字母
class PrintLetter extends Thread {
	//重写run方法
	@Override
	public void run()  {
		for(char ch='a';ch<='z';ch++) {
			System.out.print(ch);
			try {
				Thread.sleep(600);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
//第二种方法，写一个类实现Runnable接口
class PrintNum implements Runnable {
	@Override
	public void run() {
		for(int i=1;i<=26;i++) {
			System.out.print(i);
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
}

abstract class X {}
class Ximpl extends X {};