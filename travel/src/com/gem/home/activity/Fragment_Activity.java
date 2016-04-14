package com.gem.home.activity;

//最外层的fragment

import java.util.ArrayList;
import java.util.List;

import com.gem.home.dao.MyApplication;
import com.gem.home.db.MyDatabaseHelper;
import com.gem.message.MessageHomeActivity;
import com.gem.mine.activity.MyloginActivity;
import com.gem.scenery.R;
import com.gem.scenery.SceneryHomeActivity;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class Fragment_Activity extends FragmentActivity implements OnCheckedChangeListener {
	private Drawable img_on, img_off;

	private int picutre_on = R.drawable.home_logo_focus;// 动态显示的图片id
	private int picture_off;// 不选择时
	private RadioGroup mRadioGroup;
	private int currentIndex = 0;//
	private List<Fragment> fragments;
	private static final int COLOR_TEXT_HIGHTLIGHT = Color.parseColor("#FFFFFF");//选中时，字体颜色这里是白色
	private static final int COLOR_TEXT_NORMAL = Color.parseColor("#000000");//未选中时，字体颜色，这里是黑色
	private MyDatabaseHelper dbHelper;
	private FragmentManager fragmentManager = getSupportFragmentManager();
	private FragmentTransaction ft = fragmentManager.beginTransaction();
	private Context context = MyApplication.getContext();
	private int nextIndex;
	private Home_home home;
	private SceneryHomeActivity scenery;
	private MessageHomeActivity message;
	private MyloginActivity login;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.fragmentui_main);
		// 创建数据库
		dbHelper = new MyDatabaseHelper(context, "Travel.db", null, 1);
		dbHelper.getWritableDatabase();

		mRadioGroup = (RadioGroup) findViewById(R.id.radioGroup_home);
		mRadioGroup.setOnCheckedChangeListener(this);
		fragments = new ArrayList<Fragment>();
		home = new Home_home();
		scenery = new SceneryHomeActivity();
		message = new MessageHomeActivity();
		login = new MyloginActivity();
		fragments.add(home);
		fragments.add(scenery);
		fragments.add(message);
		fragments.add(login);

		// FragmentManager fragmentManager = getSupportFragmentManager();
		// FragmentTransaction ft = fragmentManager.beginTransaction();
		// 初始加载的页面
		ft.add(R.id.tab_content, fragments.get(currentIndex));
		ft.commit();
		changeTextColor(currentIndex);

	}
	
	@Override
	public void onAttachFragment(Fragment fragment) {
		// TODO Auto-generated method stub
		super.onAttachFragment(fragment);
//		FragmentManager fragmentManager = getSupportFragmentManager();
//		FragmentTransaction ft = fragmentManager.beginTransaction();
		Log.i("ft", "ft+" + ft);
		ft.remove(fragment);
		if (home == null && fragment instanceof Home_home) {
			home = (Home_home) fragment;
		} else if (scenery == null && fragment instanceof SceneryHomeActivity) {
			scenery = (SceneryHomeActivity) fragment;
		} else if (message == null && fragment instanceof MessageHomeActivity) {
			message = (MessageHomeActivity) fragment;
		} else if (login == null && fragment instanceof MyloginActivity) {
			login = (MyloginActivity) fragment;
		}

	}

	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {

		if (R.id.home == checkedId) {
			nextIndex = 0;
			//选中时图片
			picutre_on = R.drawable.home_logo_focus;
		} else if (R.id.picture == checkedId) {
			nextIndex = 1;
			picutre_on = R.drawable.picuture_on;
		} else if (R.id.chat == checkedId) {
			nextIndex = 2;
			picutre_on = R.drawable.message_on;
		} else if (R.id.mine == checkedId) {
			nextIndex = 3;
			picutre_on=R.drawable.mine_on;
		}
		resterPicutre(currentIndex);
		changefragment(nextIndex);
		changeTextColor(nextIndex);
		
	}

	// 监听时同时给一个默认值
	public void changefragment(int nextIndex) {

		if (currentIndex != nextIndex) {
			FragmentManager fragmentManager = getSupportFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			Log.i("ft", "ft+" + ft);
			ft.hide(fragments.get(currentIndex));
			if (!fragments.get(nextIndex).isAdded()) {
				ft.add(R.id.tab_content, fragments.get(nextIndex));
			}
			ft.show(fragments.get(nextIndex)).commit();

		}
		currentIndex = nextIndex;
	}

	// 图片、文字 颜色改变
	public void changeTextColor(int checkedId) {
		View view = mRadioGroup.getChildAt(checkedId);
		resterTextColor();

		if (view instanceof TextView) {
			//选中时，改变字体颜色
		//	((TextView) view).setTextColor(COLOR_TEXT_HIGHTLIGHT);

		}

		if (view instanceof RadioButton) {
			Resources res = getResources();
			img_on = res.getDrawable(picutre_on);
			img_on.setBounds(0, 0, img_on.getMinimumWidth(), img_on.getMinimumHeight());
			((RadioButton) view).setCompoundDrawables(null, img_on, null, null);
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

	public void resterPicutre(int currentIndex) {
		View view = mRadioGroup.getChildAt(currentIndex);
		switch (currentIndex) {
		case 0:
			//未选中时各个图片
			picture_off = R.drawable.home_logo_unfocus;
			break;
		case 1:
			picture_off = R.drawable.collect_bg;
			break;
		case 2:
			picture_off = R.drawable.message_off;
			break;
		case 3:
			picture_off=R.drawable.mine_off;
			break;

		default:
			break;
		}
		if (view instanceof RadioButton) {
			Resources res = getResources();
			img_off = res.getDrawable(picture_off);
			img_off.setBounds(0, 0, img_off.getMinimumWidth(), img_off.getMinimumHeight());
			((RadioButton) view).setCompoundDrawables(null, img_off, null, null);
		}
	}
}
