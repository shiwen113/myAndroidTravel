package gem.day6;
//类与接口的关系，类实现接口，implements,多接口
//意义：实现接口定义的方法
//一个接口可以被多个类实现
//
public  class HuaweiUSB implements USB {

	@Override
	public void read() {
		System.out.println("读数据!!");
		
	}
	//

	@Override
	public void write() {
		// TODO Auto-generated method stub
		
	}

}
