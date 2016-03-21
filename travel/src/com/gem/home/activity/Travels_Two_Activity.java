package com.gem.home.activity;



import com.gem.scenery.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class Travels_Two_Activity extends Activity {
	private Button btn_ok;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("xxy", "two_erro");
		setContentView(R.layout.travel_two_activity);
		btn_ok = (Button) findViewById(R.id.btn_ok);
	

		btn_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("db", "onclick");
				// TODO Auto-generated method stub
				//inData();
			//	SQLiteDatabase database = dbHelper.getWritableDatabase();
			//	values = new ContentValues();
			//	values.put("teamName", mteamName);
			//	values.put("age", mage);
			//	values.put("sex", msex);
			//	values.put("allDay", mallDay);
			//	database.insert("Travel", null, values);
				Intent intent = new Intent(Travels_Two_Activity.this, Fragment_Activity.class);
				startActivity(intent);
			}

		});
	}

}
