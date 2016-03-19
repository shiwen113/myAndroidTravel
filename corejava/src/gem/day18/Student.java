package gem.day18;
@Table("tbl_student")
@MyAnnotation(scores={1,2})
/**
 * 学生类Student,name,pwd
 * 表： tbl_student:name,password
 * @author zjwu
 *
 */


public class Student {
	String name;
	//@Column("name")  --列的名字
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	//@Column("password") 
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	String pwd;
	
	//属性==》列名
	
}
