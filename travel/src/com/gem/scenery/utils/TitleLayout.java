package com.gem.scenery.utils;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.gem.scenery.R;

public class TitleLayout extends LinearLayout{
	private Button titleBack;
	private Button titleEdit;
	public TitleLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.action_my_title, this);
		titleBack=(Button) findViewById(R.id.bt_title_back);
		titleEdit=(Button)findViewById(R.id.bt_title_edit);
		titleBack.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				((Activity)getContext()).finish();
			}
		});
		titleEdit.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Toast.makeText(getContext(), "编辑", 1).show();
			}
		});
	}


}
