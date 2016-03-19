package gem.day9.homework;
//3、（*自定义异常 综合）
//1）写一个帐户Account类，有属性姓名及余额。
//2）自定义一个异常类，定义一个带字符串参数的构造方法。
//3）写一个ATM类，实现一个取钱方法，传入Account对象及所取金额，如果帐户余额不够，
//则抛出上面的自定义异常，否则显示所需的钱，并修改帐户余额。
//4）测试ATM类上的取钱方法。




public class ATM {
	public static void drawMoney(Account account,int money) throws MyException {
		if(account.getBalance() < money) throw new MyException("余额不够");
		System.out.println("取了"+money);
		account.setBalance(account.getBalance() - money);
	}
	
	public static void main(String[] args) throws MyException {
		Account a = new Account("tom",10000);
		drawMoney(a,6000);
		drawMoney(a,6000);
	}
}


class Account {
	private String name;
	private int balance;	//余额
	public Account(String name,int balance) {
		this.name = name;
		this.balance = balance;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBalance() {
		return balance;
	}
	public void setBalance(int balance) {
		this.balance = balance;
	}
	
}

class MyException extends Exception {
	public MyException() {
		
	}
	public MyException(String message) {
		super(message);
	}
}
