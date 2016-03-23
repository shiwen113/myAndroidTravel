package com.gem.mine.activity;



import com.gem.scenery.R;

import android.app.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class TroopsActivity extends Activity implements OnClickListener {
	ImageView BT_TroopsActivity_RETURN;
	//�ҵ����ж� 
	TextView TV_TroopsActivity_information;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_troops);
		//���ؼ����¼�
		BT_TroopsActivity_RETURN=(ImageView) findViewById(R.id.BT_TroopsActivity_RETURN);
		BT_TroopsActivity_RETURN.setOnClickListener(this);
		//���ϵļ����¼�
		TV_TroopsActivity_information=(TextView) findViewById(R.id.TV_TroopsActivity_information);
		TV_TroopsActivity_information.setOnClickListener(this);
	}
	public void click(View v){
		switch(v.getId()){
		
		//������ذ�ť��ҳ����ת��������ҳ
		case R.id.BT_TroopsActivity_RETURN:
			Intent intent =new Intent(this,MyloginActivity.class);
			startActivity(intent);
			break;
			//���������ת�����Ͻ���
		case R.id.TV_TroopsActivity_information:
			//PersonalinformationActivity
			Intent intent1 =new Intent(this,PersonalinformationActivity.class);
			startActivity(intent1);
			
			break;
		
		}
	
}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
switch(v.getId()){
		
		//������ذ�ť��ҳ����ת��������ҳ
		case R.id.BT_TroopsActivity_RETURN:
			Intent intent =new Intent(this,MyloginActivity.class);
			startActivity(intent);
			break;
			//���������ת�����Ͻ���
		case R.id.TV_TroopsActivity_information:
			//PersonalinformationActivity
			Intent intent1 =new Intent(this,PersonalinformationActivity.class);
			startActivity(intent1);
			
			break;
		
		}
	}}
