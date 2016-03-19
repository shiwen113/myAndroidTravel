package gem.day9;

import java.io.Closeable;
import java.io.IOException;

public class AutoCloseDemo {

	public static void main(String[] args) throws IOException {
		// JDk7中可以只有try语句块，系统会自动关闭资源
		try(
			//条件是这个资源要实现Closeable接口
			MyCloseable mc = new MyCloseable(); 
		) 
		{
			System.out.println("in try");
		}//系统会自动的关闭资源
//		finally {
//			mc.close();	
//		}

	}

}

class MyCloseable implements Closeable {
	@Override
	public void close() throws IOException {
		System.out.println("close");
		
	}
	
}

