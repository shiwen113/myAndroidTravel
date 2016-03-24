package com.gem.mine.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.gem.scenery.R;


import android.app.Activity;
import android.graphics.Color;
import android.net.ParseException;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.StrictMode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MypageregisterActivity extends Activity {

	private EditText register_username;
	private EditText register_passwd;
	private EditText reregister_account;
	private Button register_submit;

	
	//注册页面
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mypageforgetpassword);
		setContentView(R.layout.activity_mypageregister);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);					    
		register_username=(EditText)findViewById(R.id.register_username);
		register_passwd=(EditText)findViewById(R.id.register_passwd);
		reregister_account=(EditText)findViewById(R.id.editText1);
		register_submit=(Button)findViewById(R.id.register_submit);
		register_username.setOnFocusChangeListener(new OnFocusChangeListener()
		{   
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					if(register_username.getText().toString().trim().length()<4){
						Toast.makeText(MypageregisterActivity.this, "用户名不能小于4个字符", Toast.LENGTH_SHORT).show();
					}
				}
			}
			
		});
		register_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					if(register_passwd.getText().toString().trim().length()<6){
						Toast.makeText(MypageregisterActivity.this,"请输入用户名和密码", Toast.LENGTH_SHORT).show();
					}
				}
			}
			
		});
		register_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
		{

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					if(!register_passwd.getText().toString().trim().equals(register_passwd.getText().toString().trim())){
						Toast.makeText(MypageregisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show(); 
					}
				}
			}
			
		});
		reregister_account.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					if(reregister_account.getText().toString().trim().length()<6){
						Toast.makeText(MypageregisterActivity.this,"请输入手机号", Toast.LENGTH_SHORT).show();
					}
				}
			}
			
		});
		register_submit.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				
				if(!checkEdit()){
					return ;
				}
				// TODO Auto-generated method stub
				
				String httpUrl="http://10.201.1.12:8080/travel/Mypageregister_register";
				//String httpUrl="http://localhost:8080/";
				HttpPost httpRequest=new HttpPost(httpUrl);
				
				List<NameValuePair> params=new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("userName",register_username.getText().toString().trim()));
				params.add(new BasicNameValuePair("userPwd",register_passwd.getText().toString().trim()));
				params.add(new BasicNameValuePair("account",reregister_account.getText().toString().trim()));
				
				HttpEntity httpentity = null;
				try {
					httpentity = new UrlEncodedFormEntity(params,"utf8");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				httpRequest.setEntity(httpentity);
				HttpClient httpclient=new DefaultHttpClient();
				HttpResponse httpResponse = null;
				try {
					httpResponse = httpclient.execute(httpRequest);
				} catch (ClientProtocolException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			if(httpResponse.getStatusLine().getStatusCode()==200)
				{
					String strResult = null;
					try {
						strResult = EntityUtils.toString(httpResponse.getEntity());
					} catch (ParseException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					Toast.makeText(MypageregisterActivity.this, strResult, Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(MypageregisterActivity.this, "请求错误", Toast.LENGTH_SHORT).show();
				}
				
			}
			
		});
	}
	private boolean checkEdit(){
		if(register_username.getText().toString().trim().equals("")){
			Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
		}else if(register_passwd.getText().toString().trim().equals("")){
			Toast.makeText(this,"密码不能为空", Toast.LENGTH_SHORT).show();
		}else if(!register_passwd.getText().toString().trim().equals(register_passwd.getText().toString().trim())){
			Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
		}else{
			return true;
		}
		return false;
	}
	
	}

			
			

		
