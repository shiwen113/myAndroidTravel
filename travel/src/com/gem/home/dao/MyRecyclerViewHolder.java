package com.gem.home.dao;


import com.gem.home.view.CustomImageView;
import com.gem.scenery.R;
import com.lidroid.xutils.db.sqlite.CursorUtils.FindCacheSequence;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

public class MyRecyclerViewHolder extends ViewHolder {
	TextView userName, teamName, allDay, place, intro;
	CustomImageView imageView;
	ScrollView sv;

	public MyRecyclerViewHolder(View itemView) {
		super(itemView);

		teamName = (TextView) itemView.findViewById(R.id.itview_teamName);
		place = (TextView) itemView.findViewById(R.id.itview_place);
		allDay = (TextView) itemView.findViewById(R.id.itview_allDay);
		intro = (TextView) itemView.findViewById(R.id.itview_intro);
		userName=(TextView) itemView.findViewById(R.id.itview_userName);
		imageView=(CustomImageView) itemView.findViewById(R.id.iv_civ);
		sv=(ScrollView) itemView.findViewById(R.id.sv_scrol_img);
	
	}

}
