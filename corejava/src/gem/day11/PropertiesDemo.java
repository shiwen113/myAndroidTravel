package gem.day11;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

public class PropertiesDemo {

	public static void main(String[] args) throws IOException {
		//说明Properties的用法
		//与一个属性文件（配置文件.properties），联合使用
		//属性文件中写的是：key(name)=value
		//db.properties文件与一个Porperty对象关联
		
		//创建Properties对象
		Properties prop = new Properties();
		//与文件关联，与文件对象的输入流关联
		//这里的参数是要关联的文件名,要注意不要这样：new FileInputStream("文件路径")
		InputStream in = PropertiesDemo.class.getResourceAsStream("db.properties");
		//对象prop与文件输入流关联
		prop.load(new InputStreamReader(in,"UTF-8"));
		//通过名字，读出对应的值
		String dbtype = prop.getProperty("dbtype");
		String driver = prop.getProperty("driver");
		System.out.println(dbtype+","+driver);
	}

}
