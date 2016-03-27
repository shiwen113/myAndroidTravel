package com.gem.home.title;


import com.gem.home.activity.Travels_One_Activity;
import com.gem.home.view.CustomImageView;
import com.gem.scenery.R;

import android.R.color;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class Release_Title extends LinearLayout implements OnClickListener {
	private LinearLayout left;
	private TextView title,  leftText,rightText;
	private static final int COLOR_TITLE = Color.parseColor("#FFFFFF");
	@SuppressWarnings("unused")
	private static final int COLOR_BACKGROUND = Color.parseColor("#6699CC");
	@SuppressWarnings("unused")
	private CustomImageView leftView,rightView;

	public Release_Title(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title, this);

		
		//隐藏右边和左边文字
		rightText=(TextView) findViewById(R.id.righttext);
		rightText.setText("");
		rightView=(CustomImageView) findViewById(R.id.rightview);
		rightView.setVisibility(View.GONE);

		leftText = (TextView) findViewById(R.id.lefttext);
		leftText.setVisibility(View.GONE);

		left = (LinearLayout) findViewById(R.id.home_left);
		title = (TextView) findViewById(R.id.home_title);
		title.setTextColor(COLOR_TITLE);
		title.setText("约游");
	

		left.setOnClickListener(this);
	}

	public void onClick(View v) {
	Context context=getContext();
		switch (v.getId()) {

		case R.id.home_left:
			
		Toast.makeText(context, "back", Toast.LENGTH_SHORT).show();

			break;
		default:
			break;
		}

	}

}
