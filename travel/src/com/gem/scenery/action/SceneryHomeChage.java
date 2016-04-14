package com.gem.scenery.action;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.gem.home.dao.MyApplication;
import com.gem.scenery.R;
import com.gem.scenery.SceneryHomeActivity;
import com.gem.scenery.entity.PopularScene;
import com.gem.scenery.entity.SharePicture;
import com.gem.scenery.interfaces.OnLoadListener;
import com.gem.scenery.interfaces.OnRefreshListener;
import com.gem.scenery.utils.AutoListView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

public class SceneryHomeChage implements OnPageChangeListener{
//	 private int offset;// 动画图片偏移量
//	 private int bmpW;// 动画图片宽度
//	 private int currIndex;// 当前页卡编号
	 private ViewPager vp;//叶卡内容
//	 private ImageView imageView;// 动画图片
	 private HttpUtils http=new HttpUtils();
	 private ImageView imageView,imageView2,imageView3;// 动画图片
 	 private RequestParams params = new RequestParams();
//	 int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
//     int two = offset * 3;// 页卡1 -> 页卡3 偏移量
     private Context context;
//     private List<SharePicture> listmap;
     List<AutoListView> lv;
     private Handler handler ;
 	private List<View> views;// Tab页面列表
 	private PlazaListViewAdapt adapterP;
 	private SceneryHomeActivity t;
 	private int page;
 	private int hot=1;
 	private int plaza=1;
 	private int season=1;
 	private MyApplication m;
     
     public PlazaListViewAdapt getAdapterP() {
		return adapterP;
	}

	public void setAdapterP(PlazaListViewAdapt adapterP) {
		this.adapterP = adapterP;
	}

//	private List<PopularScene> arr =new ArrayList<PopularScene>();
//     private List<PopularScene> arr1 =new ArrayList<PopularScene>();
//     private List<PopularScene> arr2 =new ArrayList<PopularScene>();
//     public SceneryHomeChage(Context context ,int offset, int bmpW, int currIndex, ViewPager vp,
//			ImageView imageView) {
//		this.offset = offset;
//		this.bmpW = bmpW;
//		this.currIndex = currIndex;
//		this.vp = vp;
//		this.imageView = imageView;
//		this.context=context;
//	}

	public SceneryHomeChage(ViewPager vp, ImageView imageView,
			ImageView imageView2, ImageView imageView3, Context context, List<AutoListView> lv,Handler handler,List<View> views) {
		super();
		this.vp = vp;
		this.imageView = imageView;
		this.imageView2 = imageView2;
		this.imageView3 = imageView3;
		this.context = context;
		this.lv=lv;
		this.handler=handler;
		this.views=views;
		m=(MyApplication) context.getApplicationContext();
	}

	@Override
     public void onPageScrollStateChanged(int arg0) {
//		arg0 ==1的时候表示正在滑动，arg0==2的时候表示滑动完毕了，arg0==0的时候表示什么都没做，就是停在那。
     }
     
     @Override
     public void onPageScrolled(int arg0, float arg1, int arg2) {
//    	 表示在前一个页面滑动到后一个页面的时候，在前一个页面滑动前调用的方法。
     }

    /**
     * 第一页
     */
     public void firstPage(){
    	 lv.get(0).setOnRefreshListener(t);
  		 lv.get(0).setOnLoadListener(t);
    	 imageView.setBackgroundResource(R.drawable.p);
    	 imageView2.setBackgroundResource(R.drawable.p_pink);
    	 imageView3.setBackgroundResource(R.drawable.p_pink);
     }
     public void secondPage(){
 		lv.get(1).setOnRefreshListener(t);
 		lv.get(1).setOnLoadListener(t);
    	 imageView.setBackgroundResource(R.drawable.p_pink);
    	 imageView2.setBackgroundResource(R.drawable.p);
    	 imageView3.setBackgroundResource(R.drawable.p_pink);
     }
     public void thirdPage(){
    	 lv.get(2).setOnRefreshListener(t);
  		 lv.get(2).setOnLoadListener(t);
    	 imageView.setBackgroundResource(R.drawable.p_pink);
    	 imageView2.setBackgroundResource(R.drawable.p_pink);
    	 imageView3.setBackgroundResource(R.drawable.p);
     }
     @Override
     public void onPageSelected(int arg0) {
    	 
    	/*
    	 * TranslateAnimation(float fromXDelta, 
    	 *		 float toXDelta, float fromYDelta, float toYDelta)
    	 * 
    	 * float fromXDelta:这个参数表示动画开始的点离当前View X坐标上的差值；

    	　　float toXDelta, 这个参数表示动画结束的点离当前View X坐标上的差值；
    	　　float fromYDelta, 这个参数表示动画开始的点离当前View Y坐标上的差值；

    	　　float toYDelta)这个参数表示动画开始的点离当前View Y坐标上的差值；
    	*/
    	 
    	 //arg0是表示你当前选中的页面，这事件是在你页面跳转完毕的时候调用的。
         //两种方法，这个是一种，下面还有一种，显然这个比较麻烦
//         Animation animation = null;
    	 page=arg0;
         switch (arg0) {
         case 0:
        	 lv.get(0).setPage(0);
        	 firstPage();
        	 sendHotData(AutoListView.REFRESH);
        	 break;
         case 1:
        	 lv.get(1).setPage(1);
        	 secondPage();
        	 sendPlazaData(AutoListView.REFRESH);
        	 break;
         case 2:
        	 lv.get(2).setPage(2);
        	 thirdPage();
        	 if(m.getLd()!=null){
        	 sendSeasonData(AutoListView.REFRESH);
        	 }else{
        		 Toast.makeText(context, "登录查看更多", Toast.LENGTH_LONG).show(); 
        	 }
        	 break;
        	 
        /* case 0:
             if (currIndex == 1) {
                 animation = new TranslateAnimation(one, 0, 0, 0);
             } else if (currIndex == 2) {
                 animation = new TranslateAnimation(two, 0, 0, 0);
             }
             break;
         case 1:
        	 sendPlazaData();
             if (currIndex == 0) {
                 animation = new TranslateAnimation(offset, one, 0, 0);
             } else if (currIndex == 2) {
                 animation = new TranslateAnimation(two, one, 0, 0);
             }
             break;
         case 2:
             if (currIndex == 0) {
                 animation = new TranslateAnimation(offset, two, 0, 0);
             } else if (currIndex == 1) {
                 animation = new TranslateAnimation(one, two, 0, 0);
             }
             break;*/
              
         }
         //代替上面的方法
//         Animation animation = new TranslateAnimation(one*currIndex, two*arg0, 0, 0);//显然这个比较简洁，只有一行代码。
         
//         currIndex = arg0;
//         animation.setFillAfter(true);// True:图片停在动画结束位置
//         animation.setDuration(300);
//         imageView.startAnimation(animation);
         
     }
     
//     String urlHot="http://10.201.1.12:8080/travel/SharePicture_remeng";
     String urlHot="http://10.201.1.12:8080/travel/SecenryAllData";
     /**
      * 请求热门的数据
      */
     public void sendHotData(final int what){
    	 http.configCurrentHttpCacheExpiry(0*1000);
    	 RequestParams params=new RequestParams();
    	 if(what==0){
    		 hot=1;
    		 params.addBodyParameter("page",String.valueOf(hot));
    	 }else if(what==1){
    		 hot++;
    		 params.addBodyParameter("page",String.valueOf(hot));
    	 }
    	 if(m.getLd()!=null){
    		 params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
    	 }
    	 http.send(HttpMethod.POST, urlHot, params,new RequestCallBack<String>() {
    		 
			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result=arg0.result;
				Type type=new TypeToken<List<PopularScene>>(){}.getType();
				Gson json=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
				if(result!=null&&!result.equals(null)&&!result.equals("")){
//					if(arr!=null){
//						arr.clear();
//					}
//					SceneryHomeActivity.f=false;
//				arr.addAll((List<PopularScene>)json.fromJson(result, type));
//				auto.setResultSize(arr.size()); 
				Message msg = handler.obtainMessage();
				msg.what = what;
				msg.obj = (List<PopularScene>)json.fromJson(result, type);
				handler.sendMessage(msg);
				msg.arg1=AutoListView.HOT;
				}
			}
		});
     }
     
     
  // String urlPlaza="http://10.201.1.12:8080/travel/SharePicture_remeng";
     String urlPlaza="http://10.201.1.12:8080/travel/SharePicture_guangchang";
     /**
      * 请求广场的数据
      */
     public void sendPlazaData(final int what){
    	 http.configCurrentHttpCacheExpiry(0*1000);
    	 RequestParams params=new RequestParams();
    	 if(what==0){
    		 plaza=1;
    		 params.addBodyParameter("page",String.valueOf(plaza));
    	 }else if(what==1){
    		 plaza++;
    		 params.addBodyParameter("page",String.valueOf(plaza));
    	 }
    	 if(m.getLd()!=null){
    		 params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
    	 }
    	 http.send(HttpMethod.POST, urlPlaza, params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result=arg0.result;
				Type type=new TypeToken<List<PopularScene>>(){}.getType();
				Gson json=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
				if(!result.equals(null)){
//					if(arr1!=null){
//						arr1.clear();
//					}
//					if(adapterP==null){
						SceneryHomeActivity.f=false;
//						arr1.addAll((List<PopularScene>)json.fromJson(result, type));
//						adapterP =new PlazaListViewAdapt(context,arr1);
//					}else{
//						SceneryHomeActivity.f=true;
//					}
					
					Message msg = handler.obtainMessage();
					msg.what = what;
					msg.obj = (List<PopularScene>)json.fromJson(result, type);
					handler.sendMessage(msg);
					msg.arg1=AutoListView.PLAZA;
//				auto.setResultSize(listmap.size()); 
//				lv.get(1).setAdapter(adapterP);
				}
			}
		});
     }
     
  // String urlSeason="http://10.201.1.12:8080/travel/SharePicture_remeng";
     String urlSeason="http://10.201.1.12:8080/travel/SharePicture_guanzhu";
     /**
      * 请求关注的数据
      */
     public void sendSeasonData(final int what){
    	 http.configCurrentHttpCacheExpiry(0*1000);
    	 RequestParams params=new RequestParams();
    	 if(what==0){
    		 season=1;
    		 params.addBodyParameter("page",String.valueOf(season));
    	 }else if(what==1){
    		 season++;
    		 params.addBodyParameter("page",String.valueOf(season));
    	 }
    	 if(m.getLd()!=null){
    		 params.addBodyParameter("ld",String.valueOf(m.getLd().getLd()));
    	 }
    	 http.send(HttpMethod.POST, urlSeason, params,new RequestCallBack<String>() {

 			@Override
 			public void onFailure(HttpException arg0, String arg1) {
 				// TODO Auto-generated method stub
 				Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
 			}

 			@Override
 			public void onSuccess(ResponseInfo<String> arg0) {
 				String result=arg0.result;
				Type type=new TypeToken<List<PopularScene>>(){}.getType();
				Gson json=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
				if(!result.equals(null)){
//					if(arr2!=null){
//						arr2.clear();
//					}
					SceneryHomeActivity.f=false;
//				arr2.addAll((List<PopularScene>)json.fromJson(result, type));
//				auto.setResultSize(listmap.size()); 
				Message msg = handler.obtainMessage();
				msg.what = what;
				msg.obj = (List<PopularScene>)json.fromJson(result, type);
				handler.sendMessage(msg);
				msg.arg1=AutoListView.SEASON;
				}
 			}
    	});
     }
     public void setThis(SceneryHomeActivity t){
    	 this.t=t;
    	 
     }
     public int getPage(){
    	 return page;
     }
// 	@Override
// 	public void onRefresh() {
// 		sendPlazaData(AutoListView.REFRESH);
// 	}
//
// 	@Override
// 	public void onLoad() {
// 		sendPlazaData(AutoListView.LOAD);
// 	}
     
}
