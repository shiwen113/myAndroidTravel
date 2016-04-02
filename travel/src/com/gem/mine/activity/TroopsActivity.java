package com.gem.mine.activity;



import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.gem.home.until.PublishTravel;
import com.gem.mine.action.ListViewJoinTravel;
import com.gem.mine.action.ListViewManagerTravel;
import com.gem.mine.action.MineViewChage;
import com.gem.mine.action.MineViewPage;
import com.gem.scenery.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

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
	}

	/**
	 * 显示
	 */
	public void showData(){
		title.setText("我的旅行队");
		if(!list.equals("")&&list!=null&&list.size()!=0){
			for (PublishTravel pt : list) {
				if(pt.getLd().getLd()==17){
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
}
