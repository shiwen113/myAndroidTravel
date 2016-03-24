package com.gem.home.dao;


import java.util.List;


import com.gem.home.until.PublishTravel;
import com.gem.scenery.R;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;
import android.view.ViewGroup;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {
	private OnItemClickLitener mOnItemClickListener;
	private List<PublishTravel> arr;
	private Context context;
	private static final int TYPE_ITEM = 0;

	public MyRecyclerViewAdapter(List<PublishTravel> arr, Context context) {
		super();
		this.arr = arr;
		this.context = context;
	}

	public void setOnItemClickListener(OnItemClickLitener mOnItemClickLitener) {
		this.mOnItemClickListener = mOnItemClickLitener;
	}

	@Override
	public int getItemCount() { // TODO Auto-generated method stub
		Log.i("fuck", "getitemcount");
		Log.i("2016", "arr.size()"+arr.size());
		return arr.size();
	}

	@Override
	public void onBindViewHolder(final MyRecyclerViewHolder arg0, int arg1) {
		Log.i("onBindViewHolder", "teamname+set");
		

		 if (arg0 instanceof MyRecyclerViewHolder&&arg1!=0) {
	           // ((MyRecyclerViewHolder) arg0).tv.setText(arr.get(arg1));
	       Log.i("2016.3.24", "set");
		arg0.teamName.setText(arr.get(arg1).getTeamName());
		
		arg0.intro.setText(arr.get(arg1).getIntro());
		arg0.allDay.setText("全程" + String.valueOf(arr.get(arg1).getAllDay()) + "天");
		arg0.place.setText(arr.get(arg1).getStatPoint() + "--" + arr.get(arg1).getDestination());
		arg0.userName.setText(arr.get(arg1).getLd().getUserName());
		String url = arr.get(arg1).getUrlLifePicture();
		new MyImageAsyncTask(arg0).execute(url);
		 }
		
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
		
		Log.i("godie", "onBindViewHolder");
	}

	@Override
	public MyRecyclerViewHolder onCreateViewHolder(ViewGroup arg0, int arg1) {

		

		  if (arg1== TYPE_ITEM) {
			  
	            View view = LayoutInflater.from(context).inflate(R.layout.item, arg0,false);
	            view.setLayoutParams(new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT));
	            return new MyRecyclerViewHolder(view);
	        }
      
	        return null;
	}
		
	

}
