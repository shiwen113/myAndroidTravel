package com.gem.scenery.action;

import java.util.List;
import java.util.Map;

import com.gem.scenery.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	private ViewHolder holder;
	private List<Map<String, String>> list;   
	private Context context;

	public ListViewAdapter(Context context, List<Map<String, String>> listmap) {
		this.list = listmap;
		this.context = context;
	}

	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = LayoutInflater.from(context).inflate(
					R.layout.action_scenery_jinse, null);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.text.setText(list.get(position).get("name"));
		return convertView;
	}

	private static class ViewHolder {
		TextView text;
	}

}
