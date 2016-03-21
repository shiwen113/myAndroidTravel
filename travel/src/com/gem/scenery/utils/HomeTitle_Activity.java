package com.gem.scenery.utils;

import com.gem.scenery.R;
import com.gem.scenery.action.SceneryPopWindow;
import com.gem.scenery.action.SceneryTile;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

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
		leftBtn = (LinearLayout) findViewById(R.id.home_left);
		title=(TextView) findViewById(R.id.home_title);
		title.setTextColor(COLOR_TITLE);
		rightBtn.setOnClickListener(this);
		leftBtn.setOnClickListener(this);
	}

	public void onClick(View v) {
		Context context = getContext();
		SceneryTile st=new SceneryTile(activity,v);
		switch (v.getId()) {
		case R.id.home_right://照相机
			st.onCamera();//弹框
			Toast.makeText(context, "照相机", Toast.LENGTH_LONG).show();
			break;
		case R.id.home_left://搜索
			Toast.makeText(context, "搜索", Toast.LENGTH_LONG).show();
			break;
		default:
			break;
		}

	}



}
