package com.gem.scenery.action;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.R.integer;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.Toast;

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

public class ListViewAdapter extends BaseAdapter{
    
//	private Map<Integer,A> map=new HashMap<Integer, A>();
	private ViewHolder holder;
	private List<PopularScene> list;   
	private Context context;
	private List<Integer> list2=new ArrayList<Integer>();
	private int state=0;//表示点赞状态
	private Intent intent;
 	private int num;//点赞量
 	private int stateD;
 	private String pNum;//评论量
 	private int comment;
	public ListViewAdapter(Context context, List<PopularScene> listmap) {
		this.list = listmap;
		this.context = context;
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
	
//	int i=0;
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
			
			v.setTag(holder);
			
		} else {
			holder = (ViewHolder) v.getTag();
		}
//		arr.add(holder);
//	 	map.put(position,v);
		
		if(sp.getPhotoState()==0){
			holder.dainzan.setImageResource(R.drawable.praise);
		}else {
			holder.dainzan.setImageResource(R.drawable.praise_have);
			if(!list2.contains(position)){
			list2.add(position);
			}
		}
//		dianZanState(sp,position);
		
//		if(position>i){
//			i=position;
//			if(map.get(sp)==0){
//				holder.dainzan.setImageResource(R.drawable.praise);
//			}else {
//				holder.dainzan.setImageResource(R.drawable.praise_have);
//			}
//		}else {
//			 if(list2.contains(position)){
//				  holder.dainzan.setImageResource(R.drawable.praise_have);
//			  }else {
//				  holder.dainzan.setImageResource(R.drawable.praise);		  
//			  }
//		}
//		holder.
		
//		holder.
		
		holder.number.setText(String.valueOf(sp.getThumbUpNumber()));
//		dianZanNum(position);
		
//		holder.number.setText(map2.get(sp));
		
		holder.numberPinglun.setText(String.valueOf(sp.getCommentNumber()));
//        sendPingLunNum(sp);
        
//        holder.numberPinglun.setText(map3.get(sp));
        
		holder.dainzan.setTag(position);
		holder.pinglun.setTag(position);
	 	
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
						
//						stateD=1;
						Toast.makeText(context, "点赞", Toast.LENGTH_SHORT).show();
					}
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
			    intent.putExtra("adapter",gson.toJson(list));
			    
//				intent.putExtra("sp", gson.toJson(gson));
//				intent.putExtra("sp", sp);
//				intent.putExtra("state", stateD);
//				intent.putExtra("num", num);
//				intent.putExtra("pNum", pNum);
				context.startActivity(intent);
				
			}
		});
		
		 if(!sp.getUriUpLoadPicture().equals("")){
		 BitmapUtils bpu=new BitmapUtils(context);
		 bpu.display(holder.userPicture, sp.getUriUpLoadPicture());
		 }
//			if(!sp.getLd().equals("")){
//				sendUserPicture(sp.getLd().getLd());
//			}
		
		if(sp!=null&&!sp.equals("")){
			//数据显示
			holder.travelName.setText("旅行队："+sp.getSp().getTd().getTeamName());
			holder.travelPoint.setText("景点："+sp.getSp().getViewPoint());
			holder.issuePicture.setText("发表时间："+ToolDao.setTimedate1(sp.getSp().getTime()));
			holder.userName.setText("用户名："+sp.getSp().getLd().getUserName());
//			holder.numberPinglun.setText(pNum);
			
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
		bu.display(holder.picture, urlP);
	}
	
	public void pinglunfanhui(int a){
//		list.set(i, ps);
		if(a!=0){
		list.get(comment).setCommentNumber(list.get(comment).getCommentNumber()+a);
		notifyDataSetChanged();
		}
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
	 	LoginData ld=new LoginData();
	 	ld.setLd(17);
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
//	
//	String dianZanNum="http://10.201.1.12:8080/travel/SharePicture_dianzanshu";
//	/**
//	 * 点赞量
//	 */
//	public void dianZanNum(final int position){
//		RequestParams params = new RequestParams();
//		HttpUtils http=new HttpUtils();
//		SharePicture sp=list.get(position);
//	 	params.addBodyParameter("sp",String.valueOf(sp.getSp()));
//		http.send(HttpMethod.POST, dianZanNum, params,new RequestCallBack<String>() {
//
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				// TODO Auto-generated method stub
//				Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_SHORT).show();
//			}
//
//			@Override
//			public void onSuccess(ResponseInfo<String> arg0) {
//				Toast.makeText(context, "请求成功", Toast.LENGTH_LONG).show();
////				num=Integer.valueOf(arg0.result);
////				View view=map.get(position);
////				ViewHolder holder=(ViewHolder) view.getTag();
//		
//				holder.number.setText(arg0.result);
////				notifyDataSetChanged();
//				Toast.makeText(context, "成功,点赞数"+arg0.result, Toast.LENGTH_SHORT).show();
////				adapter.notifyDataSetChanged();
////				ListViewAdapter.this.notifyDataSetChanged();
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
//	
//	String urlPingLunNum="http://10.201.1.12:8080/travel/ScereryCommentNumServerlet";
//	/**
//	 * 评论数
//	 */
//	public void sendPingLunNum(SharePicture sp){
//		RequestParams params = new RequestParams();
//		HttpUtils http=new HttpUtils();
//		params.addBodyParameter("sp",String.valueOf(sp.getSp()));
//		http.send(HttpMethod.POST, urlPingLunNum, params,new RequestCallBack<String>() {
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
//				pNum=arg0.result;
//				holder.numberPinglun.setText(pNum);
//				Log.i("PNum",pNum);
//				
//			}
//		});
//	}
//	
////	static class A{
////			private static int state;
////			private static int dianzanshu;
////			private static int pinglunshu;		    
////		}	
}  

