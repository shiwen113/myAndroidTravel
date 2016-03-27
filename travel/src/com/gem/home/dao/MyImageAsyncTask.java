package com.gem.home.dao;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import com.gem.scenery.R;
import com.gem.scenery.R.drawable;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.util.Log;

public class MyImageAsyncTask  extends AsyncTask<String, Void, Bitmap>{
private MyRecyclerViewHolder arg0;
private static Bitmap imgtitle;
	public MyImageAsyncTask(MyRecyclerViewHolder arg0) {
		super();
		this.arg0=arg0;
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Bitmap doInBackground(String... arg0) {
		// TODO Auto-generated method stub
		try {
			URL url = new URL(arg0[0]);
			HttpURLConnection conn;
			try {
				conn = (HttpURLConnection) url.openConnection();
				if (conn.getResponseCode() == 200) {
					InputStream is = conn.getInputStream();

					Bitmap bm = BitmapFactory.decodeStream(is);
					return bm;
				}
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		Log.i("xxy", "xxy" + result);
		
		
//Bitmap bm1=BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_launcher);
		if(result!=null){
		arg0.imageView.setSrc(result);
		}
		setImgtitle(result);
		//arg0.imageView.setImageBitmap(result);
		Log.i("boom", "arg0+"+arg0.imageView);
	}

	@Override
	protected void onPreExecute() {
		// TODO Auto-generated method stub
		super.onPreExecute();
	}

	public static Bitmap getImgtitle() {
		return imgtitle;
	}

	public void setImgtitle(Bitmap imgtitle) {
		this.imgtitle = imgtitle;
	}

}
