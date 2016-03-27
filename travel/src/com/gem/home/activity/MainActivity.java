package com.gem.home.activity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.MediaStore;
import android.text.InputType;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
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
import android.widget.Toast;

import com.gem.home.db.MyDatabaseHelper;
import com.gem.home.until.LoginData;
import com.gem.home.until.PublishTravel;
import com.gem.home.until.ToolDao;
import com.gem.scenery.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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


/**
 * 首页面activity
 *
 * @author king
 * @QQ:595163260
 * @version 2014年10月18日  下午11:48:34
 */
public class MainActivity extends Activity {

	private GridView noScrollgridview;
	private GridAdapter adapter;
	private View parentView;
	private PopupWindow pop = null;
	private LinearLayout ll_popup;
	public static Bitmap bimap ;
	PublishTravel plt;
	private Button btn_ok;
	private MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "Travel.db", null, 1);
	private EditText view, introduce;
	private String teamName, startPoint, destination, sex, city, arriveTime, startTime, allDay, age,
			teamNumber,sview,sintroduce;
	private String urlPicture="picture";
	private int i=0;
	private String url="http://10.201.1.12:8080/travel/Home_home_yy";
	private String urlsendPicture="http://10.201.1.12:8080/travel/Home_home_yyzp";
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		Res.init(this);
		bimap = BitmapFactory.decodeResource(
				getResources(),
				R.drawable.icon_addpic_unfocused);
		PublicWay.activityList.add(this);
		parentView = getLayoutInflater().inflate(R.layout.activity_selectimg, null);
		setContentView(parentView);
		Init();
		
		Intent intentDB = getIntent();
		intentDB(intentDB);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		view = (EditText) findViewById(R.id.edit_view);
		introduce = (EditText) findViewById(R.id.edit_introduce);
		//设置EditText的显示方式为多行文本输入   
		introduce.setInputType(InputType.TYPE_TEXT_FLAG_MULTI_LINE);   
		//文本显示的位置在EditText的最上方   
		introduce.setGravity(Gravity.TOP);   
		//改变默认的单行模式   
		introduce.setSingleLine(false);   
		//水平滚动设置为False   
		introduce.setHorizontallyScrolling(false); 
		btn_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
//				databaseDB();//存本地数据库；
				sview = view.getText().toString();
				 sintroduce = introduce.getText().toString();
				 Log.i("MainActivity","sview:"+sview+",sintroduce:"+sintroduce);
				sendHttp();//发送网络
//				try {
//					Thread.sleep(500);
//					sendPicture();
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
			}

		});
	}
	/**
	 * 发数据给网络
	 */
	public void sendHttp(){
		HttpUtils http=new HttpUtils();
		RequestParams params=new RequestParams();
//		params.addBodyParameter("teamName",mteamName);//队名
//		params.addBodyParameter("allDay",mallDay);//全程
//		params.addBodyParameter("startPoint",mstartPoint);//出发地
//		params.addBodyParameter("sex",msex);//性别
//		params.addBodyParameter("city",mcity);//城市
//		params.addBodyParameter("startTime",mstartTime);//出发时间
//		params.addBodyParameter("arriveTime",marriveTime);//到达时间
		LoginData ld=new LoginData();
		ld.setLd(16);
		plt=new PublishTravel();
		plt.setTeamName(teamName);
		plt.setLd(ld);
		plt.setAllDay( Integer.parseInt(allDay));
		plt.setStatPoint(startPoint);
		plt.setDestination(destination);
		plt.setSex(Integer.parseInt(sex));
		plt.setCity(Integer.parseInt(city));
		plt.setStartTime(ToolDao.getTimedate1(startTime));
		plt.setArriveTime(ToolDao.getTimedate1( arriveTime));
		plt.setViewPoint(sview);
		plt.setIntro(sintroduce);
		plt.setCreateTime(new Date());
		Log.i("MainActivity", plt.toString());
//		PublishTravel plt=new PublishTravel(mteamName, Integer.parseInt(mallDay), mstartPoint, mdestination, Integer.parseInt(msex), Integer.parseInt(mcity), Integer.parseInt(mteamNumber),ToolDao.getTimedate1(mstartTime),ToolDao.getTimedate1( marriveTime), sview, sintroduce, new Date(),ld);
		//获取相册或者拍照的多种图片
//		if(Bimp.tempSelectBitmap.size() == 0){
//			Log.i("Bimp", "Bimp.tempSelectBitmap:空");
//		}else{
//			Log.i("Bimp", "Bimp.tempSelectBitmap:1");
//		}
		//Log.i("MainActivity", "file:"+Bimp.tempSelectBitmap);
//		List<File> list=new ArrayList<File>();
		RequestParams param =new RequestParams();
		for (ImageItem image : Bimp.tempSelectBitmap) {
			File file=new File(image.getImagePath());
			params.addBodyParameter("file"+i,file);
			i++;
		}
		Gson gson=new GsonBuilder() 
		.setDateFormat("yyyy-MM-dd hh:mm:ss") 
		.create();
		String s1=gson.toJson(plt);
//		String s2=gson.toJson(list);
//		params.addBodyParameter("file",s2);
		
			params.addBodyParameter("publishTravel",s1);
        
//		params.addBodyParameter("view",sview);//景点
//		params.addBodyParameter("introduce",sintroduce);//简介
		http.send(HttpMethod.POST, urlsendPicture, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "搜索失败，请检查您的网络",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null) {
					Log.i("MainActivity", "is ok");
					String result = arg0.result;
					if(result.equals("success")){
						Toast.makeText(getApplication(), "is ok", Toast.LENGTH_LONG).show();
//						Intent intent = new Intent(MainActivity.this, Fragment_Activity.class);
//						startActivity(intent);
						//sendPicture();
					}
				}
			}
		});
		
		}
/*	*//**
	 * 请求发送图片
	 *//*
	
	public void sendPicture(){
		HttpUtils http=new HttpUtils();
		RequestParams param =new RequestParams();
		LoginData ld=new LoginData();
		Gson gson =new Gson();
		ld.setLd(16);
		String s=gson.toJson(ld);
		for (ImageItem image : Bimp.tempSelectBitmap) {
			File file=new File(image.getImagePath());
			param.addBodyParameter("file"+i,file);
			if(i==1){
				param.addBodyParameter("loginData",s);
			}
			i++;
		}
		http.send(HttpMethod.POST, url, param, new RequestCallBack<String>() {
			
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "搜索失败，请检查您的网络",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null) {
					String result = arg0.result;
					Log.i("MainActivity", "is ok"+","+result);
					if(result.equals("success")){
						Toast.makeText(getApplication(), "is ok", Toast.LENGTH_LONG).show();
						Intent intent = new Intent(MainActivity.this, Home_home.class);
						startActivity(intent);
					}
				}
			}
		});
		
	}*/
	
	/**
	 * 往本地数据库中插入数据
	 */
	public void databaseDB(){
		sview = view.getText().toString();
		 sintroduce = introduce.getText().toString();
		ContentValues values = new ContentValues();
		values.put("teamName", teamName);
//		values.put("teamZt", mteamZt);
		values.put("allDay",Integer.parseInt(allDay) );
		values.put("startPoint", startPoint);
		values.put("destination", destination);
		values.put("sex", sex);
		values.put("teamNumber", Integer.parseInt(teamNumber));
		values.put("age",Integer.parseInt(age) );
		values.put("city", city);
		values.put("startTime", startTime);
		values.put("arriveTime", arriveTime);
		values.put("introduce", sintroduce);
		values.put("view", sview);
		SQLiteDatabase db = dbHelper.getWritableDatabase();
		db.insert("Travel", null, values);
	}
	
	private void intentDB(Intent intent) {
		teamName = intent.getStringExtra("teamName");
//		mteamZt = intent.getStringExtra("teamZt");
		allDay = intent.getStringExtra("allDay");
		startPoint = intent.getStringExtra("startPoint");
		destination = intent.getStringExtra("destination");
		sex = intent.getStringExtra("sex");
		teamNumber = intent.getStringExtra("teamNumber");
//		mage = intent.getStringExtra("age");
		city = intent.getStringExtra("city");
		startTime = intent.getStringExtra("startTime");
		arriveTime = intent.getStringExtra("arriveTime");
		Log.i("MainActivity", "mteamName:"+teamName+",mallDay:"+
				allDay+",mstartPoint:"+startPoint+",destination:"+destination+",msex:" +
				sex+",mteamNumber:"+teamNumber+",mcity:"+city+",mstartTime:"+startTime+",marriveTime:"
				+arriveTime);
	}

	public void Init() {
		
		pop = new PopupWindow(MainActivity.this);
		
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
				Intent intent = new Intent(MainActivity.this,
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
					ll_popup.startAnimation(AnimationUtils.loadAnimation(MainActivity.this,R.anim.activity_translate_in));
					pop.showAtLocation(parentView, Gravity.BOTTOM, 0, 0);
				} else {
					Intent intent = new Intent(MainActivity.this,
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
			if (Bimp.tempSelectBitmap.size() < 9 && resultCode == RESULT_OK) {
				
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

