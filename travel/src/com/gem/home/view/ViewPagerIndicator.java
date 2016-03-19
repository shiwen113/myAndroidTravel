package com.gem.home.view;

import java.util.List;

import com.gem.gotravel.R;
import com.gem.home.dao.MyApplication;

import android.R.color;
import android.accounts.NetworkErrorException;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.Rect;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewParent;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.TextView;
//viewpager指示器
public class ViewPagerIndicator extends LinearLayout {
	private Paint mPaint;
	private Path mPath;
	private int mTriangleWidth;
	private int mTriangleHeight;
	private static final float RADIO_TRIANGLE_WIDTH = 1 / 6F;
	private int mInitTranslationX;
	private int mTranslationX;
	private int mTabvisibleCount;
	private static final int COUNT_DEFAULT_TAB = 4;
	private List<String> mTitleList;
	private static final int COLOR_TEXT_NORMAL =   Color.parseColor("#000000");
	private static final int COLOR_TEXT_HIGHTLIGHT =   Color.parseColor("#FFCC00");
	private static final int DIMENSION_TRIANGLE_WIDTH_MAX = (int) (getScreenWidth() / 3 * RADIO_TRIANGLE_WIDTH);
	private ViewPager mViewPager;
	public ViewPagerIndicator(Context context) {
		this(context, null);

	}

	public ViewPagerIndicator(Context context, AttributeSet attribute) {
		super(context, attribute);
		TypedArray a = context.obtainStyledAttributes(attribute, R.styleable.ViewPagerIndicator);
		mTabvisibleCount = a.getInt(R.styleable.ViewPagerIndicator_visible_tab_count, COUNT_DEFAULT_TAB);
		if (mTabvisibleCount < 0) {
			mTabvisibleCount = COUNT_DEFAULT_TAB;
		}
		a.recycle();
		mPaint = new Paint();
		mPaint.setColor(Color.parseColor("#FFCC00"));
		mPaint.setStyle(Style.FILL);
		mPaint.setPathEffect(new CornerPathEffect(3));
		mPaint.setAntiAlias(true);
	}

	@Override
	protected void dispatchDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.save();
		canvas.translate(mInitTranslationX + mTranslationX, getHeight());
		canvas.drawPath(mPath, mPaint);
		canvas.restore();
		super.dispatchDraw(canvas);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub

		super.onSizeChanged(w, h, oldw, oldh);
		mTriangleWidth = (int) (w / mTabvisibleCount * RADIO_TRIANGLE_WIDTH);
		mTriangleWidth = Math.min(mTriangleWidth, DIMENSION_TRIANGLE_WIDTH_MAX);
		mInitTranslationX = w / mTabvisibleCount / 2 - mTriangleWidth / 2;
		initTriangle();

	}

	@Override
	protected void onFinishInflate() {
		// TODO Auto-generated method stub
		super.onFinishInflate();
		int cCount = getChildCount();
		if (cCount == 0)
			return;
		for (int i = 0; i < cCount; i++) {
			View view = getChildAt(i);
			LinearLayout.LayoutParams lp = (LayoutParams) view.getLayoutParams();
			lp.weight = 0;
			lp.width = getScreenWidth() / mTabvisibleCount;
			view.setLayoutParams(lp);
		}
		setitemClickEvent();
	}

	private void initTriangle() {
		// TODO Auto-generated method stub
		mTriangleHeight = mTriangleWidth / 2;
		mPath = new Path();
		mPath.moveTo(0, 0);
		mPath.lineTo(mTriangleWidth / 2, -mTriangleHeight);
		mPath.lineTo(mTriangleWidth, 0);
		mPath.close();
	}

	public void scroll(int arg0, float arg1) {
		// TODO Auto-generated method stub
		int tabWidth = getWidth() / mTabvisibleCount;
		mTranslationX = (int) (tabWidth * (arg1 + arg0));
		if (arg0 >= (mTabvisibleCount - 2) && arg1 > 0 && getChildCount() > mTabvisibleCount) {
			if (mTabvisibleCount != 1) {
				this.scrollTo((arg0 - (mTabvisibleCount - 2)) * tabWidth + (int) (tabWidth * arg1), 0);
			} else {
				this.scrollTo((arg0 * tabWidth + (int) (tabWidth + arg1)), 0);
			}

		}
		invalidate();
	}

	public void setTabItemTitles(List<String> titles) {
		if (titles != null && titles.size() > 0) {
			this.removeAllViews();
			mTitleList = titles;
			for (String title : mTitleList) {
				addView(generateTextView(title));
			}
			setitemClickEvent();
		}
	}

public	void setVisibleTabCount(int count) {
		mTabvisibleCount = count;

	}

	private View generateTextView(String title) {
		TextView tv = new TextView(getContext());
		LinearLayout.LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		lp.width = getScreenWidth() / mTabvisibleCount;
		tv.setText(title);
		tv.setGravity(Gravity.CENTER);
		tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
		tv.setTextColor(COLOR_TEXT_NORMAL);
		tv.setLayoutParams(lp);
		// TODO Auto-generated method stub
		return tv;
	}

	private static int getScreenWidth() {
		WindowManager wm = (WindowManager) MyApplication.getContext().getSystemService(Context.WINDOW_SERVICE);
		DisplayMetrics outMetrics = new DisplayMetrics();
		wm.getDefaultDisplay().getMetrics(outMetrics);
		return outMetrics.widthPixels;
	}



	public interface PageOnChangeListener {
		public void onPageSelected(int arg0);

		public void onPageScrolled(int arg0, float arg1, int arg2);

		public void onPageScrollStateChanged(int arg0);

	}

	public PageOnChangeListener mListener;

	public void setOnPageChangeListener(PageOnChangeListener Listener) {
		this.mListener = Listener;
	}

	public void setViewPager(ViewPager viewPager, int pos) {
		mViewPager = viewPager;
		mViewPager.setOnPageChangeListener(new OnPageChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				if (mListener != null) {
					mListener.onPageSelected(arg0);

				}
				highLoghtTextView(arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				scroll(arg0, arg1);
				if (mListener != null) {
					mListener.onPageScrolled(arg0, arg1, arg2);
				}
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				if (mListener != null) {
					mListener.onPageScrollStateChanged(arg0);
				}
			}
		});
		mViewPager.setCurrentItem(pos);
		highLoghtTextView(pos);
	}

	private void resetTextViewColor() {
		for (int i = 0; i < getChildCount(); i++) {
			View view = getChildAt(i);
			if (view instanceof TextView) {
				((TextView) view).setTextColor(COLOR_TEXT_NORMAL);
			}
		}
	}

	private void highLoghtTextView(int pos) {
		resetTextViewColor();
		View view = getChildAt(pos);
		if (view instanceof TextView) {
			((TextView) view).setTextColor(COLOR_TEXT_HIGHTLIGHT);
		}
	}

	private void setitemClickEvent() {
		int cCount = getChildCount();
		for (int i = 0; i < cCount; i++) {
			final int j = i;
			View view = getChildAt(i);
			view.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					mViewPager.setCurrentItem(j);
				}
			});
		}
	}
}
