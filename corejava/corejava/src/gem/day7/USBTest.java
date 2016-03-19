package gem.day7;

public class USBTest {
	//需要一个USB接口的实现
	public static class HuweiUSB implements USB {
		@Override
		public void read() {
			System.out.println("Huwei read");
		}

		@Override
		public void write() {
			System.out.println("Huwei write");
			
		}
	}
	public static void method(USB usb) {
		
	}
	
	
	
	public static void main(String[] args) {
		USB usb = new HuweiUSB();
		//匿名内部类:实现一个接口，或是继承一个抽象类
		//定义了一个类+创建了一个对象
		USB usb1 = new USB(){

			@Override
			public void read() {
				System.out.println("Huwei read");
				
			}
			@Override
			public void write() {
				System.out.println("Huwei write");
				
			}
		};
		//调用方法method，要传一个USB对象
		USBTest.method(new USB(){
			@Override
			public void read() {
				// TODO Auto-generated method stub
			}

			@Override
			public void write() {
				// TODO Auto-generated method stub
				
			}
			
			
		});
		
		
		
	}

}
