package com.gem.mine.activity;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Type;
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

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ParseException;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnFocusChangeListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.gem.home.activity.Fragment_Activity;
import com.gem.home.dao.MyApplication;
import com.gem.home.until.LoginData;
import com.gem.message.activity.RongyunDemo;
import com.gem.scenery.R;
import com.gem.scenery.utils.SPUtils;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class MypageregisterActivity extends Activity {

	private EditText register_username;
	private EditText register_passwd;
	private EditText register_passwd1;
	private EditText reregister_account;
	private Button register_submit;
	private String str;
	 private MyApplication m;
	 private Context context;
	 private ImageView back;
	//注册页面
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mypageregister);
		StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
		StrictMode.setThreadPolicy(policy);	
		m=(MyApplication) getApplicationContext();
		context=getApplicationContext();
		register_username=(EditText)findViewById(R.id.register_username);
		register_passwd=(EditText)findViewById(R.id.register_passwd);
		register_passwd1=(EditText)findViewById(R.id.reregister_passwd);
		reregister_account=(EditText)findViewById(R.id.et_phone_num);
		register_submit=(Button)findViewById(R.id.register_submit);
		back=(ImageView) findViewById(R.id.ib_login_infor_back);
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();
			}
		});
		register_username.setOnFocusChangeListener(new OnFocusChangeListener()
		{   
			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					if(register_username.getText().toString().trim().length()<2){
						Toast.makeText(MypageregisterActivity.this, "用户名不能小于2个字符", Toast.LENGTH_SHORT).show();
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
						Toast.makeText(MypageregisterActivity.this,"密码最少6位", Toast.LENGTH_SHORT).show();
					}
				}
			}
			
		});
		register_passwd1.setOnFocusChangeListener(new OnFocusChangeListener()
		{

			@Override
			public void onFocusChange(View v, boolean hasFocus) {
				// TODO Auto-generated method stub
				if(!hasFocus){
					if(!register_passwd1.getText().toString().trim().equals(register_passwd.getText().toString().trim())){
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
					str=reregister_account.getText().toString();
					if(!str.equals("")&&!str.matches("^((13[0-9])|(15[^4\\D])|(18[0,5-9]))\\d{8}$")){
						Toast.makeText(MypageregisterActivity.this,"请正确的输入手机号", Toast.LENGTH_SHORT).show();
					}
				}
			}
			
		});
		/**
		 * 注册
		 */
		register_submit.setOnClickListener(new OnClickListener(){
			
			@Override
			public void onClick(View v) {
				str=reregister_account.getText().toString();
				if(register_username.getText().toString().trim().length()<2){
					Toast.makeText(MypageregisterActivity.this, "用户名不能小于2个字符", Toast.LENGTH_SHORT).show();
					return ;
				}
				if(register_passwd.getText().toString().trim().length()<6){
					Toast.makeText(MypageregisterActivity.this,"密码最少6位", Toast.LENGTH_SHORT).show();
					return ;
				}
			    
				if(!register_passwd1.getText().toString().trim().equals(register_passwd.getText().toString().trim())){
					Toast.makeText(MypageregisterActivity.this, "两次密码输入不一致", Toast.LENGTH_SHORT).show(); 
					return ;
				}
//				if(String.)
				if(!str.equals("")&&!str.matches("^((13[0-9])|(15[^4\\D])|(18[0,5-9]))\\d{8}$")){
					Toast.makeText(MypageregisterActivity.this,"请输入正确的手机号", Toast.LENGTH_SHORT).show();
					return ;
				}
//				if(!checkEdit()){
//					return ;
//				}
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
				    if(!strResult.equals("注册失败，账号已注册")){
				    LoginData ld=new LoginData();
				    ld.setLd(Integer.parseInt(strResult));
				    ld.setUserName(register_username.getText().toString());
				    ld.setAccount(str);
				    ld.setPassWord(register_passwd.getText().toString());
				    m.setLd(ld);
				    RongyunDemo rd=new RongyunDemo(context);
				    SPUtils.put(context, "Account",ld.getAccount());
			    	SPUtils.put(context, "pwd",ld.getPassWord());
			    	if(SPUtils.get(context,ld.getLd()+"","")!=null&&!SPUtils.get(context,ld.getLd()+"","").equals("")){
			 			rd.connect((String)SPUtils.get(context, ld.getLd()+"",""));
			 		}else{
			 			rd.getToken();
			 		}
//				    login(str,register_passwd.getText().toString());//登录
					Intent intent=new Intent(MypageregisterActivity.this,Fragment_Activity.class);
					startActivity(intent);
				    }else {
						return ;
					}
				    
				}
				else
				{
					Toast.makeText(MypageregisterActivity.this, "请求错误", Toast.LENGTH_SHORT).show();
				}
				
			}
			
		});
	}
//	private boolean checkEdit(){
//		if(register_username.getText().toString().trim().equals("")){
//			Toast.makeText(this, "用户名不能为空", Toast.LENGTH_SHORT).show();
//		}else if(register_passwd.getText().toString().trim().equals("")){
//			Toast.makeText(this,"密码不能为空", Toast.LENGTH_SHORT).show();
//		}else if(!register_passwd.getText().toString().trim().equals(register_passwd.getText().toString().trim())){
//			Toast.makeText(this, "两次密码输入不一致", Toast.LENGTH_SHORT).show();
//		}else{
//			return true;
//		}
//		return false;
//	}
/*	*//**
	 * 登录账号
	 *//*
	public void login(final String account,final String pwd){
		HttpUtils http=new HttpUtils();
		//String count=count.getText().toString();
		
		String url;
		url="http://10.201.1.12:8080/travel/Mypagelogin_login";
		if(m.getLd()==null){
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
			    }else if(data.getAccount().equals(account)&&data.getPassWord().equals(pwd)){
			    	 m.setLd(data);
			    	 RongyunDemo rd=new RongyunDemo(MypageregisterActivity.this);
//			    	 rd.getToken();
			    	 Intent intent=new Intent(MypageregisterActivity.this,Fragment_Activity.class);
			 		if(SPUtils.get(MypageregisterActivity.this,"token","")!=null&&!SPUtils.get(MypageregisterActivity.this, "token","").equals("")){
			 			rd.connect((String)SPUtils.get(MypageregisterActivity.this, "token",""));
			 		}else{
			 			rd.getToken();
			 		}
			    	startActivity(intent);
			    }
//				if(result!=null&&!result.trim().equals("null")){
//					
//					//Tt.setText(result);
//					Tt.setText(result);
//				}else{
//					Tt.setText(result);
//				}
			
			
				
			}


	
	});
		
		}
	*/
}

			
			

		
