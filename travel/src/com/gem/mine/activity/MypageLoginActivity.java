package com.gem.mine.activity;


import java.lang.reflect.Type;

import com.gem.home.activity.Fragment_Activity;
import com.gem.home.until.LoginData;
import com.gem.scenery.R;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
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
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MypageLoginActivity extends Activity implements OnClickListener {
	//登录页面
//	private Button login_Button; 
//	private View moreHideBottomView,input2; 
//	private ImageView more_imageView; 
//	private boolean mShowBottom = false; 
//	
	private EditText eT;
	//显示登录结果
	private TextView Tt;
		
	private EditText pw;
	private String pwd;
	 private String account;
	//登录头像
	private ImageView im_MypageLoginActivity_touxiang;
	//注册
	private TextView tv_MypageLoginActivity_mypageregister;
	
	//登录按钮
	private Button LoginActivitydlbutton;
	//找回密码
	private TextView tv_MypageLoginActivity_forgetpassword;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
//	        //透明导航栏  
//	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
//	  
		//setContentView(R.layout.activity_mypageforgetpassword);
		setContentView(R.layout.activity_mypage_login);
		 eT=(EditText) findViewById(R.id.searchEditText);//用户名
         Tt=(TextView) findViewById(R.id.tv_1result);//显示登录结果
         pw=(EditText) findViewById(R.id.et_MypageLoginActivity_Pleaseenteryourpassword);//��ʾ����
         //点击实现登录功能
         LoginActivitydlbutton=(Button) findViewById(R.id.LoginActivitydlbutton);
        LoginActivitydlbutton.setOnClickListener(this);
        //注册监听
        tv_MypageLoginActivity_mypageregister=(TextView) findViewById(R.id.tv_MypageLoginActivity_mypageregister);
        tv_MypageLoginActivity_mypageregister.setOnClickListener(this);
      //忘记密码监听
        tv_MypageLoginActivity_forgetpassword=(TextView) findViewById(R.id.tv_MypageLoginActivity_forgetpassword);
        tv_MypageLoginActivity_forgetpassword.setOnClickListener(this);
	   //点击登录头像，页面跳转我的页面
        im_MypageLoginActivity_touxiang=(ImageView) findViewById(R.id.im_MypageLoginActivity_touxiang);
        im_MypageLoginActivity_touxiang.setOnClickListener(this);
	}
	//实现，页面跳转至找回  页面跳转注册页面  登录按钮的实现
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		//实现页面跳转至注册页面
      case R.id.tv_MypageLoginActivity_mypageregister:
			Intent intent=new Intent(this,MypageregisterActivity.class);
			startActivity(intent);   	  
			break;
		
			//实现页面跳转 至密码找回页面
		case R.id.tv_MypageLoginActivity_forgetpassword:
			Intent intent10=new Intent(this,Mypageforgetpassword.class);
			startActivity(intent10);
			break;
			//点击头像，页面跳转至我的个人页面
		case R.id.im_MypageLoginActivity_touxiang:
			Intent intent3 =new Intent(this,MyloginActivity.class);
			startActivity(intent3);
			break;
			
			//实现登录功能
        case R.id.LoginActivitydlbutton:
        	Log.i("mylogin","ok" );
			System.out.println("ok");
	    	HttpUtils http=new HttpUtils();
			  account=eT.getText().toString();
			 pwd=pw.getText().toString();
			//String count=count.getText().toString();
			
			String url;
			url="http://10.201.1.12:8080/travel/Mypagelogin_login";
			RequestParams params=new RequestParams();
			params.addBodyParameter("account",account);
			params.addBodyParameter("userPwd", pwd);
			//params.addBodyParameter("account", count);
			http.send(HttpMethod.POST, url,params, new RequestCallBack<String>(){

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					Log.i("myutil", "执行onFailure");
					Toast.makeText(getApplication(), "登录失败，请检查网络", Toast.LENGTH_LONG).show();
				}
				@Override
				public void onSuccess(ResponseInfo<String> responseInfo) {
					// TODO Auto-generated method stub
					Log.i("urilActivity", "onSuccess");
					//从服务器返回结果
				    String result=	responseInfo.result;
				    Gson gson=new Gson();
				    Type type=new TypeToken<LoginData>(){}.getType();
				    LoginData data=gson.fromJson(result, type);
				    if(data==null||data.equals("")){
				    	System.out.println("账号不存在");
				    	Tt.setText("账号不存在");
				    }else if(data.getAccount().equals(account)&&data.getPassWord().equals(pwd)){
				    	Tt.setText("登录成功");
				    	Intent intent=new Intent(MypageLoginActivity.this,Fragment_Activity.class);
				    	startActivity(intent);
				    }else{
				    	Tt.setText("密码错误");
				    }
				    Log.i("urilActivity", "result:"+result);
//					if(result!=null&&!result.trim().equals("null")){
//						
//						//Tt.setText(result);
//						Tt.setText(result);
//					}else{
//						Tt.setText(result);
//					}
				
				
					
				}

	
		
		});
			System.out.println("ok");
		
		}
	
	}
}
	
