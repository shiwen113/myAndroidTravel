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
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MypageregisterActivity extends Activity {

	private EditText register_username;
	private EditText register_passwd;
	private EditText reregister_passwd;
	private Button register_submit;

	//private Button bt;
	//ע��ҳ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mypageregister);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);			
		
//		   bt.setOnClickListener(new OnClickListener() {
//	             
//	           
//	        });
     
		register_username=(EditText)findViewById(R.id.register_username);
		register_passwd=(EditText)findViewById(R.id.register_passwd);
		reregister_passwd=(EditText)findViewById(R.id.reregister_passwd);
		register_submit=(Button)findViewById(R.id.register_submit);
		register_username.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			public void onFocusChange1(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					if(register_username.getText().toString().trim().length()<4){
						Toast.makeText(MypageregisterActivity.this, "�û�������С��4���ַ�", Toast.LENGTH_SHORT).show();
					}
				}
			}
			@Override
			public void onFocusChange(View arg0, boolean arg1) {
				// TODO Auto-generated method stub
				
			}
			
		});
		register_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
		{
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					if(register_passwd.getText().toString().trim().length()<6){
						Toast.makeText(MypageregisterActivity.this, "�������û���������", Toast.LENGTH_SHORT).show();
					}
				}
			}
			
		});
		reregister_passwd.setOnFocusChangeListener(new OnFocusChangeListener()
		{

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					if(!reregister_passwd.getText().toString().trim().equals(register_passwd.getText().toString().trim())){
						Toast.makeText(MypageregisterActivity.this, "�����������벻һ��", Toast.LENGTH_SHORT).show(); 
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
				
				String httpUrl="http://10.201.1.14:8080/Travelnew/clentservlet";
				//String httpUrl="http://localhost:8080/";
				HttpPost httpRequest=new HttpPost(httpUrl);
				
				List<NameValuePair> params=new ArrayList<NameValuePair>();
				params.add(new BasicNameValuePair("userName",register_username.getText().toString().trim()));
				params.add(new BasicNameValuePair("userPwd",register_passwd.getText().toString().trim()));
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
					Toast.makeText(MypageregisterActivity.this, "�������", Toast.LENGTH_SHORT).show();
				}
				
			}
			
		});
	}
	private boolean checkEdit(){
		if(register_username.getText().toString().trim().equals("")){
			Toast.makeText(this, "�û�������Ϊ��", Toast.LENGTH_SHORT).show();
		}else if(register_passwd.getText().toString().trim().equals("")){
			Toast.makeText(this, "���벻��Ϊ��", Toast.LENGTH_SHORT).show();
		}else if(!register_passwd.getText().toString().trim().equals(reregister_passwd.getText().toString().trim())){
			Toast.makeText(this, "�����������벻һ��", Toast.LENGTH_SHORT).show();
		}else{
			return true;
		}
		return false;
	}
	
	}

			
			

		
