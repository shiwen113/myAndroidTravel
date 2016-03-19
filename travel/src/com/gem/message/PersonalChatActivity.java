package com.gem.message;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gem.message.action.PersonalMessageAdapt;
import com.gem.scenery.R;
import com.gem.scenery.entity.Message;
import com.google.gson.Gson;

public class PersonalChatActivity extends Activity{

	private ListView msgListView;//显示消息的listView
	private EditText inputText;//输入消息
	private Button send;//发送消息按钮
	private PersonalMessageAdapt adapter;//适配器
	private List<Message>msgList=new ArrayList<Message>();
//	private static ExecutorService exec = Executors.newFixedThreadPool(10);
//	private List<String> list=new ArrayList<String>();
	
	private static Handler handler=new Handler(){

		@Override
		public void handleMessage(android.os.Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			switch (msg.what) {
			case PersonalChatSocket.SEND_SUCCESS:
				Log.i("PersonalChatActivity", msg.obj.toString());
				break;

			default:
				break;
			}
		}
		
	};
	int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
		setContentView(R.layout.action_personal_chat);
//		initMsgs();//初始化数据
//		adapter=new PersonalMessageAdapt(this, R.layout.action_message_listview, msgList);
		inputText =(EditText) findViewById(R.id.et_pca_input_text);
		msgListView=(ListView) findViewById(R.id.lv_msg_list_view);
		send=(Button) findViewById(R.id.bt_pca_send);
		send.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String content=inputText.getText().toString();
				String sendPhone="15071046331";//自己的账号
				String acceptPhone="15927663411";//对方的账号
				Map<String,String> map=new HashMap<String, String>();
				map.put("sendPhone", sendPhone);
				map.put("acceptPhone", acceptPhone);
				map.put("content", content);
				Gson gson=new Gson();   
				String son=gson.toJson(map);
				if(!"".equals(content)&&content.length()>0){
					//当有消息时，刷新listView中的显示
					Message msg=new Message(content,Message.TYPE_SENT); 
					msgList.add(msg);
					msgListView.setSelection(msgList.size());
					if(adapter==null){
					adapter=new PersonalMessageAdapt(PersonalChatActivity.this, R.layout.action_message_listview, msgList);
					msgListView.setAdapter(adapter);
					}
					adapter.notifyDataSetChanged();
					inputText.setText("");//清空输入框中的内容
					//将listview定位到最后一行
					PersonalChatSocket pcs=new PersonalChatSocket();
					pcs.connectionChatWithSocket(son,handler);
//					pcs.sendDataToServerce(son);
//					PersonalChatAsycTask asycTask=new PersonalChatAsycTask(msgList);
					//请求网络，单线程执行
//						asycTask.execute("http://192.168.191.1:8080/travel/PersonalChatServlet?"+"son="+new String (son.getBytes("utf-8"),"ISO8859-1"));
					//多线程并行执行
//						Log.i("PersonalChatActivity", "点击了"+son);
//						asycTask.executeOnExecutor(exec, "http://192.168.191.1:8080/travel/PersonalChatServlet?"+"son="+son);
//						if(i==9){
//							exec.shutdown();
//							exec = Executors.newFixedThreadPool(10);
//						}
//					Log.i("PersonalChatActivity", "点击了"+i++);
				}else{
					Toast.makeText(PersonalChatActivity.this, "内容不能为空", 1).show();
				}
			}
		});
	}
	

	/**
	 * 连接聊天对象
	 */
	public void sendFromFriendConnection(){
		
	}
//	private void initMsgs(){
//		Message msg1=new Message("hello guy",Message.TYPE_RECEIVED);
//		msgList.add(msg1);
//		Message msg2=new Message("hello, Who is what?",Message.TYPE_SENT);
//		msgList.add(msg2);
//		Message msg3=new Message("This is Tom。Nice talking to you",Message.TYPE_RECEIVED);
//		msgList.add(msg3);
//	}
}
