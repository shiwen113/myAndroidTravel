package gem.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
	
	public static void main(String[] args) throws IOException {
		//存放在线客户的集合
		List<Socket> users = Collections.
				synchronizedList(new ArrayList<Socket>());;
		
		
		
		// 在某个端口上建一个服务器对象
		ServerSocket server = new ServerSocket(9009);
		// 阻塞方法
		// 有一个客户端联接，创建一个线程服务这个客户
		// 可以联接任意多个客户
		while(true) {
			Socket socket = server.accept();
			//加入在线客户
			users.add(socket);
			new Thread(new ServerThread(socket)).start();
		}

	}
}

// 创建一个线程，为客户服务
class ServerThread implements Runnable {
	// socket:表示所联接的客户
	Socket socket;

	public ServerThread(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		PrintStream out = null;
		BufferedReader in = null;
		try {
			//
			System.out.println("ok,联上了");
			// 发送一条消息给客户端，getOutputStream ==> PrintStream
			// 先获得PrintStream对象
			out = new PrintStream(socket.getOutputStream());
			//
			in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
			
			
			out.println("您好，欢迎你!!!"); // 发送一条消息
			// 记住
			out.flush();

			// 接收客户端发来的消息，显示
			// 加循环，判断客户端过来的消息是quit，则退出
			while (true) {
				String mess = in.readLine();
				System.out.println("客户端说：" + mess);
				//群发：？？？？？？：将mess消息发送给所有在线的客户
				//遍历users中的Socket,发送相应的消息
				// 回送接收到的客户端的消息
				out.println(mess);
				out.flush();
				if (mess.equals("quit")) {
					break;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			// close
			try {
				if(in!=null)in.close();
				if(out!=null)out.close();
				if(socket!=null)socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}

	}

}
