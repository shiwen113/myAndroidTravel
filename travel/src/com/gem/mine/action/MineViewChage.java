package com.gem.mine.action;

import com.gem.scenery.R;

import android.app.Activity;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.widget.ImageView;

public class MineViewChage implements OnPageChangeListener{

	private Activity activity;
	private ImageView myIv;
	private ImageView joinIv;
	

	public MineViewChage(Activity activity, ImageView myIv, ImageView joinIv) {
		super();
		this.activity = activity;
		this.myIv = myIv;
		this.joinIv = joinIv;
	}

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onPageSelected(int arg0) {
		switch (arg0) {
		case 0:
			backgroundMy();
			
			break;
		case 1:
			backgroundJoin();
			break;
		default:
			break;
		}
		
	}
	/**
	 * 我加入的图片设置
	 */
	public void backgroundMy(){
		myIv.setBackgroundResource(R.drawable.p);
		joinIv.setBackgroundResource(R.drawable.black);
	}
	/**
	 * 我加入的图片设置
	 */
	public void backgroundJoin(){
		myIv.setBackgroundResource(R.drawable.black);
		joinIv.setBackgroundResource(R.drawable.p);
	}
}
