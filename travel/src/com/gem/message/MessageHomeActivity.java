package com.gem.message;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.home.dao.MyApplication;
import com.gem.message.activity.FriendChatActivity;
import com.gem.message.activity.ListViewBaseAdaptAction;
import com.gem.scenery.R;
import com.gem.scenery.entity.PersonalData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MessageHomeActivity extends Fragment implements OnItemClickListener{
//	private List<View> views;// Tab页面列表
//	private ViewPager vp;// 叶卡内容
//	private  View view1;// 各个叶卡
//	private  View view2;
//	private Button btnNow,btnAdress;
//	private static ImageView imageView1;//下划线
//	private static ImageView imageView2;//下划线
//	private static TextView titleText;
	private Context context;
	private ListView lv;
	private List<PersonalData> list;
	private MyApplication m;
	private ListViewBaseAdaptAction adapter;
	private ImageView search;
	private PopupWindow popupWindow;
//	private ListView lv_information;//消息ListView
//	private ListView lv_addressBook;//通讯录ListView 
//	private BaseAdapter listViewAdapterInf;
//	private List<MessageNotifaction> listData;
//	private String token="Db+92Ff611hFJj1xrH36uKM73VU/jlJ2DUYsim9owp4loGP/+FzNyDSyRFXZSKLg3ELr3TzLDduvAwVP+7nE0Q==";
//	private Map<String,MessageNotifaction> map;
	// 初始化加载fragment里的部件
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_message_home, container, false);

		return view;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		context=getContext();
		m=(MyApplication) context.getApplicationContext();
//		views = new ArrayList<View>();
		@SuppressWarnings("static-access")
		LayoutInflater inflater = getLayoutInflater(savedInstanceState).from(context);
		list=new ArrayList<PersonalData>();
		// ViewPager加载页面
//		initViewPager(inflater);//解析Viewpage页面
		initView();// 初始化控件
//		if(m.getLd()!=null){
//		sendFrenidData();
//		}else{
//			Toast.makeText(context, "您还没登录，请先登录", Toast.LENGTH_SHORT).show();
//		}
//		showNowMessage();//一开始显示最近消息
//		views.add(view1);
//		views.add(view2);
//		
//		listData =new ArrayList<MessageNotifaction>();
//		map=new HashMap<String, MessageNotifaction>();
		
//		btnAdress.setOnClickListener(this);
//		btnNow.setOnClickListener(this);
//		
//		MessageHomeViewPage pagerAdapter = new MessageHomeViewPage(views);
//		vp.setAdapter(pagerAdapter);
//		vp.setCurrentItem(0);
//		vp.setOnPageChangeListener(new MessageHomeOnPageChangeListener());
//		messageData();//数据源
//		
//		listViewAdapterInf=new MessageHomeInformationListView(context,listData);
//		lv_information.setAdapter(listViewAdapterInf);
//		lv_information.setOnItemClickListener(new InformationOnItemClickListener());
//		RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, "1", "聊天标题");
//		RongIM.getInstance().startConversationList(context);
	}
	
	
	
	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(m.getLd()!=null){
			sendFrenidData();
			}else{
				Toast.makeText(context, "您还没登录，请先登录", Toast.LENGTH_SHORT).show();
			}
	}

	/**
	 * 初始化控件
	 */
	public void initView() {
//		vp = (ViewPager) this.getView().findViewById(R.id.vp_message);
//		titleText=(TextView) this.getView().findViewById(R.id.tv_msa_tittle);
//		imageView1=(ImageView) this.getView().findViewById(R.id.iv_msa_linear_one);
//		imageView2=(ImageView) this.getView().findViewById(R.id.iv_msa_linear_two);
//		btnNow=(Button) this.getView().findViewById(R.id.bt_message_zuijin);
//		btnAdress=(Button) this.getView().findViewById(R.id.bt_message_addressbook);
//		lv_information=(ListView) view1.findViewById(R.id.lv_message_information);
//		lv_addressBook=(ListView) view2.findViewById(R.id.lv_message_addressbook);
		lv=(ListView) this.getView().findViewById(R.id.lv_find_friend);
		lv.setOnItemClickListener(this);
		search=(ImageView) this.getView().findViewById(R.id.iv_search);
		search.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Intent intent=new Intent(context,FriendChatActivity.class);
				startActivity(intent);
			}
		});
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, final View arg1, final int arg2, long arg3) {
		sendDelete(list.get(arg2),arg1);
	}

	String url="http://10.201.1.12:8080/travel/MessageSelect";
	/**
	 * 获取好友列表
	 * 
	 */
	public void sendFrenidData(){
		HttpUtils http=new HttpUtils();
		RequestParams params=new RequestParams();
		params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "连接失败，请检查网络", Toast.LENGTH_SHORT).show();
			}

			@SuppressWarnings("unchecked")
			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String restult=arg0.result;
				if(!restult.equals("")&&!restult.equals("null")){
					Gson gson=new Gson();
					Type type=new TypeToken<List<PersonalData>>(){}.getType();
					if(list!=null){
					list.clear();
					}
					list.addAll((List<PersonalData>)gson.fromJson(restult, type));
					if(list!=null){
					if(adapter==null){
						adapter=new ListViewBaseAdaptAction(context,list);
						lv.setAdapter(adapter);
					}
					adapter.notifyDataSetChanged();
					}
				}
			}
			
		});
	}
	String urlDelete="http://10.201.1.12:8080/travel/Messagedelete";
	/**
	 * 删除好友
	 * @param pd
	 * @param arg1
	 */
	public void sendDelete(final PersonalData pd,View arg1) {
		// 通过view 和宽·高，构造PopopWindow
		View v=LayoutInflater.from(context).inflate(R.layout.select_friend_action, null);
		popupWindow = new PopupWindow(v,400, 300, true);

		popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000)
		// 此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
				);
		popupWindow.setOutsideTouchable(true);
		// 设置焦点为可点击
		popupWindow.setFocusable(true);// 可以试试设为false的结果
		// 将window视图显示在myButton下面
		// popupWindow.showAsDropDown(chaKanAllPeople);
		popupWindow.showAsDropDown(arg1, 0, 0);
		v.findViewById(R.id.tv_delete).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HttpUtils http=new HttpUtils();
				RequestParams params=new RequestParams();
				params.addBodyParameter("fd",String.valueOf(pd.getLd().getLd()));
				params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
				http.send(HttpMethod.POST, urlDelete, params, new RequestCallBack<String>() {

					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						Toast.makeText(context, "连接失败，请检查网络", Toast.LENGTH_SHORT).show();
					}

					@SuppressWarnings("unchecked")
					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						String restult=arg0.result;
						if(!restult.equals("")&&!restult.equals("null")){
							Gson gson=new Gson();
							Type type=new TypeToken<List<PersonalData>>(){}.getType();
							list.clear();
							list.addAll((List<PersonalData>)gson.fromJson(restult, type));
							adapter.notifyDataSetChanged();
							popupWindow.dismiss();
						}
					}
					
				});
			}
		});
		v.findViewById(R.id.tv_chat).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, pd.getLd().getLd()+"", "聊天标题");
			}
		});
	}
	
	/**
	 *  ViewPager加载的页面
	 * @param inflater
	 */
//	public void initViewPager(LayoutInflater inflater) {
//		// //解析要显示的界面
//		view1 = inflater.inflate(R.layout.activity_message_infotmation, null);
//		view2 = inflater.inflate(R.layout.activity_message_addressbook, null);
//	}
	
//	/**
//	 * 最近消息显示
//	 */
//	public static void showNowMessage(){
//		titleText.setText("消息");
//		imageView2.setBackgroundResource(R.drawable.black);
//		imageView1.setBackgroundResource(R.drawable.p);
//		imageView1.setVisibility(View.VISIBLE);
//	}
//	/**
//	 * 最近通讯录
//	 */
//	public static void showAddressBook(){
//		titleText.setText("通讯录");
//		imageView1.setBackgroundResource(R.drawable.black);
//		imageView2.setBackgroundResource(R.drawable.p);
//		imageView2.setVisibility(View.VISIBLE);
//	}
	
	
	
	/**
	 *小标题点击事件
	 */
	/*@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_message_zuijin://最新的处理
//			showNowMessage();
			Toast.makeText(context, "最新", Toast.LENGTH_LONG).show();
			break;
//		case R.id.bt_message_addressbook://通讯录
//			showAddressBook();
//			Toast.makeText(context, "通讯录", Toast.LENGTH_LONG).show();
//			break;
		default:
			break;
		}
	}*/
//
//	/**
//	 * 数据源
//	 */
//	public void messageData(){
//		for(int i=1;i<9;i++){
//		MessageNotifaction mnf=new MessageNotifaction("石文"+i, "刘学是Sb", PersonalChatUtil.getStringTime(new Date()),1);
//		listData.add(mnf);
//		}
//		MessageNotifaction mnf1=new MessageNotifaction("刘学", "刘学是Sb", PersonalChatUtil.getStringTime(new Date()),2);
//		listData.add(mnf1);
//	}

//	/**
//	 * 表示消息显示
//	 */
//	public class InformationOnItemClickListener implements OnItemClickListener{
//
//		@Override
//		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
//				long arg3) {
//			System.out.println(listData);
//			System.out.println(listData.get(arg2));
//			System.out.println(listData.get(arg2).getState());
//			// TODO Auto-generated method stub
//			if(listData.get(arg2).getState()==1){
//				Intent intent =new Intent(getActivity(),PersonalChatActivity.class);
//				String s="15927663411";
//				intent.putExtra("sendPhone", s);
//				startActivity(intent);
//			}else if(listData.get(arg2).getState()==2){
//				Intent intent =new Intent(getActivity(),PersonalChatActivity.class);
//				startActivity(intent);
//			}
//		}
//		
//	
//	}
}
