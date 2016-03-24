package com.gem.home.activity;

import com.gem.scenery.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;

public class SearchTravelConditionActivity extends Activity implements OnClickListener{
	private ListView lv;
	private ImageButton imgBack;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub.

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_serch_travel_condition);
		lv=(ListView) findViewById(R.id.lv_serch_travel_result);
		imgBack=(ImageButton) findViewById(R.id.ib_travel_home_back1);
		imgBack.setOnClickListener(this);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.finish();
	}
}
