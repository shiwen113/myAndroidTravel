package com.gem.scenery.action;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.gem.home.dao.MyApplication;
import com.gem.home.until.LoginData;
import com.gem.home.until.ToolDao;
import com.gem.scenery.R;
import com.gem.scenery.action.PicturePage.ViewHolder;
import com.gem.scenery.entity.ClickPraise;
import com.gem.scenery.entity.PersonalData;
import com.gem.scenery.entity.PopularScene;
import com.gem.scenery.entity.SharePicture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

public class PlazaListViewAdapt extends BaseAdapter {

	private ViewHolder holder;
	private List<PopularScene> list;   
	private Context context;
	private List<Integer> list2=new ArrayList<Integer>();
	private int state=0;//表示点赞状态
	private Intent intent;
 	int num;//点赞量
 	int stateD;
 	private MyApplication m;
	public PlazaListViewAdapt(Context context, List<PopularScene> listmap) {
		this.list = listmap;
		this.context = context;
		m=(MyApplication) context.getApplicationContext();
	}

	
	@Override
	public int getCount() {
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}
	
	@Override
	public View getView(final int position, View v, ViewGroup parent) {
		final PopularScene sp=list.get(position);
		if (v == null) {
			holder = new ViewHolder();
			v = LayoutInflater.from(context).inflate(
					R.layout.action_scenery_jinse, null);
			PicturePage pp=new PicturePage(v,holder);
			pp.initView();
//			sendUserPicture(sp.getLd().getLd());
			
//			dianZanState(sp,position);
			
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		
		if(sp.getPhotoState()==0){
			holder.dainzan.setImageResource(R.drawable.praise);
		}else {
			holder.dainzan.setImageResource(R.drawable.praise_have);
			if(!list2.contains(position)){
			list2.add(position);
			}
		}
		
//		dianZanNum(sp);
		holder.number.setText(String.valueOf(sp.getThumbUpNumber()));
		holder.numberPinglun.setText(String.valueOf(sp.getCommentNumber()));
		holder.dainzan.setTag(position);
		holder.pinglun.setTag(position);
		
//		 if(list2.contains(position)){
//			  holder.dainzan.setImageResource(R.drawable.praise_have);
//		  }else {
//			  holder.dainzan.setImageResource(R.drawable.praise);
//			  
//		  }
		 holder.dainzan.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
//					Toast.makeText(context, "点击了", Toast.LENGTH_LONG).show();
					// TODO Auto-generated method stub
					if(m.getLd()==null||m.getLd().equals("")){
						Toast.makeText(context, "请先登录", Toast.LENGTH_SHORT).show();
					}else {
					ImageButton imageButton=(ImageButton) v;
					if(list2.contains(v.getTag())){
						imageButton.setImageResource(R.drawable.praise);
						list2.remove((Integer)v.getTag());
//						list2.remove(position);
						
						list.get((Integer)v.getTag()).setPhotoState(0);
						int i=list.get((Integer)v.getTag()).getThumbUpNumber()-1;
						list.get((Integer)v.getTag()).setThumbUpNumber(i);
						
						state=0;
//						stateD=0;
						Toast.makeText(context, "取消点赞", Toast.LENGTH_SHORT).show();
					}else {
						imageButton.setImageResource(R.drawable.praise_have);
						list2.add((Integer)v.getTag());
						
						list.get((Integer)v.getTag()).setPhotoState(1);
						int i=list.get((Integer)v.getTag()).getThumbUpNumber()+1;
						list.get((Integer)v.getTag()).setThumbUpNumber(i);
						
						state=1;
//						stateD=1;
						Toast.makeText(context, "点赞", Toast.LENGTH_SHORT).show();
					}
					notifyDataSetChanged();
					joinDianzan((Integer)v.getTag());
					}
				}
			});
		 holder.pinglun.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				intent =new Intent(context,Travel_team_all_data.class);
				 PopularScene scene=list.get((Integer)v.getTag());
//				 comment=(Integer)v.getTag();
				    
				 Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
				 intent.putExtra("scene", gson.toJson(scene)); 
				 
				 intent.putExtra("adapter",gson.toJson(list));
				 
//				intent.putExtra("sp", gson.toJson(gson));
				 
//				intent.putExtra("sp", sp);
//				intent.putExtra("state", stateD);
//				intent.putExtra("num", num);
				 
				context.startActivity(intent);
			}
		});
		 
		 if(!sp.getUriUpLoadPicture().equals("")){
			 BitmapUtils bpu=new BitmapUtils(context);
			 bpu.display(holder.userPicture, "http://10.201.1.12:8080/gotravel/UserImage/"+sp.getUriUpLoadPicture());
			 }
		 
//			if(!sp.getLd().equals("")){
//				sendUserPicture(sp.getLd().getLd());
//			}
		
		if(sp!=null&&!sp.equals("")){
			//数据显示
			holder.travelName.setText("旅行队："+sp.getSp().getTd().getTeamName());
			holder.travelPoint.setText("景点："+sp.getSp().getViewPoint());
			holder.issuePicture.setText("发表时间："+ToolDao.setTimedate1(sp.getSp().getTime()));
			holder.userName.setText(sp.getSp().getLd().getUserName());
//			holder.numberPinglun.setText();
			
//			holder.fenxiang.setText(sp.getFeeling());
		}
		
//		holder.picture.setTag(sp.getViewPoint());
		
		if(!sp.getSp().getViewPoint().equals("")){
			sendPicture(sp.getSp().getUrlPhotos());
		}
		
		 
		
		return v;
	}

	
	/**
	 * 获取旅游图片
	 */
	public void sendPicture(final String urlP){
		BitmapUtils bu=new BitmapUtils(context);
		bu.display(holder.picture, "http://10.201.1.12:8080/gotravel/lvtu/"+urlP);
	}
	
//	String urlU="http://10.201.1.12:8080/travel/Home_home_yhtx";
//	/**
//	 * 获取用户图片
//	 */
//	public void sendUserPicture(int ld){
//		RequestParams params = new RequestParams();
//		HttpUtils http=new HttpUtils();
//	 	params.addBodyParameter("ld",String.valueOf(ld));
//		http.send(HttpMethod.POST, urlU, params,new RequestCallBack<String>() {
//
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				// TODO Auto-generated method stub
//				Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
//			}
//
//			@Override
//			public void onSuccess(ResponseInfo<String> arg0) {
//				// TODO Auto-generated method stub
//				String result =arg0.result;
//				if(!result.equals(null)&&result!=null){
//					Gson gson =new Gson();
//					Type type =new TypeToken<PersonalData>(){}.getType();
//					PersonalData pd=gson.fromJson(result, type);
//					BitmapUtils bpu=new BitmapUtils(context);
//					bpu.display(holder.userPicture, pd.getUriUpLoadPicture());
//				}
//			}
//		});
//	}
	String addDianzan="http://10.201.1.12:8080/travel/SharePicture_dianzhan";
	/**
	 * 添加一条赞
	 */
	public void joinDianzan(final int position){
		HttpUtils http=new HttpUtils();
		RequestParams params = new RequestParams();
	 	LoginData ld=m.getLd();
//	 	ld.setLd(17);
	 	Log.i("list", "sp:"+list.get(position).getSp());
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
//				dianZanNum(list.get(position));
			}
	 	});
	}
	
//	String dianZanNum="http://10.201.1.12:8080/travel/SharePicture_dianzanshu";
//	/**
//	 * 点赞量
//	 */
//	public void dianZanNum(SharePicture sp){
//		RequestParams params = new RequestParams();
//		HttpUtils http=new HttpUtils();
//	 	params.addBodyParameter("sp",String.valueOf(sp.getSp()));
//		http.send(HttpMethod.POST, dianZanNum, params,new RequestCallBack<String>() {
//
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				// TODO Auto-generated method stub
//				Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
//			}
//
//			@Override
//			public void onSuccess(ResponseInfo<String> arg0) {
//				Toast.makeText(context, "请求成功", Toast.LENGTH_LONG).show();
//				num=Integer.valueOf(arg0.result);
//				holder.number.setText(arg0.result);
//			}
//	 	});
//	}
//	
//	String urlDianZanState="http://10.201.1.12:8080/travel/SharePicture_xianshidianzan";
//	/**
//	 * 点赞状态
//	 */
//	public void dianZanState(SharePicture sp,final int possion){
//		RequestParams params = new RequestParams();
//		HttpUtils http=new HttpUtils();
//	 	params.addBodyParameter("sp",String.valueOf(sp.getSp()));
//	 	params.addBodyParameter("ld",String.valueOf(17));
//	 	Log.i("spld","sp:"+sp+",ld"+String.valueOf(17));
//		http.send(HttpMethod.POST, urlDianZanState, params,new RequestCallBack<String>() {
//
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				// TODO Auto-generated method stub
//				Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
//			}
//
//			@Override
//			public void onSuccess(ResponseInfo<String> arg0) {
////				Toast.makeText(context, "请求成功", Toast.LENGTH_LONG).show();
//				Log.i("spld",arg0.result);
//				stateD=Integer.parseInt(arg0.result);
//				if(stateD==0){
////					Toast.makeText(context, "请求成功,没点"+Integer.parseInt(arg0.result), Toast.LENGTH_LONG).show();
//					holder.dainzan.setImageResource(R.drawable.praise);
//				}else if(stateD==1){
//					holder.dainzan.setImageResource(R.drawable.praise_have);
//					list2.add(possion);     
//					Toast.makeText(context, "请求成功，点过"+Integer.parseInt(arg0.result), Toast.LENGTH_LONG).show();
//				}
//			}
//	 	});
//	}

}
