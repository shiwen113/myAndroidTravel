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


//�ҵĸ�����ҳ����
public class MyloginActivity extends Fragment implements OnClickListener{

	//������Ϣ
	ImageView IM_MyloginActivity_PERSIONMESSAGEPICTURE;
	//�ҵ����ж�
	ImageView IM_MyloginActivity_Mytravelteam;
	//�ҵķ���
	ImageView im_MyloginActivity_Myshare;
	//�ҵ�����
	ImageView IM_MyloginActivity_personalinformation;
	//ע��
	Button BT_MyloginActivity_myregister;
	//��¼
	ImageView IM_MyloginActivity_loginpage;
	//�ҵ����ж���Ϣ
	Button BT_MyloginActivity_myteammessage;
	//�ҵ��ղ�
	Button BT_MyloginActivity_myenshrine;
	private Context context;
	//�ҵ���ҳ
	//��������¼�  ������Ϣ    �ҵ����ж�    �ҵķ���
	//ע��     ���ж���Ϣ      �ղ�
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
		//ҳ�� ��ת��������Ϣ
		case R.id.IM_MyloginActivity_PERSIONMESSAGEPICTURE:
			Intent intent =new Intent(getActivity(),PersonalinformationActivity.class);
			startActivity(intent);
			break;
			//�ҵ����ж�
		case R.id.IM_MyloginActivity_Mytravelteam:
			Intent intent1 =new Intent(getActivity(),TroopsActivity.class);
			startActivity(intent1);
			break;
		//�ҵķ���
		case R.id.im_MyloginActivity_Myshare:
			Intent intent2 =new Intent(getActivity(),SharingActivity.class);
			startActivity(intent2);
			break;
		//��¼����	
			//��ת�����ý���
		case R.id.IM_MyloginActivity_personalinformation:
			//
			Intent intent3 =new Intent(getActivity(),SetupActivity.class);
			startActivity(intent3);
			break;
			
			//��ת����¼ҳ��
		case R.id.IM_MyloginActivity_loginpage:
			Intent intent4 =new Intent(getActivity(),MypageLoginActivity.class);
			startActivity(intent4);
			break;
		case R.id.BT_MyloginActivity_myregister:
			Intent intent5 =new Intent(getActivity(),MypageregisterActivity.class);
			startActivity(intent5);
			break;
			//���ж���Ϣ
		case R.id.BT_MyloginActivity_myteammessage:
//			Intent intent6 =new Intent(this,AdvicesActivity.class);
//			startActivity(intent6);
			break;
		case R.id.BT_MyloginActivity_myenshrine:
			Intent intent7 =new Intent(getActivity(),MycollectActivity.class);
			startActivity(intent7);
			break;
		
		}
	
}


	}
