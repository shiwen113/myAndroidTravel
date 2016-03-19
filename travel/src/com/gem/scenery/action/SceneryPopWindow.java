package com.gem.scenery.action;

import com.gem.scenery.R;

import android.view.View;
import android.view.View.OnClickListener;

public class SceneryPopWindow implements OnClickListener {

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_camera:
			System.out.println("相机点击了");
			break;

		case R.id.tv_photos:

			break;
		}
	}

}
