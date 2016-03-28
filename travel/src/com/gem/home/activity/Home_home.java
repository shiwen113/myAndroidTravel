package com.gem.home.activity;

//首页
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.gem.home.action.Renovate;
import com.gem.home.dao.MyRecyclerViewAdapter;
import com.gem.home.dao.MyViewPagerAdapter;
import com.gem.home.dao.OnItemClickLitener;
import com.gem.home.until.PublishTravel;
import com.gem.home.view.SpaceItem;
import com.gem.home.view.ViewPagerIndicator;
import com.gem.home.view.ViewPagerIndicator.PageOnChangeListener;
import com.gem.scenery.R;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;

public class Home_home extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
	private ViewPagerIndicator mIndicator;
	private ViewPager mViewPager;
	private View ah_Newsest, ah_Aimplace, ah_Hot, ah_Shuttime;
	// viewpager下四个动态页面
	private List<View> mViewList;
	private List<String> mTitleList, mDatas, userNamelist;
	private RecyclerView mRecyclerView, news_RecyclerView, aim_RecyclerView, shut_RecyclerView;
	private SwipeRefreshLayout mSwiperefreshlayout;
	private MyRecyclerViewAdapter myRecyclerViewAdapter;
	private int lastVisibleItem;
	private static final int MORE_ITEM = 2;
	private static final int REFRESH_COMPLETE = 1;
	private int Currment=0;
	private Context context;
	private List<PublishTravel> arr = new ArrayList<PublishTravel>();
	private Renovate mRenovate;
	private MyViewPagerAdapter mViewPagerAdapter;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			Log.i("refresh", "handlemessage");
			switch (msg.what) {
			case REFRESH_COMPLETE:
				//下拉刷新
				mRenovate.initData(myRecyclerViewAdapter, arr,Currment);
				mRecyclerView.setAdapter(myRecyclerViewAdapter);
				mSwiperefreshlayout.setRefreshing(false);
				
				
				
				break;
			case MORE_ITEM:
				//下拉加载，加载数据源未实现
				myRecyclerViewAdapter.notifyDataSetChanged();
				mSwiperefreshlayout.setRefreshing(false);
				break;
				default:
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
	/**
	 * 请求网络访问多级评论
	 */
	String url="http://10.201.1.12:8080/travel/TravelComment";
	public void sendContent(final PublishTravel pt,final int position){
		HttpUtils http=new HttpUtils();
		RequestParams params=new RequestParams();
		params.addBodyParameter("td",String.valueOf(pt.getTd()));
		params.addBodyParameter("ld",String.valueOf(17));
		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result=arg0.result;
				if(result!=null){
					Intent intent = new Intent(context, Item_Activity.class);
					Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
					intent.putExtra("pt",gson.toJson(pt));//旅行队
					intent.putExtra("content", result);//旅行队评论
					context.startActivity(intent);
				}
			}
		});
	}
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		context = getContext();
		// 初始化控件加载布局
		inView(savedInstanceState);
		// 初始化数据
		initData();
		// 设置recyclerview排版样式（这里是垂直排列）
		final LinearLayoutManager linearLayoutManager=new LinearLayoutManager(context, LinearLayout.VERTICAL, false);
		mRecyclerView.setLayoutManager(linearLayoutManager);
		news_RecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
		aim_RecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
		shut_RecyclerView.setLayoutManager(new LinearLayoutManager(context, LinearLayout.VERTICAL, false));
		// recycler中 item空隙及样式
		int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.activity_horizontal_margin);
		mRecyclerView.addItemDecoration(new SpaceItem(spacingInPixels));
		news_RecyclerView.addItemDecoration(new SpaceItem(spacingInPixels));
		aim_RecyclerView.addItemDecoration(new SpaceItem(spacingInPixels));
		shut_RecyclerView.addItemDecoration(new SpaceItem(spacingInPixels));

		// recycler适配器
		myRecyclerViewAdapter = new MyRecyclerViewAdapter(arr, context);
		
		// 网络通讯初始化首页数据
		mRenovate = new Renovate();
		mRenovate.initData(myRecyclerViewAdapter, arr,Currment);
		// recycler中item监听事件
		myRecyclerViewAdapter.setOnItemClickListener(new OnItemClickLitener() {

			@Override
			public void onItemClick(View view, int position) {
				Toast.makeText(context, position + " click", Toast.LENGTH_SHORT).show();
				if(view!=null){
					PublishTravel pt=arr.get(position);
					sendContent(pt,position);
				}

			}
		});
//
	
		mRecyclerView.setOnScrollListener(new RecyclerView.OnScrollListener() {

			@Override
			public void onScrollStateChanged(RecyclerView recyclerView,
					int newState) {
				super.onScrollStateChanged(recyclerView, newState);
				if (newState == RecyclerView.SCROLL_STATE_IDLE
						&& lastVisibleItem + 1 == myRecyclerViewAdapter.getItemCount()) {
					mSwiperefreshlayout.setRefreshing(true);
					mHandler.sendEmptyMessageDelayed(MORE_ITEM, 2000);
				}
			}

			@Override
			public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
				super.onScrolled(recyclerView, dx, dy);
				lastVisibleItem = linearLayoutManager.findLastVisibleItemPosition();
			}

		});
		mRecyclerView.setAdapter(myRecyclerViewAdapter);
		news_RecyclerView.setAdapter(myRecyclerViewAdapter);
		aim_RecyclerView.setAdapter(myRecyclerViewAdapter);
		shut_RecyclerView.setAdapter(myRecyclerViewAdapter);
		// 下拉刷新控件
		mSwiperefreshlayout = (SwipeRefreshLayout) ah_Hot.findViewById(R.id.hot_swiperefreshlayout);
		mSwiperefreshlayout.setOnRefreshListener(this);
		mSwiperefreshlayout.setColorSchemeResources(android.R.color.holo_green_dark, android.R.color.holo_green_light,
				android.R.color.holo_orange_light, android.R.color.holo_red_light);// 刷新控件颜色定义
		mViewPager.setAdapter(mViewPagerAdapter);
		mIndicator.setTabItemTitles(mTitleList);
		mIndicator.setVisibleTabCount(4);
		mIndicator.setViewPager(mViewPager, 0);
		mIndicator.setOnPageChangeListener(new PageOnChangeListener() {

			@Override
			public void onPageSelected(int arg0) {
				// TODO Auto-generated method stub
				// viewpager页面改变时请求新数据
				Currment=arg0;
				mRenovate.initData(myRecyclerViewAdapter, arr,Currment);
				Log.i("onPageSelected", "arg0+" + arg0);
			}

			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				// TODO Auto-generated method stub
				Log.i("onPageScrolled", "arg0+" + arg0);
				Log.i("onPageScrolled", "arg1+" + arg1);
				Log.i("onPageScrolled", "arg2+" + arg2);
			}

			@Override
			public void onPageScrollStateChanged(int arg0) {
				// TODO Auto-generated method stub
				Log.i("onPageScrollStateChanged", "arg0+" + arg0);
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
		// 加载recyleview
		mRecyclerView = (RecyclerView) ah_Hot.findViewById(R.id.hot_recyclerview);
		news_RecyclerView = (RecyclerView) ah_Newsest.findViewById(R.id.new_recyclerview);
		aim_RecyclerView = (RecyclerView) ah_Aimplace.findViewById(R.id.aim_recyclerview);
		shut_RecyclerView = (RecyclerView) ah_Shuttime.findViewById(R.id.shut_recyclerview);

	}
//下拉刷新
	@Override
	public void onRefresh() { // TODO Auto-generated method stub //
		mHandler.sendEmptyMessageDelayed(REFRESH_COMPLETE, 2000);
	
		Log.i("refresh", "下拉刷新+onrefresh");
	}

	private void initData() {
		// TODO Auto-generated method stub
		// 初始化item里的数据
		// mDatas = new ArrayList<String>();
		// userNamelist = new ArrayList<String>();
		// userNamelist.add("tim");
		// userNamelist.add("milo");
		// mDatas.add("java");
		// mDatas.add("android");

		// 布局加入
		mViewList = new ArrayList<View>();
		mViewList.add(ah_Hot);
		mViewList.add(ah_Newsest);
		mViewList.add(ah_Aimplace);
		mViewList.add(ah_Shuttime);

		mTitleList = new ArrayList<String>();
		mTitleList.add("热门");
		mTitleList.add("最新");
		mTitleList.add("随机");
		mTitleList.add("同城");

		// viewpager适配器

		mViewPagerAdapter = new MyViewPagerAdapter(mViewList);
	}
}
