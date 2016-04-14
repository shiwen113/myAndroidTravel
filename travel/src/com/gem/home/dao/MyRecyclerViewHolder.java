package com.gem.home.dao;


import com.gem.home.view.CustomImageView;
import com.gem.scenery.R;
import com.lidroid.xutils.db.sqlite.CursorUtils.FindCacheSequence;

import android.support.v7.widget.RecyclerView.ViewHolder;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class MyRecyclerViewHolder extends ViewHolder {
	TextView userName, teamName,limitSex, allDay, place, intro,gotime,chakanliang;
	CustomImageView imageView;
	LinearLayout ll;
//	ImageView lv;

	public MyRecyclerViewHolder(View itemView) {
		super(itemView);

		teamName = (TextView) itemView.findViewById(R.id.itview_teamName);
		place = (TextView) itemView.findViewById(R.id.itview_place);
		allDay = (TextView) itemView.findViewById(R.id.itview_allDay);
		intro = (TextView) itemView.findViewById(R.id.itview_intro);
		userName=(TextView) itemView.findViewById(R.id.itview_userName);
//		imageView=(CustomImageView) itemView.findViewById(R.id.iv_civ);
		imageView=(CustomImageView) itemView.findViewById(R.id.iv_civ_uer);
		gotime=(TextView) itemView.findViewById(R.id.tv_show_date);
		chakanliang=(TextView) itemView.findViewById(R.id.tv_chakanliang);
		ll=(LinearLayout) itemView.findViewById(R.id.ll_linear_scrol_view);
//		lv=(ImageView) itemView.findViewById(R.id.iv_img_picture);
		limitSex=(TextView) itemView.findViewById(R.id.tv_sex_limit);
	}

}
