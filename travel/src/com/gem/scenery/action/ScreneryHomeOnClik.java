package com.gem.scenery.action;

import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;

public class ScreneryHomeOnClik implements OnClickListener {
	private ViewPager vp;//叶卡内容
	private int index;
	
    public ScreneryHomeOnClik(int i,ViewPager vp){
        index=i;
        this.vp=vp;
    }
    @Override
    public void onClick(View v) {
		vp.setCurrentItem(index);           
    }
}
