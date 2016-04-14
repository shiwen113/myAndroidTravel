package com.gem.mine.action;

import java.lang.reflect.Type;
import java.util.List;

import com.gem.home.until.PublishTravel;
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

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MycollectAdapter extends BaseAdapter {
    Context context;
    List<PublishTravel> arr;
    
	
	public MycollectAdapter(Context context, List<PublishTravel> arr) {
		super();
		this.context = context;
		this.arr = arr;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return arr.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return arr.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder =null;
		if(convertView==null){
		viewHolder=new ViewHolder();
		convertView=LayoutInflater.from(context).inflate(R.layout.activity_mycollect_item,null);
		viewHolder.imageView=(ImageView) convertView.findViewById(R.id.iv_lvxingdui_shoucang);
		viewHolder.textView=(TextView) convertView.findViewById(R.id.tv_lvxingdui_shoucang);
		convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder) convertView.getTag();
		}
		PublishTravel pt =arr.get(position);
		viewHolder.textView.setText(pt.getTeamName());
		select(position, viewHolder.imageView);
//		viewHolder.imageView.setImageBitmap(bm);
		return convertView;
	}

	static class ViewHolder{
		ImageView imageView;
		TextView textView;
	}
	
	public void  select(int position,final ImageView view){
//		final PersonalData ld = null;
		int id=arr.get(position).getLd().getLd();
		String url="http://10.201.1.12:8080/travel/Home_home_yhtx";
		HttpUtils utils=new HttpUtils();
		RequestParams params=new RequestParams();
		params.addBodyParameter("ld",String.valueOf(id));
		utils.send(HttpMethod.POST, url,params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(context, "连接失败,请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result=arg0.result;
				if(result!=null&&!result.equals("")){
					Gson gson=new Gson();
					Type type =new TypeToken<PersonalData>(){}.getType();
                    
					PersonalData ld=gson.fromJson(result,type);	
					BitmapUtils bitmapUtils=new BitmapUtils(context);
					bitmapUtils.display(view,"http://10.201.1.12:8080/gotravel/UserImage/"+ld.getUriUpLoadPicture());
//					Type type=new TypeToken<List<PublishTravel>>(){}.getType();
//                    arr=gson.fromJson(result,type);
//                    adapter.notifyDataSetChanged();
				}
				
			}
		});	
	}
}
