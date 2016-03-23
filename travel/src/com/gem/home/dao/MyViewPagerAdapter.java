package com.gem.home.dao;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

public class MyViewPagerAdapter extends PagerAdapter{
	private List<View> mViewList;


	public MyViewPagerAdapter(List<View> mViewList) {
		super();
		this.mViewList=mViewList;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		
		
		return arg0 == arg1;

	}

	@Override
	public int getCount() {
	
		return mViewList.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {

		((ViewPager) container).removeView(mViewList.get(position));
	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		
		((ViewPager) container).addView(mViewList.get(position));
		return mViewList.get(position);
	}

	@Override
	public int getItemPosition(Object object) {
		
		return super.getItemPosition(object);
	}
}
