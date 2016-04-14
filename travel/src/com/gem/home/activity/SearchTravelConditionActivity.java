package com.gem.home.activity;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import com.gem.home.dao.ListViewAdaptSearchData;
import com.gem.home.until.PublishTravel;
import com.gem.scenery.R;
import com.gem.scenery.entity.PersonalData;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SearchTravelConditionActivity extends Activity implements OnClickListener{
	private ListView lv;//
	private ImageButton imgBack;//返回
	private List<PublishTravel> list;//查询结果
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub.

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_serch_travel_condition);
		lv=(ListView) findViewById(R.id.lv_serch_travel_result);
		imgBack=(ImageButton) findViewById(R.id.ib_travel_home_back1);
		imgBack.setOnClickListener(this);
		
		//获取查询的值
		Intent intent =getIntent();
		String reuslt=intent.getStringExtra("result");
		if(reuslt!=null&&!reuslt.equals("")){
		Gson gson =new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		Type type=new TypeToken<List<PublishTravel>>(){}.getType();
		list =gson.fromJson(reuslt, type);
		ListViewAdaptSearchData adapter=new ListViewAdaptSearchData(list,this);
		lv.setAdapter(adapter);
		}else {
			list=new ArrayList<PublishTravel>();
		}
		lv.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View v, int arg2,
					long arg3) {
				Toast.makeText(SearchTravelConditionActivity.this, "item", 1).show();
				sendUserPicture(list.get(arg2),arg2);
				
			}
			
		});
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		this.finish();
	}
	/**
	 * 请求网络访问多级评论
	 */
	String url="http://10.201.1.12:8080/travel/TravelComment";
	public void sendContent(final PublishTravel pt,final int position,final PersonalData pd){
		HttpUtils http=new HttpUtils();
		RequestParams params=new RequestParams();
		params.addBodyParameter("td",String.valueOf(pt.getTd()));
//		params.addBodyParameter("ld",String.valueOf(maApplication.getLd().getLd()));
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(SearchTravelConditionActivity.this, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
				Intent intent = new Intent(SearchTravelConditionActivity.this, Item_Activity.class);
				 intent.putExtra("flag", false);
				 
				 Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
				 intent.putExtra("pt",gson.toJson(pt));//旅行队
				 startActivity(intent);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result=arg0.result;
				if(result!=null){
					Intent intent = new Intent(SearchTravelConditionActivity.this, Item_Activity.class);
					Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
					intent.putExtra("flag", true);
					intent.putExtra("pt",gson.toJson(pt));//旅行队
					intent.putExtra("content", result);//旅行队评论
					intent.putExtra("pd",pd);
					startActivity(intent);
				}
			}    
		});
	}
	String urlU="http://10.201.1.12:8080/travel/Home_home_yhtx";
	/**
	 * 获取用户图片
	 */
	public void sendUserPicture(final PublishTravel pt,final int position){
		RequestParams params = new RequestParams();
		HttpUtils http=new HttpUtils();
	 	params.addBodyParameter("ld",String.valueOf(pt.getLd().getLd()));
		http.send(HttpMethod.POST, urlU, params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(SearchTravelConditionActivity.this, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result =arg0.result;
				if(!result.equals(null)&&result!=null){
					Gson gson =new Gson();
					Type type =new TypeToken<PersonalData>(){}.getType();
					PersonalData pd=gson.fromJson(result, type);
					sendContent(pt,position,pd);
				}
			}
		});
	}
}
