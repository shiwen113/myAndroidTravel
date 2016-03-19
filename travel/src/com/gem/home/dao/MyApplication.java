package com.gem.home.dao;

import android.app.Application;
import android.content.Context;
//获取全局对象
public class MyApplication extends Application{
private static Context context;
@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		context=getApplicationContext();
	}
public static Context getContext() {
	return context;
}
}
