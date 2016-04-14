package com.gem.mine.action;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

import com.gem.home.dao.MyApplication;
import com.gem.home.until.LoginData;
import com.gem.home.until.ToolDao;
import com.gem.scenery.R;
import com.gem.scenery.action.PicturePage;
import com.gem.scenery.action.Travel_team_all_data;
import com.gem.scenery.action.PicturePage.ViewHolder;
import com.gem.scenery.entity.ClickPraise;
import com.gem.scenery.entity.PopularScene;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SharePictureBaseAdapt extends BaseAdapter {
	private List<PopularScene> list;
	private Context context;
	private ViewHolder holder;
	private Intent intent;
	private List<Integer> list2=new ArrayList<Integer>();
	private MyApplication m;
	private int state=0;//表示点赞状态
	private int comment;
	public SharePictureBaseAdapt(List<PopularScene> list, Context context) {
		super();
		this.list = list;
		this.context = context;
		m=(MyApplication) context.getApplicationContext();
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
	public View getView(int position, View v, ViewGroup arg2) {
		final PopularScene ps=list.get(position);
		if(v==null){
			holder = new ViewHolder();
			v=LayoutInflater.from(context).inflate(R.layout.action_scenery_jinse, null);
			PicturePage pp=new PicturePage(v,holder);
			pp.initView();
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		if(ps!=null&&!ps.equals("")){
			//数据显示
			holder.travelName.setText("旅行队："+ps.getSp().getTd().getTeamName());
			holder.travelPoint.setText("景点："+ps.getSp().getViewPoint());
			holder.issuePicture.setText("发表时间："+ToolDao.setTimedate1(ps.getSp().getTime()));
			holder.userName.setText("用户名："+ps.getSp().getLd().getUserName());
			holder.numberPinglun.setText(String.valueOf(ps.getCommentNumber()));
			holder.fenxiang.setText(ps.getSp().getFeeling());
			holder.number.setText(String.valueOf(ps.getThumbUpNumber()));
			if(ps.getPhotoState()==0){
				holder.dainzan.setImageResource(R.drawable.praise);
			}else {
				holder.dainzan.setImageResource(R.drawable.praise_have);
				if(!list2.contains(position)){
				list2.add(position);
				}
			}
			sendUserPicture(ps.getUriUpLoadPicture());
		}
		
		holder.dainzan.setTag(position);
		holder.pinglun.setTag(position);
		if(!ps.getSp().getViewPoint().equals("")){
			sendPicture(ps.getSp().getUrlPhotos());
		}
		holder.dainzan.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
//					Toast.makeText(context, "点击了", Toast.LENGTH_SHORT).show();
					// TODO Auto-generated method stub
					ImageButton imageButton=(ImageButton) v;
					if(list2.contains(v.getTag())){
						imageButton.setImageResource(R.drawable.praise);
						list2.remove((Integer)v.getTag());
						
//						list2.remove(position);
						
						list.get((Integer)v.getTag()).setPhotoState(0);
						int i=list.get((Integer)v.getTag()).getThumbUpNumber()-1;
						list.get((Integer)v.getTag()).setThumbUpNumber(i);
						state=0;
						
//						num=num--;	
						
//						notifyDataSetChanged();
						
//						stateD=0;
						
						Toast.makeText(context, "取消点赞", Toast.LENGTH_SHORT).show();
					}else {
						imageButton.setImageResource(R.drawable.praise_have);
						list2.add((Integer)v.getTag());
						
						list.get((Integer)v.getTag()).setPhotoState(1);
						int i=list.get((Integer)v.getTag()).getThumbUpNumber()+1;
						list.get((Integer)v.getTag()).setThumbUpNumber(i);
						state=1;
					}
//						stateD=1;
						Toast.makeText(context, "点赞", Toast.LENGTH_SHORT).show();
					//回调改变      不用
					notifyDataSetChanged();
					joinDianzan((Integer)v.getTag());
					
//					joinDianzan(position);

			}
		});
	 holder.pinglun.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			intent =new Intent(context,Travel_team_all_data.class);
		    PopularScene scene=list.get((Integer)v.getTag());
		    comment=(Integer)v.getTag();
		    
		    Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		    intent.putExtra("scene", gson.toJson(scene));    
//		    intent.putExtra("adapter",gson.toJson(list));
		    intent.putExtra("sa","sa");
//			intent.putExtra("sp", gson.toJson(gson));
//			intent.putExtra("sp", sp);
//			intent.putExtra("state", stateD);
//			intent.putExtra("num", num);
			intent.putExtra("comment", comment);
			context.startActivity(intent);
			
		}
	});
//		holder.
		return v;
	}
	
	/**
	 * 获取旅游图片
	 */
	public void sendPicture(final String urlP){
		BitmapUtils bu=new BitmapUtils(context);
		bu.display(holder.picture, "http://10.201.1.12:8080/gotravel/lvtu/"+urlP);
	}
	String urlU="http://10.201.1.12:8080/travel/Home_home_yhtx";
	/**
	 * 获取用户图片
	 */
	public void sendUserPicture(String ld){
		BitmapUtils bu=new BitmapUtils(context);
		bu.display(holder.userPicture, "http://10.201.1.12:8080/gotravel/UserImage/"+ld);
	}

	String addDianzan="http://10.201.1.12:8080/travel/SharePicture_dianzhan";
	/**
	 * 添加一条赞
	 */
	public void joinDianzan(final int position){
		HttpUtils http=new HttpUtils();
		RequestParams params = new RequestParams();
	 	LoginData ld=m.getLd();
//	 	ld.setLd(17);
	 	
//	 	Log.i("list", "sp:"+list.get(position).getSp());
	 	ClickPraise cp=new ClickPraise(ld, state,list.get(position).getSp());
	 	Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
	 	String s=gson.toJson(cp);
	 	params.addBodyParameter("cp",s);
	 	http.send(HttpMethod.POST, addDianzan, params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				Toast.makeText(context, "请求成功", Toast.LENGTH_LONG).show();
//				dianZanNum(position);
			}
	 	});
	}
}
