package com.gem.mine.activity;



import com.gem.scenery.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class PersonalinformationActivity extends Activity {

	ImageView im_PersonalinformationActivity_return;
	//������Ϣ����������дҳ��
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_personalinformation);
	}

	public void click(View v){
switch(v.getId()){
		
		//������ذ�ť��ҳ����ת��������ҳ
		case R.id.im_PersonalinformationActivity_return:
			finish();
			break;
		
	}
	}
}
