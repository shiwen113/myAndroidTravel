package com.gem.mine.activity;



import com.gem.scenery.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class SetupActivity extends Activity implements OnClickListener {

	ImageView BT__SetupActivity_backtrack;
	//��������ҳ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_setup);
		BT__SetupActivity_backtrack=(ImageView) findViewById(R.id.BT__SetupActivity_backtrack);
		BT__SetupActivity_backtrack.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		//������ذ�ť
		case R.id.BT__SetupActivity_backtrack:	
			Intent intent1 =new Intent(this,MyloginActivity.class);
			startActivity(intent1);
			break;
			//�����ҳ����ת������֪ͨҳ��
		case R.id.TV_SetupActivity_push_Notification:
			Intent intent =new Intent(this,InformActivity.class);
			startActivity(intent);
			break;
		}
		
	}



}
