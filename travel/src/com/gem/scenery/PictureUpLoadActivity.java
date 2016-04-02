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
		private GridView noScrollgridview;
		private GridAdapter adapter;
		private View parentView;
		private PopupWindow pop = null;
		private LinearLayout ll_popup;
		public static Bitmap bimap ;
		private ImageView back;//返回
		private TextView fabu;//发布
		private EditText share;//分享此刻
		private EditText point;//定位地点
		private ImageView pointBtn;//定位按钮
		
		protected void onCreate(Bundle savedInstanceState) {
			super.onCreate(savedInstanceState);
			requestWindowFeature(Window.FEATURE_NO_TITLE);
			Res.init(this);
			bimap = BitmapFactory.decodeResource(
					getResources(),
					R.drawable.icon_addpic_unfocused);
			PublicWay.activityList.add(this);
			parentView = getLayoutInflater().inflate(R.layout.send_share_picture_action, null);
			setContentView(parentView);
			Init();
			back=(ImageView) findViewById(R.id.im_back_share);
			back.setOnClickListener(this);
			fabu=(TextView) findViewById(R.id.tv_fabu_share);
			fabu.setOnClickListener(this);
			share=(EditText) findViewById(R.id.et_chike_share);
			point=(EditText) findViewById(R.id.et_point_share);
			pointBtn=(ImageView) findViewById(R.id.iv_point_share);
			pointBtn.setOnClickListener(this);
		}

		@Override
		public void onClick(View v) {
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
		public void Init() {
			
			pop = new PopupWindow(PictureUpLoadActivity.this);
			
			View view = getLayoutInflater().inflate(R.layout.item_popupwindows, null);

			ll_popup = (LinearLayout) view.findViewById(R.id.ll_popup);
			
			pop.setWidth(LayoutParams.MATCH_PARENT);
			pop.setHeight(LayoutParams.WRAP_CONTENT);
			pop.setBackgroundDrawable(new BitmapDrawable());
			pop.setFocusable(true);
			pop.setOutsideTouchable(true);
			pop.setContentView(view);
			
			RelativeLayout parent = (RelativeLayout) view.findViewById(R.id.parent);
			Button bt1 = (Button) view
					.findViewById(R.id.item_popupwindows_camera);
			Button bt2 = (Button) view
					.findViewById(R.id.item_popupwindows_Photo);
			Button bt3 = (Button) view
					.findViewById(R.id.item_popupwindows_cancel);
			parent.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					pop.dismiss();
					ll_popup.clearAnimation();
				}
			});
			bt1.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					photo();
					pop.dismiss();
					ll_popup.clearAnimation();
				}
			});
			bt2.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					Intent intent = new Intent(PictureUpLoadActivity.this,
							AlbumActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.activity_translate_in, R.anim.activity_translate_out);
					pop.dismiss();
					ll_popup.clearAnimation();
				}
			});
			bt3.setOnClickListener(new OnClickListener() {
				public void onClick(View v) {
					pop.dismiss();
					ll_popup.clearAnimation();
				}
			});
			
			noScrollgridview = (GridView) findViewById(R.id.noScrollgridview);	
			noScrollgridview.setSelector(new ColorDrawable(Color.TRANSPARENT));
			adapter = new GridAdapter(this);
			adapter.update();
			noScrollgridview.setAdapter(adapter);
			noScrollgridview.setOnItemClickListener(new OnItemClickListener() {

				public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
						long arg3) {
					if (arg2 == Bimp.tempSelectBitmap.size()) {
						Log.i("ddddddd", "----------");
						ll_popup.startAnimation(AnimationUtils.loadAnimation(PictureUpLoadActivity.this,R.anim.activity_translate_in));
						pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
					} else {
						Intent intent = new Intent(PictureUpLoadActivity.this,
								GalleryActivity.class);
						intent.putExtra("position", "1");
						intent.putExtra("ID", arg2);
						startActivity(intent);
					}
				}
			});

		}

		@SuppressLint("HandlerLeak")
		public class GridAdapter extends BaseAdapter {
			private LayoutInflater inflater;
			private int selectedPosition = -1;
			private boolean shape;

			public boolean isShape() {
				return shape;
			}

			public void setShape(boolean shape) {
				this.shape = shape;
			}

			public GridAdapter(Context context) {
				inflater = LayoutInflater.from(context);
			}

			public void update() {
				loading();
			}

			public int getCount() {
				if(Bimp.tempSelectBitmap.size() == 9){
					return 9;
				}
				return (Bimp.tempSelectBitmap.size() + 1);
			}

			public Object getItem(int arg0) {
				return null;
			}

			public long getItemId(int arg0) {
				return 0;
			}

			public void setSelectedPosition(int position) {
				selectedPosition = position;
			}

			public int getSelectedPosition() {
				return selectedPosition;
			}

			public View getView(int position, View convertView, ViewGroup parent) {
				ViewHolder holder = null;
				if (convertView == null) {
					convertView = inflater.inflate(R.layout.item_published_grida,
							parent, false);
					holder = new ViewHolder();
					holder.image = (ImageView) convertView
							.findViewById(R.id.item_grida_image);
					convertView.setTag(holder);
				} else {
					holder = (ViewHolder) convertView.getTag();
				}

				if (position ==Bimp.tempSelectBitmap.size()) {
					holder.image.setImageBitmap(BitmapFactory.decodeResource(
							getResources(), R.drawable.icon_addpic_unfocused));
					if (position == 9) {
						holder.image.setVisibility(View.GONE);
					}
				} else {
					holder.image.setImageBitmap(Bimp.tempSelectBitmap.get(position).getBitmap());
				}

				return convertView;
			}

			public class ViewHolder {
				public ImageView image;
			}

			Handler handler = new Handler() {
				public void handleMessage(Message msg) {
					switch (msg.what) {
					case 1:
						adapter.notifyDataSetChanged();
						break;
					}
					super.handleMessage(msg);
				}
			};

			public void loading() {
				new Thread(new Runnable() {
					public void run() {
						while (true) {
							if (Bimp.max == Bimp.tempSelectBitmap.size()) {
								Message message = new Message();
								message.what = 1;
								handler.sendMessage(message);
								break;
							} else {
								Bimp.max += 1;
								Message message = new Message();
								message.what = 1;
								handler.sendMessage(message);
							}
						}
					}
				}).start();
			}
		}

		public String getString(String s) {
			String path = null;
			if (s == null)
				return "";
			for (int i = s.length() - 1; i > 0; i++) {
				s.charAt(i);
			}
			return path;
		}

		protected void onRestart() {
			adapter.update();
			super.onRestart();
		}

		private static final int TAKE_PICTURE = 0x000001;

		public void photo() {
			Intent openCameraIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(openCameraIntent, TAKE_PICTURE);
		}

		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			switch (requestCode) {
			case TAKE_PICTURE:
				if (Bimp.tempSelectBitmap.size() < 1 && resultCode == RESULT_OK) {
					
					String fileName = String.valueOf(System.currentTimeMillis());
					Bitmap bm = (Bitmap) data.getExtras().get("data");
					FileUtils.saveBitmap(bm, fileName);
					
					ImageItem takePhoto = new ImageItem();
					takePhoto.setBitmap(bm);
					Bimp.tempSelectBitmap.add(takePhoto);
				}
				break;
			}
		}
		
		public boolean onKeyDown(int keyCode, KeyEvent event) {
			if (keyCode == KeyEvent.KEYCODE_BACK) {
				for(int i=0;i<PublicWay.activityList.size();i++){
					if (null != PublicWay.activityList.get(i)) {
						PublicWay.activityList.get(i).finish();
					}
				}
				System.exit(0);
			}
			return true;
		}

}
