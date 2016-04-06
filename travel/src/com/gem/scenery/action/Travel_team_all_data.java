package com.gem.scenery.action;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import android.R.string;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.home.dao.MyApplication;
import com.gem.home.until.LoginData;
import com.gem.home.until.ToolDao;
import com.gem.scenery.R;
import com.gem.scenery.entity.PersonalData;
import com.gem.scenery.entity.PictureComment;
import com.gem.scenery.entity.PopularScene;
import com.gem.scenery.entity.SharePicture;
import com.gem.travel.raw.PropertiesUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Travel_team_all_data extends Activity implements OnClickListener{
	private TextView travelName;//队名
	private TextView travelPoint;//景点
	private TextView issuePicture;//发布时间
	private ImageView picture;//发表的旅图
	private ImageView userPicture;//用户头像
	private TextView userName;//用户名
	private TextView number;//点赞次数
	private ImageButton dainzan;//点赞
	private ImageView back;//返回
	private ListView lv;
	private EditText input;//输入值
	private Button send;//发送发送
	private SharePicture sp;
	private int num;//点赞量
	private int state;//点赞状态
	private List<PictureComment> list;
	private String text;//获取的内容
	private PictureComment pc;
	private BaseAdapter adapter;
	private PopularScene ps;
	private MyApplication m;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.travel_picture_particulars);
		m=(MyApplication) getApplicationContext();
		initView();
		Intent intent=getIntent();
		String string=intent.getStringExtra("scene");
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		ps=gson.fromJson(string, PopularScene.class);
//		sp=(SharePicture) intent.getSerializableExtra("sp");
//		num=intent.getIntExtra("num",0);
//		state=intent.getIntExtra("state", 0);
//		Log.i("state", "state"+state);
		sendShareComment(ps.getSp());
		showData();
		
	}
	
	public void initView(){
		travelName=(TextView)findViewById(R.id.tv_picture_comment);
		travelPoint=(TextView)findViewById(R.id.tv_point_picture_comment);
		issuePicture=(TextView)findViewById(R.id.iv_issue_picture_comment);
		picture=(ImageView) findViewById(R.id.iv_picture_picture_comment);
		userPicture=(ImageView)findViewById(R.id.iv_user_picture_comment);
		userName=(TextView)findViewById(R.id.tv_user_name_comment);
		number=(TextView)findViewById(R.id.Search_tv_Adapter_comment);
		dainzan=(ImageButton)findViewById(R.id.Search_ib_comment);
		back=(ImageView) findViewById(R.id.im_back_picture_all_data);
		back.setOnClickListener(this);
		lv=(ListView) findViewById(R.id.lv_comment_danji_pinglun);
		input=(EditText) findViewById(R.id.et_comment_input);
		send=(Button) findViewById(R.id.bt_fasong_comment);
		send.setOnClickListener(this);
	}

	public void showData(){
		//数据显示
		travelName.setText("旅行队："+ps.getSp().getTd().getTeamName());
		travelPoint.setText("景点："+ps.getSp().getViewPoint());
		issuePicture.setText("发表时间："+ToolDao.setTimedate1(ps.getSp().getTime()));
		userName.setText("用户名："+ps.getSp().getLd().getUserName());
		number.setText(String.valueOf(ps.getThumbUpNumber()));
		if(ps.getPhotoState()==0){
			dainzan.setBackgroundResource(R.drawable.praise);
		}else {
			dainzan.setBackgroundResource(R.drawable.praise_have);
		}
		 if(!ps.getUriUpLoadPicture().equals("")){
			 BitmapUtils bpu=new BitmapUtils(this);
			 bpu.display(userPicture, ps.getUriUpLoadPicture());
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.im_back_picture_all_data://返回
			finish();
			break;
		case R.id.bt_fasong_comment:
			text=input.getText().toString();
			if(text!=null){
			addComment(text);
			}else{
				Toast.makeText(getApplication(), "请输入", Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}
	 
//	String urlU=PropertiesUtil.getPropertiesURL(this)+"Sharepicture_pinglunchalu";
	String urlU="http://10.201.1.12:8080/travel/Sharepicture_pinglunchalu";
	/**
	 * 添加一条单级评论
	 */
	public void addComment(String text){
		RequestParams params = new RequestParams();
		HttpUtils http=new HttpUtils();
		pc=new PictureComment();
//		LoginData ld=new LoginData();
//		ld.setLd(17);
		
		pc.setLd(m.getLd());
		pc.setCommentNotes(text);
		pc.setSp(ps.getSp());
		pc.setPhotoTime(new Date());
		Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
		params.addBodyParameter("pc",gson.toJson(pc));
		input.setText("");
		http.send(HttpMethod.POST, urlU, params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplication(), "请求失败，请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
//				String result =arg0.result;
//				if(!result.equals(null)&&result!=null){
//					Gson gson =new Gson();
//					Type type =new TypeToken<PersonalData>(){}.getType();
//				}
				if(list==null){
					list=new ArrayList<PictureComment>();
				}
				list.add(pc);
				if(adapter==null){
					adapter=new PictureCommentBastAdapter(list,Travel_team_all_data.this);
					lv.setAdapter(adapter);
				}
				adapter.notifyDataSetChanged();
			}
		});
	}
	String url="http://10.201.1.12:8080/travel/SharePicture_pinglun";
	/**
	 * 初始请求评论
	 */
	public void sendShareComment(SharePicture sp){
		RequestParams params = new RequestParams();
		HttpUtils http=new HttpUtils();
		params.addBodyParameter("sp",String.valueOf(sp.getSp()));
		http.send(HttpMethod.POST, url, params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				// TODO Auto-generated method stub
				Toast.makeText(getApplication(), "请求失败，请检查网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				// TODO Auto-generated method stub
				String result=arg0.result;
				if(!result.equals("")&&result!=null&&!result.equals("null")){
					Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
					Type type =new TypeToken<List<PictureComment>>(){}.getType();
					list =gson.fromJson(result, type);
					if(list!=null){
					adapter=new PictureCommentBastAdapter(list,Travel_team_all_data.this);
					lv.setAdapter(adapter);
					adapter.notifyDataSetChanged();
					}
				}
			}
		});
	}
}
