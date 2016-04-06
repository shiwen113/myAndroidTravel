package com.gem.mine.activity;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;

import com.gem.home.dao.MyApplication;
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

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;
import android.view.View;
import android.view.Window;

import android.view.View.OnClickListener;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
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
    private MyApplication m;
    private ImageView img;
    private PopupWindow popupWindow; 
	private Uri imageUri;
	public static final int TAKE_PHOTO = 1;
	public static final int CROP_PHOTO = 2;
	public static final int CHOOSE_PHOTO = 3;
	@Override
	protected void onCreate(Bundle savedInstanceState) {		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);  		
		setContentView(R.layout.activity_personalinformation);
		m=(MyApplication) getApplicationContext();
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
		img=(ImageView) findViewById(R.id.tv_PersonalinformationActivity_mypicturetouxiang);
		img.setOnClickListener(this);
		if(m.getLd()!=null){
		//分别写每个控件的实现		
		HttpUtils httpUtils=new HttpUtils();
	    RequestParams params=new RequestParams();	
		Gson gson=new Gson();
	    String s=gson.toJson(m.getLd());
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
				if(!s.equals("null")&&s!=null){
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
		}else{
			Toast.makeText(getApplication(), "请先登陆", Toast.LENGTH_SHORT).show();
		}
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
				onCamera();
				break;
				//点击保存  传送数据
			case R.id.mysave:
				Log.i("mysave","ok2");
				if(m.getLd()!=null){
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
			    ld.setLd(m.getLd().getLd());
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
			}else{
				Toast.makeText(getApplication(), "请先登陆", Toast.LENGTH_SHORT).show();
			}
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
	
	/**
	  * 照相机弹框
	  */
	 public void onCamera(){
		//点击相机图片弹出PopupWindow
//		 局注入器，注入布局给View对象
      View myView = getLayoutInflater().inflate(R.layout.action_camera, null);
      //通过view 和宽·高，构造PopopWindow
      popupWindow = new PopupWindow(myView, 300, 400, true);
       
      popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000)
              //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
             );
      popupWindow.setOutsideTouchable(true);
      //设置焦点为可点击
      popupWindow.setFocusable(true);//可以试试设为false的结果
      //将window视图显示在myButton下面
      popupWindow.showAsDropDown(img);
//		System.out.println("点击了");
      TextView tv_camera=(TextView) myView.findViewById(R.id.tv_camera);
      TextView tv_photos=(TextView) myView.findViewById(R.id.tv_photos);
      tv_camera.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			// 定义file对象 用于存储摄像头拍下来的图片 图片命名
			File outputImage = new File(Environment
					.getExternalStorageDirectory(), "output_image.jpg");
			if (outputImage.exists()) {
				outputImage.delete();
			}
			try {
				outputImage.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			// 调用URI的fromFile方法将File对象转化成Uri对象 这个对象指的就是拍出来的图片的唯一地址
			imageUri = Uri.fromFile(outputImage);
			// 利用意图调用相机
			Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
			// Android自定义裁切输出位置
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			// 调用startActivityForResult()这个方法来启动相机程序
			startActivityForResult(intent, TAKE_PHOTO);
		}
	});
      tv_photos.setOnClickListener(new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent = new Intent("android.intent.action.GET_CONTENT");// 打开相机程序，选择照片
			intent.setType("image/*");
			startActivityForResult(intent, CHOOSE_PHOTO);
			popupWindow.dismiss();
		}
	});
	 }

	// 通过onActivityResult()接收传回的图像
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			// TODO Auto-generated method stub
			super.onActivityResult(requestCode, resultCode, data);
			switch (requestCode) {
			case TAKE_PHOTO:
				if (resultCode == RESULT_OK) {
					Toast.makeText(getApplication(), "onActivityResult_TAKE_PHOTO", Toast.LENGTH_SHORT).show();
					Log.i("onActivityResult", "TAKE_PHOTO");
					Intent intent = new Intent("com.android.camera.action.CROP");
					intent.setDataAndType(imageUri, "image/*");
					intent.putExtra("scale", true);
					intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
					startActivityForResult(intent, CROP_PHOTO);
				}
				break;
			case CROP_PHOTO:
				if (resultCode == RESULT_OK) {
					Log.i("log", "ok");
					try {
						Bitmap bitmap = BitmapFactory
								.decodeStream(getContentResolver().openInputStream(
										imageUri));
						Toast.makeText(getApplication(), "onActivityResult_TAKE_PHOTO", Toast.LENGTH_SHORT).show();
						Log.i("onActivityResult", "CROP_PHOTO");
						// 将裁剪后的照片显示出来
						img.setImageBitmap(bitmap);
					} catch (FileNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}
				break;
			case CHOOSE_PHOTO:
				if (resultCode ==RESULT_OK) {
					// 判断手机系统版本号
					if (Build.VERSION.SDK_INT >= 19) {
						HandlerImageOnKitKat(data);
					} else {
						HandlerImageBeforeKitKat(data);
					}
				}
				break;
			default:
				break;
		
		}
		}
		@TargetApi(19)
		private void HandlerImageOnKitKat(Intent data) {
			// TODO Auto-generated method stub
			String imagePath = null;
			Uri uri = data.getData();
			if (DocumentsContract.isDocumentUri(this, uri)) {
				String docId = DocumentsContract.getDocumentId(uri);
				if ("com.android.providers.downloads.documents".equals(uri
						.getAuthority())) {
					String id = docId.split(":")[1];
					String selection = MediaStore.Images.Media._ID + "=" + id;
					imagePath = getImagePath(
							MediaStore.Images.Media.EXTERNAL_CONTENT_URI, selection);
				} else if ("com.android.providers.downloads.documents".equals(uri
						.getAuthority())) {
					Uri contentUri = ContentUris.withAppendedId(Uri
							.parse("content://downloads/public_" + "dowloads"),
							Long.valueOf(docId));
					imagePath = getImagePath(contentUri, null);
				}
			} else if ("content".equalsIgnoreCase(uri.getScheme())) {
				imagePath = getImagePath(uri, null);
			}
			displayImage(imagePath);
		}

		private void HandlerImageBeforeKitKat(Intent data) {
			// TODO Auto-generated method stub
			Uri uri = data.getData();
			String imagePath = getImagePath(uri, null);
			displayImage(imagePath);
		}


		private String getImagePath(Uri uri, String selection) {
			// TODO Auto-generated method stub
			String path = null;
			Cursor cursor =getContentResolver().query(uri, null, selection,
					null, null);
			if (cursor != null) {
				if (cursor.moveToFirst()) {
					path = cursor.getString(cursor.getColumnIndex(Media.DATA));
				}
				cursor.close();
			}
			return path;
		}
		
		private void displayImage(String imagePath) {
			// TODO Auto-generated method stub
			if (imagePath != null) {
				Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
				img.setImageBitmap(bitmap);
			} else {
				Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT)
						.show();
			
			}
		}
}

