package gem.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SimpleServer {

	public static void main(String[] args) throws IOException {
		//在某个端口上建一个服务器对象
		//联多个客户端???
		ServerSocket server = new ServerSocket(9009);
		//阻塞方法
		Socket socket = server.accept();
		//
		System.out.println("ok,联上了");
		//发送一条消息给客户端，getOutputStream ==> PrintStream
		//先获得PrintStream对象
		PrintStream out = new PrintStream(socket.getOutputStream());
		//
		BufferedReader in = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		
		out.println("您好，欢迎你!!!");	//发送一条消息
		//记住
		out.flush();
		
		//接收客户端发来的消息，显示
		//加循环，判断客户端过来的消息是quit，则退出
		while(true) {
			String mess = in.readLine();
			System.out.println("客户端说："+mess);
			//回送接收到的客户端的消息
			out.println(mess);
			out.flush();
			if(mess.equals("quit")) {
				break;
			}
		}
	
		//close
		in.close();
		out.close();
		socket.close();
		server.close();
		
	}
}
