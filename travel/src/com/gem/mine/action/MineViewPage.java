package com.gem.mine.action;

import java.util.List;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MineViewPage extends PagerAdapter {

	private List<View> views;
	public MineViewPage(List<View> views) {
		this.views = views;
	}

	@Override
	public int getCount() {
		return views.size();
	}

	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		//移除view
		container.removeView(views.get(position));
	}

//	@Override
//	public CharSequence getPageTitle(int position) {
//		return tittle.get(position);//直接用适配器来完成标题的显示
//	}

	@Override
	public Object instantiateItem(ViewGroup container, int position) {//叶卡初始化
		container.addView(views.get(position),0);//添加叶卡
		return position;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		
		return arg0==views.get((int)Integer.parseInt(arg1.toString()));
	}
	

}
