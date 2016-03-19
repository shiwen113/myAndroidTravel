package com.gem.home.activity;



import com.gem.gotravel.R;
import com.gem.home.db.MyDatabaseHelper;

import android.app.Activity;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Travels_Activity extends Activity {
	private EditText teamName, allDay, startPoint, destination, sex, age, startTime, arriveTime;
	private String mteamName, mstartPoint, mdestination;
	private int mallDay, mage, msex;
	private Button btnNext;
	private ContentValues values;
	private MyDatabaseHelper dbHelper = new MyDatabaseHelper(this, "Travel.db", null, 1);
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.travel_activity);
		teamName = (EditText) findViewById(R.id.eview_teamName);
		allDay = (EditText) findViewById(R.id.eview_allDay);
		startPoint = (EditText) findViewById(R.id.eview_startPoint);
		destination = (EditText) findViewById(R.id.eview_destination);
		sex = (EditText) findViewById(R.id.eview_sex);
		age = (EditText) findViewById(R.id.eview_age);
		startTime = (EditText) findViewById(R.id.eview_startTime);
		arriveTime = (EditText) findViewById(R.id.eview_arriveTime);
		btnNext = (Button) findViewById(R.id.btn_next);

		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Log.i("db","onclick");
				// TODO Auto-generated method stub
				inData();
				SQLiteDatabase database=dbHelper.getWritableDatabase();
				values = new ContentValues();
				values.put("teamName", mteamName);
				values.put("age", mage);
				values.put("sex", msex);
				values.put("allDay", mallDay);
				database.insert("Travel", null, values);
				// Intent intent = new Intent(Trave_Activity.this,
				// Trave_Activity.class);
				// startActivity(intent);
			}

		});
	}

	private void inData() {
		// TODO Auto-generated method stub

		mteamName = teamName.getText().toString();
		mallDay = Integer.parseInt(allDay.getText().toString());
		mstartPoint = startPoint.getText().toString();
		mdestination = destination.getText().toString();
		msex = Integer.parseInt(sex.getText().toString());
		mage = Integer.parseInt(age.getText().toString());
	}
}
