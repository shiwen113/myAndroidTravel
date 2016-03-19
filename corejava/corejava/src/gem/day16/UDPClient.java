package gem.day16;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class UDPClient {

	public static void main(String[] args) throws IOException {
		//创建一个DatagramSocket对象
		DatagramSocket socket = new DatagramSocket();
		//发送的地址
		InetAddress address = InetAddress.getLocalHost();
		
		//准备信封，及信中的内容
		String str = "C508右上角有一个包，包里有和宝贝";
		DatagramPacket dp = 
				new DatagramPacket(str.getBytes(),
						str.getBytes().length,address,8090);
		socket.send(dp);
		System.out.println("发送成功!!!!");
		
		socket.close();
	}

}
