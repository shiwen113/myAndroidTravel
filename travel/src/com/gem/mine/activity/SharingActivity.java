package com.gem.mine.activity;



import com.gem.scenery.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class SharingActivity extends Activity {

	ImageView im_SharingActivity_return;
	//�ҵķ���ҳ�����
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sharing);
	}
	public void click(View v){
		switch(v.getId()){
		
		//������ذ�ť��ҳ����ת��������ҳ
		case R.id.im_SharingActivity_return:
			Intent intent =new Intent(this,MyloginActivity.class);
			startActivity(intent);
			break;
		
		}
		
	}
	
}
