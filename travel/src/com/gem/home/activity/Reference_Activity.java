package com.gem.home.activity;



import android.app.Activity;
import android.os.Bundle;
import android.view.Window;

public class Reference_Activity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub.
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(com.gem.gotravel.R.layout.reference);
	}

}
