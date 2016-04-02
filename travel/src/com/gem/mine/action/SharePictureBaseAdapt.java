package com.gem.mine.action;

import java.lang.reflect.Type;
import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Toast;

import com.gem.home.until.ToolDao;
import com.gem.scenery.R;
import com.gem.scenery.action.PicturePage;
import com.gem.scenery.action.PicturePage.ViewHolder;
import com.gem.scenery.entity.PersonalData;
import com.gem.scenery.entity.SharePicture;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class SharePictureBaseAdapt extends BaseAdapter {
	private List<SharePicture> list;
	private Context context;
	private ViewHolder holder;
	public SharePictureBaseAdapt(List<SharePicture> list, Context context) {
		super();
		this.list = list;
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
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
	public View getView(int position, View v, ViewGroup arg2) {
		final SharePicture sp=list.get(position);
		if(v==null){
			holder = new ViewHolder();
			v=LayoutInflater.from(context).inflate(R.layout.action_scenery_jinse, null);
			PicturePage pp=new PicturePage(v,holder);
			pp.initView();
			v.setTag(holder);
		} else {
			holder = (ViewHolder) v.getTag();
		}
		if(sp!=null&&!sp.equals("")){
			//数据显示
			holder.travelName.setText("旅行队："+sp.getTd().getTeamName());
			holder.travelPoint.setText("景点："+sp.getViewPoint());
			holder.issuePicture.setText("发表时间："+ToolDao.setTimedate1(sp.getTime()));
			holder.userName.setText("用户名："+sp.getLd().getUserName());
//			holder.numberPinglun.setText();
			holder.fenxiang.setText(sp.getFeeling());
		}
		
		if(!sp.getViewPoint().equals("")){
			sendPicture(sp.getUrlPhotos());
		}
//		holder.
		return v;
	}
	
	/**
	 * 获取旅游图片
	 */
	public void sendPicture(final String urlP){
		BitmapUtils bu=new BitmapUtils(context);
		bu.display(holder.picture, urlP);
	}
	String urlU="http://10.201.1.12:8080/travel/Home_home_yhtx";
	/**
	 * 获取用户图片
	 */
	public void sendUserPicture(int ld){
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
					BitmapUtils bpu=new BitmapUtils(context);
					bpu.display(holder.userPicture, pd.getUriUpLoadPicture());
				}
			}
		});
	}

}
