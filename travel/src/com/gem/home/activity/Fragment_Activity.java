package com.gem.home.activity;

//最外层的fragment

import java.util.ArrayList;
import java.util.List;

import com.gem.home.dao.MyApplication;
import com.gem.home.db.MyDatabaseHelper;
import com.gem.message.MessageHomeActivity;
import com.gem.scenery.R;
import com.gem.scenery.SceneryHomeActivity;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.view.Window;

import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Fragment_Activity extends FragmentActivity implements OnCheckedChangeListener {

	private RadioGroup mRadioGroup;
	private int currentIndex;
	private List<Fragment> fragments;
	private static final int COLOR_TEXT_HIGHTLIGHT = Color.parseColor("#FFFFFF");
	private static final int COLOR_TEXT_NORMAL = Color.parseColor("#000000");
	private MyDatabaseHelper dbHelper;
	private Context  context=MyApplication.getContext();

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragmentui_main);
		//创建数据库
		dbHelper = new MyDatabaseHelper(context, "Travel.db", null, 1);
		dbHelper.getWritableDatabase();
		
		mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup_home);
		mRadioGroup.setOnCheckedChangeListener(this);
		fragments = new ArrayList<Fragment>();
		fragments.add(new Home_home());
		fragments.add(new SceneryHomeActivity());
		fragments.add(new MessageHomeActivity());
		fragments.add(new Mine());
		FragmentManager fragmentManager = getSupportFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		// 初始加载的页面
		ft.add(R.id.tab_content, fragments.get(0));
		ft.commit();
		changeTextColor(0);
		
		
	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		int nextIndex = 0;

		if (R.id.home == checkedId) {
			nextIndex = 0;
		}
		if (R.id.picture == checkedId) {
			nextIndex = 1;
		}
		if (R.id.chat == checkedId) {
			nextIndex = 2;
		}
		if (R.id.mine == checkedId) {
			nextIndex = 3;
		}

		changefragment(nextIndex);
		changeTextColor(nextIndex);
	}

	// 监听时同时给一个默认值
	public void changefragment(int nextIndex) {
		if (currentIndex != nextIndex) {
			FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
			ft.hide(fragments.get(currentIndex));
			if (!fragments.get(nextIndex).isAdded()) {
				ft.add(R.id.tab_content, fragments.get(nextIndex));
			}
			ft.show(fragments.get(nextIndex)).commit();
		}
		currentIndex = nextIndex;
	}

	// 颜色改变
	public void changeTextColor(int checkedId) {
		resterTextColor();
		View view = mRadioGroup.getChildAt(checkedId);
		if (view instanceof TextView) {
			((TextView) view).setTextColor(COLOR_TEXT_HIGHTLIGHT);
		}

	}

	// 重置颜色
	public void resterTextColor() {
		int textNumber = 5;
		for (int i = 0; i < textNumber; i++) {
			View view = mRadioGroup.getChildAt(i);
			if (view instanceof TextView) {
				((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
			}
		}

	}

}
