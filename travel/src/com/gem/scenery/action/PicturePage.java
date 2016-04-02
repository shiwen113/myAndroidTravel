package com.gem.scenery.action;

import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.gem.scenery.R;

public class PicturePage{
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
		holder.pinglun=(ImageButton) v.findViewById(R.id.btn_scenery_pinglun);
		holder.numberPinglun=(TextView) v.findViewById(R.id.tv_comment_num);
		holder.fenxiang=(TextView) v.findViewById(R.id.tv_share_zheke);
	}
	public static class ViewHolder {
		public TextView travelName;//队名
		public TextView travelPoint;//景点
		public TextView issuePicture;//发布时间
		public ImageView picture;//发表的旅图
		public ImageView userPicture;//用户头像
		public TextView userName;//用户名
		public TextView number;//点赞次数
		public ImageButton dainzan;//点赞
		public ImageButton pinglun;//评论
		public TextView numberPinglun;//评论次数
		public TextView fenxiang;//分享此刻
	}
}
