package com.gem.home.action;

import java.lang.reflect.Type;

import java.util.List;

import com.gem.home.activity.Home_home;
import com.gem.home.dao.MyApplication;
import com.gem.home.dao.MyRecyclerViewAdapter;
import com.gem.home.until.PublishTravel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.hardware.Camera.ShutterCallback;
import android.util.Log;
import android.widget.Toast;

public class Renovate {
	private String url;
	private String key;
	private String LX = null;
	private static final int ITEM = 5;
	private int page = 1;
	private static final String URL_HOT = "http://10.201.1.12:8080/travel/Home_home_remeng";// 热门
	private static final String URL_NEWS = "http://10.201.1.12:8080/travel/Home_home_zuixin";// 最新
	private static final String URL_AIM = "http://10.201.1.12:8080/travel/Home_home_mdd";// 目的地
	private static final String URL_SHUT = "http://10.201.1.12:8080/travel/Home_home_js";// 结束时间
	// int a=1;
	// int b=1;
	// int c=1;
	// int d=1;
	// 初始化数据网络端
	// 初始化数据网络端

	public void initData(final MyRecyclerViewAdapter adapter,final List<PublishTravel> arr ,int arg0,final int page) {
	Log.i("xxy", "renovate+initdata");
	switch (arg0) {
	case 0:
		 url = URL_HOT;
		 LX="qc";
		break;
	case 1:
		url=URL_NEWS;
		LX="qc";
		break;
	case 2:
		url=URL_AIM;
		LX="mdd";
		key="苏州";
	break;
	case 3:
		url=URL_SHUT;
		LX="qc";
		key="3";
		break;
	default:
		break;
	}
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				HttpUtils httpUtils = new HttpUtils();
				
				RequestParams requestParams = new RequestParams();
				requestParams.addBodyParameter("ITEM", String.valueOf(ITEM));
				requestParams.addBodyParameter("page",String.valueOf(page));
				requestParams.addBodyParameter(LX,key);
				httpUtils.send(HttpMethod.POST, url, requestParams, new RequestCallBack<String>() {
					@Override
					public void onFailure(HttpException arg0, String arg1) {
						Toast.makeText(MyApplication.getContext(), "访问数据失败，请检查网络", Toast.LENGTH_SHORT).show();
						Log.i("download", "failure" + arg1);
					}
				@Override
					public void onSuccess(ResponseInfo<String> arg0) {
					Log.i("ResponseInfo", "arg0+"+arg0);
						Log.i("download", "success");
						String result = arg0.result;
						Log.i("bd", "result+" + result);
						Gson gson = new Gson();
						Type type = new TypeToken<List<PublishTravel>>() {
						}.getType();
						List<PublishTravel> list =gson.fromJson(result, type);
						if(list!=null){
							if (arr.size() != 0) {
								arr.clear();
							}
							Home_home.setFlag(true);
							arr.addAll(list);
							Log.i("xxy", "arr+"+arr);
							adapter.notifyDataSetChanged();
						}else{
							Home_home.setFlag(false);
						}
				}
				});
				
			}}).start();
				
	}

	public void loadData(final MyRecyclerViewAdapter adapter,
			final List<PublishTravel> arr, int arg0, final int page) {
		Log.i("xxy", "renovate+initdata");
		// final int j = 0;
		switch (arg0) {
		case 0:
			url = URL_HOT;
			LX = "qc";
			// j=a++;
			break;
		case 1:
			url = URL_NEWS;
			LX = "qc";
			// j=b++;
			break;
		case 2:
			url = URL_AIM;
			LX = "mdd";
			key = "苏州";
			// j=c++;
			break;
		case 3:
			url = URL_SHUT;
			LX = "qc";
			key = "3";
			// j=d++;
			break;
		default:
			break;
		}
		new Thread(new Runnable() {

			@Override
			public void run() {
				HttpUtils httpUtils = new HttpUtils();

				RequestParams requestParams = new RequestParams();
				requestParams.addBodyParameter("ITEM", String.valueOf(ITEM));
				requestParams.addBodyParameter("page", String.valueOf(page));
				requestParams.addBodyParameter(LX, key);
				httpUtils.send(HttpMethod.POST, url, requestParams,
						new RequestCallBack<String>() {
							@Override
							public void onFailure(HttpException arg0,
									String arg1) {
								Toast.makeText(MyApplication.getContext(),
										"访问数据失败，请检查网络", Toast.LENGTH_SHORT)
										.show();
								Log.i("download", "failure" + arg1);
							}

							@Override
							public void onSuccess(ResponseInfo<String> arg0) {
								Log.i("ResponseInfo", "arg0+" + arg0);
								Log.i("download", "success");
								String result = arg0.result;
								Log.i("bd", "result+" + result);
								Gson gson = new Gson();
								Type type = new TypeToken<List<PublishTravel>>() {
								}.getType();
								// if (arr.size() != 0) {
								// arr.clear();
								// }
								List<PublishTravel> list = gson.fromJson(
										result, type);
								if (list != null) {
									arr.addAll(list);
									Log.i("xxy", "arr+" + arr);
									adapter.notifyDataSetChanged();
								} else {
									Toast.makeText(MyApplication.getContext(),
											"没有更多数据", Toast.LENGTH_SHORT)
											.show();
								}
							}
						});

			}
		}).start();

	}
}
