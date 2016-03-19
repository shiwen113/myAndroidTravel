package gem.day13.homework;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
/*
 * 4、（**Data 流）设有一个Account类，有属性id，name，password,
 * 要求自动分配id,并且每次加1（模拟自增长），
 * 利用Data 流，完成下面的要求：要求每次启动程序时，
 * id 的自动分配都能在上一次运行的基础上继续。例如，假设有以下代码：
 * 
 */
import java.io.FileOutputStream;
import java.io.IOException;

public class Account {
	private long id;
	private String name;
	private String password;
	public Account() throws IOException {
		//inc.dat
		//第一次时，文件没有，id=100001(初值)，作为这个对象id,加1
		File file = new File("src\\gem\\day13\\homework\\inc.dat");
		if(file.exists()) {//文件已存在
			//Account的id保存，每次创建这个对象
			//从这个文件中取出id作为我这个对象的id
			DataInputStream din = new DataInputStream(
					new FileInputStream(file));
			this.id = din.readLong();
			din.close();
		}else {//文件不存在
			this.id = 100001;
		}
		//id+1保存到这个文件中
		DataOutputStream dout = new DataOutputStream(
				new FileOutputStream(file));
		dout.writeLong(this.id + 1);
		dout.close();
		
		
		
		
	}
	public long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	//get/set方法
	
	
}
