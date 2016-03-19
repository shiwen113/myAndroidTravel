package gem.day16.homework;

/*创建一个多线程的TCP 服务器以及客户端，完成下面的功能：
 服务端：读入客户端发给服务器端的字符串，在服务器当前目录下查找以该字符串作为
 文件名的文件，并把该文件内容发送给客户端。
 文件中内容 ==》送到网上 
 客户端：发送给服务器端一个字符串filename 表示服务器上的一个文件，然后从服务器
 端读入文件内容，并起名叫server_filename 保存在当前目录。
 网上接收到的数据 ==》存到文件中
 例如，假设服务器当前目录下有个myphoto.jpg 文件，则客户端发送字符串“myphoto.jpg”
 给服务器端，然后从服务器端读入myphoto.jpg 文件的内容，并起名为server_myphoto.jpg
 保存在客户端当前目录下
 */

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TCPServer {
	public static void main(String[] args) throws IOException {
		ServerSocket server = new ServerSocket(9998);
		Socket socket =server.accept();
		new Thread(new PrintFile(socket)).start();

	}

}

class PrintFile implements Runnable {
	Socket socket;

	public PrintFile(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		BufferedReader in = null;
		BufferedReader br = null;
		PrintStream out = null;
		String str = null;
		try {
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			String fileName = in.readLine();
			br = new BufferedReader(new InputStreamReader(new FileInputStream(
					fileName)));
			out = new PrintStream(socket.getOutputStream());
			while ((str = br.readLine()) != null) {
				out.println(str);
				out.flush();
			}

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				if (in != null)
					in.close();
				if (br != null)
					br.close();
				if (out != null)
					out.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
