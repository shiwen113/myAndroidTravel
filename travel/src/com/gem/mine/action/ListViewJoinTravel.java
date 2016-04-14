package com.gem.mine.action;

import java.lang.reflect.Type;
import java.util.List;

import com.gem.home.until.PublishTravel;
import com.gem.scenery.R;
import com.gem.scenery.entity.PTPD;
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

public class ListViewJoinTravel extends BaseAdapter {

	private List<PTPD> joinList;
	private Context context;
	private Holder holder;
	private BitmapUtils bu;
	public ListViewJoinTravel(List<PTPD> joinList, Context context) {
		super();
		this.joinList = joinList;
		this.context = context;
		bu=new BitmapUtils(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return joinList.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return joinList.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int arg0, View v, ViewGroup arg2) {
		if(v==null){
			v=LayoutInflater.from(context).inflate(R.layout.activity_my_travel_team_name, null);
			holder=new Holder();
			holder.civ=(ImageView) v.findViewById(R.id.civ_picture);
			holder.tv=(TextView) v.findViewById(R.id.tv_my_all_travel_team);
			v.setTag(holder);
		}else{
			holder=(Holder) v.getTag();
		}
		PublishTravel pt=joinList.get(arg0).getPt();
		if(pt!=null){
			holder.tv.setText(pt.getTeamName());
		
//			sendPersonalData(pt);
		}
		String string=joinList.get(arg0).getUriUpLoadPicture();
		if(string!=null&&!string.equals("")){
			bu.display(holder.civ,"http://10.201.1.12:8080/gotravel/UserImage/"+string);
		}
		return v;
	}

	public static class Holder{
		ImageView civ;
		TextView tv;
	}
//	String url="http://10.201.1.12:8080/travel/Home_home_yhtx";
//	/**
//	 * 请求个人资料
//	 */
//	public void sendPersonalData(PublishTravel pt){
//		HttpUtils http =new HttpUtils();
//		RequestParams params =new RequestParams();
//		params.addBodyParameter("ld",String.valueOf(pt.getLd().getLd()));
//		http.send(HttpMethod.POST, url, params, new RequestCallBack<String>() {
//
//			@Override
//			public void onFailure(HttpException arg0, String arg1) {
//				// TODO Auto-generated method stub
//				Toast.makeText(context, "请求失败,请检查您的网络", Toast.LENGTH_LONG).show();
//			}
//
//			@Override
//			public void onSuccess(ResponseInfo<String> arg0) {
//				String result =arg0.result;
//				if(!result.equals("")){
//					Gson gson=new Gson();
//					Type type =new TypeToken<PersonalData>(){}.getType();
//					PersonalData pd=gson.fromJson(result, type);
//					bu.display(holder.civ, "http://10.201.1.12:8080/gotravel/UserImage/"+pd.getUriUpLoadPicture());
//				}
//			}
//		});
//		
//	}

}
