package com.gem.home.activity;

import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.gem.scenery.R;
import com.gem.scenery.entity.PersonalData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;

public class LookAllPeopleNumberActivity extends Activity implements OnClickListener{
	private TextView allNum;//显示当前人数
	private TextView title;
	private ImageView back;//返回
	private LinearLayout ll;
	private List<PersonalData> list;
	private int num;//旅行队规定人数
	private String time;//出发时间
	private String point;//目的地
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_look_at_all);
		initView();
		Intent intent=getIntent();
		Type type=new TypeToken<List<PersonalData>>(){}.getType();
		list =new Gson().fromJson(intent.getStringExtra("result"),
				type);
		num=intent.getIntExtra("num", 0);
		time=intent.getStringExtra("time");
		point=intent.getStringExtra("point");
		showData();
		sendTitlePicture();
	}
	/**
	 * 初始化     
	 */
	public void initView(){
		allNum=(TextView) findViewById(R.id.tv_look_perpeo_num);
		title=(TextView) findViewById(R.id.tv_look_all);
		back=(ImageView) findViewById(R.id.im_back_look_all);
		back.setOnClickListener(this);
		ll=(LinearLayout) findViewById(R.id.ll_look_all_people);
	}
	/**
	 * 显示数据
	 */
	public void showData(){
		title.setText(time+"--"+point);
		if(list!=null){
			allNum.setText(String.valueOf(list.size())+"/"+num);
		}else{
			if(num!=0){
				allNum.setText(String.valueOf(0)+"/"+num);
			}else{
				allNum.setText(String.valueOf(0)+"/"+"不限");
			}
		}
		
	}
	/**
	 * 请求图像
	 */
	public void sendTitlePicture(){
		BitmapUtils bu=new BitmapUtils(getApplication());
		ll.removeAllViews();
		if(list!=null){
		for (PersonalData pd : list) {
			String url=pd.getUriUpLoadPicture();
			ImageView iv=new ImageView(getApplication());
			TextView tv=new TextView(getApplication());
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(200, 200);
		    iv.setLayoutParams(layoutParams);
			bu.display(iv, url);
			tv.setText(pd.getLd().getUserName());
			ll.addView(iv);
			ll.addView(tv);
		}
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.im_back_look_all:
			finish();
			break;

		default:
			break;
		}
		
	}
}
