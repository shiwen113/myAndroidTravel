package com.gem.home.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

import com.gem.scenery.R;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Reference_Activity extends Activity implements OnClickListener {
	private EditText editPoint;// 目的地
	private EditText editGoOutDate;// 出发时间
	private EditText editArrive;// 到达时间
	private EditText editAllDate;// 全程天数
	// private RadioButton radioMen;//男
	// private RadioButton radioWommen;//女
	private RadioButton radioUnLinit;// 不限
	private ImageButton imageBack;// 返回
	private Button btnserch;// 搜索
	private RadioGroup radio;
	private int sexi=2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub.

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.reference);
		intiView();
	}

	public void intiView() {
		editPoint = (EditText) findViewById(R.id.et_edit_point);
		editGoOutDate = (EditText) findViewById(R.id.et_edit_goout);
		editArrive = (EditText) findViewById(R.id.et_edit_arrive);
		editAllDate = (EditText) findViewById(R.id.et_edit_alldate);
		// radioMen=(RadioButton) findViewById(R.id.rb_radio_sex_man);
		// radioWommen=(RadioButton) findViewById(R.id.rb_radio_sex_wommen);
		radioUnLinit = (RadioButton) findViewById(R.id.rb_radio_sex_unlimit);
		imageBack = (ImageButton) findViewById(R.id.ib_travel_home_back);
		btnserch = (Button) findViewById(R.id.bt_button_submit);
		radio = (RadioGroup) findViewById(R.id.rg_radio);
		radioUnLinit.setChecked(true);
		radio.setOnCheckedChangeListener(new OnRadioButton());
		imageBack.setOnClickListener(this);
		btnserch.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ib_travel_home_back:
			Toast.makeText(getApplication(), "返回", Toast.LENGTH_LONG).show();
			this.finish();
			break;
		case R.id.bt_button_submit:
			Toast.makeText(getApplication(), "提交", Toast.LENGTH_LONG).show();
			buttonSubmit();
			break;
		default:
			break;
		}

	}

	/**
	 * 搜索数据
	 */
	public void buttonSubmit() {
		String goOutDate=editGoOutDate.getText().toString();
		String arriveDate=editArrive.getText().toString();
		if(goOutDate.equals("请点击右边的图标")){
			goOutDate=null;
		}
		if(arriveDate.equals("请点击右边的图标")){
			arriveDate=null;
		}
		HttpUtils http = new HttpUtils();
		RequestParams params = new RequestParams();
		params.addBodyParameter("editPoint", editPoint.getText().toString());
		params.addBodyParameter("editGoOutDate",goOutDate);
		params.addBodyParameter("editArrive", arriveDate);
		params.addBodyParameter("editAllDate", editAllDate.getText().toString());
		params.addBodyParameter("sexText", String.valueOf(sexi));
		String url = "http://10.201.1.12:8080/travel/Home_home_dtj";
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getBaseContext(), "搜索失败，请检查您的网络",
						Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				if (arg0 != null) {
					String result = arg0.result;
					// Gson gson = new Gson();
					// Type type=new
					// TypeToken<List<PublishTravel>>(){}.getType();
					// List<PublishTravel> listPub=gson.fromJson(result, type);
					Log.i("result", "result:" + result);
					 Intent intent=new Intent(Reference_Activity.this,SearchTravelConditionActivity.class);
					 intent.putExtra("result", result);
					 Reference_Activity.this.startActivity(intent);
				}
			}
		});
	}

	public class OnRadioButton implements OnCheckedChangeListener {

		@Override
		public void onCheckedChanged(RadioGroup group, int cheketId) {
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

	}
}
