package gem.day18;

public class HibernateToolTest {

	public static void main(String[] args) {
		
		Student s = new Student();
		//获得Student类对应的表名
		String tableName = HibernateTool.getTableName(s);
		System.out.println("Student类对应的表名是："+tableName);
		Teacher t = new Teacher();
		//获得Teacher类对应的表名
		tableName = HibernateTool.getTableName(t);
		System.out.println("Teacher类对应的表名是:"+tableName);
		
		//测试代码：
		String columnName = HibernateTool.getColumnName(
				s, "name");	//name
		columnName = HibernateTool.getColumnName(
				s, "pwd");	//password
		
	}

}
