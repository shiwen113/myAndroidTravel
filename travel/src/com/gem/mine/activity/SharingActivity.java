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
import android.widget.ImageView;

public class SharingActivity extends Activity {

	ImageView im_SharingActivity_return;
	//我的分享页面设计
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
//	        //透明导航栏  
//	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
//	  
		setContentView(R.layout.activity_mypageforgetpassword);
		setContentView(R.layout.activity_sharing);
	}
	public void click(View v){
		switch(v.getId()){
		
		//点击返回按钮，页面跳转至个人主页
		case R.id.im_SharingActivity_return:
			finish();
			break;
		
		}
		
	}
	
}
