package com.gem.scenery.action;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gem.scenery.R;

public class PicturePage {
	private ViewHolder holder;
	private View v;
	public PicturePage(View v,ViewHolder holder) {
		this.v=v;
		this.holder=holder;
	}
	public void initView(){
		holder.travelName=(TextView) v.findViewById(R.id.Search_tv);
		holder.travelPoint=(TextView) v.findViewById(R.id.tv_point);
		holder.issuePicture=(TextView) v.findViewById(R.id.iv_issue_picture);
		holder.picture=(ImageView) v.findViewById(R.id.iv_picture);
		holder.userPicture=(ImageView) v.findViewById(R.id.iv_user_picture);
		holder.userName=(TextView) v.findViewById(R.id.tv_user_name);
		holder.number=(TextView) v.findViewById(R.id.Search_tv_Adapter);
		holder.dainzan=(ImageButton) v.findViewById(R.id.Search_ib);
		holder.pinglun=(Button) v.findViewById(R.id.btn_scenery_pinglun);
	}
	public static class ViewHolder {
		TextView travelName;//队名
		TextView travelPoint;//景点
		TextView issuePicture;//发布时间
		ImageView picture;//发表的旅图
		ImageView userPicture;//用户头像
		TextView userName;//用户名
		TextView number;//
		ImageButton dainzan;//点赞
		Button pinglun;//评论
	}
}
