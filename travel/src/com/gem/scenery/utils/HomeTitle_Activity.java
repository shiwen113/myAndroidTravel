package com.gem.scenery.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.scenery.PictureUpLoadActivity;
import com.gem.scenery.R;

public class HomeTitle_Activity extends LinearLayout implements OnClickListener {
	private LinearLayout rightBtn,leftBtn;
	private TextView title;
	private static final int COLOR_TITLE =   Color.parseColor("#FFFFFF");
	private Activity activity;
	public HomeTitle_Activity(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.home_title, this);
		activity=(Activity) context;
//		new SceneryPopWindow(context);
		rightBtn = (LinearLayout) findViewById(R.id.home_right);
//		leftBtn = (LinearLayout) findViewById(R.id.home_left);
		title=(TextView) findViewById(R.id.home_title);
		title.setTextColor(COLOR_TITLE);
		rightBtn.setOnClickListener(this);
//		leftBtn.setOnClickListener(this);
	}

	public void onClick(View v) {
		Context context = getContext();
		switch (v.getId()) {
		case R.id.home_right://照相机
			Intent intent =new Intent(activity,PictureUpLoadActivity.class);
			context.startActivity(intent);
			break;
		case R.id.home_left://搜索
			Toast.makeText(context, "搜索", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}

	}



}
