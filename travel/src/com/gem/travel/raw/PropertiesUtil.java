package com.gem.travel.raw;

import java.util.Properties;

import android.content.Context;
import android.util.Log;

public class PropertiesUtil {

	public static String getPropertiesURL(Context c) {
		  String url = null;
		  Properties properties = new Properties();
		  try {
		   properties.load(c.getAssets().open("db.properties"));
		   url = properties.getProperty("url");
		   Log.i("url",url);
		  } catch (Exception e) {
		   e.printStackTrace();
		  }
		  return url;
		 }

}
