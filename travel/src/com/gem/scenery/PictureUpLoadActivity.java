package com.gem.scenery;

import java.util.Date;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.home.until.LoginData;
import com.gem.scenery.action.SceneryTile;
import com.gem.scenery.entity.SharePicture;
import com.king.photo.activity.AlbumActivity;
import com.king.photo.activity.GalleryActivity;
import com.king.photo.util.Bimp;
import com.king.photo.util.FileUtils;
import com.king.photo.util.ImageItem;
import com.king.photo.util.PublicWay;
import com.king.photo.util.Res;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class PictureUpLoadActivity extends Activity implements OnClickListener{
		private View parentView;
		private PopupWindow pop = null;
		private LinearLayout ll_popup;
		public static Bitmap bimap ;
		private ImageView back;//返回
		private TextView fabu;//发布
		private EditText share;//分享此刻
		private EditText point;//定位地点
		private ImageView pointBtn;//定位按钮
		private ImageView addPicture;//上传图片
		private LinearLayout ll;
		
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			setContentView(R.layout.send_share_picture_action);
			back=(ImageView) findViewById(R.id.im_back_share);
			back.setOnClickListener(this);
			fabu=(TextView) findViewById(R.id.tv_fabu_share);
			fabu.setOnClickListener(this);
			share=(EditText) findViewById(R.id.et_chike_share);
			point=(EditText) findViewById(R.id.et_point_share);
			pointBtn=(ImageView) findViewById(R.id.iv_point_share);
			pointBtn.setOnClickListener(this);
			addPicture=(ImageView) findViewById(R.id.iv_add_picture);
			addPicture.setOnClickListener(this);
			ll=(LinearLayout) findViewById(R.id.ll_add_one_picture);
		}

		@Override
		public void onClick(View v) {
			SceneryTile st=new SceneryTile(PictureUpLoadActivity.this,v);
			switch (v.getId()) {
			case R.id.im_back_share://返回
				finish();
				break;
			case R.id.tv_fabu_share://发布
				String feel=share.getText().toString();
				String pointText=point.getText().toString();
				if(pointText!=null){
				sendFaBu(feel,pointText);
				}else{
					Toast.makeText(getApplication(), "请填写地址", Toast.LENGTH_LONG).show();
				}
				break;
			case R.id.iv_point_share://定位
				
				break;
			case R.id.iv_add_picture://弹出poPupWindow
				st.onCamera();//弹框
				Toast.makeText(PictureUpLoadActivity.this, "照相机", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
			
		}
		
		String url="http://10.201.1.12:8080/travel/SharePicture_shangchuanlvtu";
		/**
		 * 发布旅途
		 */
		public void sendFaBu(String feel,String point){
			HttpUtils http=new HttpUtils();
			RequestParams params =new RequestParams();
			SharePicture sp=new SharePicture();
			LoginData ld=new LoginData();
			ld.setLd(17);
			sp.setFeeling(feel);
			sp.setLd(ld);
			sp.setTime(new Date());
			sp.setUrlPhotos("123");
			sp.setViewPoint(point);
			http.send(HttpMethod.POST, url, params,new RequestCallBack<String>() {

				@Override
				public void onFailure(HttpException arg0, String arg1) {
					// TODO Auto-generated method stub
					Toast.makeText(getApplication(), "请求失败，请检查网络", Toast.LENGTH_LONG).show();
				}

				@Override
				public void onSuccess(ResponseInfo<String> arg0) {
					Toast.makeText(getApplication(), "发布成功", Toast.LENGTH_LONG).show();
				}
			});
		}
		
}
