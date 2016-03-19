package gem.day5;

public class PrivilegeDemo {
	int money = 100000;
	
	public int getMoney() {
		return money;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
}

class PrivilegeTest1 {
	public int getMoney() {
		PrivilegeDemo pd = new PrivilegeDemo();
		int m = pd.money;
		return 100;
	}
}
