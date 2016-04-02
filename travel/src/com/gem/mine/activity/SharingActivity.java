package com.gem.mine.activity;



import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;

import com.gem.mine.action.SharePictureBaseAdapt;
import com.gem.scenery.R;
import com.gem.scenery.entity.SharePicture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class SharingActivity extends Activity implements OnClickListener{

	private ImageButton back;
	private ListView lv;
	private List<SharePicture> list;
	private SharePictureBaseAdapt adapter ;
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
		setContentView(R.layout.activity_sharing);
		back=(ImageButton) findViewById(R.id.ib_back);
		lv=(ListView) findViewById(R.id.lv_mine_listview);
		back.setOnClickListener(this);
		Intent intent=getIntent();
		String s=intent.getStringExtra("result");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		Type type = new TypeToken<List<SharePicture>>(){}.getType();
		list=gson.fromJson(s,type);
		if(list!=null&&!list.equals("")){
		adapter =new SharePictureBaseAdapt(list,this);
		lv.setAdapter(adapter);
		}
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		//点击返回按钮，页面跳转至个人主页
		case R.id.ib_back:
			finish();
			break;
		}	
	}

	
}
