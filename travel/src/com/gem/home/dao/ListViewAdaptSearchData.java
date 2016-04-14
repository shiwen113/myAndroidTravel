package com.gem.home.dao;

import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout.LayoutParams;

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

public class ListViewAdaptSearchData extends BaseAdapter {
	private List<PublishTravel> list;//查询结果
	private Context context;
	private MyRecyclerViewHolder holder;
	private ImageLoader imageloader = ImageLoader.getInstance();
	
	public ListViewAdaptSearchData(List<PublishTravel> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if(list!=null){
		return list.size();
		}else{
			return 0;
		}
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return list.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View v, ViewGroup arg2) {
		if(v==null){
			v=LayoutInflater.from(context).inflate(R.layout.item, null);
			holder=new MyRecyclerViewHolder(v);
			v.setTag(holder);
		}else{
			holder =(MyRecyclerViewHolder) v.getTag();
		}
		PublishTravel pt=list.get(arg0);
		if(pt!=null){
			holder.teamName.setText("旅行队"+pt.getTeamName());
			
			holder.intro.setText(pt.getIntro());
			holder.allDay.setText("全程" + String.valueOf(pt.getAllDay()) + "天");
			holder.place.setText(pt.getStatPoint() + "--" + pt.getDestination());
			if (pt.getSex() == 1) {
				holder.limitSex.setText("性别限制:男");
			} else if (pt.getSex() == 0) {
				holder.limitSex.setText("性别限制:女");
			}else if (pt.getSex() == 2) {
				holder.limitSex.setText("性别限制:不限");
			}
			holder.gotime.setText(ToolDao.setTimedate1(pt.getStartTime()));
			holder.chakanliang.setText("点击量："+pt.getToView());
			String url =pt.getUrlLifePicture();
			sendPictureFromServer(holder,url);
			sendUserPicture( pt,holder);
		}
		return v;
	}
	
	/**
	 * 获取生活照
	 */
	public void sendPictureFromServer(MyRecyclerViewHolder arg0,String lifPicture){
		String[] picture=lifPicture.split(";");
		BitmapUtils bpu=new BitmapUtils(context);
		arg0.ll.removeAllViews();
		float scale = context.getResources().getDisplayMetrics().density;
		int px = (int) (scale * 100 + 0.5f); 
		for(int i=0;picture.length>i;i++){
			String url=picture[i];
			ImageView iv=new ImageView(context);
			iv.setPadding(0, 0, 10, 0);
			imageloader.displayImage(url, iv);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(px, px);
		    iv.setLayoutParams(layoutParams);
		    iv.setScaleType(ScaleType.CENTER_CROP);
			bpu.display(iv,"http://10.201.1.12:8080/gotravel/"+"TravelTeam/"+url);
			arg0.ll.addView(iv);
		}
	}
	
	String urlU="http://10.201.1.12:8080/travel/Home_home_yhtx";
	/**
	 * 获取用户图片
	 */
	public void sendUserPicture(final PublishTravel pt,final MyRecyclerViewHolder a){
		RequestParams params = new RequestParams();
		HttpUtils http=new HttpUtils();
		http.configCurrentHttpCacheExpiry(0*1000);
	 	params.addBodyParameter("ld",String.valueOf(pt.getLd().getLd()));
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
				if(!result.equals(null)&&!result.equals("")){
					Gson gson =new Gson();
					Type type =new TypeToken<PersonalData>(){}.getType();
					PersonalData pd=gson.fromJson(result, type);
					Log.i("result", result);
					int sex=pd.getSex();
					if(sex==1){
						holder.userName.setText(pt.getLd().getUserName()+":男,"+pd.getAge()+"岁");
					}else if(sex==0){
						holder.userName.setText(pt.getLd().getUserName()+":女,"+pd.getAge()+"岁");
					}
					new MyImageAsyncTask(a).execute("http://10.201.1.12:8080/gotravel/UserImage/"+pd.getUriUpLoadPicture());
				}else{
					holder.imageView.setBackgroundResource(R.drawable.pic_duckr);
				}
			}
		});
	}
}
