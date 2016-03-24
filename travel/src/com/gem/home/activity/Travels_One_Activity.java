package com.gem.home.activity;

import com.gem.home.db.MyDatabaseHelper;
import com.gem.scenery.R;
import com.lidroid.xutils.view.annotation.ViewInject;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class Travels_One_Activity extends Activity {
	private EditText city, teamNumber, teamZt, teamName, allDay, startPoint, destination, sex, age, startTime,
			arriveTime;
	private String mteamName, mstartPoint, mdestination,mteamZt,msex,mcity,marriveTime,mstartTime,mallDay, mage,mteamNumber;
	
	private Button btnNext;
	private ContentValues values;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.travel_one_activity);
		//初始化view
		inView();

		btnNext.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				
				//获得输入框数据
				 inData();
				
		
				Intent intent = new Intent(Travels_One_Activity.this, Travels_Two_Activity.class);
				intent.putExtra("teamName", mteamName);
//				intent.putExtra("teamZt", mteamZt);
				intent.putExtra("allDay", mallDay);
				intent.putExtra("startPoint", mstartPoint);
				intent.putExtra("destination", mdestination);
				intent.putExtra("sex", msex);
				intent.putExtra("teamNumber", mteamNumber);
				intent.putExtra("age", mage);
				intent.putExtra("city", mcity);
				intent.putExtra("startPoint", mstartTime);
				intent.putExtra("arriveTime", marriveTime);
				startActivity(intent);
			}

		});

	}

	private void inData() {
		// TODO Auto-generated method stub

		mteamName = teamName.getText().toString();
//		mteamZt=teamZt.getText().toString();
		mallDay = allDay.getText().toString();
		mstartPoint = startPoint.getText().toString();
		mdestination = destination.getText().toString();
		msex = sex.getText().toString();
		mteamNumber=teamNumber.getText().toString();
		mage = age.getText().toString();
		mcity=city.getText().toString();
		mstartTime=startTime.getText().toString();
		marriveTime=arriveTime.getText().toString();
		
	}
	private void inView(){
		teamName = (EditText) findViewById(R.id.edit_teamName);
//		teamZt = (EditText) findViewById(R.id.eidt_teamzt);
		allDay = (EditText) findViewById(R.id.edit_allDay);
		startPoint = (EditText) findViewById(R.id.edit_startPoint);
		destination = (EditText) findViewById(R.id.edit_destination);
		sex = (EditText) findViewById(R.id.edit_sex);
		teamNumber = (EditText) findViewById(R.id.edit_teamnumber);
		age = (EditText) findViewById(R.id.edit_age);
		city = (EditText) findViewById(R.id.edit_city);
		startTime = (EditText) findViewById(R.id.edit_starttime);
		arriveTime = (EditText) findViewById(R.id.edit_arriveTime);
		btnNext = (Button) findViewById(R.id.btn_next);
	}
}
