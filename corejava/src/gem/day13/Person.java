package gem.day13;

import java.io.Serializable;

public class Person implements Serializable{
	private String name;
	private transient String pwd;//不序列化
	public Person(String name,String pwd) {
		this.name = name;
		this.pwd = pwd;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	
}
