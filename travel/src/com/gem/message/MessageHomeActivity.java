package com.gem.message;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.message.action.MessageHomeInformationListView;
import com.gem.message.action.MessageHomeOnPageChangeListener;
import com.gem.message.action.MessageHomeViewPage;
import com.gem.message.entity.MessageNotifaction;
import com.gem.message.utils.PersonalChatUtil;
import com.gem.scenery.R;

public class MessageHomeActivity extends Fragment implements OnClickListener{
	private List<View> views;// Tab页面列表
	private ViewPager vp;// 叶卡内容
	private  View view1;// 各个叶卡
	private  View view2;
	private Button btnNow,btnAdress;
	private static ImageView imageView1;//下划线
	private static ImageView imageView2;//下划线
	private static TextView titleText;
	private Context context;
	private ListView lv_information;//消息ListView
	private ListView lv_addressBook;//通讯录ListView 
	private BaseAdapter listViewAdapterInf;
	private List<MessageNotifaction> listData;
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
		views = new ArrayList<View>();
		@SuppressWarnings("static-access")
		LayoutInflater inflater = getLayoutInflater(savedInstanceState).from(context);
		// ViewPager加载页面
		initViewPager(inflater);//解析Viewpage页面
		initView();// 初始化控件
		showNowMessage();//一开始显示最近消息
		views.add(view1);
		views.add(view2);
		
		listData =new ArrayList<MessageNotifaction>();
//		map=new HashMap<String, MessageNotifaction>();
		
		btnAdress.setOnClickListener(this);
		btnNow.setOnClickListener(this);
		
		MessageHomeViewPage pagerAdapter = new MessageHomeViewPage(views);
		vp.setAdapter(pagerAdapter);
		vp.setCurrentItem(0);
		vp.setOnPageChangeListener(new MessageHomeOnPageChangeListener());
		messageData();//数据源
		
		listViewAdapterInf=new MessageHomeInformationListView(context,listData);
		lv_information.setAdapter(listViewAdapterInf);
		lv_information.setOnItemClickListener(new InformationOnItemClickListener());
	}
	/**
	 * 初始化控件
	 */
	public void initView() {
		vp = (ViewPager) this.getView().findViewById(R.id.vp_message);
		titleText=(TextView) this.getView().findViewById(R.id.tv_msa_tittle);
		imageView1=(ImageView) this.getView().findViewById(R.id.iv_msa_linear_one);
		imageView2=(ImageView) this.getView().findViewById(R.id.iv_msa_linear_two);
		btnNow=(Button) this.getView().findViewById(R.id.bt_message_zuijin);
		btnAdress=(Button) this.getView().findViewById(R.id.bt_message_addressbook);
		lv_information=(ListView) view1.findViewById(R.id.lv_message_information);
		lv_addressBook=(ListView) view2.findViewById(R.id.lv_message_addressbook);
	}

	/**
	 *  ViewPager加载的页面
	 * @param inflater
	 */
	public void initViewPager(LayoutInflater inflater) {
		// //解析要显示的界面
		view1 = inflater.inflate(R.layout.activity_message_infotmation, null);
		view2 = inflater.inflate(R.layout.activity_message_addressbook, null);
	}
	
	/**
	 * 最近消息显示
	 */
	public static void showNowMessage(){
		titleText.setText("消息");
		imageView2.setBackgroundResource(R.drawable.black);
		imageView1.setBackgroundResource(R.drawable.p);
		imageView1.setVisibility(View.VISIBLE);
	}
	/**
	 * 最近通讯录
	 */
	public static void showAddressBook(){
		titleText.setText("通讯录");
		imageView1.setBackgroundResource(R.drawable.black);
		imageView2.setBackgroundResource(R.drawable.p);
		imageView2.setVisibility(View.VISIBLE);
	}
	/**
	 *小标题点击事件
	 */
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bt_message_zuijin://最新的处理
			showNowMessage();
			Toast.makeText(context, "最新", Toast.LENGTH_LONG).show();
			break;
		case R.id.bt_message_addressbook://通讯录
			showAddressBook();
			Toast.makeText(context, "通讯录", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}
	}

	/**
	 * 数据源
	 */
	public void messageData(){
		for(int i=1;i<9;i++){
		MessageNotifaction mnf=new MessageNotifaction("石文"+i, "刘学是Sb", PersonalChatUtil.getStringTime(new Date()),1);
		listData.add(mnf);
		}
		MessageNotifaction mnf1=new MessageNotifaction("刘学", "刘学是Sb", PersonalChatUtil.getStringTime(new Date()),2);
		listData.add(mnf1);
	}

	/**
	 * 表示消息显示
	 */
	public class InformationOnItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			System.out.println(listData);
			System.out.println(listData.get(arg2));
			System.out.println(listData.get(arg2).getState());
			// TODO Auto-generated method stub
			if(listData.get(arg2).getState()==1){
				Intent intent =new Intent(getActivity(),PersonalChatActivity.class);
				String s="15927663411";
				intent.putExtra("sendPhone", s);
				startActivity(intent);
			}else if(listData.get(arg2).getState()==2){
				Intent intent =new Intent(getActivity(),PersonalChatActivity.class);
				startActivity(intent);
			}
		}
		
	
	}
}
