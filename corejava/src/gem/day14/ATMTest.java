package gem.day14;

public class ATMTest {

	public static void main(String[] args) {
		// 模拟多人（多个线程对象）从同一个帐户中取款
		Account account = new Account();
		new ATM("大哥",account,4000).start();
		new ATM("二哥",account,3000).start();
		new ATM("三哥",account,4000).start();

	}

}
//线程:模拟从帐号中取款
class ATM extends Thread {
	//谁取走的钱
	//从哪个帐户中取走
	private String name;
	private Account account;
	private int money;	//取款的金额
	public ATM(String name,Account account,int money) {
		super(name);
		this.name = name;
		this.account = account;
		this.money = money;
	}
	
	@Override
	public void run() {
		account.getMoney1(money);
		try {
			sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}