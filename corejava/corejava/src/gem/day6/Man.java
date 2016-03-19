package gem.day6;
//接口可以多继承
public interface Man extends Teacher,Husband,Friend {
	//也可以增加方法
	public void walk();
}
