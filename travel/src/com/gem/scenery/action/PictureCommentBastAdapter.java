package com.gem.scenery.action;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gem.home.until.ToolDao;
import com.gem.scenery.R;
import com.gem.scenery.entity.PictureComment;
public class PictureCommentBastAdapter extends BaseAdapter {
	private List<PictureComment> list;
	private Context context;
	private ViewHolder viewHolder;
	public PictureCommentBastAdapter(List<PictureComment> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View v, ViewGroup arg2) {
		// TODO Auto-generated method stub
		if(v==null){
			viewHolder =new ViewHolder();
			v=LayoutInflater.from(context).inflate(R.layout.travelcommnet_adapter,null);
			viewHolder.textView=(TextView) v.findViewById(R.id.travelcommnet_tv1);
			viewHolder.textView2=(TextView) v.findViewById(R.id.travelcommnet_tv2);
			v.setTag(viewHolder);
			}else {
				viewHolder=(ViewHolder) v.getTag();
			}
		PictureComment pc=list.get(arg0);
		if(pc!=null){
			viewHolder.textView.setText(pc.getCommentNotes());
			viewHolder.textView2.setText(ToolDao.setTimedate1(pc.getPhotoTime()));
		}
		return v;
	}
	static class ViewHolder{
		TextView textView;
	    TextView textView2;
	}
}
