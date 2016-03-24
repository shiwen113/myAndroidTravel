package com.gem.home.activity;



import com.gem.scenery.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.LinearLayout;

public class Item_Activity extends Activity {
private static final String jion1="申请加入";
private static final String jion2="已加入";
private Button commnet,join;
private LinearLayout linear_btn;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_item);
		linear_btn=(LinearLayout) findViewById(R.id.linear_btn);
		//linear_btn.setal
		commnet=(Button) findViewById(R.id.item_comment);
		join=(Button) findViewById(R.id.item_join);
	}

}
