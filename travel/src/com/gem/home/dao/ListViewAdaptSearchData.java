package com.gem.home.dao;

import java.util.List;

import com.gem.home.until.PublishTravel;
import com.gem.home.until.ToolDao;
import com.gem.scenery.R;
import com.lidroid.xutils.BitmapUtils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class ListViewAdaptSearchData extends BaseAdapter {
	private List<PublishTravel> list;//查询结果
	private Context context;
	private MyRecyclerViewHolder holder;
	
	public ListViewAdaptSearchData(List<PublishTravel> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(list!=null){
		return list.size();
		}else{
			return 0;
		}
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
		if(v==null){
			v=LayoutInflater.from(context).inflate(R.layout.item, null);
			holder=new MyRecyclerViewHolder(v);
			v.setTag(holder);
		}else{
			holder =(MyRecyclerViewHolder) v.getTag();
		}
		PublishTravel pt=list.get(arg0);
		if(pt!=null){
			holder.teamName.setText(pt.getTeamName());
			
			holder.intro.setText(pt.getIntro());
			holder.allDay.setText("全程" + String.valueOf(pt.getAllDay()) + "天");
			holder.place.setText(pt.getStatPoint() + "--" + pt.getDestination());
			holder.userName.setText(pt.getLd().getUserName());
			holder.gotime.setText(ToolDao.setTimedate1(pt.getStartTime()));
			holder.chakanliang.setText("点击量："+pt.getToView());
			String url =pt.getUrlLifePicture();
			sendPictureFromServer(holder,url);
		}
		return v;
	}
	
	/**
	 * 获取生活照
	 */
	public void sendPictureFromServer(MyRecyclerViewHolder arg0,String lifPicture){
		String[] picture=lifPicture.split(";");
		BitmapUtils bpu=new BitmapUtils(context);
		for(int i=0;picture.length>i;i++){
			String url=picture[i];
			bpu.display(arg0.ll,"http://10.201.1.12:8080/gotravel/"+"TravelTeam/"+url);
		}
	}
}
