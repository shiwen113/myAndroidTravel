package gem.day16;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//创建两个线程，一个线程负责发送数据，
		//负责接收数据
		//联接到服务器
		Socket socket = new Socket("localhost",9009);
		//启动线程
		new Thread(new ClientSend(socket)).start();
		new Thread(new ClientReceiver(socket)).start();
		

	}
}
//一个线程负责发送数据
class ClientSend implements Runnable {
	Socket socket;
	//通过构造方法传入socket对象
	public ClientSend(Socket socket) {
		this.socket = socket;
	}
	@Override
	public void run() {
		//循环，从键盘读入
		Scanner scanner = new Scanner(System.in);
		//发送数据，PrintStream
		try {
			PrintStream out = new PrintStream(socket.getOutputStream());
			while(true) {
				//从键盘输入消息
				String message = scanner.nextLine();
				//发送消息
				out.println(message);
				out.flush();
				if(message.equals("quit")) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
}

//一个线程负责接收数据
class ClientReceiver implements Runnable {
	Socket socket;
	public ClientReceiver(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		//接收服务器传来的消息,
		//socket.getOutputStream ==> BufferedReader
		try {
			BufferedReader in = new BufferedReader(
					new InputStreamReader(socket.getInputStream()));
			//循环接收消息
			while(true) {
				String message = in.readLine();
				System.out.println("server:"+message);
				if(message.equals("quit")) {
					break;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}





