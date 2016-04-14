package com.gem.mine.activity;



import java.lang.reflect.Type;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.gem.home.dao.MyApplication;
import com.gem.mine.action.SharePictureBaseAdapt;
import com.gem.scenery.R;
import com.gem.scenery.action.ListViewAdapter;
import com.gem.scenery.entity.PopularScene;
import com.gem.scenery.entity.SharePicture;
import com.gem.scenery.utils.SPUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SharingActivity extends Activity implements OnClickListener{

	private ImageButton back;
	private ListView lv;
	private List<PopularScene> list;
	private SharePictureBaseAdapt adapter ;
	private MyApplication m;
	private PopularScene auto;
	private boolean flag=false;
	private int comment;
	//我的分享页面设计
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
//	requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
//	        //透明导航栏  
//	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
//	  
		setContentView(R.layout.activity_sharing);
		m=(MyApplication) getApplicationContext();
		back=(ImageButton) findViewById(R.id.ib_back);
		lv=(ListView) findViewById(R.id.lv_mine_listview);
		back.setOnClickListener(this);
		/*Intent intent=getIntent();
		String s=intent.getStringExtra("result");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		Type type = new TypeToken<List<SharePicture>>(){}.getType();
		list=gson.fromJson(s,type);*/
//		if(list!=null&&!list.equals("")){
//		adapter =new SharePictureBaseAdapt(list,this);
//		lv.setAdapter(adapter);
//		}
		if(m.getLd()!=null){
			ListData();
		}else{
			Toast.makeText(getApplication(), "请登录，查看更多", Toast.LENGTH_LONG).show();
		}
	}
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		//点击返回按钮，页面跳转至个人主页
		case R.id.ib_back:
			finish();
			break;
		}	
	}

	
	@Override
	protected void onRestart() {
		// TODO Auto-generated method stub
		super.onRestart();
		if(flag){
			Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
			String s=(String) SPUtils.get(getApplicationContext(), "ps","1");
			PopularScene ps=gson.fromJson(s, PopularScene.class);
			int comment=Integer.parseInt((String)SPUtils.get(getApplicationContext(), "comment", "0"));
			list.set(comment, ps);
			adapter.notifyDataSetChanged();
		}
	}


	String url="http://10.201.1.12:8080/travel/Wodefenxiang";
    //从网络上获取数据
    public void ListData(){
    	HttpUtils http=new HttpUtils();
    	RequestParams params = new RequestParams();
    	params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
    	http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Log.i("sw", "onfailure");
				Toast.makeText(getApplication(), "请求失败，请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result=arg0.result;
				Type type=new TypeToken<List<PopularScene>>(){}.getType();
				Gson json=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
				if(!result.equals(null)){
					list=json.fromJson(result, type);
//					auto.setResultSize(list.size());   
					flag=true;
					if(adapter==null){
						adapter=new SharePictureBaseAdapt(list,SharingActivity.this);
						lv.setAdapter(adapter);	
					}
					adapter.notifyDataSetChanged();
				}
			}
    	});
    }
	
}
