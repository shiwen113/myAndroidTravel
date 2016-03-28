package com.gem.mine.activity;


import com.gem.scenery.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;


//我的个人主页界面
public class MyloginActivity extends Fragment implements OnClickListener{

	//个人信息
	ImageView IM_MyloginActivity_PERSIONMESSAGEPICTURE;
	//我的旅行队
	ImageView IM_MyloginActivity_Mytravelteam;
	//我的分享
	ImageView im_MyloginActivity_Myshare;
	//我的设置
	ImageView IM_MyloginActivity_personalinformation;
	//注册
	Button BT_MyloginActivity_myregister;
	//登录
	ImageView IM_MyloginActivity_loginpage;
	//我的旅行队消息
	Button BT_MyloginActivity_myteammessage;
	//我的收藏
	Button BT_MyloginActivity_myenshrine;
	private Context context;
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
		//页面 跳转至个人信息
		case R.id.IM_MyloginActivity_PERSIONMESSAGEPICTURE:
			Intent intent =new Intent(getActivity(),PersonalinformationActivity.class);
			startActivity(intent);
			break;
			//我的旅行队
		case R.id.IM_MyloginActivity_Mytravelteam:
			Intent intent1 =new Intent(getActivity(),TroopsActivity.class);
			startActivity(intent1);
			break;
			//我的分享
		case R.id.im_MyloginActivity_Myshare:
			Intent intent2 =new Intent(getActivity(),SharingActivity.class);
			startActivity(intent2);
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
			Intent intent6 =new Intent(getActivity(),TroopsActivity.class);
			startActivity(intent6);
			break;
			//跳转至收藏页面
		case R.id.BT_MyloginActivity_myenshrine:
			Intent intent7 =new Intent(getActivity(),MycollectActivity.class);
			startActivity(intent7);
			break;
		
		}
	
}


	}
