package com.gem.scenery;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.ActionBar;
import android.app.Activity;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.scenery.R;
import com.gem.scenery.R.drawable;
import com.gem.scenery.R.id;
import com.gem.scenery.R.layout;
import com.gem.scenery.R.menu;
import com.gem.scenery.action.ListViewAdapter;
import com.gem.scenery.action.SceneryActionBar;
import com.gem.scenery.action.SceneryHomeAdapt;
import com.gem.scenery.action.SceneryHomeChage;
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

public class SceneryHomeActivity extends Activity implements OnRefreshListener,OnLoadListener{
		private List<View> views;// Tab页面列表
		private ViewPager vp;//叶卡内容
	    private ImageView imageView;// 动画图片
	    private TextView textView1,textView2,textView3;
	    private int offset = 0;// 动画图片偏移量
	    private int currIndex = 0;// 当前叶卡编号
	    private int bmpW;// 动画图片宽度
	    private View view1,view2,view3;//各个叶卡
	    private ListView lv_hot;
	    private List<Map<String,String>> listmap;
	    private Senery senery;
	    private AutoListView auto;
	    private int size=1;//第几页
	    private ListViewAdapter adapter;//ListVeiw适配器
	    // 区分当前操作是刷新还是加载
	    public  int refresh ;//刷新  
	    public  int load ;//加载
	    
	    //ViewPager加载的页面
	    public void initViewPager(LayoutInflater inflater){
//	    	 //解析要显示的界面
	        view1= inflater.inflate(R.layout.activity_scenery_hot, null);
	        view2= inflater.inflate(R.layout.activity_scenery_plaza, null);
	        view3= inflater.inflate(R.layout.activity_scenery_present, null);
	    }
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scenery_home);
        vp=(ViewPager) findViewById(R.id.vp_scenery);
        //获得LayoutInflater对象
        LayoutInflater inflater=getLayoutInflater();
        //ViewPager加载页面
        initViewPager(inflater);
        //找标题控件
        textView1=(TextView) findViewById(R.id.tv_screnery_hot);
        textView2=(TextView) findViewById(R.id.tv_screnery_plaza);
        textView3=(TextView) findViewById(R.id.tv_screnery_present);
        //给标题控件注册点击事件、监听
        textView1.setOnClickListener(new ScreneryHomeOnClik(0,vp));
        textView2.setOnClickListener(new ScreneryHomeOnClik(1,vp));
        textView3.setOnClickListener(new ScreneryHomeOnClik(2,vp));
        
//        //将要分叶显示的View添加到list集合中
        views=new ArrayList<View>();
        views.add(view1);
        views.add(view2);
        views.add(view3);
        
        SceneryHomeAdapt sha=new SceneryHomeAdapt(views);
        
        //menu 自定义ActionBar
        ActionBar actionBar=getActionBar();//获得ActionBar对象
//       设置显示选项。这种变化显示选项部分。改变显示选项的有限子集,看到setDisplayOptions(int,int)。
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        actionBar.setCustomView(R.layout.action_scenery_menu);//�Զ���ActionBar����
        actionBar.getCustomView().findViewById(R.id.action_camera).setOnClickListener(new SceneryActionBar(this));//ע�����
        actionBar.getCustomView().findViewById(R.id.action_search).setOnClickListener(new SceneryActionBar(this));//ע�����
        
        //下划线图片处理
        imageView= (ImageView) findViewById(R.id.iv_underline);
        bmpW = BitmapFactory.decodeResource(getResources(), R.drawable.p).getWidth();// 获取图片宽度
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
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


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         getMenuInflater().inflate(R.menu.scenery_home, menu);
         //使用代码来动态控制MenuItem的显示状态 or XML定义MenuItem的显示状态   
//          MenuItem camera=menu.findItem(R.id.action_camera); 
//          MenuItem search=menu.findItem(R.id.action_search); 
        return true;  
    }
    
    @Override  
    public boolean onOptionsItemSelected(MenuItem item) {  
        switch (item.getItemId()) {  
        case R.id.action_camera:  
//        	((CameraPopWindow) popupWindow).showPopupWindow(item);
//        	Toast.makeText(getApplicationContext(), "点击了", 1).show();
            break;  
        case R.id.action_search:  
        	Toast.makeText(getApplicationContext(), "点击了", 1).show();
            break;   
        default:  
            break;  
        }  
        return super.onOptionsItemSelected(item);  
    }  
  
    //从网络上获取数据
    public void ListData(final int what){
    	HttpUtils http=new HttpUtils();
    	RequestParams params = new RequestParams();
    	params.addBodyParameter("size",String.valueOf(size));
    	size++;
    	//��ַ
    	String url="http://192.168.191.1:8080/android_net/androidweb";
    	http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("sw", "onfailure");
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
				adapter=new ListViewAdapter(SceneryHomeActivity.this, listmap);
		        lv_hot.setAdapter(adapter);	
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

}
