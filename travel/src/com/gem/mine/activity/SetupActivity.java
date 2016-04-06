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
import android.widget.Button;
import android.widget.ImageView;

public class SetupActivity extends Activity implements OnClickListener {

	ImageView BT__SetupActivity_backtrack;
	//关于设置页面
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
//	        //透明导航栏  
//	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
//	  
		setContentView(R.layout.activity_setup);
		BT__SetupActivity_backtrack=(ImageView) findViewById(R.id.BT__SetupActivity_backtrack);
		BT__SetupActivity_backtrack.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		//点击返回按钮
		case R.id.BT__SetupActivity_backtrack:	
//			Intent intent1 =new Intent(this,MyloginActivity.class);
//			startActivity(intent1);
		  finish();
			break;
			//点击，页面跳转至推送通知页面
		case R.id.TV_SetupActivity_push_Notification:
			Intent intent =new Intent(this,InformActivity.class);
			startActivity(intent);
			break;
		}
		
	}



}
