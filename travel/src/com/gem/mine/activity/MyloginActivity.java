package com.gem.mine.activity;


import com.gem.scenery.R;
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
	private ImageView IM_MyloginActivity_personalinformation;
	//注册
	private Button BT_MyloginActivity_myregister;
	//登录
	private ImageView IM_MyloginActivity_loginpage;
	//我的旅行队消息
	private Button BT_MyloginActivity_myteammessage;
	//我的收藏
	private Button BT_MyloginActivity_myenshrine;
	private Context context;
	private HttpUtils http;
	private RequestParams params;
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
		IM_MyloginActivity_personalinformation=(ImageView) getView().findViewById(R.id.IM_MyloginActivity_personalinformation);
		IM_MyloginActivity_PERSIONMESSAGEPICTURE.setOnClickListener(this);
		IM_MyloginActivity_Mytravelteam.setOnClickListener(this);
		im_MyloginActivity_Myshare.setOnClickListener(this);
		IM_MyloginActivity_personalinformation.setOnClickListener(this);
		BT_MyloginActivity_myregister=(Button) getView().findViewById(R.id.BT_MyloginActivity_myregister);
		BT_MyloginActivity_myteammessage=(Button) getView().findViewById(R.id.BT_MyloginActivity_myteammessage);
		BT_MyloginActivity_myenshrine=(Button) getView().findViewById(R.id.BT_MyloginActivity_myenshrine);
		BT_MyloginActivity_myregister.setOnClickListener(this);
		BT_MyloginActivity_myteammessage.setOnClickListener(this);
		BT_MyloginActivity_myenshrine.setOnClickListener(this);
		IM_MyloginActivity_loginpage=(ImageView) getView().findViewById(R.id.IM_MyloginActivity_loginpage);
		IM_MyloginActivity_loginpage.setOnClickListener(this);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState)  {
		super.onActivityCreated(savedInstanceState);	
		context=getContext();
		viewInit();
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
			sendShare(intent2);
			break;
			//登录界面	
			//跳转至设置界面
		case R.id.IM_MyloginActivity_personalinformation:
			//
			Intent intent3 =new Intent(getActivity(),SetupActivity.class);
			startActivity(intent3);
			break;
			
			//跳转至登录页面
		case R.id.IM_MyloginActivity_loginpage:
			Intent intent4 =new Intent(getActivity(),MypageLoginActivity.class);
			startActivity(intent4);
			break;
			//注册页面
		case R.id.BT_MyloginActivity_myregister:
			Intent intent5 =new Intent(getActivity(),MypageregisterActivity.class);
			startActivity(intent5);
			break;
			//页面跳转至旅行队消息
		case R.id.BT_MyloginActivity_myteammessage:
//			Intent intent6 =new Intent(getActivity(),TroopsActivity.class);
//			startActivity(intent6);
			break;
			//跳转至收藏页面
		case R.id.BT_MyloginActivity_myenshrine:
			Intent intent7 =new Intent(getActivity(),MycollectActivity.class);
			startActivity(intent7);
			break;
		
		}
	
	}
	
	String urlAllTravel="http://10.201.1.12:8080/travel/Wode_lvxingdui";
	/**
	 * 请求获得我相关的旅行队
	 */
		public void sendAllMyTravel(final Intent intent1){
			http=new HttpUtils();
			params=new RequestParams();
			params.addBodyParameter("ld",String.valueOf(17));
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

		String urlShare="http://10.201.1.12:8080/travel/Wode_wodefenxiang";
		/**
		 * 获取我分享的旅图
		 */
		public void sendShare(final Intent intent2){
			http=new HttpUtils();
			params=new RequestParams();
			params.addBodyParameter("ld",String.valueOf(17));
			http.send(HttpMethod.POST, urlShare, params, new RequestCallBack<String>() {
				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(context, "请求失败,请检查您的网络", Toast.LENGTH_LONG).show();
					startActivity(intent2);
				}
				
				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					String result=arg0.result;
					intent2.putExtra("result", result);
					startActivity(intent2);
				}
			});
		}
}
