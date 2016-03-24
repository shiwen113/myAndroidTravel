package com.gem.home.title;

import com.gem.home.activity.Reference_Activity;
import com.gem.home.activity.Travels_One_Activity;
import com.gem.home.view.CustomImageView;
import com.gem.scenery.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class HomeTitle_Activity extends LinearLayout implements OnClickListener {
	private LinearLayout rightBtn, leftBtn;
	private TextView title;
	private Intent intent;
	private static final int COLOR_TITLE = Color.parseColor("#FFFFFF");

	public HomeTitle_Activity(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title, this);

		rightBtn = (LinearLayout) findViewById(R.id.home_right);
		leftBtn = (LinearLayout) findViewById(R.id.home_left);
		title = (TextView) findViewById(R.id.home_title);
		title.setTextColor(COLOR_TITLE);
		rightBtn.setOnClickListener(this);
		leftBtn.setOnClickListener(this);
	}

	public void onClick(View v) {

		Context context = getContext();
		switch (v.getId()) {
		case R.id.home_right:
			Toast.makeText(context, "发布新约游", Toast.LENGTH_SHORT).show();
			intent = new Intent(context, Travels_One_Activity.class);
			context.startActivity(intent);

			break;
		case R.id.home_left:

			Toast.makeText(context, "查询", Toast.LENGTH_SHORT).show();
			intent = new Intent(context, Reference_Activity.class);
			context.startActivity(intent);
			break;
		default:
			break;
		}

	}

}
