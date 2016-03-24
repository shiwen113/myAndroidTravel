package com.gem.message.action;

import java.util.List;

import com.gem.message.entity.MessageNotifaction;
import com.gem.scenery.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class MessageHomeInformationListView extends BaseAdapter{
	private Context context;
	private List<MessageNotifaction> list;
	private MessageNotifaction mnf;
	public MessageHomeInformationListView(Context context,List<MessageNotifaction> list) {
		this.context=context;
		this.list=list;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder ;
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.activity_message_infotmation_listview, null);
			holder.userName = (TextView) convertView.findViewById(R.id.tv_message_inf_username);
			holder.infContent = (TextView) convertView.findViewById(R.id.tv_message_inf_content);
			holder.agree = (TextView) convertView.findViewById(R.id.tv_message_agree);
			holder.userName.setText(list.get(position).getUserName());
			holder.infContent.setText(list.get(position).getNotifaction());
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		mnf=list.get(position);
		if(mnf!=null){
			holder.agree.setText(mnf.getTime());
			holder.userName.setText(mnf.getUserName());
			holder.infContent.setText(mnf.getNotifaction());
		}
		
		return convertView;
	}
	private static class ViewHolder {
		TextView userName;
		TextView infContent;
		TextView agree;
//		View changeFragment;
	}
	  
}
