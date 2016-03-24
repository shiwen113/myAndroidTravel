package com.gem.mine.activity;



import com.gem.scenery.R;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.View.OnClickListener;


public class MineTravelTeamDataActivity extends Activity implements OnClickListener {

	//旅行队资料
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
//        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
//		 getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);  
//	        //透明导航栏  
//	        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);  
//	  
		//setContentView(R.layout.activity_mypageforgetpassword);
        setContentView(R.layout.activity_travel_team_data);
    }

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
         switch(v.getId()){
		
         //点击返回按钮返回
		case R.id.bt_MineTravelTeamDataActivity_Retnrn:
//			Intent intent =new Intent(this,MyloginActivity.class);
//			startActivity(intent);
			finish();
			break;
	}


	}
}
