package com.gem.scenery.action;

import com.gem.scenery.R;
import android.app.Activity;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.view.View;
import android.widget.PopupWindow;
import android.widget.TextView;
public class SceneryTile{

	 private PopupWindow popupWindow; 
	 private Activity activity;
	 private View v;
	 public SceneryTile(Activity activity,View v) {
		 this.activity=activity;
		 this.v=v;
	}
	 /**
	  * 照相机弹框
	  */
	 public void onCamera(){
		//点击相机图片弹出PopupWindow
//		 局注入器，注入布局给View对象
        View myView = activity.getLayoutInflater().inflate(R.layout.action_camera, null);
        //通过view 和宽·高，构造PopopWindow
        popupWindow = new PopupWindow(myView, 300, 400, true);
         
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000)
                //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
               );
        popupWindow.setOutsideTouchable(true);
        //设置焦点为可点击
        popupWindow.setFocusable(true);//可以试试设为false的结果
        //将window视图显示在myButton下面
        popupWindow.showAsDropDown(v);
//		System.out.println("点击了");
        TextView tv_camera=(TextView) myView.findViewById(R.id.tv_camera);
        TextView tv_photos=(TextView) myView.findViewById(R.id.tv_photos);
        tv_camera.setOnClickListener(new SceneryPopWindow(activity));
        tv_photos.setOnClickListener(new SceneryPopWindow(activity));
	 }
	/**
	 * 搜索方法
	 */
	 public void sharePictureSercher(){
		 
	 }

}

