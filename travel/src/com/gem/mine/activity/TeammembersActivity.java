package com.gem.mine.activity;



import com.gem.scenery.R;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.Window;


import android.widget.Button;

public class TeammembersActivity extends Activity implements OnClickListener{

	Button BT_TeammembersActivity_return;
	//旅行队成员页面  .....
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		//用来消除顶部系统导航
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
//	        //透明导航栏  
//	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
//	  
		setContentView(R.layout.activity_mypageforgetpassword);
		setContentView(R.layout.activity_teammembers);
		BT_TeammembersActivity_return=(Button) findViewById(R.id.BT_TeammembersActivity_return);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		//点击按钮返回
		case R.id.BT_TeammembersActivity_return:
			finish();
			break;
		
		}
		
	}




}
