package com.gem.message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class PersonalChatSocket{
	private Socket socket;
	public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	public final static int SEND_SUCCESS = 0;// 发送成功
	public final static int ACCEPT_SUCCESS = 1;// 接受成功

	/**
	 * 连接数据库
	 * 
	 * @param context
	 */
	public void connectionChatWithSocket(final String s, final Handler handler) {

		// 创建一个Socket对象,并指定服务端的iP及端口号
		// isa=new InetSocketAddress("192.168.191.1", 8010);
		// socket = new Socket();
		new Thread() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				Log.i("Message","发送信息");
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
					Log.i("Message", "连接成功");
					// socket.connect(serverAddr,3000);
					if (socket.isConnected()) {
						Message msg = new Message();
						msg.what = 0;
						msg.obj = "发送了数据给服务端";
						handler.sendMessage(msg);
						sendDataToServerce(s, handler);
						Log.i("Message", "发送数据给服务端成功");
					}
				} catch (IOException e1) {
					try {
						if(socket!=null){
						socket.close();
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
//				//接受服务器数据
//				Log.i("Message", "开始接受服务器数据");
				if(socket!=null){
					accepetDataFromServerce(socket,handler);
					}
			}
		}.start();
	}

	/**
	 * 向服务端发送数据
	 * 
	 * @param s
	 */
	public void sendDataToServerce(String s, Handler handler) {
		// 获得输出流对象
		OutputStream out = null;
		try {
			/*
			 * try { Thread.sleep(500); } catch (InterruptedException e) { //
			 * TODO Auto-generated catch block e.printStackTrace(); }
			 */
			out = socket.getOutputStream();
			out.write(s.getBytes());
			out.flush();
			//防止服务端read方法阻塞       
//			socket.shutdownOutput();
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

	/**
	 * 接受服务器数据
	 */
	public void accepetDataFromServerce( final Socket socket,
			final Handler handler) {

		new Thread() {
			@SuppressWarnings("null")
			@Override
			public void run() {
				super.run();
				Log.i("Message", "进入接收数据线程");
				BufferedReader br = null;
				try {
					br = new BufferedReader(new InputStreamReader(
							socket.getInputStream()));
					Log.i("Message", "输入流："+br);
					String content = null;
					StringBuffer msgdata = null;
					// 不断读取Socket输入流中的内容。
					while ((content = br.readLine()) != null) {
						msgdata.append(content);
					}
					Log.i("Message", "接收的输入数据msgdata"+msgdata);
					Gson gson =new Gson();
					Type type=new TypeToken<Map<String,String>>(){}.getType();
					Map<String,String> mapdata=gson.fromJson(new String(msgdata), type);
					String data=mapdata.get("content");
					Log.i("Message", "接收的输入数据content"+data);
					Message msg = new Message();
					msg.what = 1;
					msg.obj = data;
					handler.sendMessage(msg);
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}

		}.start();
	}
}
