package gem.day6;

public class USBTest {

	public static void main(String[] args) {
		//接口用作类型，用在等号的左边,不能用在等号的右边
		USB usb = new HuaweiUSB();
		usb.read();
		usb.write();
		//基于继承（抽象类的多态）
		//基于接口
	}

}
