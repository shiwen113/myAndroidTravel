package com.gem.home.activity;



import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.home.dao.MyImageAsyncTask;
import com.gem.home.dao.TravelCommentAdapter;
import com.gem.home.until.LoginData;
import com.gem.home.until.PublishTravel;
import com.gem.home.until.TravelComment1;
import com.gem.scenery.R;
import com.gem.scenery.utils.CircleImageView;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;

public class Item_Activity extends Activity implements OnClickListener{
private static final String jion1="申请加入";
private static final String jion2="已加入";
private TextView userName;//用户名
private TextView userSexAndAge;//用户性别
private TextView allDay;//全程天数
private TextView point;//目的地
private TextView arrive;//出发地
private TextView jianjie;//简介
private TextView peopleNum;//人数
private TextView chaKanAllPeople;//所有人数
private TextView teamName;//旅行队名
private TextView joinText;//
private ListView lv;//评论
private CircleImageView civ;
private LinearLayout linearLeft;//左边的评论
private LinearLayout linearRirght;//右边的申请加入
private String content;//传过来的评论
private String plt;//旅行的json
private PublishTravel pt;
private TravelCommentAdapter adapter;
private Gson gson=new GsonBuilder().setDateFormat("yyyy-MM-dd hh:mm:ss").create();
private List<TravelComment1>listContent ;
private PopupWindow popupWindow;
private View myView ;//popupWindow布局文件
private Button btnSend;//发送
private EditText contentEdit;//输入框的评论
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_item);
		initView();//初始化控件
		Intent intent=getIntent();
		content=intent.getStringExtra("content");
		plt=intent.getStringExtra("pt");
		showData();//显示数据
		
	}
 
	public void initView(){
		teamName=(TextView) findViewById(R.id.tv_team_travel_name);
		userName=(TextView) findViewById(R.id.tv_travel_user_name);
		userSexAndAge=(TextView) findViewById(R.id.tv_travel_age);
		allDay=(TextView) findViewById(R.id.tv_travel_all_date);
		point=(TextView) findViewById(R.id.tv_travel_arrive);
		arrive=(TextView) findViewById(R.id.tv_travel_go_out_point);
		jianjie=(TextView) findViewById(R.id.tv_travel_jianjie);
		peopleNum=(TextView) findViewById(R.id.tv_travel_attend_num);
		chaKanAllPeople=(TextView) findViewById(R.id.tv_travel_chakan);
		civ=(CircleImageView) findViewById(R.id.civ_user_picture);
		joinText=(TextView) findViewById(R.id.tv_shengqingjiaru);
		lv=(ListView) findViewById(R.id.lv_content_travel);
		linearLeft=(LinearLayout) findViewById(R.id.ll_travel_pinglun);
		linearLeft.setOnClickListener(this);
		linearRirght =(LinearLayout)findViewById(R.id.ll_travel_apply);
		linearRirght.setOnClickListener(this);
	}
	/**
	 * 显示数据，bug???
	 */
	public void showData(){
		//先显示PublishTravel对象
		Type type =new TypeToken<PublishTravel>(){}.getType();
		 pt=gson.fromJson(plt, type);
		teamName.setText("旅行队："+pt.getTeamName());
		userName.setText(pt.getLd().getUserName());
//		userSexAndAge.setText(pt.getLd().get)//???
		allDay.setText("全程 "+pt.getAllDay()+"天");
		arrive.setText(pt.getStatPoint());
		point.setText(pt.getDestination());
		jianjie.setText(pt.getIntro());
		peopleNum.setText("人数"+pt.getPeopleNumber());
		if(MyImageAsyncTask.getImgtitle()!=null){
			civ.setImageBitmap(MyImageAsyncTask.getImgtitle());//设置图片
		}
		//在显示旅行队
		Type typeList=new TypeToken<List<TravelComment1>>(){}.getType();
		listContent=gson.fromJson(content, typeList);
		if(listContent!=null){
		adapter=new TravelCommentAdapter(this,listContent);
		lv.setAdapter(adapter);
		}
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ll_travel_pinglun://评论的点击事件
			contentEditText();
			break;
		case R.id.ll_travel_apply://请求点击事件
			String text=joinText.getText().toString();
			int i;
			if(text.equals("申请加入")){
				i=0;
			sendJoinTravel(i);
			}else if(text.equals("退出")){
				i=1;
				sendJoinTravel(i);
			}
			break;
		default:
			break;
		}
	}
	/**
	 * 点击添加评论 弹出输入框
	 */
	public void contentEditText(){
		 //局注入器，注入布局给View对象
	        myView = getLayoutInflater().inflate(R.layout.action_content_edittext, null);
	        btnSend=(Button) myView.findViewById(R.id.btn_content_fasong);
	        contentEdit	=(EditText) myView.findViewById(R.id.et_content_data);
	        btnSend.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					//发送请求并存到listconten集合,显示页面
//					Toast.makeText(getApplication(), "发送", Toast.LENGTH_LONG).show();
					String etContent=contentEdit.getText().toString();
					if(!etContent.equals("")){
						sendContenData(etContent);
						contentEdit.setText("");
					}else{
						Toast.makeText(getApplication(), "请输入", Toast.LENGTH_LONG).show();
					}
				}
			});
	        //通过view 和宽·高，构造PopopWindow
	        popupWindow = new PopupWindow(myView, getWindowManager().getDefaultDisplay().getWidth(), 300, true);
	         
	        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000)
	                //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
	               );
	        popupWindow.setOutsideTouchable(true);
	        //设置焦点为可点击
	        popupWindow.setFocusable(true);//可以试试设为false的结果
	        //将window视图显示在myButton下面
//	        popupWindow.showAsDropDown(chaKanAllPeople);
	        popupWindow.showAsDropDown(linearLeft,0,-30-300-80);
	        
	}
	
	private String url="http://10.201.1.12:8080/travel/TravelCommentInsert2";
	/**
	 * 发送评论的数据,有bug???
	 */
	public void sendContenData(String content){
		 Log.i("TravelCommentActivity", "login2点击了按钮");
	      
// 	   TravelComment1 travelComment1=list.get(0);
// 	   System.out.println(travelComment1);
 	   TravelComment1 travelComment12=new TravelComment1();
 	   travelComment12.setTd(listContent.get(0).getTd());
 	   LoginData  ld=new LoginData();
 	   ld.setLd(17);
 	   travelComment12.setLd(ld);
 	   travelComment12.setIdWith(0);
 	   travelComment12.setIsend(0);
 	   travelComment12.setCommentNotes(content);
 	   travelComment12.setCommentTime(new Date());
 	     Gson gson=new GsonBuilder() 
 			.setDateFormat("yyyy-MM-dd hh:mm:ss") 
 			.create();
 	   	   HttpUtils http=new HttpUtils();
 	   	   RequestParams params=new RequestParams();
// 	   String s=gson.toJson(travelComment1);
 	   String s1=gson.toJson(travelComment12);
// 	   params.addBodyParameter("travelcomment",s);
 	   params.addBodyParameter("travelcomment1",s1);
 	   http.send(HttpMethod.POST,url,params,new RequestCallBack<String>() {

		@Override
		public void onFailure(HttpException arg0, String arg1) {
			// TODO Auto-generated method stub
			Log.i("TravelCommentActivity", "访问失败");
		}

		@SuppressWarnings("unchecked")
		@Override
		public void onSuccess(ResponseInfo<String> arg0) {
			// TODO Auto-generated method stub
			          Log.i("TravelCommentActivity", "访问成功");
			// TODO Auto-generated method stub
						String string=arg0.result;
						if(string!=null&&!string.equals("null")){
						Gson gson=new GsonBuilder() 
						.setDateFormat("yyyy-MM-dd hh:mm:ss") 
						.create();
						Type type=new TypeToken<List<TravelComment1>>(){}.getType();
						if(listContent.size()!=0){
							listContent.clear();
						}
//						list=gson.fromJson(string,type);  list 的引用变了，notifyDataSetChanged会出问题
						listContent.addAll((List<TravelComment1>)(gson.fromJson(string,type)));
//						textView.setText(list.get(0).getTd().getLd().getUserName()+"发布的旅行队");
                      
						
						adapter.notifyDataSetChanged();
						}
		       }
	    });

		
	}
	
	String urlJoin="http://10.201.1.12:8080/travel/TravelComment_Join_Exit";
	/**
	 * 申请加入 bug？？？
	 */
	public void sendJoinTravel(int i){
		 HttpUtils http=new HttpUtils();
	   	 RequestParams params=new RequestParams();
	   	 params.addBodyParameter("td",String.valueOf(pt.getTd()));
	   	 params.addBodyParameter("ld",String.valueOf(17));
	   	 params.addBodyParameter("state",String.valueOf(i));
	   	 http.send(HttpMethod.POST,urlJoin,params,new RequestCallBack<String>() {

	 		@Override
	 		public void onFailure(HttpException arg0, String arg1) {
	 			// TODO Auto-generated method stub
	 			Log.i("TravelCommentActivity", "访问失败");
	 		}

	 		@SuppressWarnings("unchecked")
	 		@Override
	 		public void onSuccess(ResponseInfo<String> arg0) {
	 			// TODO Auto-generated method stub
	 			          Log.i("TravelCommentActivity", "访问成功");
	 			 String result=arg0.result;
	 			 if(!result.equals("")){
	 				 if(Integer.parseInt(result)==0){
	 					joinText.setText("申请加入");
	 				 }else if(Integer.parseInt(result)==1){
	 					joinText.setText("退出");
	 				 }
	 			 }
	 		}
	 	    });
	}
}
