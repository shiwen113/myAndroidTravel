package com.gem.home.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;

import com.gem.home.dao.ListViewAdaptSearchData;
import com.gem.home.until.PublishTravel;
import com.gem.scenery.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

public class SearchTravelConditionActivity extends Activity implements OnClickListener{
	private ListView lv;//
	private ImageButton imgBack;//返回
	private List<PublishTravel> list;//查询结果
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub.

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_serch_travel_condition);
		lv=(ListView) findViewById(R.id.lv_serch_travel_result);
		imgBack=(ImageButton) findViewById(R.id.ib_travel_home_back1);
		imgBack.setOnClickListener(this);
		
		//获取查询的值
		Intent intent =getIntent();
		String reuslt=intent.getStringExtra("result");
		if(reuslt!=null&&!reuslt.equals("")){
		Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		Type type=new TypeToken<List<PublishTravel>>(){}.getType();
		list =gson.fromJson(reuslt, type);
		ListViewAdaptSearchData adapter=new ListViewAdaptSearchData(list,this);
		lv.setAdapter(adapter);
		}else {
			list=new ArrayList<PublishTravel>();
		}
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.finish();
	}
}
