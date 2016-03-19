package gem.day18;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
//@interface：定义Annotation,注解与@关联     数组[]  范型<> 
//外面能写什么:何处
//说明这个标注在使用时，可以用在什么地方(变量前、方法前、类的前面。。。)
@Target({ElementType.METHOD,ElementType.TYPE,
	ElementType.FIELD,ElementType.LOCAL_VARIABLE,ElementType.PACKAGE})
//何时可用：类上用的注解保留到何时
//只保留在源代码中，只保留中类文件中，运行时
@Retention(value=RetentionPolicy.RUNTIME)

public @interface MyAnnotation {
	//能写什么：“方法”:   public + abstract
	//返回类型：基本数据类型(8个)、String、Class,及其数组
	//不可以有参数
	//可以有缺省值：值与前面类型要一致
	String value1() default "java";
	int[]  scores();
}

interface MyInterface {
	//public + abstract
	Object method(int i,String s);
}