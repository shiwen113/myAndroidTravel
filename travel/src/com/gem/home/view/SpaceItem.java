package com.gem.home.view;

import android.graphics.Rect;
import android.net.NetworkInfo.State;
import android.support.v7.widget.RecyclerView;
import android.view.View;
//recyclerview中item间空间绘制
public class SpaceItem extends RecyclerView.ItemDecoration{
private int space;
public SpaceItem (int space) {
	this.space=space;
}
@Override
	public void getItemOffsets(Rect outRect, View view, RecyclerView parent,
			android.support.v7.widget.RecyclerView.State state) {
		// TODO Auto-generated method stub
		super.getItemOffsets(outRect, view, parent, state);
		   if(parent.getChildPosition(view) != 0)  
               outRect.top = space;  
	}
}
