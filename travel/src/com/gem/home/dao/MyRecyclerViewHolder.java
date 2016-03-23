package com.gem.home.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.gem.home.view.CustomImageView;
import com.gem.scenery.R;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.TextView;

public class MyRecyclerViewHolder extends ViewHolder {
	TextView userName, teamName, allDay, place, intro;
	CustomImageView imageView;

	public MyRecyclerViewHolder(View itemView) {
		super(itemView);

		teamName = (TextView) itemView.findViewById(R.id.itview_teamName);
		place = (TextView) itemView.findViewById(R.id.itview_place);
		allDay = (TextView) itemView.findViewById(R.id.itview_allDay);
		intro = (TextView) itemView.findViewById(R.id.itview_intro);
		userName=(TextView) itemView.findViewById(R.id.itview_userName);
		imageView=(CustomImageView) itemView.findViewById(R.id.iv_civ);
	
	}

}
