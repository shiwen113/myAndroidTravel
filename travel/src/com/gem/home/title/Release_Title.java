package com.gem.home.title;

import com.gem.home.activity.Travels_One_Activity;
import com.gem.home.dao.MyApplication;
import com.gem.home.view.CustomImageView;
import com.gem.scenery.R;

import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;

public class Release_Title extends LinearLayout implements OnClickListener {
	private Context context = MyApplication.getContext();

	private ImageButton back;

	public Release_Title(Context context, AttributeSet attrs) {
		super(context, attrs);

		LayoutInflater.from(context).inflate(R.layout.release_title, this);

		back = (ImageButton) findViewById(R.id.back_btn);
		back.setOnClickListener(this);

	}

	public void onClick(View v) {
		Context context = getContext();
		switch (v.getId()) {

		case R.id.back_btn:

			Toast.makeText(context, "back", Toast.LENGTH_SHORT).show();
			((Activity) context).finish();
			break;
		default:
			break;
		}

	}

}
