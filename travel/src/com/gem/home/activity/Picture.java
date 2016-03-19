package com.gem.home.activity;




import com.gem.gotravel.R;

import android.R.integer;
import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Picture extends Fragment {
	private NetworkChangeReciver networkChangeReciver;
	private IntentFilter intentFilter;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.picture, container, false);
		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {

		super.onActivityCreated(savedInstanceState);
		IntentFilter intentFilter=new IntentFilter();
		intentFilter.addAction("android.net.conn.CONNECTIVITY_CHANGE"); 
		networkChangeReciver = new NetworkChangeReciver();
		
	}

	
		
	}
class NetworkChangeReciver extends BroadcastReceiver{

	@Override
	public void onReceive(Context context, Intent intent) {
	//	ConnectivityManager connectivityManager=getSystemService(Context.CONNECTIVITY_SERVICE);	}}
	
	}
}
