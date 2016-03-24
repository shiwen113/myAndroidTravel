package com.gem.scenery.utils;

import com.gem.scenery.R;
import com.gem.scenery.interfaces.OnLoadListener;
import com.gem.scenery.interfaces.OnRefreshListener;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ProgressBar;
import android.widget.TextView;

public class AutoListView extends ListView implements OnScrollListener{
	 // 区分当前操作是刷新还是加载  
    public static final int REFRESH = 0;//刷新  
    public static final int LOAD = 1;//加载
  
    // 区分PULL和RELEASE的距离的大小  
    private static final int SPACE = 20;  
  
    // 定义header的四种状态和当前状态  
    private static final int NONE = 0; //无状态 
    private static final int PULL = 1; //拉动状态 
    private static final int RELEASE = 2;  //释放状态
    private static final int REFRESHING = 3;//恢复状态  
    private int state; //当前状态 
  
    private LayoutInflater inflater;  
    private View header;//下拉头 
    private View footer; //加载头
    private TextView tip;  
    private TextView lastUpdate;//最后更新时间  
    private ImageView arrow;//箭头图片  
    private ProgressBar refreshing;//刷新进度（圆环）  
  
    private TextView noData;  
    private TextView loadFull;  
    private TextView more;  
    private ProgressBar loading;//加载进度 （圆环）
  
    private RotateAnimation animation;//旋转变化动画类(没拉完)  
    private RotateAnimation reverseAnimation; //拉完状态时
  
    private int startY;//起始y坐标  
  
    private int firstVisibleItem;//第一个Item  
    private int scrollState;//滚动状态  
    private int headerContentInitialHeight;//
    private int headerContentHeight;//头部的高度   
    
    // 只有在listview第一个item显示的时候（listview滑到了顶部）才进行下拉刷新， 否则此时的下拉只是滑动listview  
    private boolean isRecorded;//判断是否有记录  
    private boolean isLoading;// 判断是否正在加载  
    private boolean loadEnable = true;// 开启或者关闭加载更多功能  
    private boolean isLoadFull; //判断是否装满 
    private int pageSize = 10;//显示个数  
  
    private OnRefreshListener onRefreshListener;//自定义下拉刷新接口  
    private OnLoadListener onLoadListener; //自定义上拉加载接口
	public AutoListView(Context context) {
		super(context);
		init(context);
	}

	public AutoListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	public AutoListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	
	/**
	 * 监听手势事件
	 */
	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		Log.i("AutoListView","onTouchEvent");
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN://手指按下是
			if(firstVisibleItem==0){
				Log.i("AutoListView", "MotionEvent.ACTION_DOWN："+MotionEvent.ACTION_DOWN);
				isRecorded=true;
				//获取y轴的起始坐标
				startY=(int) ev.getY();
				Log.i("AutoListView", "Y的起始坐标："+startY);
			}
			break;
			// 用户无规则的操作时可能触发. 此操作用于表明,
			//一个触摸序列在未发生任何实际操作的情况下结束.
		case MotionEvent.ACTION_CANCEL:
			Log.i("AutoListView", "MotionEvent.ACTION_CANCEL："+MotionEvent.ACTION_CANCEL);
			//手指放开时
		case MotionEvent.ACTION_UP:
			if(state==PULL){
				state=NONE;
				refreshHeaderViewByState();
				Log.i("AutoListView", "MotionEvent.ACTION_UP："+MotionEvent.ACTION_UP);
			}else if(state==RELEASE){
				state=REFRESHING;
				refreshHeaderViewByState();
				onRefresh();
			}
			isRecorded=false;
			break;
		case MotionEvent.ACTION_MOVE:
			//解读手势，刷新header的状态
			if(!isRecorded){
				return true;
			}
			//获得当前状态的y
			int tmpY=(int) ev.getY();
			//移动的距离
			int space=tmpY-startY;
			//表示头部还有多少没有移出来
			int topPadding=space-headerContentHeight;
			switch (state) {
			case NONE:
				if(space>0){//说明在拉动
					state=PULL;//改变为拉动状态
					refreshHeaderViewByState();
				}
				break;
			case PULL:
				 topPadding(topPadding);//拉动设置header控件值
				 if (scrollState == SCROLL_STATE_TOUCH_SCROLL//用户使用触摸滚动,手指仍在屏幕上 
		                    && space > headerContentHeight + SPACE) {
					 state=RELEASE;//设置成释放状态
					 refreshHeaderViewByState();
				 }
				break;
			case RELEASE:  
	            topPadding(topPadding);  
	            if (space > 0 && space < headerContentHeight + SPACE) {  
	                state = PULL;  
	                refreshHeaderViewByState();  
	            } else if (space <= 0) {  
	                state = NONE;  
	                refreshHeaderViewByState();  
	            }  
	            break;  
			default:
				break;
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {
		Log.i("AutoListView","onScrollStateChanged");
		//获取滚动状态
		this.scrollState=scrollState;
		//如果需要加载
		ifNeedLoad(view, scrollState);
	}
 
	/**
	 * 滚动监听获取第一个Item
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		Log.i("AutoListView","onScroll");
		this.firstVisibleItem=firstVisibleItem;
	}
    /**
     * 调整header的大小。其实调整的只是距离顶部的高度。  
     * @param topPadding
     */
    private void topPadding(int topPadding) {  
        header.setPadding(header.getPaddingLeft(), topPadding,  
                header.getPaddingRight(), header.getPaddingBottom());  
        header.invalidate();  
    } 
	/**
	 * 初始化
	 * @param context
	 */
	public void init(Context context){
		animation=new RotateAnimation(0, 
				180, 
				RotateAnimation.RELATIVE_TO_SELF, 
				0.5f, 
				RotateAnimation.RELATIVE_TO_SELF, 
				0.5f);
		animation.setInterpolator(new LinearInterpolator());//设置补间器，控制动画的变化速率
		animation.setDuration(100);//设置显示视图的时间
		//如果fillAfter是真的,这个动画的转换完成时将持续执行。默认为错误的如果不设置
		animation.setFillAfter(true);
		
		reverseAnimation=new RotateAnimation(-180, 0,  
                RotateAnimation.RELATIVE_TO_SELF, 0.5f,  
                RotateAnimation.RELATIVE_TO_SELF, 0.5f);  
		reverseAnimation.setInterpolator(new LinearInterpolator());
		reverseAnimation.setDuration(100);
		reverseAnimation.setFillAfter(true);
		
		inflater=LayoutInflater.from(context);//获得解析对象
		//解析listveiw_footer文件，加载头
		footer = inflater.inflate(R.layout.listview_footer, null);  
		//此空间表示加载满了
	    loadFull = (TextView) footer.findViewById(R.id.loadFull);  
	    //没有数据
	    noData = (TextView) footer.findViewById(R.id.noData);  
	    //更多
	    more = (TextView) footer.findViewById(R.id.more);  
	    //正在加载
	    loading = (ProgressBar) footer.findViewById(R.id.loading);  
		//找头部刷新界面
	    header=inflater.inflate(R.layout.pull_to_refresh_header, null);
	    //箭头控件
	    arrow = (ImageView) header.findViewById(R.id.arrow);  
        tip = (TextView) header.findViewById(R.id.tip); 
        //最后更新
        lastUpdate = (TextView) header.findViewById(R.id.lastUpdate);  
        //刷新
        refreshing = (ProgressBar) header.findViewById(R.id.refreshing);  
        // 为listview添加头部和尾部，并进行初始化  
        headerContentInitialHeight = header.getPaddingTop();  
        // 用来计算header大小的。
        measureView(header); //获得控件的高度的 
        headerContentHeight = header.getMeasuredHeight();  
        // 调整header的大小。其实调整的只是距离顶部的高度。 
//        topPadding(-headerContentHeight);  
        this.addHeaderView(header);  
        this.addFooterView(footer);  
        this.setOnScrollListener(this);
	}
	/**
	 * 回调刷新方法
	 */
	public void onRefresh(){
		if(onRefreshListener!=null){
			onRefreshListener.onRefresh();
		}
	}
	/**
	 * 回调加载方法
	 */
	 public void onLoad() {  
	        if (onLoadListener != null) {  
	            onLoadListener.onLoad();  
	        }  
	    }
	/**
	 * 下拉刷新完成的时间
	 * @param updateTime
	 */
	 public void onRefreshComplete(String updateTime){
		 lastUpdate.setText(this.getContext().getString(R.string.lastUpdateTime,  
	                lastUpdate));  
		 	//改变状态为None
	        state = NONE;  
	        refreshHeaderViewByState(); 
	 }
	 /** 
	     * 这个方法是根据结果的大小来决定footer显示的。 
	     * <p> 
	     * 这里假定每次请求的条数为10。如果请求到了10条。则认为还有数据。如过结果不足10条，则认为数据已经全部加载，这时footer显示已经全部加载 
	     * </p> 
	     *  
	     * @param resultSize 
	     */  
	 public void setResultSize(int resultSize) {
			if (resultSize == 0) {
				isLoadFull = true;
				loadFull.setVisibility(View.GONE);
				loading.setVisibility(View.GONE);
				more.setVisibility(View.GONE);
				noData.setVisibility(View.VISIBLE);
			} else if (resultSize > 0 && resultSize < pageSize) {
				isLoadFull = true;
				loadFull.setVisibility(View.VISIBLE);
				loading.setVisibility(View.GONE);
				more.setVisibility(View.GONE);
				noData.setVisibility(View.GONE);
			} else if (resultSize == pageSize) {
				isLoadFull = false;
				loadFull.setVisibility(View.GONE);
				loading.setVisibility(View.VISIBLE);
				more.setVisibility(View.VISIBLE);
				noData.setVisibility(View.GONE);
			}

		}

	 
	 /**
	  * 用于下拉刷新结束后的回调  ,获取下拉刷新完成的时间
	  */
	   public void onRefreshComplete() {  
	        String currentTime = Utils.getCurrentTime();  
	        onRefreshComplete(currentTime);  
	    } 
	 
	   // 用于加载更多结束后的回调  
	   public void onLoadComplete() {  
		   //设置不是加载状态
	        isLoading = false;  
	    } 
	 
	   private void ifNeedLoad(AbsListView view, int scrollState) {  
		   if(!loadEnable){//关闭加载更多功能
			   return;
		   }
		   try {  
			   /*
			    * 如果没有滚动，并且也不是正在加载
			    */
	            if (scrollState == OnScrollListener.SCROLL_STATE_IDLE  
	                    && !isLoading  
	                    && view.getLastVisiblePosition() == view  
	                            .getPositionForView(footer) && !isLoadFull) {  
	            	//加载
	                onLoad(); 
	                //设置成正在加载状态
	                isLoading = true;  
	            }  
	        } catch (Exception e) {  
	        }  
	   }
	   /**
	    * 根据当前状态调整header
	    */
	 private void refreshHeaderViewByState() {
		 switch (state) {  
	        case NONE:  
	            topPadding(-headerContentHeight);  
	            tip.setText(R.string.pull_to_refresh);  
	            refreshing.setVisibility(View.GONE);  
	            arrow.clearAnimation();  
	            arrow.setImageResource(R.drawable.common_listview_headview_red_arrow);  
	            break;  
	        case PULL:  
	            arrow.setVisibility(View.VISIBLE);  
	            tip.setVisibility(View.VISIBLE);  
	            lastUpdate.setVisibility(View.VISIBLE);  
	            refreshing.setVisibility(View.GONE);  
	            tip.setText(R.string.pull_to_refresh);  
	            arrow.clearAnimation();  
	            arrow.setAnimation(reverseAnimation);  
	            break;  
	        case RELEASE:  
	            arrow.setVisibility(View.VISIBLE);  
	            tip.setVisibility(View.VISIBLE);  
	            lastUpdate.setVisibility(View.VISIBLE);  
	            refreshing.setVisibility(View.GONE);  
	            tip.setText(R.string.pull_to_refresh);  
	            tip.setText(R.string.release_to_refresh);  
	            arrow.clearAnimation();  
	            arrow.setAnimation(animation);  
	            break;  
	        case REFRESHING:  
	            topPadding(headerContentInitialHeight);  
	            refreshing.setVisibility(View.VISIBLE);  
	            arrow.clearAnimation();  
	            arrow.setVisibility(View.GONE);  
	            tip.setVisibility(View.GONE);  
	            lastUpdate.setVisibility(View.GONE);  
	            break;  
	        }  
		
	}

	   // 用来计算header大小的。比较隐晦。  
	    private void measureView(View child) {  
	        ViewGroup.LayoutParams p = child.getLayoutParams();  
	        if (p == null) {  
	            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,  
	                    ViewGroup.LayoutParams.WRAP_CONTENT);  
	        }  
	        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);  
	        int lpHeight = p.height;  
	        int childHeightSpec;  
	        if (lpHeight > 0) {  
	            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,  
	                    MeasureSpec.EXACTLY);  
	        } else {  
	            childHeightSpec = MeasureSpec.makeMeasureSpec(0,  
	                    MeasureSpec.UNSPECIFIED);  
	        }  
	        child.measure(childWidthSpec, childHeightSpec);  
	    }  
	  
	// 下拉刷新监听  
    public void setOnRefreshListener(OnRefreshListener onRefreshListener) {  
        this.onRefreshListener = onRefreshListener;  
    }  
  
    // 加载更多监听  
    public void setOnLoadListener(OnLoadListener onLoadListener) {  
        this.loadEnable = true;  
        this.onLoadListener = onLoadListener;  
    }  
    //是否能够加载
    public boolean isLoadEnable() {  
        return loadEnable;  
    }  
  
    // 这里的开启或者关闭加载更多，并不支持动态调整  
    public void setLoadEnable(boolean loadEnable) {  
        this.loadEnable = loadEnable;  
        this.removeFooterView(footer);  
    }  
    
    
    
    
    
    
  
    public int getPageSize() {  
        return pageSize;  
    }  
  
    public void setPageSize(int pageSize) {  
        this.pageSize = pageSize;  
    }  

}
