package com.gem.mine.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.gem.home.dao.MyApplication;
import com.gem.home.until.PublishTravel;
import com.gem.mine.action.MycollectAdapter;
import com.gem.scenery.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

public class MycollectActivity extends Activity implements OnClickListener{
    private ListView listView;
    private List<PublishTravel> arr=new ArrayList<PublishTravel>();
    private MycollectAdapter adapter;
    private ImageView back;
    private MyApplication m;
	//收藏页面
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_mycollect);
		m=(MyApplication) getApplicationContext();
		listView=(ListView) findViewById(R.id.lv_lvxingdui_shoucang);
		back=(ImageView) findViewById(R.id.ibtn_shoucang);
//		adapter= new MycollectAdapter(this, arr);
//		listView.setAdapter(adapter);
		back.setOnClickListener(this);
//		if(m.getLd()!=null){
		initialize();
//		}else{
//			Toast.makeText(getApplication(), "请先登录，查看更多", Toast.LENGTH_LONG).show();
//		}
	}

	public void initialize(){
//		final List<PublishTravel> list=new ArrayList<PublishTravel>();
		String url="http://10.201.1.12:8080/travel/Wode_shoucang";
		HttpUtils utils=new HttpUtils();
		RequestParams params=new RequestParams();
		params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
		utils.send(HttpMethod.POST, url,params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplication(), "连接失败,请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result=arg0.result;
				if(result!=null&&!result.equals("")){
					Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
					Type type=new TypeToken<List<PublishTravel>>(){}.getType();
                    arr=gson.fromJson(result,type);
                    adapter= new MycollectAdapter(MycollectActivity.this, arr);
            		listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();
				}
				
			}
		});
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibtn_shoucang:
			finish();
			break;

		default:
			break;
		}
	}
}
