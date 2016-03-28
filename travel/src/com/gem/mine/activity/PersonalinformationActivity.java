package com.gem.mine.activity;


import java.lang.reflect.Type;

import com.gem.home.until.LoginData;
import com.gem.scenery.R;
import com.gem.scenery.entity.PersonalData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;

import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;

import android.widget.Toast;
    //个人信息基本资料填写页面
public class PersonalinformationActivity extends Activity implements OnClickListener,OnCheckedChangeListener{
		
	//用户名
	private EditText et_PersonalinformationActivity_name;
	//性别
	private  RadioGroup radioGroup2;	
	//常在地
	private   EditText et_PersonalinformationActivity_address;		
	//年龄
	private EditText et_myage;	
	private int sex=1;
	//实现图片上传  
    private Button mysave; 
    
    private RadioButton radioButton1;
    private RadioButton radioButton2;
	//点击更换图片
    private String httpUrl="http://10.201.1.12:8080/travel/Mypagesave_save";
    private String httpUrl1="http://10.201.1.12:8080/travel/Mypageeditor_editor";
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  		
		setContentView(R.layout.activity_personalinformation);
		mysave=(Button) findViewById(R.id.mysave);
		mysave.setOnClickListener(this);
		
		radioButton1=(RadioButton) findViewById(R.id.radio0);
		radioButton2=(RadioButton) findViewById(R.id.radio1);
		//用户名
		et_PersonalinformationActivity_name=(EditText) findViewById(R.id.et_PersonalinformationActivity_name);
		//性别
		radioGroup2=(RadioGroup) findViewById(R.id.rg_save_personal_datas);	
		radioGroup2.setOnCheckedChangeListener(this);
		//年龄
		et_myage=(EditText) findViewById(R.id.et_myage);
		//常用地址
		et_PersonalinformationActivity_address=(EditText) findViewById(R.id.et_PersonalinformationActivity_address);
		
		//分别写每个控件的实现		
		HttpUtils httpUtils=new HttpUtils();
	    RequestParams params=new RequestParams();	
	    LoginData ld=new LoginData();
	    /*
	     * ??? ld是死数据
	     */
	    ld.setLd(16);
		Gson gson=new Gson();
	    String s=gson.toJson(ld);
	    params.addBodyParameter("LoginData",s);
	    httpUtils.send(HttpMethod.POST,httpUrl1, params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("mysave","not ok1");
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				Log.i("mysave","ok1");
				String s=arg0.result;
				if(s!=null&&!s.equals("")){
					Gson gson=new Gson();
					Type type=new TypeToken<PersonalData>(){}.getType();
					PersonalData data=gson.fromJson(s, type);
					et_PersonalinformationActivity_name.setText(data.getLd().getUserName());
					et_myage.setText(String.valueOf(data.getAge()));
					et_PersonalinformationActivity_address.setText(data.getOftenPoint());
					if(data.getSex()==1){
						radioButton1.setChecked(true);
					}else {
						radioButton2.setChecked(true);
					}
				}
			}
	    	
	    });
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){		
	     //点击返回按钮，页面跳转至个人主页
			case R.id.im_PersonalinformationActivity_return:
				finish();
				break;
			//点击更换图片
			case R.id.tv_PersonalinformationActivity_mychange:		
				break;
				//点击保存  传送数据
			case R.id.mysave:
				Log.i("mysave","ok2");
				HttpUtils httpUtils=new HttpUtils();
			    RequestParams params=new RequestParams();	
//				HttpPost httpRequest=new HttpPost(httpUrl);				
//				List<NameValuePair> params=new ArrayList<NameValuePair>();
				PersonalData personal=new PersonalData();
				//常用地址
				personal.setOftenPoint(et_PersonalinformationActivity_address.getText().toString());
				//用户名
				
			    //年龄
				if(!et_myage.getText().toString().equals("")){
				personal.setAge(Integer.parseInt(et_myage.getText().toString()));
			    }else {
			    	personal.setAge(-1);
				}
			
			    Log.i("my",""+sex);
			    personal.setSex(sex);
			    LoginData ld=new LoginData();
			    /*
			     * ??? ld是死数据
			     */
			    ld.setLd(16);
			    ld.setUserName(et_PersonalinformationActivity_name.getText().toString());
			    personal.setLd(ld);
				//传性别过去
				Gson gson=new Gson();
			    String s=gson.toJson(personal);
			    params.addBodyParameter("PersonalData",s);
			    httpUtils.send(HttpMethod.POST,httpUrl, params,new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						// TODO Auto-generated method stub
						Log.i("mysave","notOk2");
					}

					@Override
					public void onSuccess(ResponseInfo<String> arg0) {
						// TODO Auto-generated method stub
						Log.i("mysave","ok2");
					}
				});				
	}
  }
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		// TODO Auto-generated method stub
		RadioButton rb = (RadioButton) findViewById(group
				.getCheckedRadioButtonId());
		String text = rb.getText().toString();
		if (text.equals("男")) {
			sex = 1;
		} else if (text.equals("女")) {
			sex = 0;
		} 
	}
}

