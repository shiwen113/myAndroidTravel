package gem.day15;

public class PrintAlphaTest {

	public static void main(String[] args) {
		//先两个线程，使用同一个对象PrintAlpha上的
		//printUpper方法输出大写字母
		//另一个线程调用printLower方法输出小写字母
		PrintAlpha print = new PrintAlpha();
		//输出大写字母
		new Thread(new Runnable(){
			@Override
			public void run() {
				//输出26个大写字母
				for(char ch='A';ch<='Z';ch++) {
					print.printUpper(ch);
					try {
						Thread.sleep(200);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		//输出小写字母
		new Thread(new Runnable(){
			@Override
			public void run() {
				//输出26个大写字母
				for(char ch='a';ch<='z';ch++) {
					print.printLower(ch);
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
		}).start();
	}

}
