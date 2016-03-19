package gem.day18;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class SimpleHibernate {

	//获得通用的update SQL语句
	// update Student set sno=值
	//save  insert into student() values()
	public static String updateSQL(Object entity) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
		String sql = "update  ";
		//先获得Class对象
		Class c = entity.getClass();
		//判断，类前有没有标注Table，有则用该标注定义的名字、
		//否则，用类名
		Table tanno = (Table) c.getAnnotation(Table.class);
		if(tanno==null) {//无标注
			sql += c.getSimpleName(); 
		}else{//加标注
			sql += tanno.value();
		}//表名
		sql += " set ";
		//要修改的列名,处理每个getXxx方法，
		//如果方法是getId，则不需要处理
		//其它的getXxx方法，要获得列名，
		//如果方法前有Column标注话，用这个标注定义的列名,否则就是getXxx中的Xxx
		//还获得要修改的值 value  组装set语句，列名=值
		//如果是字符串，值 ==》 ‘值’
		Method[] methods = c.getDeclaredMethods();
		for(Method m :methods) {
			if(m.getName().startsWith("get") ) {
				if(!m.getName().equals("getId")) {
					//要得列名,先获得该方法前的标注Column对象
					Column canno = (Column)m.getAnnotation(Column.class);
					String colName = "";
					if(canno==null) {//方法前没有使用Column标注
						colName = m.getName().substring(3);
					}else{
						colName = canno.value();
					}
					//要获得修改的值
					Object value = m.invoke(entity);
					//组装，判断对象是否是字符串
					if(value instanceof String) {
						sql += colName +"= '" + value +"',";
					}else {
						sql += colName + "=" + value +",";
					}
				}
			}
		}
		//去掉,
		sql = sql.substring(0,sql.length()-1);
		//加一个where子句
		sql += " where  ";
		//直接获得getId方法，
		Method mid = c.getDeclaredMethod("getId");
		//用Column标注,获得id对应的列名，并且获得对象
		Column canno = mid.getAnnotation(Column.class);
		String idName = ""; 
		if(canno==null) {
			idName = "id";
		}else {
			idName = canno.value();
		}
		//获得id对象的傎
		Object id = mid.invoke(entity);
		sql += idName + " = " + id;
		return sql;
	}
	//GeericDao

	public static void main(String[] args) throws IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException, SecurityException {
//		Student student = new Student(100,"tom",20,"1120");
//		String sql =  updateSQL(student);
//		System.out.println(sql);
		//Student  stu
		//id       id         主键
		//name     loginname
		//age      age
		//sno      studentNumer
		//update stu set loginname='tom',
		//           age = 20,
		//           studentNumber = '1120'
		//where id=100
		
//		Employee e = new Employee(10,"alice",8000);
//		sql =  updateSQL(e);
//		System.out.println(sql);
		//Employee       emp
		//id             eid
		//eno            eno
		//salary         esalary
		//update  emp  set ename='alice',
		//             esalary=8000
		// where eid=10
		
		//
		
	}

}
