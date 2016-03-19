package gem.day16;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class InetAddressDemo {

	public static void main(String[] args) throws IOException {
		//说明InetAddress的用法
		//通过InetAddress的静态方法byName，获得其实例
		InetAddress ia = InetAddress.getByName("www.csdn.net");
		//获得一些信息
		System.out.println(ia.getHostAddress());	//ip
		System.out.println(ia.getHostName());		//域名
		//测试是否可达
		System.out.println(ia.isReachable(1000));	//??
		//得到本地的InetAddress的实例
		InetAddress local = InetAddress.getLocalHost();
		System.out.println(local.getHostAddress());
		System.out.println(local.getHostName());
		
		
		
		

	}

}
