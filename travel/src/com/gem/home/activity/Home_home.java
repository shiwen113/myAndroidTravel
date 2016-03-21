package com.gem.home.activity;

//首页
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


import com.gem.home.action.Renovate;
import com.gem.home.dao.OnItemClickLitener;
import com.gem.home.view.ViewPagerIndicator;
import com.gem.home.view.ViewPagerIndicator.PageOnChangeListener;
import com.gem.scenery.R;


import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.PagerAdapter;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;

import android.widget.LinearLayout;

import android.widget.TextView;
import android.widget.Toast;

public class Home_home extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
	private ViewPagerIndicator mIndicator;
	private ViewPager mViewPager;
	private View ah_Newsest, ah_Aimplace, ah_Hot, ah_Shuttime;
	// viewpager下四个动态页面
	private List<View> mViewList;
	private List<String> mTitleList, mDatas, userNamelist;
	private RecyclerView mRecyclerView;
	private SwipeRefreshLayout mSwiperefreshlayout;
	private MyAdapter myAdapter;
	private static final int REFRESH_COMPLETE = 0X110;
	private Context context;
	private Renovate mRenovate;
	private PagerAdapter mViewPagerAdapter;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Log.i("refresh", "handlemessage");
			switch (msg.what) {
			case REFRESH_COMPLETE:
				myAdapter.notifyDataSetChanged();
				mSwiperefreshlayout.setRefreshing(false);
				break;

			}
		};
	};

	// 初始化加载fragment里的部件
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_home, container, false);

		return view;
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		context = getContext();
		// 初始化控件加载布局
		inView(savedInstanceState);
		// 初始化数据
		initData();
		// 做成集合

		mRecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
		Log.i("fuck", "man+context");
		//
		int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
		// item空隙
		mRecyclerView.addItemDecoration(new com.gem.home.view.SpaceItem(spacingInPixels));

		myAdapter = new MyAdapter();
		myAdapter.setOnItemClickListener(new OnItemClickLitener() {

			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(context, position + " click", Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(context, Item_Activity.class);
				context.startActivity(intent);

			}
		});
		mRecyclerView.setAdapter(myAdapter);
		// 下拉刷新控件
		mSwiperefreshlayout = (SwipeRefreshLayout) ah_Hot.findViewById(R.id.hot_swiperefreshlayout);
		mSwiperefreshlayout.setOnRefreshListener(this);
		mSwiperefreshlayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);
		mViewPager.setAdapter(mViewPagerAdapter);
		mIndicator.setTabItemTitles(mTitleList);
		mIndicator.setVisibleTabCount(4);
		mIndicator.setViewPager(mViewPager, 0);
		mIndicator.setOnPageChangeListener(new PageOnChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub

			}
		});

	}

	// 初始化控件
	private void inView(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		mViewPager = (ViewPager) this.getView().findViewById(R.id.home_viewpager);
		mIndicator = (ViewPagerIndicator) this.getView().findViewById(R.id.id_indicator);
		// 找到viewpager和滑动标题
		// 四个滑动页面
		ah_Hot = this.getView().findViewById(R.layout.ah_hot);
		ah_Newsest = this.getView().findViewById(R.layout.ah_newsest);
		ah_Aimplace = this.getView().findViewById(R.layout.ah_aimplace);
		ah_Shuttime = this.getView().findViewById(R.layout.ah_shuttime);
		// 加载布局

		LayoutInflater lf = getLayoutInflater(savedInstanceState).from(context);
		ah_Hot = lf.inflate(R.layout.ah_hot, null);
		ah_Newsest = lf.inflate(R.layout.ah_newsest, null);
		ah_Aimplace = lf.inflate(R.layout.ah_aimplace, null);
		ah_Shuttime = lf.inflate(R.layout.ah_shuttime, null);
		// 加载recyleview还有适配器
		mRecyclerView = (RecyclerView) ah_Hot.findViewById(R.id.hot_recyclerview);
		Log.i("fuck", "rec+" + mRecyclerView);
		// 初始化加载数据
		// mRenovate.renvoate();
	}

	@Override
	public void onRefresh() { // TODO Auto-generated method stub //
		mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
		// 下拉刷新
		// mRenovate.renvoate();
		mDatas.addAll(Arrays.asList("Lucene", "Canvas", "Bitmap"));
		userNamelist.addAll(Arrays.asList("jack", "tony", "mike"));
		Log.i("refresh", "onrefresh");
	}

	public class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {
		private OnItemClickLitener mOnItemClickListener;

		public void setOnItemClickListener(OnItemClickLitener mOnItemClickLitener) {
			this.mOnItemClickListener = mOnItemClickLitener;
		}

		@Override
		public int getItemCount() { // TODO Auto-generated method stub
			Log.i("fuck", "getitemcount");
			return mDatas.size();

		}

		@Override
		public void onBindViewHolder(final MyViewHolder arg0, int arg1) {

			Log.i("fuck", "onbindviewholder");
			arg0.teamName.setText(mDatas.get(arg1));
			arg0.userName.setText(userNamelist.get(arg1));
			if (mOnItemClickListener != null) {
				arg0.itemView.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						int pos = arg0.getPosition();
						mOnItemClickListener.onItemClick(arg0.itemView, pos);
					}
				});

			}
		}

		@Override
		public MyViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {
			MyViewHolder holder = new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item, arg0, false));

			return holder;
		}

	}

	public class MyViewHolder extends ViewHolder {
		TextView userName;
		TextView teamName;

		public MyViewHolder(View itemView) {
			super(itemView);
			userName = (TextView) itemView.findViewById(R.id.itview_userName);
			teamName = (TextView) itemView.findViewById(R.id.itview_teamName);

		}

	}

	private void initData() {
		// TODO Auto-generated method stub
		// 初始化item里的数据
		mDatas = new ArrayList<String>();
		userNamelist = new ArrayList<String>();
		userNamelist.add("tim");
		userNamelist.add("milo");
		mDatas.add("java");
		mDatas.add("android");
		// 布局加入
		mViewList = new ArrayList<View>();
		mViewList.add(ah_Hot);
		mViewList.add(ah_Newsest);
		mViewList.add(ah_Shuttime);
		mViewList.add(ah_Aimplace);

		mTitleList = new ArrayList<String>();
		mTitleList.add("热门");
		mTitleList.add("最新");
		mTitleList.add("目的地");
		mTitleList.add("结束时间");

		// viewpager适配器
		mViewPagerAdapter = new PagerAdapter() {

			@Override
			public boolean isViewFromObject(View arg0, Object arg1) {
				// TODO Auto-generated method stub
				Log.i("fuck", "vp.set");
				return arg0 == arg1;

			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return mViewList.size();
			}

			@Override
			public void destroyItem(ViewGroup container, int position, Object object) {
				// TODO Auto-generated method stub
				((ViewPager) container).removeView(mViewList.get(position));
			}

			@Override
			public Object instantiateItem(ViewGroup container, int position) {
				// TODO Auto-generated method stub
				((ViewPager) container).addView(mViewList.get(position));
				return mViewList.get(position);
			}

			@Override
			public int getItemPosition(Object object) {
				// TODO Auto-generated method stub
				return super.getItemPosition(object);
			}

		};
	}
}
