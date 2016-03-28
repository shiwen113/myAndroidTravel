package com.gem.mine.activity;



import com.gem.scenery.R;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;

import android.widget.ImageView;
import android.widget.TextView;

public class TroopsActivity extends Activity implements OnClickListener {
	ImageView BT_TroopsActivity_RETURN;
	//我的旅行队 
	TextView TV_TroopsActivity_information;
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
		//返回监听事件
		BT_TroopsActivity_RETURN=(ImageView) findViewById(R.id.BT_TroopsActivity_RETURN);
		BT_TroopsActivity_RETURN.setOnClickListener(this);
		//资料的监听事件
		TV_TroopsActivity_information=(TextView) findViewById(R.id.TV_TroopsActivity_information);
		TV_TroopsActivity_information.setOnClickListener(this);
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
    switch(v.getId()){
		
       //点击返回按钮，页面跳转至个人主页
		case R.id.BT_TroopsActivity_RETURN:
			Intent intent =new Intent(this,MyloginActivity.class);
			startActivity(intent);
		//	finish();
			break;
			//点击资料跳转至资料界面
		case R.id.TV_TroopsActivity_information:
			//PersonalinformationActivity
			Intent intent1 =new Intent(this,MineTravelTeamDataActivity.class);
			startActivity(intent1);
			
			break;
		
		}
	}
	}
