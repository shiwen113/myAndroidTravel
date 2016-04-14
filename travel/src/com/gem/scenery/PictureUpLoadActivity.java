package com.gem.scenery;

import java.io.File;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

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
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.provider.MediaStore.Audio.Media;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.Poi;
import com.gem.home.baiduMap.LocationService;
import com.gem.home.dao.MyApplication;
import com.gem.home.until.PublishTravel;
import com.gem.scenery.action.popupListView;
import com.gem.scenery.entity.FileUtils;
import com.gem.scenery.entity.ImageItem;
import com.gem.scenery.entity.SharePicture;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class PictureUpLoadActivity extends Activity implements OnClickListener {
//	private View parentView;
//	private PopupWindow pop = null;
//	private LinearLayout ll_popup;
	public static Bitmap bimap;
	private ImageView back;// 返回
	private TextView fabu;// 发布
	private EditText share;// 分享此刻
	private EditText point;// 定位地点
	private ImageView pointBtn;// 定位按钮
	private ImageView addPicture;// 上传图片
	private LinearLayout ll;
	private MyApplication m;
	private LocationService locationService;
	public BDLocationListener myListener = new MyLocationListener();
	private PopupWindow popupWindow;
//	private Uri imageUri;
	public static final int TAKE_PHOTO = 1;
	public static final int CROP_PHOTO = 2;
	public static final int CHOOSE_PHOTO = 3;
	private ImageItem takePhoto ;

	
	private EditText lvxingdui;
	private PopupWindow popup; 
	private ListView listView;
	private List<PublishTravel> arr;
	private PublishTravel pt;
	private boolean flag=false;
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.send_share_picture_action);
		m = (MyApplication) getApplicationContext();
		back = (ImageView) findViewById(R.id.im_back_share);
		back.setOnClickListener(this);
		fabu = (TextView) findViewById(R.id.tv_fabu_share);
		fabu.setOnClickListener(this);
		share = (EditText) findViewById(R.id.et_chike_share);
		point = (EditText) findViewById(R.id.et_point_share);
		pointBtn = (ImageView) findViewById(R.id.iv_point_share);
		pointBtn.setOnClickListener(this);
		addPicture = (ImageView) findViewById(R.id.iv_add_picture);
		addPicture.setOnClickListener(this);
		ll = (LinearLayout) findViewById(R.id.ll_add_one_picture);
		
		lvxingdui=(EditText) findViewById(R.id.et_input_travel);
		lvxingdui.setOnClickListener(this);
		if(m.getLd()!=null){
		initialize();
		
		}else{
//			Toast.makeText(getApplication(), "请先登录", Toast.LENGTH_LONG)
//			.show();
		}
	}

	
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.im_back_share:// 返回
			finish();
			break;
		case R.id.tv_fabu_share:// 发布
			if(m.getLd()!=null){
			String feel = share.getText().toString();
			String pointText = point.getText().toString();
			if (pointText != null) {
				Log.i("takePhoto", takePhoto.getImagePath());
				Log.i("takePhoto", takePhoto.getBitmap()+"");
				sendFaBu(feel, pointText,takePhoto.getImagePath());
			} else {
				Toast.makeText(getApplication(), "请填写地址", Toast.LENGTH_LONG)
						.show();
			}
			}else{
				Toast.makeText(getApplication(), "请先登录", Toast.LENGTH_LONG)
				.show();
			}
			break;
		case R.id.iv_point_share:// 定位
			Toast.makeText(m, "定位", Toast.LENGTH_SHORT).show();
			locationService = m.locationService;
			// 获取locationservice实例，建议应用中只初始化1个location实例，然后使用，可以参考其他示例的activity，都是通过此种方式获取locationservice实例的
			locationService.registerListener(myListener);
			// 注册监听
			int type = getIntent().getIntExtra("from", 0);
			if (type == 0) {
				locationService.setLocationOption(locationService
						.getDefaultLocationClientOption());
			} else if (type == 1) {
				locationService.setLocationOption(locationService.getOption());
			}
			locationService.start();
			break;
		case R.id.iv_add_picture:// 弹出poPupWindow
			onCamera();// 弹框
			Toast.makeText(PictureUpLoadActivity.this, "照相机", Toast.LENGTH_LONG)
					.show();
			break;
		case R.id.et_input_travel:
			if(m.getLd()==null){
				 popup();
				Toast.makeText(PictureUpLoadActivity.this, "请先登录", Toast.LENGTH_SHORT)
				.show();
			}else {
				 popup();	 
			}
		default:
			break;
		}

	}

	/**
	 * 照相机弹框
	 */
	public void onCamera() {
		// 点击相机图片弹出PopupWindow
		// 局注入器，注入布局给View对象
		View myView = getLayoutInflater().inflate(R.layout.action_camera, null);
		// 通过view 和宽·高，构造PopopWindow
		popupWindow = new PopupWindow(myView, 300, 400, true);

		popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000)
		// 此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
				);
		popupWindow.setOutsideTouchable(true);
		// 设置焦点为可点击
		popupWindow.setFocusable(true);// 可以试试设为false的结果
		// 将window视图显示在myButton下面
		popupWindow.showAsDropDown(addPicture);
		// System.out.println("点击了");
		TextView tv_camera = (TextView) myView.findViewById(R.id.tv_camera);
		TextView tv_photos = (TextView) myView.findViewById(R.id.tv_photos);
		tv_camera.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// 定义file对象 用于存储摄像头拍下来的图片 图片命名
				/*File outputImage = new File(Environment
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
				imageUri = Uri.fromFile(outputImage);*/
				// 利用意图调用相机
				Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
			/*	Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
				// Android自定义裁切输出位置
				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
				// 调用startActivityForResult()这个方法来启动相机程序
*/				startActivityForResult(intent, TAKE_PHOTO);
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
//				Toast.makeText(getApplication(), "onActivityResult_TAKE_PHOTO",
//						Toast.LENGTH_SHORT).show();
//				Log.i("onActivityResult", "TAKE_PHOTO");
//				Intent intent = new Intent("com.android.camera.action.CROP");
//				intent.setDataAndType(imageUri, "image/*");
//				intent.putExtra("scale", true);
//				intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
//				startActivityForResult(intent, CROP_PHOTO);
				String fileName = String.valueOf(System.currentTimeMillis());
				// Bitmap bitmap = BitmapFactory
				// .decodeStream(getContentResolver().openInputStream(
				// imageUri));
				Bitmap bm = (Bitmap) data.getExtras().get("data");
				Toast.makeText(getApplication(), "onActivityResult_TAKE_PHOTO",
						Toast.LENGTH_SHORT).show();
				Log.i("onActivityResult", "CROP_PHOTO");
				FileUtils.saveBitmap(bm, fileName);

				takePhoto = new ImageItem();
				takePhoto.setBitmap(bm);
				takePhoto.setImagePath(FileUtils.SDPATH + fileName + ".JPEG");
				// 将裁剪后的照片显示出来
				addPicture.setImageBitmap(bm);

			}
			break;
		case CROP_PHOTO:
			if (resultCode == RESULT_OK) {
				Log.i("log", "ok");
//				String fileName = String.valueOf(System.currentTimeMillis());
//				// Bitmap bitmap = BitmapFactory
//				// .decodeStream(getContentResolver().openInputStream(
//				// imageUri));
//				Bitmap bm = (Bitmap) data.getExtras().get("data");
//				Toast.makeText(getApplication(), "onActivityResult_TAKE_PHOTO",
//						Toast.LENGTH_SHORT).show();
//				Log.i("onActivityResult", "CROP_PHOTO");
//				FileUtils.saveBitmap(bm, fileName);
//
//				ImageItem takePhoto = new ImageItem();
//				takePhoto.setBitmap(bm);
//				takePhoto.setImagePath(FileUtils.SDPATH + fileName + ".JPEG");
//				// 将裁剪后的照片显示出来
//				addPicture.setImageBitmap(bm);

			}
			break;
		case CHOOSE_PHOTO:
			if (resultCode == RESULT_OK) {
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
		Cursor cursor = getContentResolver().query(uri, null, selection, null,
				null);
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
			takePhoto = new ImageItem();
			takePhoto.setBitmap(bitmap);
			takePhoto.setImagePath(imagePath);
			addPicture.setImageBitmap(bitmap);
		} else {
			Toast.makeText(this, "failed to get image", Toast.LENGTH_SHORT)
					.show();

		}
	}

	String url = "http://10.201.1.12:8080/travel/SharePicture_shangchuanlvtu";

	/**
	 * 发布旅途
	 */
	public void sendFaBu(String feel, String point, final String urlP) {
		if (m.getLd() != null) {
			HttpUtils http = new HttpUtils();
			RequestParams params = new RequestParams();
			final SharePicture sp = new SharePicture();
			sp.setFeeling(feel);
			sp.setLd(m.getLd());
			sp.setTime(new Date());
			sp.setUrlPhotos("123");
			sp.setViewPoint(point);
			PublishTravel pt=new PublishTravel();
			pt.setTd(12);
			sp.setTd(pt);
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
			params.addBodyParameter("sp",gson.toJson(sp));
			http.send(HttpMethod.POST, url, params,
					new RequestCallBack<String>() {

						@Override
						public void onFailure(HttpException arg0, String arg1) {
							// TODO Auto-generated method stub
							Toast.makeText(getApplication(), "请求失败，请检查网络",
									Toast.LENGTH_LONG).show();
						}

						@Override
						public void onSuccess(ResponseInfo<String> arg0) {
							Toast.makeText(getApplication(), "发布成功",
									Toast.LENGTH_LONG).show();
							String result=arg0.result;
							
							sendPictureTravel(Integer.parseInt(result), urlP);
						}
					});
		} else {
			Toast.makeText(getApplication(), "请先登陆", Toast.LENGTH_SHORT).show();
		}
	}

	/**
	 * 发图片
	 */
	public void sendPictureTravel(int sp, String urlp) {
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("sp", String.valueOf(sp));
		File file = new File(urlp);
		params.addBodyParameter("file", file);
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub

			}
		});
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub

		super.onStop();
		if (locationService != null) {
			locationService.unregisterListener(myListener); // 注销掉监听
			locationService.stop(); // 停止定位服务
		}
	}

	public void onConfigurationChanged(
			android.content.res.Configuration newConfig) {
		// 其实这里什么都不要做
		super.onConfigurationChanged(newConfig);
	};

	public class MyLocationListener implements BDLocationListener {
		String city;

		@Override
		public void onReceiveLocation(BDLocation location) {
			// Receive Location
			StringBuffer sb = new StringBuffer(256);

			// sb.append("time : ");
			// sb.append(location.getTime());
			// sb.append("\nerror code : ");
			// sb.append(location.getLocType());
			// sb.append("\nlatitude : ");
			// sb.append(location.getLatitude());
			// sb.append("\nlontitude : ");
			// sb.append(location.getLongitude());
			// sb.append("\nradius : ");
			// sb.append(location.getRadius());
			// sb.append("\nCountryCode : ");
			// sb.append(location.getCountryCode());
			// sb.append("\nCountry : ");
			// sb.append(location.getCountry());
			// sb.append("\ncitycode : ");
			// sb.append(location.getCityCode());

			// sb.append("\nDistrict : ");
			// sb.append(location.getDistrict());
			// sb.append("\nStreet : ");
			// sb.append(location.getStreet());
			// sb.append("\naddr : ");
			// sb.append(location.getAddrStr());
			// sb.append("\nDescribe: ");
			// sb.append(location.getLocationDescribe());
			// sb.append("\nDirection(not all devices have value): ");
			// sb.append(location.getDirection());
			// sb.append("\nPoi: ");

			// sb.append("\ncity : ");
			sb.append(location.getCity());
			city = sb.toString();
			Log.i("cityxxy", "fuck+" + city);
			setMyText(city);
			if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
				sb.append("\nspeed : ");
				sb.append(location.getSpeed());// 单位：公里每小时
				sb.append("\nsatellite : ");
				sb.append(location.getSatelliteNumber());
				sb.append("\nheight : ");
				sb.append(location.getAltitude());// 单位：米
				sb.append("\ndirection : ");
				sb.append(location.getDirection());// 单位度
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				sb.append("\ndescribe : ");
				sb.append("gps定位成功");

			} else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
				sb.append("\naddr : ");
				sb.append(location.getAddrStr());
				// 运营商信息
				sb.append("\noperationers : ");
				sb.append(location.getOperators());
				sb.append("\ndescribe : ");
				sb.append("网络定位成功");
			} else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
				sb.append("\ndescribe : ");
				sb.append("离线定位成功，离线定位结果也是有效的");
			} else if (location.getLocType() == BDLocation.TypeServerError) {
				sb.append("\ndescribe : ");
				sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
			} else if (location.getLocType() == BDLocation.TypeNetWorkException) {
				sb.append("\ndescribe : ");
				sb.append("网络不同导致定位失败，请检查网络是否通畅");
			} else if (location.getLocType() == BDLocation.TypeCriteriaException) {
				sb.append("\ndescribe : ");
				sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
			}
			sb.append("\nlocationdescribe : ");
			sb.append(location.getLocationDescribe());// 位置语义化信息
			List<Poi> list = location.getPoiList();// POI数据
			if (list != null) {
				sb.append("\npoilist size = : ");
				sb.append(list.size());
				for (Poi p : list) {
					sb.append("\npoi= : ");
					sb.append(p.getId() + " " + p.getName() + " " + p.getRank());
				}
			}
			Log.i("BaiduLocationApiDem", sb.toString());

		}

	}

	public void setMyText(String city) {
		Log.i("cityxxy", "2");

		Log.i("cityxxy", city);
		point.setText(city);
	}

	public void initialize(){
	       HttpUtils httpUtils=new HttpUtils();
	       RequestParams params=new RequestParams();
	       params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
	       String url="http://10.201.1.12:8080/travel/Wode_lvxingdui";
	       httpUtils.send(HttpMethod.POST, url, params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String s=arg0.result;
				if(s!=null&&!s.equals("")&&!s.equals("null")){
				Gson gson=new GsonBuilder()
 			.setDateFormat("yyyy-MM-dd hh:mm:ss")
 			.create();
				Type type=new TypeToken<List<PublishTravel>>(){}.getType();
				arr=gson.fromJson(s,type);	
				}
			}
			});
	}
	 	
	public void popup(){
		   View myView = getLayoutInflater().inflate(R.layout.action_popup, null);
		   popup = new PopupWindow(myView, 500, 400, true);
	        
		   popup.setBackgroundDrawable(new ColorDrawable(0x00000000)
	               //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
	              );
		   popup.setOutsideTouchable(true);
	       //设置焦点为可点击
		   popup.setFocusable(true);//可以试试设为false的结果
		  
		   
	       listView=(ListView) myView.findViewById(R.id.lv_popup);
	       if(arr!=null&&arr.size()!=0){
		   popupListView adapter=new popupListView(arr,PictureUpLoadActivity.this);
		   listView.setAdapter(adapter);
		   listView.setOnItemClickListener(new OnItemClickListener() {

					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						// TODO Auto-generated method stub
						pt=arr.get(position);
					    lvxingdui.setText(pt.getTeamName());
						popup.dismiss();
					}
					
				});
	       }
	       popup.showAsDropDown(lvxingdui);
//      listView.setAdapter(new popupListView(arr, context))          	       
	}
}
