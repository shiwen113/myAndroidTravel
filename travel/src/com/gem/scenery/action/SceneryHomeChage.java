package com.gem.scenery.action;

import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

public class SceneryHomeChage implements OnPageChangeListener{
	 private int offset;// 动画图片偏移量
	 private int bmpW;// 动画图片宽度
	 private int currIndex;// 当前页卡编号
	 private ViewPager vp;//叶卡内容
	 private ImageView imageView;// 动画图片
	
	 int one = offset * 2 + bmpW;// 页卡1 -> 页卡2 偏移量
     int two = offset * 3;// 页卡1 -> 页卡3 偏移量
     
     public SceneryHomeChage(int offset, int bmpW, int currIndex, ViewPager vp,
			ImageView imageView) {
		this.offset = offset;
		this.bmpW = bmpW;
		this.currIndex = currIndex;
		this.vp = vp;
		this.imageView = imageView;
	}

	@Override
     public void onPageScrollStateChanged(int arg0) {
//		arg0 ==1的时候表示正在滑动，arg0==2的时候表示滑动完毕了，arg0==0的时候表示什么都没做，就是停在那。
     }
     
     @Override
     public void onPageScrolled(int arg0, float arg1, int arg2) {
//    	 表示在前一个页面滑动到后一个页面的时候，在前一个页面滑动前调用的方法。
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
         Animation animation = null;
         switch (arg0) {
         case 0:
             if (currIndex == 1) {
                 animation = new TranslateAnimation(one, 0, 0, 0);
             } else if (currIndex == 2) {
                 animation = new TranslateAnimation(two, 0, 0, 0);
             }
             break;
         case 1:
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
             break;
              
         }
         //代替上面的方法
//         Animation animation = new TranslateAnimation(one*currIndex, two*arg0, 0, 0);//显然这个比较简洁，只有一行代码。
         
         currIndex = arg0;
         animation.setFillAfter(true);// True:图片停在动画结束位置
         animation.setDuration(300);
         imageView.startAnimation(animation);
         
     }
      
}
