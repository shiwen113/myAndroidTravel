package com.gem.scenery.action;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;

import com.gem.scenery.R;

public class SceneryPopWindow implements OnClickListener {
	private Activity activity;
	public static final int REQUESTCODE_ONE=1;
	public static final int REQUESTCODE_TWO=2;
	public static final int SELECT_PIC = 11;
	public static final int TAKE_PHOTO = 12;
	public static final int CROP_PHOTO = 13;
	private Uri imageUri;
	public SceneryPopWindow(Context context) {
		this.activity=(Activity) context;
	}
	public SceneryPopWindow(Uri imageUri) {
		this.imageUri=imageUri;
	}
	@Override
	public void onClick(View v) {
		Intent intent=new Intent();
		switch (v.getId()) {
		case R.id.tv_camera:
			intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			activity.startActivityForResult(intent, REQUESTCODE_ONE);
			System.out.println("相机点击了");
			break;

		case R.id.tv_photos:
			intent.setAction(Intent.ACTION_PICK);
			intent.setType("image/*");
			//裁剪
			intent.putExtra("crop", "true");
			//宽高比例
			intent.putExtra("aspectX", 1);
			intent.putExtra("aspectY", 1);
			//定义宽和高
			intent.putExtra("outputX", 300);
			intent.putExtra("outputY", 300);
			//图片是否缩放
			intent.putExtra("scale", true);
			//是否要返回值
			intent.putExtra("return-data", false);
			//把图片存放到imageUri
			intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
			//图片输出格式
			intent.putExtra("outputFormat", Bitmap.CompressFormat.JPEG.toString());
			intent.putExtra("noFaceDetection", true); 
			activity.startActivityForResult(intent, REQUESTCODE_TWO);
			break;
		}
		
		
	}

}
