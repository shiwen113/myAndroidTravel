package com.gem.message.action;

import java.util.List;

import com.gem.scenery.R;
import com.gem.scenery.entity.Message;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.LinearLayout;
import android.widget.TextView;

public class PersonalMessageAdapt extends ArrayAdapter<Message> {
	private int resourceId;
	private ViewHolder v;
	private List<Message> msglist;
	public PersonalMessageAdapt(Context context, int textViewResourceId,
			List<Message> objects) {
		super(context, textViewResourceId,objects);
		this.resourceId=textViewResourceId;
		msglist=objects;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		Message msg=getItem(position);
		View view;
		if(convertView==null){
			view=LayoutInflater.from(getContext()).inflate(resourceId, null);
			v=new ViewHolder();
			v.leftLayout=(LinearLayout) view.findViewById(R.id.ly_msg_left_layout);
			v.rightLayout=(LinearLayout) view.findViewById(R.id.ly_msg_right_layout);
			
			v.leftMsg=(TextView) view.findViewById(R.id.tv_msg_left);
			v.rightMsg=(TextView) view.findViewById(R.id.tv_msg_right);
			view.setTag(v);
		}else{
			view=convertView;
			v=(ViewHolder) view.getTag();
		}
		if(msg.getType()==Message.TYPE_RECEIVED){
			//如果是收到消息，则显示在左边的消息布局，将右边的消息布局隐藏
			v.leftLayout.setVisibility(view.VISIBLE);//显示
			v.rightLayout.setVisibility(view.GONE);//隐藏
			v.leftMsg.setText(msg.getContent());//显示消息
		}else if(msg.getType()==Message.TYPE_SENT){
			//如果发出的是消息，则显示右边的消息布局，将左边的隐藏
			v.rightLayout.setVisibility(view.VISIBLE);//显示
			v.leftLayout.setVisibility(view.GONE);//隐藏
			v.rightMsg.setText(msg.getContent());//显示消息
		}
		return view;
	}
	class ViewHolder{
		LinearLayout leftLayout;
		LinearLayout rightLayout;
		TextView leftMsg;
		TextView rightMsg;
	}

	
}
