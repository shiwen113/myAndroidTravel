package com.gem.message.action;

import java.util.LinkedList;
import java.util.List;

import com.gem.home.dao.MyApplication;
import com.gem.message.PersonalChatActivity;
import com.gem.message.entity.ChatMsgEntity;
import com.gem.message.entity.Message;
import com.gem.message.entity.RecentChatEntity;
import com.gem.message.entity.User;
import com.gem.scenery.R;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonalMessageAdapt extends BaseAdapter {
//	private int resourceId;
//	private ViewHolder v;
//	private List<Message> msglist;
	private Context context;
//	private LinkedList<RecentChatEntity> list;
	private MyApplication application;
	private LayoutInflater inflater;
	private static final int ITEMCOUNT = 2;// 消息类型的总数
	private List<ChatMsgEntity> coll;// 消息对象数组
	

public PersonalMessageAdapt(Context context, List<ChatMsgEntity> coll) {
	this.context = context;
	this.application = (MyApplication) context.getApplicationContext();
	this.coll = coll;
	this.inflater = LayoutInflater.from(context);
	}

	public static interface IMsgViewType {
		int IMVT_COM_MSG = 0;// 收到对方的消息
		int IMVT_TO_MSG = 1;// 自己发送出去的消息
	}
	
//	public PersonalMessageAdapt(Context context, int textViewResourceId,
//			List<Message> objects) {
//		super(context, textViewResourceId,objects);
//		this.resourceId=textViewResourceId;
//		msglist=objects;
//	}
	/*@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
//		Message msg=getItem(position);
//		View view;
//		if(convertView==null){
//			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
//			v=new ViewHolder();
//			v.leftLayout=(LinearLayout) view.findViewById(R.id.ly_msg_left_layout);
//			v.rightLayout=(LinearLayout) view.findViewById(R.id.ly_msg_right_layout);
//			
//			v.leftMsg=(TextView) view.findViewById(R.id.tv_msg_left);
//			v.rightMsg=(TextView) view.findViewById(R.id.tv_msg_right);
//			view.setTag(v);
//		}else{
//			view=convertView;
//			v=(ViewHolder) view.getTag();
//		}
//		if(msg.getType()==Message.TYPE_RECEIVED){
//			//如果是收到消息，则显示在左边的消息布局，将右边的消息布局隐藏
//			v.leftLayout.setVisibility(view.VISIBLE);//显示
//			v.rightLayout.setVisibility(view.GONE);//隐藏
//			v.leftMsg.setText(msg.getContent());//显示消息
//		}else if(msg.getType()==Message.TYPE_SENT){
//			//如果发出的是消息，则显示右边的消息布局，将左边的隐藏
//			v.rightLayout.setVisibility(view.VISIBLE);//显示
//			v.leftLayout.setVisibility(view.GONE);//隐藏
//			v.rightMsg.setText(msg.getContent());//显示消息
//		}
		return null;
	}*/
	/*class ViewHolder{
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMsg;
		TextView rightMsg;
	}*/
	public int getCount() {
		return coll.size();
	}

	public Object getItem(int position) {
		return coll.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	/**
	 * 得到Item的类型，是对方发过来的消息，还是自己发送出去的
	 */
	public int getItemViewType(int position) {
		ChatMsgEntity entity = coll.get(position);

		if (entity.getMsgType()) {// 收到的消息
			return IMsgViewType.IMVT_COM_MSG;
		} else {// 自己发送的消息
			return IMsgViewType.IMVT_TO_MSG;
		}
	}

	/**
	 * Item类型的总数
	 */
	public int getViewTypeCount() {
		return ITEMCOUNT;
	}

	public View getView(int position, View convertView, ViewGroup parent) {

		ChatMsgEntity entity = coll.get(position);
		boolean isComMsg = entity.getMsgType();

		ViewHolder viewHolder = null;
		if (convertView == null) {
			if (isComMsg) {
				convertView = inflater.inflate(
						R.layout.chatting_item_msg_text_left, null);
			} else {
				convertView = inflater.inflate(
						R.layout.chatting_item_msg_text_right, null);
			}

			viewHolder = new ViewHolder();
			viewHolder.tvSendTime = (TextView) convertView
					.findViewById(R.id.tv_sendtime);
			viewHolder.tvUserName = (TextView) convertView
					.findViewById(R.id.tv_username);
			viewHolder.tvContent = (TextView) convertView
					.findViewById(R.id.tv_chatcontent);
			viewHolder.icon = (ImageView) convertView
					.findViewById(R.id.iv_userhead);
			viewHolder.isComMsg = isComMsg;

			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.tvSendTime.setText(entity.getDate());
		viewHolder.tvUserName.setText(entity.getName());
		viewHolder.tvContent.setText(entity.getMessage());
//		viewHolder.icon.setImageResource(imgs[entity.getImg()]);
		return convertView;
	}

	static class ViewHolder {
		public TextView tvSendTime;
		public TextView tvUserName;
		public TextView tvContent;
		public ImageView icon;
		public boolean isComMsg = true;
	}

	
}
