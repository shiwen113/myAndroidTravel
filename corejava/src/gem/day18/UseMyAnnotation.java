package gem.day18;
//说明使用自定义的Annotation

//@MyAnnotation
public class UseMyAnnotation {
	//@MyAnnotation
	private int score = 100;	//成员变量
	
	//传值，与注解的返回类型之间的关系
	@MyAnnotation(value1="android",scores={10,20,30})
	public void test() {
		
	}

	public static void main(String[] args) {
		//@MyAnnotation
		String s = "java";

	}

}
