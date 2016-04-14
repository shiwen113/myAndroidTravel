package com.gem.mine.activity;


import java.lang.reflect.Type;
import java.util.List;

import io.rong.imkit.RongIM;
import io.rong.imlib.model.Conversation;

import com.gem.home.dao.MyApplication;
import com.gem.home.dao.MyImageAsyncTask;
import com.gem.home.dao.MyRecyclerViewHolder;
import com.gem.home.until.PublishTravel;
import com.gem.scenery.R;
import com.gem.scenery.action.ListViewAdapter;
import com.gem.scenery.entity.PersonalData;
import com.gem.scenery.entity.PopularScene;
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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;


//我的个人主页界面
public class MyloginActivity extends Fragment implements OnClickListener{

	//个人信息
	private ImageView IM_MyloginActivity_PERSIONMESSAGEPICTURE;
	//我的旅行队
	private ImageView IM_MyloginActivity_Mytravelteam;
	//我的分享
	private ImageView im_MyloginActivity_Myshare;
	//我的设置
//	private ImageView IM_MyloginActivity_personalinformation;
	//注册
//	private Button BT_MyloginActivity_myregister;
	//登录
	private ImageView IM_MyloginActivity_loginpage;
	//我的旅行队消息
	private LinearLayout BT_MyloginActivity_myteammessage;
	//我的收藏
	private LinearLayout BT_MyloginActivity_myenshrine;
	private Context context;
	private HttpUtils http;
	private RequestParams params;
	private MyApplication m;
	//我的主页
		//三个点击事件  个人信息    我的旅行队    我的分享
		//注册     旅行队消息      收藏
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_mylogin, container, false);

		return view;
	}	
	public void viewInit(){
		IM_MyloginActivity_PERSIONMESSAGEPICTURE=(ImageView) getView().findViewById(R.id.IM_MyloginActivity_PERSIONMESSAGEPICTURE);
		IM_MyloginActivity_Mytravelteam=(ImageView) getView().findViewById(R.id.IM_MyloginActivity_Mytravelteam);
		im_MyloginActivity_Myshare=(ImageView) getView().findViewById(R.id.im_MyloginActivity_Myshare);
		IM_MyloginActivity_PERSIONMESSAGEPICTURE.setOnClickListener(this);
		IM_MyloginActivity_Mytravelteam.setOnClickListener(this);
		im_MyloginActivity_Myshare.setOnClickListener(this);
		BT_MyloginActivity_myenshrine=(LinearLayout) getView().findViewById(R.id.BT_MyloginActivity_myenshrine);
		BT_MyloginActivity_myteammessage=(LinearLayout) getView().findViewById(R.id.IM_MyloginActivity_mytravelteammessage);
		BT_MyloginActivity_myteammessage.setOnClickListener(this);
		BT_MyloginActivity_myenshrine.setOnClickListener(this);
		IM_MyloginActivity_loginpage=(ImageView) getView().findViewById(R.id.IM_MyloginActivity_loginpage);
		IM_MyloginActivity_loginpage.setOnClickListener(this);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState)  {
		super.onActivityCreated(savedInstanceState);	
		context=getContext();
		m=(MyApplication) context.getApplicationContext();
		viewInit();
	}
	
	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if(m.getLd()!=null){
			sendUserPicture();
		}
	}
	public void onClick(View v) {		
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		case R.id.IM_MyloginActivity_PERSIONMESSAGEPICTURE://页面 跳转至个人信息
			Intent intent =new Intent(getActivity(),PersonalinformationActivity.class);
			startActivity(intent);
			break;
			
		case R.id.IM_MyloginActivity_Mytravelteam://我的旅行队
			Intent intent1 =new Intent(getActivity(),TroopsActivity.class);
			sendAllMyTravel(intent1);
			break;
			
		case R.id.im_MyloginActivity_Myshare://我的分享
			Intent intent2 =new Intent(getActivity(),SharingActivity.class);
//			sendShare(intent2);
			startActivity(intent2);
			break;
		case R.id.IM_MyloginActivity_loginpage://登录
			Intent intent4 =new Intent(getActivity(),MypageLoginActivity.class);
			startActivity(intent4);
			break;
		case R.id.IM_MyloginActivity_mytravelteammessage://我的消息
//			if(RongIM.getInstance() != null){
//			    RongIM.getInstance().startSubConversationList(context, Conversation.CREATOR);
//			}
			RongIM.getInstance().startConversationList(context);
			break;
			//跳转至收藏页面
		case R.id.BT_MyloginActivity_myenshrine:
			if(m.getLd()!=null){
			Intent intent7 =new Intent(context,MycollectActivity.class);
			startActivity(intent7);
			}else{
				Toast.makeText(context, "请先登录，查看更多", Toast.LENGTH_LONG).show();
			}
			break;
		
		}
	
	}
	
	
	/**
	 * 请求获得我相关的旅行队
	 */
		public void sendAllMyTravel(final Intent intent1){
			http=new HttpUtils();
			params=new RequestParams();
			String urlAllTravel = null;
			if(m.getLd()!=null){
			urlAllTravel="http://10.201.1.12:8080/travel/Wode_lvxingdui";
			params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
			}else{
				urlAllTravel="http://10.201.1.12:8080/travel/Wo";
				Toast.makeText(context, "请先登陆", Toast.LENGTH_LONG).show();
			}
			http.send(HttpMethod.POST,urlAllTravel,params,new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					Toast.makeText(context, "请求失败,请检查您的网络", Toast.LENGTH_LONG).show();
					startActivity(intent1);
				}

				public void onSuccess(ResponseInfo<String> arg0) {
					String result=arg0.result;
					intent1.putExtra("result", result);
					startActivity(intent1);
				}
		 	});
		}
		
		String urlU="http://10.201.1.12:8080/travel/Home_home_yhtx";
		/**
		 * 获取用户图片
		 */
		public void sendUserPicture(){
			RequestParams params = new RequestParams();
			HttpUtils http=new HttpUtils();
		 	params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
			http.send(HttpMethod.POST, urlU, params,new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
//					Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					// TODO Auto-generated method stub
					String result =arg0.result;
					if(!result.equals(null)&&result!=null){
						Gson gson =new Gson();
						Type type =new TypeToken<PersonalData>(){}.getType();
						PersonalData pd=gson.fromJson(result, type);
						if(!pd.getUriUpLoadPicture().equals(null)&&!pd.getUriUpLoadPicture().equals("null")&&pd.getUriUpLoadPicture()!=null){
						BitmapUtils bu=new BitmapUtils(context);
						bu.display(IM_MyloginActivity_loginpage, "http://10.201.1.12:8080/gotravel/UserImage/"+pd.getUriUpLoadPicture());
						}
					}
				}
			});
		}

		/*String urlShare=null;
		*//**
		 * 获取我分享的旅图
		 *//*
		private boolean flag=true;
		public void sendShare(final Intent intent2){
			http=new HttpUtils();
			params=new RequestParams();
			if(m.getLd()!=null){
				urlShare="http://10.201.1.12:8080/travel/Wode_wodefenxiang";
			params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
			}else{
				flag=false;
				urlShare="http://10.201.1.12:8080/travel/g";
			}
			http.send(HttpMethod.POST, urlShare, params, new RequestCallBack<String>() {
				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					if(flag){
						Toast.makeText(context, "请求失败,请检查您的网络", Toast.LENGTH_LONG).show();
					}else{
						Toast.makeText(context, "请登录查看更多", Toast.LENGTH_LONG).show();
					}
					startActivity(intent2);
				}
				
				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					String result=arg0.result;
					intent2.putExtra("result", result);
					startActivity(intent2);
				}
			});
		}*/
}
