package gem.day16;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

public class UDPServer {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		//创建一个DatagramSocket对象
		DatagramSocket  socket = new DatagramSocket(8090);
		//创建一个字节数组，存接收到的数据
		byte[] buff = new byte[512];
		//创建好信封
		DatagramPacket dp = new DatagramPacket(buff,buff.length);
		//接收数据，放在dp中,阻塞方法
		socket.receive(dp);
		//获得接收到的数据
		String s = new String(dp.getData(),0,dp.getData().length);
		System.out.println("接收到的数据:"+s);
		
		socket.close();
	}

}
