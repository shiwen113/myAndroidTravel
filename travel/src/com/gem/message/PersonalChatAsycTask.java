package com.gem.message;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;

import android.os.AsyncTask;

import com.gem.message.utils.PersonalChatUtil;
import com.gem.scenery.entity.Message;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
/**
 * 
 * @author shiwen
 * Params： String 启动任务执行的输入参数
 * Progress：Integer 后台执行任务的进度
 * Result ：String 后台计算结果的类型
 *
 */
public class PersonalChatAsycTask extends AsyncTask<String, Integer, Message> {

	private List<Message>msgList;
	public PersonalChatAsycTask(List<Message> msgList){
		this.msgList=msgList;
	}
	/**
	 * 在onPreExecute()完成后立即执行，用于执行较为费时的操作，
	 * 此方法将接收输入参数和返回计算结果。在执行过程中可以调用
	 * publishProgress(Progress... values)来更新进度信息。
	 */
	@Override
	protected Message doInBackground(String... params) {
		// TODO Auto-generated method stub
		try {
			URL url=new URL(params[0]);
			HttpURLConnection conn=(HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setConnectTimeout(5000);
			
			//响应成功
			if(conn.getResponseCode()==200){
				//读取响应结果
			/*	InputStream is=conn.getInputStream();
				//输入流转化成字符串
				String resultStr=PersonalChatUtil.convertSteamToString(is);
				if(resultStr!=null){
				Message msg =new Message(resultStr, Message.TYPE_RECEIVED);
				Gson gson;
				 if(resultStr!=null){
				 gson=new Gson();
				 Type type=new TypeToken<Map<String,String>>(){}.getType();
				 Map<String,String> map=gson.fromJson(resultStr, type);
				 Set<String> set=map.keySet();
				 String sendPhone=null;
				 String acceptPhone=null;
				 String content=null;
				 for (String string : set) {
							switch (string) {
							case "myPhone":
								acceptPhone=map.get(string);
								break;
							case "friendPhone":
								sendPhone=map.get(string);
								break;
							case "content":
								content=map.get(string);
								break;
							default:
								break;
							}

				return msg;
				}
			}
				}*/
			}
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	/**
	 * 当后台操作结束时，此方法将会被调用，
	 * 计算结果将做为参数传递到此方法中，直接将结果显示到UI组件上。
	 */
	@Override
	protected void onPostExecute(Message result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		msgList.add(result);
	}
	/**
	 * 在execute(Params... params)
	 * 被调用后立即执行，一般用来在执行后台任务前对UI做一些标记。
	 */
	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}
	/**
	 * 在调用publishProgress(Progress... values)时，
	 * 此方法被执行，直接将进度信息更新到UI组件上。
	 */
	@Override
	protected void onProgressUpdate(Integer... values) {
		// TODO Auto-generated method stub
		super.onProgressUpdate(values);
	}
	public ExecutorService newFixedThreadPool(int i) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
