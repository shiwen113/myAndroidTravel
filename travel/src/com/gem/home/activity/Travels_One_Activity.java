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
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Travels_One_Activity extends Activity implements OnCheckedChangeListener{
	private EditText teamNumber, teamZt, teamName, allDay, startPoint, destination, sex, age, startTime,
			arriveTime;
	private String mteamName, mstartPoint, mdestination,mteamZt,msex,mcity,marriveTime,mstartTime,mallDay, mage,mteamNumber;
	
	private Button btnNext;
	private ContentValues values;
	private RadioGroup radioSex;
	private RadioGroup city;
	private int sexi=2;
	private int cityi=2;
	private RadioButton rb_one;
	

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
				
		
				Intent intent = new Intent(Travels_One_Activity.this, MainActivity.class);
				intent.putExtra("teamName", mteamName);
//				intent.putExtra("teamZt", mteamZt);
				intent.putExtra("allDay", mallDay);
				intent.putExtra("startPoint", mstartPoint);
				intent.putExtra("destination", mdestination);
				intent.putExtra("sex",msex );
				intent.putExtra("teamNumber", mteamNumber);
//				intent.putExtra("age", mage);
				intent.putExtra("city", mcity);
				intent.putExtra("startTime", mstartTime);
				intent.putExtra("arriveTime", marriveTime);
				if(!mteamName.equals("")&&!destination.equals("")&&!mteamNumber.equals("")){
				startActivity(intent);
				}else{
					Toast.makeText(getApplication(), "请检查又没填写用户名，目的地，人数", Toast.LENGTH_LONG).show();
				}
			}

		});

	}
	public void onConfigurationChanged( android.content.res.Configuration newConfig) {
		// 其实这里什么都不要做
		 super.onConfigurationChanged(newConfig); };

	private void inData() {
		// TODO Auto-generated method stub

		mteamName = teamName.getText().toString();
//		mteamZt=teamZt.getText().toString();
		mallDay = allDay.getText().toString();
		mstartPoint = startPoint.getText().toString();
		mdestination = destination.getText().toString();
		msex = String.valueOf(sexi);
		mteamNumber=teamNumber.getText().toString();
//		mage = age.getText().toString();
		mcity=String.valueOf(cityi);
		mstartTime=startTime.getText().toString();
		marriveTime=arriveTime.getText().toString();
		
	}
	private void inView(){
		teamName = (EditText) findViewById(R.id.edit_teamName);
//		teamZt = (EditText) findViewById(R.id.eidt_teamzt);
		allDay = (EditText) findViewById(R.id.edit_allDay);
		startPoint = (EditText) findViewById(R.id.edit_startPoint);
		destination = (EditText) findViewById(R.id.edit_destination);
//		sex = (EditText) findViewById(R.id.edit_sex);
		teamNumber = (EditText) findViewById(R.id.edit_teamnumber);
//		age = (EditText) findViewById(R.id.edit_age);
		city = (RadioGroup) findViewById(R.id.edit_city);
		startTime = (EditText) findViewById(R.id.edit_starttime);
		arriveTime = (EditText) findViewById(R.id.edit_arriveTime);
		btnNext = (Button) findViewById(R.id.btn_next);
		radioSex=(RadioGroup) findViewById(R.id.rg_radio_sex_send);
		rb_one=(RadioButton) findViewById(R.id.rb_unlimit_one);
		rb_one.setChecked(true);
		radioSex.setOnCheckedChangeListener(this);
		city.setOnCheckedChangeListener(new OnCheckedChangedCity());
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int cheketId) {
		// TODO Auto-generated method stub
		// 根据Id获取选中项的Id
					RadioButton rb = (RadioButton) findViewById(group
							.getCheckedRadioButtonId());
					String text = rb.getText().toString();
					if (text.equals("男")) {
						sexi = 1;
					} else if (text.equals("女")) {
						sexi = 0;
					} else if (text.equals("不限")) {
						sexi = 2;
					}
					Toast.makeText(getApplication(), "text" + text, Toast.LENGTH_LONG)
							.show();
	}
	
	public class OnCheckedChangedCity implements OnCheckedChangeListener{

		@Override
		public void onCheckedChanged(RadioGroup arg0, int arg1) {
			// TODO Auto-generated method stub
			// 根据Id获取选中项的Id
			RadioButton rb = (RadioButton) findViewById(arg0
					.getCheckedRadioButtonId());
			String text = rb.getText().toString();
			if (text.equals("同城")) {
				cityi = 0;
			} else if (text.equals("非同城")) {
				cityi = 1;
			} else if (text.equals("不限")) {
				cityi = 2;
			}
			Toast.makeText(getApplication(), "text" + text, Toast.LENGTH_LONG)
					.show();
		}

	
		
	}
}
