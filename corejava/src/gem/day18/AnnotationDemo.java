package gem.day18;

import java.util.ArrayList;
import java.util.List;

//取消警告信息
@SuppressWarnings({ "unused", "rawtypes" })

public class AnnotationDemo {
	
	//方法，说明过时
	@Deprecated
	public void testDepreCated() {
		
	}
	
	//
	
	public void testOverride() {
		
		List students = new ArrayList();
	}

	public static void main(String[] args) {
	
		List students = new ArrayList();
		
		List teachers = new ArrayList();
		
		new AnnotationDemo().testDepreCated();
		

	}

}
//写一个AnnotationDemo的子类
class SubAnnotationDemo extends AnnotationDemo {
	//重写父类的方法testOverride
	//注解@Override说时这是一个重写的方法
	//Eclipse用
	@Override
	public void testOverride() {
		
		
	}
}