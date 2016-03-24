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

public class TeammembersActivity extends Activity implements OnClickListener{

	Button BT_TeammembersActivity_return;
	//���жӳ�Աҳ��  .....
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_teammembers);
		BT_TeammembersActivity_return=(Button) findViewById(R.id.BT_TeammembersActivity_return);
		
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		
		//���һ�����ص����ж�
		case R.id.BT_TeammembersActivity_return:
			Intent intent1=new Intent(this,TroopsActivity.class);
			startActivity(intent1);
			break;
		
		}
		
	}




}
