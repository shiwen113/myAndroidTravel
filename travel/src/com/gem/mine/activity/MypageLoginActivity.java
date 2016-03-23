package com.gem.mine.activity;


import com.gem.scenery.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;

import android.content.Intent;

import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MypageLoginActivity extends Activity implements OnClickListener {
	//��¼ҳ��
	private Button login_Button; 
	private View moreHideBottomView,input2; 
	private ImageView more_imageView; 
	private boolean mShowBottom = false; 
	
	EditText eT;
	 //��ʾ��¼���
	TextView Tt;
		
	EditText pw;

	
	//ע��
	TextView tv_MypageLoginActivity_mypageregister;
	
	//��¼��ť
	Button LoginActivitydlbutton;
	//�һ�����
	TextView tv_MypageLoginActivity_forgetpassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mypage_login);
		 eT=(EditText) findViewById(R.id.searchEditText);//�û���
         Tt=(TextView) findViewById(R.id.tv_1result);//��ʾ��¼���
         pw=(EditText) findViewById(R.id.et_MypageLoginActivity_Pleaseenteryourpassword);//��ʾ����
         //���ʵ�ֵ�¼����
         LoginActivitydlbutton=(Button) findViewById(R.id.LoginActivitydlbutton);
        LoginActivitydlbutton.setOnClickListener(this);
        //ע�����
        tv_MypageLoginActivity_mypageregister=(TextView) findViewById(R.id.tv_MypageLoginActivity_mypageregister);
        tv_MypageLoginActivity_mypageregister.setOnClickListener(this);
       //�����������
        tv_MypageLoginActivity_forgetpassword=(TextView) findViewById(R.id.tv_MypageLoginActivity_forgetpassword);
        tv_MypageLoginActivity_forgetpassword.setOnClickListener(this);
	}
	//ʵ�֣�ҳ����ת���һ�  ҳ����תע��ҳ��  ��¼��ť��ʵ��
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		//ʵ��ҳ����ת��ע��ҳ��
      case R.id.tv_MypageLoginActivity_mypageregister:
			Intent intent=new Intent(this,MypageregisterActivity.class);
			startActivity(intent);
    	  
			break;
		
		//ʵ��ҳ����ת �������һ�ҳ��
		case R.id.tv_MypageLoginActivity_forgetpassword:
//			Intent intent1=new Intent(this,MypageforgetpasswordActivity.class);
//			startActivity(intent1);
			break;
			
		//ʵ�ֵ�¼����
        case R.id.LoginActivitydlbutton:
        	Log.i("mylogin","�û���¼�ɹ�" );
			System.out.println("ok");
	    	HttpUtils http=new HttpUtils();
			String name=eT.getText().toString();
			String pwd=pw.getText().toString();
			
			String url;
			url="http://10.201.1.14:8080/travel/clentservlet";
			RequestParams params=new RequestParams();
			params.addBodyParameter("userName",name);
			params.addBodyParameter("userPwd", pwd);
			http.send(HttpMethod.POST, url,params, new RequestCallBack<String>(){

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					Log.i("myutil", "ִ��onFailure");
				}

				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					// TODO Auto-generated method stub
					Log.i("urilActivity", "onSuccess");
					//�ӷ��������ؽ��
				    String result=	responseInfo.result;
				    Log.i("urilActivity", "result:"+result);
					if(result!=null&&!result.trim().equals("null")){
						
						//Tt.setText(result);
						Tt.setText("��¼�ɹ�");
					}else{
						Tt.setText("��¼ʧ��");
					}
					
				}

	
		
		});
			System.out.println("ok");
		
		}
	
	}
}
	
