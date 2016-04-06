package com.gem.mine.activity;



import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ApplicationErrorReport.CrashInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.home.dao.MyApplication;
import com.gem.home.until.PublishTravel;
import com.gem.message.activity.RongyunDemo;
import com.gem.mine.action.ListViewJoinTravel;
import com.gem.mine.action.ListViewManagerTravel;
import com.gem.mine.action.MineViewChage;
import com.gem.mine.action.MineViewPage;
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

public class TroopsActivity extends Activity implements OnClickListener {
	private ImageView back;//返回
	private TextView title;//标题
	private TextView edit;//编辑
	private TextView myTravel;//我的旅行队
	private TextView joinTravelteam;//加入的旅行队
	private ImageView myIv;
	private ImageView joinIv;
	private LayoutInflater inflater;
	private ViewPager vp;
	private List<View> views;
	private TextView travelManager;//我创建的旅行队
	private TextView joinText;//加入的旅行队
	private ListView createTravel;//创建的旅行队
	private ListView joinTravel;//加入的旅行队
	private List<PublishTravel> list;
	private List<PublishTravel> createList=new ArrayList<PublishTravel>();
	private List<PublishTravel> joinList=new ArrayList<PublishTravel>();
	private View v1;
	private View v2;
	private int i=0;
	private MyApplication m;
	private Context context;
	private PopupWindow popupWindow;
	private RongyunDemo ryun;
	@SuppressWarnings("deprecation")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
//	        //透明导航栏  
//	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
//	  
		//setContentView(R.layout.activity_mypageforgetpassword);
		setContentView(R.layout.activity_troops);
		context=getApplication();
		m=(MyApplication) getApplicationContext();
		views=new ArrayList<View>();
		inflater=LayoutInflater.from(this);
		v1=inflater.inflate(R.layout.action_listview_string_my_travel, null);
		v2=inflater.inflate(R.layout.action_join_string_travel, null);
		initView();
		views.add(v1);
		views.add(v2);
		MineViewPage mvp=new MineViewPage(views);
		backgroundMy();
		Intent intent=getIntent();
		String s=intent.getStringExtra("result");
		Type type=new TypeToken<List<PublishTravel>>(){}.getType();
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		list =gson.fromJson(s, type);
		showData();
		vp.setAdapter(mvp);
		MineViewChage mvc=new MineViewChage(this,myIv,joinIv);
		vp.setOnPageChangeListener(mvc);
		createTravel.setAdapter(new ListViewManagerTravel(createList,this));
		joinTravel.setAdapter(new ListViewJoinTravel(joinList,this));
	}
	public void initView(){
		back=(ImageView) findViewById(R.id.im_back_travel_team);
		back.setOnClickListener(this);
		title=(TextView) findViewById(R.id.tv_travel_team_my);
		title.setText("我的旅行队");
		edit=(TextView) findViewById(R.id.tv_edit_travel_team);
		edit.setOnClickListener(this);
		myTravel=(TextView) findViewById(R.id.tv_string_my_travel);
		joinTravelteam=(TextView) findViewById(R.id.tv_string_join_travel);
		myTravel.setOnClickListener(this);
		joinTravelteam.setOnClickListener(this);
		myIv=(ImageView) findViewById(R.id.im_string_my_travel);
		joinIv=(ImageView) findViewById(R.id.im_string_join_travel);
		vp=(ViewPager) findViewById(R.id.vp_travel_data);
		travelManager=(TextView) v1.findViewById(R.id.tv_my_travel_manager);
		joinText=(TextView) v2.findViewById(R.id.tv_my_join_tavel_team);
		createTravel=(ListView) v1.findViewById(R.id.lv_my_travel_manager);
		joinTravel=(ListView) v2.findViewById(R.id.lv_my_join_tavel_team);
		createTravel.setOnItemClickListener(new MyCreateTravel());
		joinTravel.setOnItemClickListener(new MyjoinTravel());
	}

	/**
	 * 显示
	 */
	public void showData(){
		title.setText("我的旅行队");
		if(list!=null&&!list.equals("")&&list.size()!=0){
			for (PublishTravel pt : list) {
				if(pt.getLd().getLd()==m.getLd().getLd()){
					createList.add(pt);
				}else{
					joinList.add(pt);
				}
			}
		}
		if(createList!=null){
			travelManager.setText("我创建的旅行队("+createList.size()+")");
		}else{
			travelManager.setText("我创建的旅行队("+0+")");
		}
		if(joinList!=null){
			joinText.setText("我加入的旅行队("+joinList.size()+")");
		}else{
			joinText.setText("我加入的旅行队("+0+")");
		}
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
    switch(v.getId()){
		case R.id.tv_edit_travel_team://编辑
			
			break;
		case R.id.im_back_travel_team://返回
			finish();
			break;
		case R.id.tv_string_my_travel://我的旅行队
			i=0;
			backgroundMy();
			vp.setCurrentItem(i);
			break;
		case R.id.tv_string_join_travel://我加入的旅行队
			i=1;
			backgroundJoin();
			vp.setCurrentItem(i);
			break;
		}
	}

	/**
	 * 我加入的图片设置
	 */
	public void backgroundMy(){
		myIv.setBackgroundResource(R.drawable.p);
		joinIv.setBackgroundResource(R.drawable.black);
	}
	/**
	 * 我加入的图片设置
	 */
	public void backgroundJoin(){
		myIv.setBackgroundResource(R.drawable.black);
		joinIv.setBackgroundResource(R.drawable.p);
	}
	
	String urlDelete="";
	public void sendDelete(final PublishTravel pt,View arg1) {
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
		TextView del=(TextView) v.findViewById(R.id.tv_delete);
		del.setText("退队");
		del.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				HttpUtils http=new HttpUtils();
				RequestParams params=new RequestParams();
				params.addBodyParameter("fd",String.valueOf(pt.getLd().getLd()));
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
//							list.clear();
//							list.addAll((List<PersonalData>)gson.fromJson(restult, type));
//							adapter.notifyDataSetChanged();
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
//				聊天
//				RongIM.getInstance().startConversation(context, Conversation.ConversationType.PRIVATE, pt.getLd().getLd()+"", "聊天标题");
				RongIM.getInstance().startGroupChat(TroopsActivity.this, pt.getTd()+"", null);
			}
		});
	}
	
	/**
	 * 我的旅行队的Item
	 */
	public class MyCreateTravel implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			ryun=new RongyunDemo(createList.get(arg2));
			ryun.createQun();
			ryun.addQun();
			sendDelete(createList.get(arg2),arg1);
		}
		
	}
	
	/**
	 * 我加入的旅行队Item
	 */
	public class MyjoinTravel implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			ryun=new RongyunDemo(joinList.get(arg2));
			ryun.createQun();
			sendDelete(joinList.get(arg2),arg1);
		}
		
	}
}
