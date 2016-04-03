package com.gem.home.dao;


import java.lang.reflect.Type;
import java.util.List;


import com.gem.home.until.PublishTravel;
import com.gem.home.until.ToolDao;
import com.gem.scenery.R;
import com.gem.scenery.entity.PersonalData;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.nostra13.universalimageloader.core.ImageLoader;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.view.ViewGroup;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewHolder> {
	private OnItemClickLitener mOnItemClickListener;
	private List<PublishTravel> arr;
	private Context context;
	private static final int TYPE_ITEM = 0;
	private ImageLoader imageloader = ImageLoader.getInstance();

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
		
		return arr.size();
	}
	/**
	 * 获取生活照
	 */
	public void sendPictureFromServer(MyRecyclerViewHolder arg0,List<PublishTravel> arr,int arg1){
		String lifPicture =arr.get(arg1).getUrlLifePicture();
		String[] picture=lifPicture.split(";");
		Log.i("test", picture.length + "");
		BitmapUtils bpu=new BitmapUtils(context);
		arg0.ll.removeAllViews();
		for(int i=0;picture.length>i;i++){
			String url=picture[i];
			ImageView iv=new ImageView(context);
//			imageloader.displayImage(url, iv);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500, 500);
		    iv.setLayoutParams(layoutParams);
		    iv.setScaleType(ScaleType.CENTER_INSIDE);
		    bpu.display(iv,"http://10.201.1.12:8080/gotravel/"+"TravelTeam/"+url);
			arg0.ll.addView(iv);
		}
	}
	String urlU="http://10.201.1.12:8080/travel/Home_home_yhtx";
	/**
	 * 获取用户图片
	 */
	public void sendUserPicture(int ld,final MyRecyclerViewHolder a){
		RequestParams params = new RequestParams();
		HttpUtils http=new HttpUtils();
	 	params.addBodyParameter("ld",String.valueOf(ld));
		http.send(HttpMethod.POST, urlU, params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "请求失败，请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result =arg0.result;
				if(!result.equals(null)&&result!=null){
					Gson gson =new Gson();
					Type type =new TypeToken<PersonalData>(){}.getType();
					PersonalData pd=gson.fromJson(result, type);
					new MyImageAsyncTask(a).execute(pd.getUriUpLoadPicture());
				}
			}
		});
	}
	@Override
	public void onBindViewHolder(final MyRecyclerViewHolder arg0, int arg1) {
		Log.i("onBindViewHolder", "teamname+set");
		

		 if (arg0 instanceof MyRecyclerViewHolder) {
	           // ((MyRecyclerViewHolder) arg0).tv.setText(arr.get(arg1));
	       Log.i("2016.3.24", "set");
		arg0.teamName.setText(arr.get(arg1).getTeamName());
		arg0.intro.setText(arr.get(arg1).getIntro());
		arg0.allDay.setText("全程" + String.valueOf(arr.get(arg1).getAllDay()) + "天");
		arg0.place.setText(arr.get(arg1).getStatPoint() + "--" + arr.get(arg1).getDestination());
		arg0.userName.setText(arr.get(arg1).getLd().getUserName());
		arg0.gotime.setText("出发时间:"+ToolDao.setTimedate1((arr.get(arg1).getStartTime())));
		arg0.chakanliang.setText("点击量："+arr.get(arg1).getToView());
//		String url = arr.get(arg1).getUrlLifePicture();
//		Log.i("myurl", "myurl:"+url);
//		new MyImageAsyncTask(arg0).execute(url);
		sendPictureFromServer(arg0,arr,arg1);
		sendUserPicture(arr.get(arg1).getLd().getLd(),arg0);
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
