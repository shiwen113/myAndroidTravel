package com.gem.home.activity;

import com.gem.home.db.MyDatabaseHelper;
import com.gem.scenery.R;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class Travels_Two_Activity extends Activity {
	private Button btn_ok;
	private MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "Travel.db", null, 1);
	private EditText view, introduce;
	private String mteamName, mstartPoint, mdestination, msex, mcity, marriveTime, mstartTime, mallDay, mage,
			mteamNumber;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.travel_two_activity);
		Intent intentDB = getIntent();
		intentDB(intentDB);
		btn_ok = (Button) findViewById(R.id.btn_ok);
		view = (EditText) findViewById(R.id.edit_view);
		introduce = (EditText) findViewById(R.id.edit_introduce);
		btn_ok.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				

				String sview = view.getText().toString();
				String sintroduce = introduce.getText().toString();
				ContentValues values = new ContentValues();
				values.put("teamName", mteamName);
//				values.put("teamZt", mteamZt);
				values.put("allDay",Integer.parseInt(mallDay) );
				values.put("startPoint", mstartPoint);
				values.put("destination", mdestination);
				values.put("sex", msex);
				values.put("teamNumber", Integer.parseInt(mteamNumber));
				values.put("age",Integer.parseInt(mage) );
				values.put("city", mcity);
				values.put("startTime", mstartTime);
				values.put("arriveTime", marriveTime);
				values.put("introduce", sintroduce);
				values.put("view", sview);
				SQLiteDatabase db = dbHelper.getWritableDatabase();
				db.insert("Travel", null, values);
				Intent intent = new Intent(Travels_Two_Activity.this, Fragment_Activity.class);
				startActivity(intent);
			}

		});
	}

	private void intentDB(Intent intent) {
		mteamName = intent.getStringExtra("teamName");
//		mteamZt = intent.getStringExtra("teamZt");
		mallDay = intent.getStringExtra("allDay");
		mstartPoint = intent.getStringExtra("startPoint");
		mdestination = intent.getStringExtra("destination");
		msex = intent.getStringExtra("sex");
		mteamNumber = intent.getStringExtra("teamNumber");
		mage = intent.getStringExtra("age");
		mcity = intent.getStringExtra("city");
		mstartPoint = intent.getStringExtra("startTime");
		marriveTime = intent.getStringExtra("arriveTime");
	}
}
