package com.gem.scenery;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.scenery.R;
import com.gem.scenery.action.ListViewAdapter;
import com.gem.scenery.action.SceneryHomeAdapt;
import com.gem.scenery.action.SceneryHomeChage;
import com.gem.scenery.action.SceneryPopWindow;
import com.gem.scenery.action.ScreneryHomeOnClik;
import com.gem.scenery.entity.Senery;
import com.gem.scenery.interfaces.OnLoadListener;
import com.gem.scenery.interfaces.OnRefreshListener;
import com.gem.scenery.utils.AutoListView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SceneryHomeActivity extends Fragment implements OnRefreshListener,OnLoadListener{
		private List<View> views;// Tab页面列表
		private ViewPager vp;//叶卡内容
	    private ImageView imageView;// 动画图片
	    private TextView textView1,textView2,textView3;
	    private int offset = 0;// 动画图片偏移量
	    private int currIndex = 0;// 当前叶卡编号
	    private int bmpW;// 动画图片宽度
	    private View view1,view2,view3;//各个叶卡
	    private ListView lv_hot;//热门
	    private ListView lv_plaza;//广场
	    private ListView lv_season;//当季
	    private List<Map<String,String>> listmap;
	    private Senery senery;
	    private AutoListView auto;
	    private int size=1;//第几页
	    private Context context;
	    private ListViewAdapter adapter;//ListVeiw适配器
	    // 区分当前操作是刷新还是加载
	    public  int refresh ;//刷新  
	    public  int load ;//加载
	    private ImageView imageview;
	    private Uri imageUri;
		// 初始化加载fragment里的部件
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.activity_scenery_home, container, false);

			return view;
		}
	  
	    //ViewPager加载的页面
	    
	    public void initViewPager(LayoutInflater inflater){
//	    	 //解析要显示的界面
	        view1= inflater.inflate(R.layout.activity_scenery_hot, null);
	        view2= inflater.inflate(R.layout.activity_scenery_plaza, null);
	        view3= inflater.inflate(R.layout.activity_scenery_present, null);
	    }
	    
	@Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
     //   requestWindowFeature(Window.FEATURE_NO_TITLE);//去掉标题
      //  setContentView(R.layout.activity_scenery_home);
        vp=(ViewPager) this.getView().findViewById(R.id.vp_scenery);
        context=getContext();
        //获得LayoutInflater对象
        LayoutInflater inflater=getLayoutInflater(savedInstanceState).from(context);
        //ViewPager加载页面
        initViewPager(inflater);
        //找标题控件
        textView1=(TextView) this.getView().findViewById(R.id.tv_screnery_hot);
        textView2=(TextView) this.getView().findViewById(R.id.tv_screnery_plaza);
        textView3=(TextView) this.getView().findViewById(R.id.tv_screnery_present);
        //给标题控件注册点击事件、监听
        textView1.setOnClickListener(new ScreneryHomeOnClik(0,vp));
        textView2.setOnClickListener(new ScreneryHomeOnClik(1,vp));
        textView3.setOnClickListener(new ScreneryHomeOnClik(2,vp));
        
        imageview=(ImageView) this.getView().findViewById(R.id.imageView1);
        File file = new File(Environment.getExternalStorageDirectory(),"temp.jpg");
		imageUri = Uri.fromFile(file);
		new SceneryPopWindow(imageUri);
//        //将要分叶显示的View添加到list集合中
        views=new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        
        SceneryHomeAdapt sha=new SceneryHomeAdapt(views);
        
        //menu 自定义ActionBar
//        ActionBar actionBar=getActionBar();//获得ActionBar对象
////       设置显示选项。这种变化显示选项部分。改变显示选项的有限子集,看到setDisplayOptions(int,int)。
//        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
//        actionBar.setCustomView(R.layout.action_scenery_menu);//�Զ���ActionBar����
//        actionBar.getCustomView().findViewById(R.id.action_camera).setOnClickListener(new SceneryActionBar(this));//ע�����
//        actionBar.getCustomView().findViewById(R.id.action_search).setOnClickListener(new SceneryActionBar(this));//ע�����
        
        //下划线图片处理
        imageView= (ImageView) this.getView().findViewById(R.id.iv_underline);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.p).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
      //getWindowManager().getDefaultDisplay().getMetrics(dm);
        int screenW = dm.widthPixels;// 获取分辨率宽度
        offset = (screenW / 3 - bmpW) / 2;// 计算偏移量
        Matrix matrix = new Matrix();//图片处理对象
        matrix.postTranslate(offset, 0);
        imageView.setImageMatrix(matrix);// 设置动画初始位置
        
      //下拉刷新上拉加载
        lv_hot=(ListView) view1.findViewById(R.id.lv_hot);
        
        View v=inflater.inflate(R.layout.action_autolistview_title, null);
        auto=(AutoListView)v.findViewById(R.id.lstv);
		auto.setOnRefreshListener(this);
        auto.setOnLoadListener(this);
        ListData(AutoListView.REFRESH);
        
        vp.setAdapter(sha);
        vp.setCurrentItem(0);
        vp.setOnPageChangeListener(new SceneryHomeChage(offset, bmpW, currIndex, vp, imageView));
        
    }

	String url="http://192.168.191.1:8080/android_net/androidweb";
    //从网络上获取数据
    public void ListData(final int what){
    	HttpUtils http=new HttpUtils();
    	RequestParams params = new RequestParams();
    	params.addBodyParameter("success","success");
    	//
    	http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("sw", "onfailure");
				Toast.makeText(getActivity(), "请求失败，请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				listmap=new ArrayList<Map<String,String>>();
				String result=arg0.result;
				Gson json=new Gson();
				Map<String,String> map=null;
				if(!result.equals(null)){
				List<Senery> list=json.fromJson(result,new TypeToken<List<Senery>>() {}.getType());
				for (Senery senery : list) {
					map=new HashMap<String, String>();
					String name=senery.getName();
					String imn=senery.getUrlImage();
					map.put("name",name);
					map.put("imn", imn);
					System.out.println(senery);
					listmap.add(map);
					}
				}
//				SimpleAdapter adapter=new SimpleAdapter(SceneryHomeActivity.this,listmap, R.layout.action_listview_hot, new String[]{"name","imn"},new int[]{R.id.tv_hot_list,R.id.tv_hot_list1});
				auto.setResultSize(listmap.size());
				if(adapter==null){
				adapter=new ListViewAdapter(context, listmap);
		        lv_hot.setAdapter(adapter);	
				}
//		        Message msg = handler.obtainMessage();
//				msg.what = what;
//				msg.obj = listmap;
//				handler.sendMessage(msg);
			}
    	});
    }
//    private Handler handler = new Handler() {
//		public void handleMessage(Message msg) {
//			Collection<? extends Map<String, String>> result = (Collection<? extends Map<String, String>>) msg.obj;
//			switch (msg.what) {
//			case AutoListView.REFRESH:
//				auto.onRefreshComplete();
////				listmap.clear();
//				listmap.addAll(result);
//				break;
//			case AutoListView.LOAD:
//				auto.onLoadComplete();
//				listmap.addAll(result);
//				break;
//			}
//			auto.setResultSize(result.size());
//			adapter.notifyDataSetChanged();
//		}
//	};

    
	@Override
	public void onRefresh() {
		ListData(AutoListView.REFRESH);
	}

	@Override
	public void onLoad() {
		ListData(AutoListView.LOAD);
	}
	/*
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode != RESULT_OK){
			Log.i("MainActivity", "select pic error!");
			return;
		}
		if(requestCode == SceneryPopWindow.SELECT_PIC){
			if(imageUri != null){
				InputStream is = null;
				try {
					//读取图片到io流
					is = getContentResolver().openInputStream(imageUri);
					//内存中的图片
					Bitmap bm = BitmapFactory.decodeStream(is);
					imageview.setImageBitmap(bm);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
			}
		}else if(requestCode == SceneryPopWindow.TAKE_PHOTO){
		    Intent intent = new Intent("com.android.camera.action.CROP");
		    intent.setDataAndType(imageUri, "image/*");
		    intent.putExtra("crop", "true");
		    intent.putExtra("aspectX", 1);
		    intent.putExtra("aspectY", 1);
		    intent.putExtra("outputX", 200);
		    intent.putExtra("outputY", 200);
		    intent.putExtra("scale", true);
		    intent.putExtra("return-data", true);
		    startActivityForResult(intent, SceneryPopWindow.CROP_PHOTO);//启动裁剪
		}else if(requestCode == SceneryPopWindow.CROP_PHOTO){//获取裁剪后的结果
			Bundle bundle = data.getExtras();
			if(bundle != null){
				Bitmap bm = bundle.getParcelable("data");//  bundle.putParceable("data",bm);
//				bm.compress(CompressFormat.JPEG, 100, new FileOutputStream());
				imageview.setImageBitmap(bm);
			}
		}
	}
*/
	
	
}
