package com.gem.mine.activity;



import java.io.FileNotFoundException;
import com.gem.scenery.R;

import android.annotation.TargetApi;
import android.app.Activity;
import android.content.ContentUris;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class PersonalinformationActivity extends Activity implements OnClickListener{

	ImageView im_PersonalinformationActivity_return;
	//个人信息基本资料填写页面
	
	//点击拍照更换图片
	
	
	//实现图片上传  
	public static final int TAKE_PHOTO = 1;
	public static final int CROP_PHOTO = 2;
	public static final int CHOOSE_PHOTO = 3;
	//点击更换图片
	private Button tv_PersonalinformationActivity_mychange;
	private ImageView tv_PersonalinformationActivity_mypicturetouxiang;
	
	private Uri imageUri;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
//	        //透明导航栏  
//	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
//	  
		setContentView(R.layout.activity_mypageforgetpassword);
		setContentView(R.layout.activity_personalinformation);
		//按钮点击更换头像
		tv_PersonalinformationActivity_mychange=(Button) findViewById(R.id.tv_PersonalinformationActivity_mychange);
		//头像
		tv_PersonalinformationActivity_mypicturetouxiang=(ImageView) findViewById(R.id.tv_PersonalinformationActivity_mypicturetouxiang);
		
		tv_PersonalinformationActivity_mychange.setOnClickListener(new OnClickListener() {
			
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub		
				// 打开相机程序，选择照片
			Intent intent = new Intent("android.intent.action.GET_CONTENT");
				intent.setType("image/*");
				startActivityForResult(intent, CHOOSE_PHOTO);
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
					// 将裁剪后的照片显示出来
					tv_PersonalinformationActivity_mypicturetouxiang.setImageBitmap(bitmap);
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
			break;
		case CHOOSE_PHOTO:
			if (resultCode ==RESULT_OK) {
				// 判断手机系统版本号
				if (Build.VERSION.SDK_INT >= 14) {
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
			tv_PersonalinformationActivity_mypicturetouxiang.setImageBitmap(bitmap);
		} else {
			Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT)
					.show();
		
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
				
				
				
				break;
				//点击保存
			case R.id.bt_PersonalinformationActivity_mysave:
				
				break;
			
		}
	}
}
