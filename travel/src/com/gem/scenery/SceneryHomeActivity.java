package com.gem.scenery;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.home.dao.MyApplication;
import com.gem.scenery.R;
import com.gem.scenery.action.ListViewAdapter;
import com.gem.scenery.action.PlazaListViewAdapt;
import com.gem.scenery.action.SceneryHomeAdapt;
import com.gem.scenery.action.SceneryHomeChage;
import com.gem.scenery.action.ScreneryHomeOnClik;
import com.gem.scenery.action.SeasonListViewAdapt;
import com.gem.scenery.entity.PopularScene;
import com.gem.scenery.entity.Senery;
import com.gem.scenery.entity.SharePicture;
import com.gem.scenery.interfaces.OnLoadListener;
import com.gem.scenery.interfaces.OnRefreshListener;
import com.gem.scenery.utils.AutoListView;
import com.gem.scenery.utils.SPUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SceneryHomeActivity extends Fragment implements OnRefreshListener,
		OnLoadListener {
	private List<View> views;// Tab页面列表
	private ViewPager vp;// 叶卡内容
	private ImageView imageView, imageView2, imageView3;// 动画图片
	private TextView textView1, textView2, textView3;
	// private int offset = 0;// 动画图片偏移量
	//
	// private int curr+Index = 0;// 当前叶卡编号
	// private int bmpW;// 动画图片宽度
	private View view1, view2, view3;// 各个叶卡
	private AutoListView lv_hot;// 热门
	private AutoListView lv_plaza;// 广场
	private AutoListView lv_season;// 当季
//	private List<SharePicture> listmap;
	private List<AutoListView> lv = new ArrayList<AutoListView>();
//	private Senery senery;
//	private AutoListView auto;
//	private int size = 1;// 第几页
	private Context context;
	private ListViewAdapter adapter;// ListVeiw适配器
	private SceneryHomeChage shg;
	private List<PopularScene> arr =new ArrayList<PopularScene>();
	private PlazaListViewAdapt adapterP ;
	private SeasonListViewAdapt adapterS;
	// 区分当前操作是刷新还是加载
//	public int refresh;// 刷新
//	public int load;// 加载
	private MyApplication m;
	public static boolean f = true;
	int i = 1;
	private Handler handler = new Handler() {
		public void handleMessage(Message msg) {
			List<PopularScene> result = (List<PopularScene>) msg.obj;
			switch (msg.arg1) {
			case AutoListView.HOT:
				if(adapter==null){
					f=false;
					refreshORLoda(msg,result);
					adapter=new ListViewAdapter(context, arr);
					lv_hot.setAdapter(adapter);
				}else{
					f=true;
					refreshORLoda(msg,result);
				}
				lv_hot.setResultSize(result.size());
				adapter.notifyDataSetChanged();
				break;
			case AutoListView.PLAZA:
				f = true;
				refreshORLoda(msg,result);
				lv_plaza.setResultSize(result.size());
				if(adapterP==null){
				adapterP =new PlazaListViewAdapt(context,arr);
				lv_plaza.setAdapter(adapterP);
				}
				adapterP.notifyDataSetChanged();
				break;
			case AutoListView.SEASON:
				f = true;
				refreshORLoda(msg,result);
				lv_season.setResultSize(result.size());
				if(adapterS==null){
				adapterS=new SeasonListViewAdapt(context,arr);
				lv_season.setAdapter(adapter);
				}
				adapterS.notifyDataSetChanged();
				break;
			default:
				break;
			}
		};
	};
	
	public void refreshORLoda(Message msg,List<PopularScene> result){
		switch (msg.what) {
		case AutoListView.REFRESH://下拉刷新
			switch (shg.getPage()) {
			case 0:
				lv_hot.onRefreshComplete();
			break;
			case 1:
				lv_plaza.onRefreshComplete();
				break;
			case 2:
				lv_season.onRefreshComplete();
				break;
			}
			if(f){
			arr.clear();
			}
			if(result!=null&&!result.equals("")){
			arr.addAll(result);
			}
			break;
		case AutoListView.LOAD://上拉加载
			switch (shg.getPage()) {
			case 0:
				lv_hot.onLoadComplete();
			break;
			case 1:
				lv_plaza.onLoadComplete();
				break;
			case 2:
				lv_season.onLoadComplete();
				break;
			}
			if(result!=null&&!result.equals("")){
			arr.addAll(result);
			}
			break;
		}
	}

	// private ImageView imageview;
	// 初始化加载fragment里的部件
	// private Callback callback;//回调接口
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_scenery_home, container,
				false);

		return view;
	}

	// ViewPager加载的页面

	public void initViewPager(LayoutInflater inflater) {
		// //解析要显示的界面
		view1 = inflater.inflate(R.layout.activity_scenery_hot, null);
		view2 = inflater.inflate(R.layout.activity_scenery_plaza, null);
		view3 = inflater.inflate(R.layout.activity_scenery_present, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
		// setContentView(R.layout.activity_scenery_home);
		vp = (ViewPager) this.getView().findViewById(R.id.vp_scenery);
		context = getContext();
		m = (MyApplication) getActivity().getApplicationContext();
		// 获得LayoutInflater对象
		LayoutInflater inflater = getLayoutInflater(savedInstanceState).from(
				context);
		// ViewPager加载页面
		initViewPager(inflater);
		// 找标题控件
		textView1 = (TextView) this.getView()
				.findViewById(R.id.tv_screnery_hot);
		textView2 = (TextView) this.getView().findViewById(
				R.id.tv_screnery_plaza);
		textView3 = (TextView) this.getView().findViewById(
				R.id.tv_screnery_present);
		// 给标题控件注册点击事件、监听
		textView1.setOnClickListener(new ScreneryHomeOnClik(0, vp));
		textView2.setOnClickListener(new ScreneryHomeOnClik(1, vp));
		textView3.setOnClickListener(new ScreneryHomeOnClik(2, vp));

		// imageview=(ImageView) this.getView().findViewById(R.id.imageView1);
		// //将要分叶显示的View添加到list集合中
		views = new ArrayList<View>();
		views.add(view1);
		views.add(view2);
		views.add(view3);

		SceneryHomeAdapt sha = new SceneryHomeAdapt(views);

		// menu 自定义ActionBar
		// ActionBar actionBar=getActionBar();//获得ActionBar对象
		// // 设置显示选项。这种变化显示选项部分。改变显示选项的有限子集,看到setDisplayOptions(int,int)。
		// actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
		// actionBar.setCustomView(R.layout.action_scenery_menu);//�Զ���ActionBar����
		// actionBar.getCustomView().findViewById(R.id.action_camera).setOnClickListener(new
		// SceneryActionBar(this));//ע�����
		// actionBar.getCustomView().findViewById(R.id.action_search).setOnClickListener(new
		// SceneryActionBar(this));//ע�����

		// 下划线图片处理
		// imageView= (ImageView)
		// this.getView().findViewById(R.id.iv_underline);
		// bmpW = BitmapFactory.decodeResource(getResources(),
		// R.drawable.p).getWidth();// 获取图片宽度
		// DisplayMetrics dm = new DisplayMetrics();
		// //getWindowManager().getDefaultDisplay().getMetrics(dm);
		// int screenW = dm.widthPixels;// 获取分辨率宽度
		// offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
		// Matrix matrix = new Matrix();//图片处理对象
		// matrix.postTranslate(offset, 0);
		// imageView.setImageMatrix(matrix);// 设置动画初始位置
		//
		imageView = (ImageView) this.getView().findViewById(R.id.iv_underline);
		imageView2 = (ImageView) this.getView()
				.findViewById(R.id.iv_underline2);
		imageView3 = (ImageView) this.getView()
				.findViewById(R.id.iv_underline3);
		// 下拉刷新上拉加载
		// View v=inflater.inflate(R.layout.action_autolistview_title, null);
		lv_hot = (AutoListView) view1.findViewById(R.id.lv_hot);
		lv_hot.setOnRefreshListener(this);
		lv_hot.setOnLoadListener(this);
		
		lv_plaza = (AutoListView)view2.findViewById(R.id.lv_plaza);
		// lv_hot = (ListView) view1.findViewById(R.id.lv_hot);
		lv_season = (AutoListView) view3.findViewById(R.id.lv_season);
		lv_season.setOnRefreshListener(this);
		lv_season.setOnLoadListener(this);
		lv.add(lv_hot);
		lv.add(lv_plaza);
		lv.add(lv_season);

		vp.setAdapter(sha);
		vp.setCurrentItem(0);
		shg = new SceneryHomeChage(vp, imageView, imageView2, imageView3,
				context,lv,handler,views);
		shg.firstPage();
		shg.setThis(this);
		vp.setOnPageChangeListener(shg);
//		ListData(AutoListView.REFRESH);
		shg.sendHotData(AutoListView.REFRESH);
		lv_hot.setPage(0);
		lv_hot.setShg(shg);

	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		if (i != 1) {
			String s = (String) SPUtils.get(context, "ps", "1");
			if(s!=null&&s.equals("")){
				Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss")
						.create();
			PopularScene ps = gson.fromJson(s, PopularScene.class);
			int comment = Integer.parseInt((String) SPUtils.get(context,
					"comment", "0"));
			arr.set(comment, ps);
			adapter.notifyDataSetChanged();
			}
		}
		i++;
	}

	// String url="http://10.201.1.12:8080/travel/SharePicture_remeng";
/*	String url = "http://10.201.1.12:8080/travel/SecenryAllData";

	// 从网络上获取数据
	public void ListData(final int what) {
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		if (m.getLd() == null || m.getLd().equals("")) {
			params.addBodyParameter("ld", "");
		} else {
			params.addBodyParameter("ld", String.valueOf(m.getLd().getLd()));
		}
		//
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("sw", "onfailure");
				Toast.makeText(getActivity(), "请求失败，请检查网络", Toast.LENGTH_LONG)
						.show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				Type type = new TypeToken<List<PopularScene>>() {
				}.getType();
				Gson json = new GsonBuilder().setDateFormat(
						"yyyy-MM-dd hh:mm:ss").create();
				if (!result.equals(null)) {
					arr = json.fromJson(result, type);
					if (adapter == null) {
						f=false;
						adapter = new ListViewAdapter(context, arr);
						lv_hot.setAdapter(adapter);
					}else{
						f=true;
					}
					Message msg = handler.obtainMessage();
					msg.what = what;
					msg.arg1=AutoListView.HOT;
					msg.obj = arr;
					handler.sendMessage(msg);
				}
			}
		});
	}*/

	// private Handler handler = new Handler() {
	// public void handleMessage(Message msg) {
	// Collection<? extends Map<String, String>> result = (Collection<? extends
	// Map<String, String>>) msg.obj;
	// switch (msg.what) {
	// case AutoListView.REFRESH:
	// auto.onRefreshComplete();
	// // listmap.clear();
	// listmap.addAll(result);
	// break;
	// case AutoListView.LOAD:
	// auto.onLoadComplete();
	// listmap.addAll(result);
	// break;
	// }
	// auto.setResultSize(result.size());
	// adapter.notifyDataSetChanged();
	// }
	// };

//	/**
//	 * 获取旅游图片
//	 */
//	public void sendPicture(final String urlP) {
//		BitmapUtils bu = new BitmapUtils(context);
//		// bu.display(holder.picture, urlP);
//	}

	@Override
	public void onRefresh() {
		switch (shg.getPage()) {
		case 0:
			shg.sendHotData(AutoListView.REFRESH);
			break;
		case 1:
			shg.sendPlazaData(AutoListView.REFRESH);
			break;
		case 2:
			shg.sendSeasonData(AutoListView.REFRESH);
			break;
		default:
			break;
		}
	}

	@Override
	public void onLoad() {
		switch (shg.getPage()) {
		case 0:
			shg.sendHotData(AutoListView.LOAD);
			break;
		case 1:
			shg.sendPlazaData(AutoListView.LOAD);
			break;
		case 2:
			shg.sendSeasonData(AutoListView.LOAD);
			break;
		default:
			break;
		}
	}
	/*
	 * @Override protected void onActivityResult(int requestCode, int
	 * resultCode, Intent data) { // TODO Auto-generated method stub
	 * super.onActivityResult(requestCode, resultCode, data); if(resultCode !=
	 * RESULT_OK){ Log.i("MainActivity", "select pic error!"); return; }
	 * if(requestCode == SceneryPopWindow.SELECT_PIC){ if(imageUri != null){
	 * InputStream is = null; try { //读取图片到io流 is =
	 * getContentResolver().openInputStream(imageUri); //内存中的图片 Bitmap bm =
	 * BitmapFactory.decodeStream(is); imageview.setImageBitmap(bm); } catch
	 * (FileNotFoundException e) { e.printStackTrace(); } } }else if(requestCode
	 * == SceneryPopWindow.TAKE_PHOTO){ Intent intent = new
	 * Intent("com.android.camera.action.CROP"); intent.setDataAndType(imageUri,
	 * "image/*"); intent.putExtra("crop", "true"); intent.putExtra("aspectX",
	 * 1); intent.putExtra("aspectY", 1); intent.putExtra("outputX", 200);
	 * intent.putExtra("outputY", 200); intent.putExtra("scale", true);
	 * intent.putExtra("return-data", true); startActivityForResult(intent,
	 * SceneryPopWindow.CROP_PHOTO);//启动裁剪 }else if(requestCode ==
	 * SceneryPopWindow.CROP_PHOTO){//获取裁剪后的结果 Bundle bundle = data.getExtras();
	 * if(bundle != null){ Bitmap bm = bundle.getParcelable("data");//
	 * bundle.putParceable("data",bm); // bm.compress(CompressFormat.JPEG, 100,
	 * new FileOutputStream()); imageview.setImageBitmap(bm); } } }
	 */
	/**
	 * 回调，处理adapter的点击事件
	 */
	// @Override
	// public void myClick(View v) {
	//
	// }

}
