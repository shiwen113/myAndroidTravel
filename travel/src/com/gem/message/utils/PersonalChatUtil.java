package com.gem.message.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.HttpsURLConnection;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

/**
 * 单人聊天访问网络:子线程
 * @author shiwen
 *
 */
public class PersonalChatUtil{
	
	/**
	 * 访问网络Post方法
	 * @param urlStr 访问的网站
	 * @param handler 消息处理
	 */
	public static void getConnetionByGet(final String urlStr,final Handler handler){

new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				Log.i("PersonalChatActivity", "语句三");
				
				try {
					URL url=new URL(urlStr);//字符串转化成url
					
				   try {
					//建立连接
					HttpURLConnection conn=(HttpURLConnection) url.openConnection();
					conn.setConnectTimeout(5000);//设置连接超时时间
					
					//响应成功
					if(conn.getResponseCode()==200){
						//读取响应结果
						InputStream is=conn.getInputStream();
						//输入流转化成字符串
						String result=convertSteamToString(is);
						
						//将result传给主线程
						
						
						//创建消息对象：标识+传数据
						//Message msg=new Message();
						Message msg=Message.obtain();
						//设置标识，传数据
						msg.what=1;
						msg.obj=result;
						
						//handler发送消息
						handler.sendMessage(msg);
					}
					
					
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
					
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
			
			
			
		}.start();
		
		
		
		
	}
	
	/**
	 * 发送post请求
	 * @param urlStr 网址
	 * @param maps 数据
	 * @param handler 消息处理
	 */
	public static void getConnectionByPost(final String urlStr,final HashMap<String,Object> maps, final Handler handler){
		
		new Thread(){

			@Override
			public void run() {
				// TODO Auto-generated method stub
				super.run();
				try {
					URL url=new URL(urlStr);//获取url对象
					
					try {
						HttpURLConnection conn=(HttpURLConnection) url.openConnection();
						conn.setRequestMethod("POST");//设置请求方式
						conn.setConnectTimeout(5000);
						
						//设置post请求的参数
						OutputStream os=conn.getOutputStream();
						
						//将hashmap转化成String:  "userName=admin&usePwd=123456&"
						//map转化成set集合
						Set<Entry<String, Object>>	set= maps.entrySet();
						
						//存放转化结果
						StringBuilder builder=new StringBuilder();
						
						//遍历set:取出key和value   key=value&
						for(Entry<String, Object> entry:set){
							builder.append(entry.getKey()+"="+entry.getValue()+"&");
						}
						//去除最后一个”&“
						String params=builder.toString().substring(0, builder.toString().length()-1);				
		
						os.write(params.getBytes());
						
						
						//响应成功
						if(conn.getResponseCode()==200){
							
							//读取响应结果
							InputStream is=conn.getInputStream();
							//输入流转化成字符串
							String result=convertSteamToString(is);
							
							//将result传给主线程
							
							
							//创建消息对象：标识+传数据
							//Message msg=new Message();
							Message msg=Message.obtain();
							//设置标识，传数据
							msg.what=2;
							msg.obj=result;
							
							//handler发送消息
							handler.sendMessage(msg);
							
						}
						
						
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
					
					
				} catch (MalformedURLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
			}
			
			
			
		}.start();
		
	}


	/**
	 * 输入流转换成字符串
	 */
	public static String convertSteamToString(InputStream in){
		//创建字节数组
		ByteArrayOutputStream baos=new ByteArrayOutputStream();
		int b;//读取字节
		try{
			while((b=in.read())!=0){
				baos.write(b);
			}
			return baos.toString();
		}catch(IOException e){
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 时间格式转换
	 */
	@SuppressLint("SimpleDateFormat") 
	public static String getStringTime(Date date){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String s=sdf.format(date);
		return s;
	}
}
