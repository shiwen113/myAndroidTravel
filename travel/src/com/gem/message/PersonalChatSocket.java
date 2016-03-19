package com.gem.message;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.widget.Toast;

public class PersonalChatSocket {
	private Socket socket;
	public final static int SEND_SUCCESS=0;//发送成功
	/**
	 * 连接数据库
	 * @param context
	 */
	public void connectionChatWithSocket(final String s,final Handler handler) {

		// 创建一个Socket对象,并指定服务端的iP及端口号
		// isa=new InetSocketAddress("192.168.191.1", 8010);
		// socket = new Socket();
		new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				InetAddress serverAddr = null;
				try {
					serverAddr = InetAddress.getByName("192.168.191.1");
				} catch (UnknownHostException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}// TCPServer.SERVERIP

				// 应用Server的IP和端口建立Socket对象
				try {
					socket = new Socket(serverAddr, 8010);
//					 socket.connect(serverAddr,3000);
					if (socket.isConnected()) {
						Message msg=new Message();
						msg.what=0;
						msg.obj="发送了数据给服务端";
						handler.sendMessage(msg);
						sendDataToServerce(s,handler);
					} 
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}


			}

		}.start();
	}
		/**
		 * 向服务端发送数据
		 * @param s
		 */
		public void sendDataToServerce(String s,Handler handler){
			 //获得输出流对象
			OutputStream out = null;
			try {
				/*try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}*/
				out = socket.getOutputStream();
				out.write(s.getBytes());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
}
