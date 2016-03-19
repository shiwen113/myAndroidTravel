package gem.day7.homework;

public class ArmyTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Tank t1 = new Tank();
		Flighter f1 = new Flighter();
		WarShip w = new WarShip();
		Army army = new Army(3);
		army.addWeapon(t1);
		//army.addWeapon(t1);
		army.addWeapon(f1);
		army.addWeapon(w);
		army.moveAll();
		army.attackAll();
	}

}

//9 
//a、定义一个接口Assaultable(可攻击的)，该接口有一个抽象方法attack()。
// b、定义一个接口Mobile（可移动的）,该接口有一个抽象方法move()。
// c、定义一个抽象类Weapon,实现Assaultable接口和Mobile接口，但并没有给出具体的
// 实现方法。
// d、定义3个类：Tank,Flighter,WarShip都继承自Weapon,分别用不同的方式实现 Weapon 类中的抽象方法。
// e、写一个类Army,代表一支军队，这个类有一个属性是Weapon数组w
// （用来存储该军队所拥有的所有武器）；该类还提供一个构造方法，
// 在构造方法里通过传一个int类型的参数来限定该类所能拥有的最大武器数量,
// 并用这一大小来初始化数组w。该类还提供一个方法addWeapon(Weapon wa),
// 表示把参数wa所代表的武器加入到数组w中。
// 在这个类中还定义两个方法attackAll()和moveAll()，让w数组中的所有武器攻击和移 动。
// f、写一个主方法去测试以上程序。
interface Assaultable {
	void attack();
}

interface Mobile {
	void move();
}

abstract class Weapon implements Assaultable, Mobile {
	public abstract void attack();

	public abstract void move();
}

class Tank extends Weapon {
	public void attack() {
		System.out.println("tank attack");
	}

	public void move() {
		System.out.println("tank move");
	}

}

class Flighter extends Weapon {
	public void attack() {
		System.out.println("Flighter attack");
	}

	public void move() {
		System.out.println("Flighter move");
	}

}

class WarShip extends Weapon {
	public void attack() {
		System.out.println("WarShip attack");
	}

	public void move() {
		System.out.println("WarShip move");
	}
}

class Army {
	private int max;
	private int curr = 0;
	private Weapon w[] = null;

	public Army(int max) {
		this.max = max;
		w = new Weapon[max];
	}

	public void addWeapon(Weapon wa) {
		if (curr >= max)
			throw new RuntimeException();
		w[curr++] = wa;
	}

	public void attackAll() {
		for(int i=0;i<curr;i++) {
			w[i].attack();
		}
	}

	public void moveAll() {
		for(int i=0;i<curr;i++) {
			w[i].move();
		}

	}

}
