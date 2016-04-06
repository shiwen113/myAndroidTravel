package com.gem.message;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.home.dao.MyApplication;
import com.gem.message.action.ChatMsgViewAdapter;
import com.gem.message.action.PersonalMessageAdapt;
import com.gem.message.client.Client;
import com.gem.message.client.ClientOutputThread;
import com.gem.message.client.TranObject;
import com.gem.message.client.TranObjectType;
import com.gem.message.entity.ChatMsgEntity;
import com.gem.message.entity.Message;
import com.gem.message.entity.RecentChatEntity;
import com.gem.message.entity.TextMessage;
import com.gem.message.entity.User;
import com.gem.message.utils.MessageDB;
import com.gem.message.utils.MyDate;
import com.gem.message.utils.SharePreferenceUtil;
import com.gem.scenery.R;
import com.google.gson.Gson;

public class PersonalChatActivity extends MyActivity implements OnClickListener {

	private ListView msgListView;// 显示消息的listView
	private EditText inputText;// 输入消息
	private Button send;// 发送消息按钮
	private Button back;// 返回
	private TextView chatName;// 聊天姓名
	private PersonalMessageAdapt adapter;// 适配器
	private List<ChatMsgEntity> mDataArrays = new ArrayList<ChatMsgEntity>();// 消息对象数组
	private PersonalChatSocket pcs;
	private User user;
	private MessageDB messageDB;
	private SharePreferenceUtil util;
	private MyApplication application;

	// private static ExecutorService exec = Executors.newFixedThreadPool(10);
	// private List<Socket> list=new ArrayList<Socket>();
	// int i=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题
		setContentView(R.layout.action_personal_chat);
		// initMsgs();//初始化数据
		// adapter=new PersonalMessageAdapt(this,
		// R.layout.action_message_listview, msgList);
		inputText = (EditText) findViewById(R.id.et_pca_input_text);
		msgListView = (ListView) findViewById(R.id.lv_msg_list_view);
		send = (Button) findViewById(R.id.bt_pca_send);
		send.setOnClickListener(this);
		back = (Button) findViewById(R.id.chat_back);
		back.setOnClickListener(this);
		chatName = (TextView) findViewById(R.id.chat_name);
		// initData();// 初始化数据
	}

	/**
	 * 加载消息历史，从数据库中读出
	 */
	public void initData() {
		List<ChatMsgEntity> list = messageDB.getMsg(user.getId());
		if (list.size() > 0) {
			for (ChatMsgEntity entity : list) {
				if (entity.getName().equals("")) {
					entity.setName(user.getName());
				}
				if (entity.getImg() < 0) {
					entity.setImg(user.getImg());
				}
				mDataArrays.add(entity);
			}
			Collections.reverse(mDataArrays);
		}

		adapter = new PersonalMessageAdapt(this, mDataArrays);
		msgListView.setAdapter(adapter);
		msgListView.setSelection(adapter.getCount() - 1);
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
		if (messageDB != null) {
			messageDB.close();
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_pca_send:// 发送按钮点击事件
			send();
			break;
		case R.id.chat_back:// 返回按钮点击事件
			finish();// 结束,实际开发中，可以返回主界面
			break;
		}
	}

	/**
	 * 发送消息
	 */
	private void send() {
		// 获取文本框的数据
		String contString = inputText.getText().toString();
		if (contString.length() > 0) {
			ChatMsgEntity entity = new ChatMsgEntity();
			// entity.setName(util.getName());
			entity.setDate(MyDate.getDateEN());
			entity.setMessage(contString);
			// entity.setImg(util.getImg());
			entity.setMsgType(false);

			// messageDB.saveMsg(user.getId(), entity);

			mDataArrays.add(entity);
			if (adapter == null) {
				adapter = new PersonalMessageAdapt(this, mDataArrays);
				msgListView.setAdapter(adapter);
				msgListView.setSelection(adapter.getCount() - 1);
			}
			adapter.notifyDataSetChanged();// 通知ListView，数据已发生改变
			inputText.setText("");// 清空编辑框数据
			msgListView.setSelection(msgListView.getCount() - 1);// 发送一条消息时，ListView显示选择最后一项
			MyApplication application = (MyApplication) this
					.getApplicationContext();
			Client client = application.getClient();
			Boolean flag = client.start();
			if (flag) {
				ClientOutputThread out = client.getClientOutputThread();
				if (out != null) {
					TranObject<TextMessage> o = new TranObject<TextMessage>(
							TranObjectType.MESSAGE);
					TextMessage message = new TextMessage();
					message.setMessage(contString);
					o.setObject(message);
//					o.setFromUser(Integer.parseInt(util.getId()));
//					o.setToUser(user.getId());
					out.setMsg(o);
				}
				// 下面是添加到最近会话列表的处理，在按发送键之后
//				RecentChatEntity entity1 = new RecentChatEntity(user.getId(),
//						user.getImg(), 0, user.getName(), MyDate.getDate(),
//						contString);
//				application.getmRecentList().remove(entity1);
//				application.getmRecentList().addFirst(entity1);
//				application.getmRecentAdapter().notifyDataSetChanged();
			}
		}
	}

	/**
	 * 处理消息，继承MyActivity
	 */
	@Override
	public void getMessage(TranObject msg) {
		// TODO Auto-generated method stub
		switch (msg.getType()) {
		case MESSAGE:// 用户发送消息
			TextMessage tm = (TextMessage) msg.getObject();// 传输对象
			String message = tm.getMessage();
			ChatMsgEntity entity = new ChatMsgEntity(user.getName(),
					MyDate.getDateEN(), message, user.getImg(), true);// 收到的消息
			if (msg.getFromUser() == user.getId() || msg.getFromUser() == 0) {// 如果是正在聊天的好友的消息，或者是服务器的消息

				messageDB.saveMsg(user.getId(), entity);

				mDataArrays.add(entity);
				adapter.notifyDataSetChanged();
				msgListView.setSelection(msgListView.getCount() - 1);
				MediaPlayer.create(this, R.raw.msg).start();
			} else {
				messageDB.saveMsg(msg.getFromUser(), entity);// 保存到数据库
				Toast.makeText(PersonalChatActivity.this,
						"您有新的消息来自：" + msg.getFromUser() + ":" + message, 0)
						.show();// 其他好友的消息，就先提示，并保存到数据库
				MediaPlayer.create(this, R.raw.msg).start();
			}
			break;
		case LOGIN:// 登陆
			User loginUser = (User) msg.getObject();
			Toast.makeText(PersonalChatActivity.this,
					loginUser.getId() + "上线了", 0).show();
			MediaPlayer.create(this, R.raw.msg).start();
			break;
		case LOGOUT:// 用户退出登陆
			User logoutUser = (User) msg.getObject();
			Toast.makeText(PersonalChatActivity.this,
					logoutUser.getId() + "下线了", 0).show();
			MediaPlayer.create(this, R.raw.msg).start();
			break;
		default:
			break;
		}
	}

	/*
	 * send.setOnClickListener(new OnClickListener() {
	 * 
	 * @Override public void onClick(View v) { String
	 * content=inputText.getText().toString(); String
	 * sendPhone="15927663411";//自己的账号 String acceptPhone="15071046331";//对方的账号
	 * Map<String,String> map=new HashMap<String, String>();
	 * map.put("sendPhone", sendPhone); map.put("acceptPhone", acceptPhone);
	 * map.put("content", content); Gson gson=new Gson(); String
	 * son=gson.toJson(map); if(!"".equals(content)&&content.length()>0){
	 * //当有消息时，刷新listView中的显示 Message msg=new
	 * Message(content,Message.TYPE_SENT); msgList.add(msg); //将listview定位到最后一行
	 * msgListView.setSelection(msgList.size()); if(adapter==null){ adapter=new
	 * PersonalMessageAdapt(PersonalChatActivity.this,
	 * R.layout.action_message_listview, msgList);
	 * msgListView.setAdapter(adapter); } adapter.notifyDataSetChanged();
	 * inputText.setText("");//清空输入框中的内容
	 * pcs.connectionChatWithSocket(son,handler); //
	 * pcs.accepetDataFromServerce(handler); // pcs.sendDataToServerce(son); //
	 * PersonalChatAsycTask asycTask=new PersonalChatAsycTask(msgList);
	 * //请求网络，单线程执行 //
	 * asycTask.execute("http://192.168.191.1:8080/travel/PersonalChatServlet?"
	 * +"son="+new String (son.getBytes("utf-8"),"ISO8859-1")); //多线程并行执行 //
	 * Log.i("PersonalChatActivity", "点击了"+son); //
	 * asycTask.executeOnExecutor(exec,
	 * "http://192.168.191.1:8080/travel/PersonalChatServlet?"+"son="+son); //
	 * if(i==9){ // exec.shutdown(); // exec = Executors.newFixedThreadPool(10);
	 * // } // Log.i("PersonalChatActivity", "点击了"+i++); }else{
	 * Toast.makeText(PersonalChatActivity.this, "内容不能为空", 1).show(); } } });
	 * 
	 * //运行时连接服务器，即表示登陆成功 pcs=new PersonalChatSocket(list); Intent
	 * intent=getIntent(); String s=intent.getStringExtra("sendPhone");
	 * Map<String, String> map =new HashMap<String, String>(); Gson gson =new
	 * Gson(); map.put("sendPhone", s); String str = gson.toJson(map);
	 * pcs.connectionChatWithSocket(str, handler);
	 */

	// @Override
	// protected void onResume() {
	// super.onResume();
	// if (pcs.getSocket()!=null) {
	// pcs.accepetDataFromServerce(handler);
	// }
	// }

	/*
	 * @SuppressLint("HandlerLeak") private Handler handler=new Handler(){
	 * 
	 * @Override public void handleMessage(android.os.Message msg) { // TODO
	 * Auto-generated method stub super.handleMessage(msg); switch (msg.what) {
	 * case PersonalChatSocket.SEND_SUCCESS: Log.i("PersonalChatActivity",
	 * msg.obj.toString()); break; case PersonalChatSocket.ACCEPT_SUCCESS:
	 * Message m=new Message(msg.obj.toString(),Message.TYPE_RECEIVED);
	 * Log.i("PersonalChatActivity:", msg.obj.toString()); addListAccept(m);
	 * break; default: break; }
	 * 
	 * }
	 * 
	 * };
	 * 
	 * public void addListAccept(Message m){ msgList.add(m);
	 * msgListView.setSelection(msgList.size()); adapter.notifyDataSetChanged();
	 * }
	 */
	// private void initMsgs(){
	// Message msg1=new Message("hello guy",Message.TYPE_RECEIVED);
	// msgList.add(msg1);
	// Message msg2=new Message("hello, Who is what?",Message.TYPE_SENT);
	// msgList.add(msg2);
	// Message msg3=new
	// Message("This is Tom。Nice talking to you",Message.TYPE_RECEIVED);
	// msgList.add(msg3);
	// }

}
