package gem.day18;

import java.lang.annotation.Annotation;

public class HibernateTool {
	//对任意的实体类
	public static String getTableName(Object entity) {
		//反射
		//获得这个对象entity的类对象 
		Class clazz = entity.getClass();
		//获得这个类上面的类型为Table的Annotation
		Table a = (Table)clazz.getAnnotation(Table.class);
		//获得这个Annotation上的值
		String tableName = a.value();
		//返回这个值
		return tableName;
	}
	//Servlet,Android,Struts,Hibernate,Spring
	//方法：
	//返回成员变量（属性对应的列名）
	public static String getColumnName(Object entity,String attribute) {
		//1、获得类对象
		//2、属性名转换为getXxx方法的名字
		//     name ==>  getName
		//     pwd  ==>  getPwd
		//3、获得这个方法对象Method
		//Method m = clazz.getMethod("");
		//获得方法上的Annotation，与前面一样
		return null;
	}
	
}
