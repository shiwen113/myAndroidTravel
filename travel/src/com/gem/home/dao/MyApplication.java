package com.gem.home.dao;

import io.rong.imkit.RongIM;
import io.rong.imlib.RongIMClient;

import java.util.LinkedList;

import android.app.ActivityManager;
import android.app.Application;
import android.app.NotificationManager;
import android.app.Service;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Vibrator;

import com.baidu.mapapi.SDKInitializer;
import com.gem.home.baiduMap.LocationService;
import com.gem.home.until.LoginData;
import com.gem.message.action.RecentChatAdapter;
import com.gem.message.client.Client;
import com.gem.message.entity.RecentChatEntity;
import com.gem.message.utils.Constants;
import com.gem.message.utils.SharePreferenceUtil;
import com.gem.scenery.R;
import com.gem.scenery.entity.PopularScene;
import com.nostra13.universalimageloader.cache.memory.impl.UsingFreqLimitedMemoryCache;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.download.BaseImageDownloader;

//获取全局对象
public class MyApplication extends Application {
	private static Context context;
	private Client client;// 客户端
	private boolean isClientStart;// 客户端连接是否启动
	private NotificationManager mNotificationManager;
	private int newMsgNum = 0;// 后台运行的消息
	private LinkedList<RecentChatEntity> list;
	private RecentChatAdapter adapter;
	private int recentNum = 0;
	private LoginData ld;
	public LocationService locationService;
	public Vibrator mVibrator;
	private PopularScene ps;
	@Override
	public void onCreate() {
		context = getApplicationContext();
		SharePreferenceUtil util = new SharePreferenceUtil(this,
				Constants.SAVE_USER);
		System.out.println(util.getIp() + " " + util.getPort());
		client = new Client(util.getIp(), util.getPort());// 从配置文件中读ip和地址
		list = new LinkedList<RecentChatEntity>();
		adapter = new RecentChatAdapter(context,list);
		// TODO Auto-generated method stub
		
		//融云
//		if (getApplicationInfo().packageName.equals(getCurProcessName(getApplicationContext())) ||
//                "io.rong.push".equals(getCurProcessName(getApplicationContext()))) {
			RongIM.init(this);
//        }
		
			/***
			 * 初始化定位sdk，建议在Application中创建
			 */
			locationService = new LocationService(getApplicationContext());
			mVibrator = (Vibrator) getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
			SDKInitializer.initialize(getApplicationContext());
			
		super.onCreate();
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				context)
				.memoryCacheExtraOptions(480, 800)
				.threadPoolSize(3)
				// 线程池内加载的数量
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 * 1024))
				// implementation/你可以通过自己的内存缓存实现
				.memoryCacheSize(2 * 1024 * 1024)
				// .discCacheSize(50 * 1024 * 1024)
				// .discCacheFileNameGenerator(new
				// Md5FileNameGenerator())//将保存的时候的URI名称用MD5 加密
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				// .discCacheFileCount(100) //缓存的文件数量
				// .discCache(new UnlimitedDiscCache(cacheDir))//自定义缓存路径
				.defaultDisplayImageOptions(DisplayImageOptions.createSimple())
				.imageDownloader(
						new BaseImageDownloader(context, 5 * 1000, 30 * 1000)) // connectTimeout
				.writeDebugLogs() // Remove for release app
				.build();// 开始构建

		DisplayImageOptions options;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.ic_launcher) // 设置图片在下载期间显示的图片
				.showImageForEmptyUri(R.drawable.ic_launcher)// 设置图片Uri为空或是错误的时候显示的图片
				.showImageOnFail(R.drawable.ic_launcher) // 设置图片加载/解码过程中错误时候显示的图片
				.cacheInMemory(true)// 设置下载的图片是否缓存在内存中
				.cacheOnDisc(true)// 设置下载的图片是否缓存在SD卡中
				.considerExifParams(true) // 是否考虑JPEG图像EXIF参数（旋转，翻转）
				.imageScaleType(ImageScaleType.EXACTLY_STRETCHED)// 设置图片以如何的编码方式显示
				.bitmapConfig(Bitmap.Config.RGB_565)// 设置图片的解码类型//
				// .decodingOptions(android.graphics.BitmapFactory.Options
				// decodingOptions)//设置图片的解码配置
				// .delayBeforeLoading(int delayInMillis)//int
				// delayInMillis为你设置的下载前的延迟时间
				// 设置图片加入缓存前，对bitmap进行设置
				// .preProcessor(BitmapProcessor preProcessor)
				.resetViewBeforeLoading(true)// 设置图片在下载前是否重置，复位
				.displayer(new RoundedBitmapDisplayer(20))// 是否设置为圆角，弧度为多少
				.displayer(new FadeInBitmapDisplayer(100))// 是否图片加载好后渐入的动画时间
				.build();// 构建完成

		ImageLoader.getInstance().init(config);
	}

	
	 public static String getCurProcessName(Context context) {

	        int pid = android.os.Process.myPid();

	        ActivityManager activityManager = (ActivityManager) context
	                .getSystemService(Context.ACTIVITY_SERVICE);

	        for (ActivityManager.RunningAppProcessInfo appProcess : activityManager
	                .getRunningAppProcesses()) {

	            if (appProcess.pid == pid) {
	                return appProcess.processName;
	            }
	        }
	        return null;
	    }

	public static Context getContext() {
		return context;
	}
	public Client getClient() {
		return client;
	}

	public boolean isClientStart() {
		return isClientStart;
	}

	public void setClientStart(boolean isClientStart) {
		this.isClientStart = isClientStart;
	}

	public NotificationManager getmNotificationManager() {
		return mNotificationManager;
	}

	public void setmNotificationManager(NotificationManager mNotificationManager) {
		this.mNotificationManager = mNotificationManager;
	}

	public int getNewMsgNum() {
		return newMsgNum;
	}

	public void setNewMsgNum(int newMsgNum) {
		this.newMsgNum = newMsgNum;
	}

	public LinkedList<RecentChatEntity> getmRecentList() {
		return list;
	}

	public void setmRecentList(LinkedList<RecentChatEntity> mRecentList) {
		this.list = mRecentList;
	}

	public RecentChatAdapter getmRecentAdapter() {
		return adapter;
	}

	public void setmRecentAdapter(RecentChatAdapter mRecentAdapter) {
		this.adapter = mRecentAdapter;
	}

	public int getRecentNum() {
		return recentNum;
	}

	public void setRecentNum(int recentNum) {
		this.recentNum = recentNum;
	}

	public LoginData getLd() {
		return ld;
	}

	public void setLd(LoginData ld) {
		this.ld = ld;
	}


	public PopularScene getPs() {
		return ps;
	}


	public void setPs(PopularScene ps) {
		this.ps = ps;
	}

}
