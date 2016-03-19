package gem.day16;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class SimpleClient {

	public static void main(String[] args) throws UnknownHostException, IOException {
		//联接一个服务器
		Socket  socket = new Socket("localhost",9009);
		//接收服务器端送来的消息
		//socket.getInputStream ==>  BufferedReader 
		BufferedReader in = new BufferedReader(
				new InputStreamReader(socket.getInputStream()));
		//获得PrintStream对象
		PrintStream out = new PrintStream(socket.getOutputStream());
		//获得消息
		String mess = in.readLine();
		System.out.println(mess);
		//发送一条消息给服务端，加循环，发送的消息从键盘读入,当从鍵盘中输入的是quit时
		//从键盘读：System.in ==> InputStreamReader ==> BufferedReader
		Scanner scanner = new Scanner(System.in);
		//退出循环
		while(true) {
			//从键盘读入一条消息
			String message = scanner.nextLine();
			//发送给服务器
			out.println(message);
			out.flush();
			//获得服务端回送的消处，输出
			mess = in.readLine();
			System.out.println("server:"+mess);
			
			//如果输入的是quit，退出
			if(message.equals("quit")) {
				break;
			}
		}
		
		//close
		in.close();
		out.close();
		socket.close();
		
	}

}
