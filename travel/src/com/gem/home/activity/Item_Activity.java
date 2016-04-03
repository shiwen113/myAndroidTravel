package com.gem.home.activity;

import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.text.Layout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.HorizontalScrollView;
import android.widget.ImageView.ScaleType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.gem.home.dao.MyImageAsyncTask;
import com.gem.home.dao.TravelCommentAdapter;
import com.gem.home.dao.UserPictureAsyncTask;
import com.gem.home.until.LoginData;
import com.gem.home.until.PublishTravel;
import com.gem.home.until.ToolDao;
import com.gem.home.until.TravelComment1;
import com.gem.home.until.TravelMember;
import com.gem.scenery.R;
import com.gem.scenery.entity.PersonalData;
import com.gem.scenery.utils.CircleImageView;
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

public class Item_Activity extends Activity implements OnClickListener,OnItemClickListener{
private static final String jion1="立即加入";
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
private TextView gotoDate;//出发时间
private ListView lv;//评论
private ImageButton back;//返回
private CircleImageView civ;
private LinearLayout ll;
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
private Intent intentLook;
private TextView huifu;//回复
private TextView delete;//删除
private TextView shouchang;//收藏
boolean flag;  //是否有数据(旅行队的评论)传过来（是否有网）
private PersonalData pd;//个人资料（发布者的个人资料）
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_item);
		initView();//初始化控件
		Intent intent=getIntent();
		flag=intent.getBooleanExtra("flag",false);
		
		content=intent.getStringExtra("content");
		plt=intent.getStringExtra("pt");
		pd=(PersonalData) intent.getSerializableExtra("pd");
		showData();//显示数据
		
		isMemberSend();
	
	}
 
	String urlMember="http://10.201.1.12:8080/travel/TravelComment_member";
	/**
	 * 获取是否是此旅行队的成员
	 */
	public void isMemberSend(){
		 HttpUtils http=new HttpUtils();
	   	 RequestParams params=new RequestParams();
	   	 params.addBodyParameter("td",String.valueOf(pt.getTd()));
//	   	 params.addBodyParameter("ld",String.valueOf(17));
	   	 http.send(HttpMethod.POST,urlMember,params,new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(getApplication(), "请求失败,请检查您的网络", Toast.LENGTH_LONG).show();
				
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result = arg0.result;
				Gson gson=new GsonBuilder() 
	 			.setDateFormat("yyyy-MM-dd hh:mm:ss") 
	 			.create();
				Type type = new TypeToken<List<TravelMember>>(){}.getType();
				if(result!=null&&!result.equals("null")){
					List<TravelMember> ltm=gson.fromJson(result, type);
					for (TravelMember tm : ltm) {
						
						if(tm.getLd().getLd()==17){
							joinText.setText("退出");
						}else{
							joinText.setText("立即加入");
						}
					}
				}
			}
	   		 
	   	 });
	}
	public void initView(){
		gotoDate= (TextView) findViewById(R.id.tv_date);
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
		back=(ImageButton) findViewById(R.id.ib_travel_infor_back);
		back.setOnClickListener(this);
		chaKanAllPeople.setOnClickListener(this);
		shouchang=(TextView) findViewById(R.id.tv_shouchang);
		shouchang.setOnClickListener(this);
		ll=(LinearLayout) findViewById(R.id.ll_picture_show);
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
		if(pd.getSex()==1){
		userSexAndAge.setText("男"+" "+pd.getAge());
		}else if(pd.getSex()==0){
			userSexAndAge.setText("女"+" "+pd.getAge());
		}
		allDay.setText("全程 "+pt.getAllDay()+"天");
		arrive.setText(pt.getStatPoint());
		point.setText(pt.getDestination());
		jianjie.setText(pt.getIntro());
		peopleNum.setText("人数"+pt.getPeopleNumber());
		gotoDate.setText("出发时间"+ToolDao.setTimedate1(pt.getStartTime()));
		new UserPictureAsyncTask(civ).execute(pd.getUriUpLoadPicture());
//		if(MyImageAsyncTask.getImgtitle()!=null){
//			civ.setImageBitmap(MyImageAsyncTask.getImgtitle());//设置图片
//		}
		//在显示旅行队
		if(flag){
		Type typeList=new TypeToken<List<TravelComment1>>(){}.getType();
		listContent=gson.fromJson(content, typeList);
		if(listContent!=null){
		adapter=new TravelCommentAdapter(this,listContent);
		lv.setAdapter(adapter);
		}
		lv.setOnItemClickListener(this);
		}
		
		//显示生活照
		String[] urlP=pt.getUrlLifePicture().split(";");
		BitmapUtils bu=new BitmapUtils(getApplication());
		for (int i = 0; i < urlP.length; i++) {
			String uri="http://10.201.1.12:8080/gotravel/TravelTeam/"+urlP[i];
			Log.i("MyUri", uri);
			ImageView iv=new ImageView(getApplication());
//			imageloader.displayImage(url, iv);
			LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(500, 500);
		    iv.setLayoutParams(layoutParams);
		    iv.setScaleType(ScaleType.CENTER_INSIDE);
			bu.display(iv, uri);
			ll.addView(iv);
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
//			Toast.makeText(this, text, Toast.LENGTH_LONG).show();
			int i;
			if(text.equals("立即加入")){
				i=0;
			sendJoinTravel(i);
			}else if(text.equals("退出")){
				i=1;
				sendJoinTravel(i);
			}
			break;
		case R.id.ib_travel_infor_back:
			finish();
			
			break;
		case R.id.tv_travel_chakan:
			intentLook=new Intent(Item_Activity.this,LookAllPeopleNumberActivity.class);
			sendLookAllPeople();
			break;
		case R.id.tv_shouchang://收藏
			if(flag){
			sendShouChang();
			}else {
			Toast.makeText(getApplication(), "请求失败,请检查您的网络", Toast.LENGTH_LONG).show();
			}
			break;
		default:
			break;
		}
	}
	
	
//	/**
//	 * 判断是否收藏过该旅行队  刘学
//	 */
//	String urlsifoushoucang="";
//	public void sendSifFouShouCang(){
//		HttpUtils http=new HttpUtils();
//	   	RequestParams params=new RequestParams();
//		params.addBodyParameter("ld",String.valueOf(17));
//	   	params.addBodyParameter("td",String.valueOf(pt.getTd()));	
//	   	
//	}
	
	String urlShouChang="http://10.201.1.12:8080/travel/Wode_tianjiashoucang";
	public void sendShouChang(){
		HttpUtils http=new HttpUtils();
	   	RequestParams params=new RequestParams();
	   	params.addBodyParameter("ld",String.valueOf(17));
	   	params.addBodyParameter("td",String.valueOf(pt.getTd()));
	   	http.send(HttpMethod.POST, urlShouChang, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(getApplication(), "请求失败,请检查您的网络", Toast.LENGTH_LONG).show();
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result=arg0.result;
				if(result!=null&&!result.equals("null")){
					int i=Integer.parseInt(result);
					if(i==0){
					Toast.makeText(getApplication(), "收藏成功", Toast.LENGTH_LONG).show();
					}else {
					Toast.makeText(getApplication(), "收藏过该旅行队", Toast.LENGTH_LONG).show();
					}
				}
			}
	   	});
	}
	
	
	String urlAll="http://10.201.1.12:8080/travel/Home_home_chakanquanbu";
	/**
	 * 查看所有人，请求旅行队所有人
	 */
	public void sendLookAllPeople(){
		HttpUtils http=new HttpUtils();
	   	RequestParams params=new RequestParams();
	   	params.addBodyParameter("td",String.valueOf(pt.getTd()));
	   	http.send(HttpMethod.POST, urlAll, params, new RequestCallBack<String>() {

			@Override
			public void onFailure(HttpException arg0, String arg1) {
				Toast.makeText(getApplication(), "请求失败,请检查您的网络", Toast.LENGTH_LONG).show();
				startActivity(intentLook);
			}

			@Override
			public void onSuccess(ResponseInfo<String> arg0) {
				String result=arg0.result;
				if(result!=null&&!result.equals("null")){
					intentLook.putExtra("result", result);
				}
				intentLook.putExtra("num", pt.getPeopleNumber());
				intentLook.putExtra("time", ToolDao.setTimedate1(pt.getStartTime()));
				intentLook.putExtra("point", pt.getDestination());
				startActivity(intentLook);
			}
		});
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
						popupWindow.dismiss();
						//()myView.dismiss();
//			            myView.setVisibility(View.GONE);
			            Toast.makeText(getApplication(), "发送成功", Toast.LENGTH_LONG).show();
					}else{
						Toast.makeText(getApplication(), "请输入", Toast.LENGTH_LONG).show();
					}
				}
			});
	        sendInputText();
	}
	public void sendInputText(){
		  //通过view 和宽·高，构造PopopWindow
        popupWindow = new PopupWindow(myView, getWindowManager().getDefaultDisplay().getWidth(), 300, true);
         
        popupWindow.setBackgroundDrawable(new ColorDrawable(0x00000000)
                //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
               );
        popupWindow.setOutsideTouchable(true);
        //设置焦点为可点击
        popupWindow.setFocusable(true);//可以试试设为false的结果
        //将window视图显示在myButton下面
//        popupWindow.showAsDropDown(chaKanAllPeople);
        popupWindow.showAsDropDown(linearLeft,0,-30-300-80);
	}
	
	private String url="http://10.201.1.12:8080/travel/TravelCommentInsert2";
	/**
	 * 发送评论的数据
	 * 增加评论
	 */
	public void sendContenData(String content){
		 Log.i("TravelCommentActivity", "login2点击了按钮");
	      
 	   TravelComment1 travelComment12=new TravelComment1();
 	   travelComment12.setTd(pt);
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
			Toast.makeText(getApplication(), "请求失败,请检查您的网络", Toast.LENGTH_LONG).show();
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
						if(listContent!=null&&listContent.size()!=0){
							listContent.clear();
						}
						listContent.addAll((List<TravelComment1>)(gson.fromJson(string,type)));
						}
//						list=gson.fromJson(string,type);  list 的引用变了，notifyDataSetChanged会出问题
//						textView.setText(list.get(0).getTd().getLd().getUserName()+"发布的旅行队");
                      
						adapter=new TravelCommentAdapter(Item_Activity.this,listContent);
						lv.setAdapter(adapter);
//						adapter.notifyDataSetChanged();
						}
	    });

		
	}
	
	String urlJoin="http://10.201.1.12:8080/travel/TravelComment_Join_Exit";
	/**
	 * 申请加入
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
	 			Toast.makeText(getApplication(), "请求失败,请检查您的网络", Toast.LENGTH_LONG).show();
	 			Log.i("TravelCommentActivity", "访问失败");
	 		}

	 		@SuppressWarnings("unchecked")
	 		@Override
	 		public void onSuccess(ResponseInfo<String> arg0) {
	 			// TODO Auto-generated method stub
	 			          Log.i("TravelCommentActivity", "访问成功");
	 			 String result=arg0.result;
	 			 if(result!=null&&!result.equals("null")){
	 				 if(Integer.parseInt(result)==0){
	 					joinText.setText("立即加入");
	 					Toast.makeText(getApplication(), "已退出", Toast.LENGTH_LONG).show();
	 				 }else if(Integer.parseInt(result)==1){
	 					joinText.setText("退出");
	 					Toast.makeText(getApplication(), "申请成功", Toast.LENGTH_LONG).show();
	 				 }
	 			 }
	 		}
	 	    });
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
//		Toast.makeText(getApplication(), "点击成功", Toast.LENGTH_LONG).show();
		if(listContent.get(arg2).getLd().getLd()==17){//只能弹删除
			popPupWindow(arg1,arg2);
			
			huifu.setVisibility(View.GONE);
		}else{
			popPupWindow(arg1,arg2);
			
			delete.setVisibility(View.GONE);
		}
	}
	 PopupWindow popupWindow1;
	public void popPupWindow(View arg1,int arg2){
		 //局注入器，注入布局给View对象
	        View myView1 = getLayoutInflater().inflate(R.layout.deleteorhuifu, null);
	        //通过view 和宽·高，构造PopopWindow
	        huifu=(TextView) myView1.findViewById(R.id.tv_huifu);
	        delete=(TextView) myView1.findViewById(R.id.tv_delete);
	       popupWindow1 = new PopupWindow(myView1, getWindowManager().getDefaultDisplay().getWidth(),200, true);
	         
	        popupWindow1.setBackgroundDrawable(new ColorDrawable(0x00000000)
	                //此处为popwindow 设置背景，同事做到点击外部区域，popwindow消失
	               );
	        popupWindow1.setOutsideTouchable(true);
	        //设置焦点为可点击
	        popupWindow1.setFocusable(true);//可以试试设为false的结果
	        //将window视图显示在myButton下面
	        popupWindow1.showAsDropDown(arg1);
	        huifu.setOnClickListener(new onClickHuiFuDelete(arg2));
	        delete.setOnClickListener(new onClickHuiFuDelete(arg2));
	}
	public class onClickHuiFuDelete implements OnClickListener{
		int arg2;
		
		public onClickHuiFuDelete(int arg2) {
			this.arg2 = arg2;
		}

		@Override
		public void onClick(View v) {
		    popupWindow1.dismiss();
			switch (v.getId()) {
			case R.id.tv_huifu:
//				Toast.makeText(getApplication(), "点击成功", Toast.LENGTH_LONG).show();
				 myView = getLayoutInflater().inflate(R.layout.action_content_edittext, null);
			     btnSend=(Button) myView.findViewById(R.id.btn_content_fasong);
			     contentEdit=(EditText) myView.findViewById(R.id.et_content_data);
			     sendInputText();
			     Log.i("content", "String content"+content);
			    	 btnSend.setOnClickListener(new OnClickListener() {
					
			    		 @Override
			    		 public void onClick(View arg0) {
			    			 popupWindow.dismiss();
			    			 huiFu(arg2);
			    			
			    		 }
			    	 });
				break;
			case R.id.tv_delete:
				delete(arg2);
				
				Toast.makeText(getApplication(), "点击成功", Toast.LENGTH_LONG).show();
				break;
			default:
				break;
			}
			
		}
		
	}
	/**
	 * 多级评论回复
	 */
	public void huiFu(int arg2){
//		 Log.i("TravelCommentActivity", "login1点击了按钮");
		   String contentTo=contentEdit.getText().toString();
		   if(!contentTo.equals("")){
			   TravelComment1 travelComment1=listContent.get(arg2);
			   System.out.println(travelComment1);
			   TravelComment1 travelComment12=new TravelComment1();
			   travelComment12.setTd(travelComment1.getTd());
			   LoginData  ld=new LoginData();
			   ld.setLd(17);
			   travelComment12.setLd(ld);
			   travelComment12.setIdWith(travelComment1.getTcm());
			   travelComment12.setIsend(0);
			   travelComment12.setCommentNotes(contentTo);
			   travelComment12.setCommentTime(new Date());
	   	     	Gson gson=new GsonBuilder() 
	   			.setDateFormat("yyyy-MM-dd hh:mm:ss") 
	   			.create();
	   	   	   HttpUtils http=new HttpUtils();
	   	   	   RequestParams params=new RequestParams();
	   	   	   String s=gson.toJson(travelComment1);
	   	   	   String s1=gson.toJson(travelComment12);
	   	   	   params.addBodyParameter("travelcomment",s);
	   	   	   params.addBodyParameter("travelcomment1",s1);
	   	   	   http.send(HttpMethod.POST,"http://10.201.1.12:8080/travel/TravelCommentInsert",params,new RequestCallBack<String>() {

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
//							list=gson.fromJson(string,type);  list 的引用变了，notifyDataSetChanged会出问题
							listContent.addAll((List<TravelComment1>)(gson.fromJson(string,type)));
//							textView.setText(listContent.get(0).getTd().getLd().getUserName()+"发布的旅行队");

//							travelCommentAdapter.i=1;
							adapter=new TravelCommentAdapter(Item_Activity.this,listContent);
							lv.setAdapter(adapter);
//							adapter.notifyDataSetChanged();
							}
			       }
		    });
	   	   contentEdit.setText("");
		   }else{
		    	 Toast.makeText(getApplication(), "请输入", Toast.LENGTH_LONG).show();
		     }
	}
	/**
	 * 多级评论删除评论 ld17
	 */
	public void delete(int agr2){
		Log.i("TravelCommentActivity", "login3点击了按钮");
        
  	   TravelComment1 travelComment1=listContent.get(agr2);
  	   System.out.println(travelComment1);
//  	   TravelComment1 travelComment12=new TravelComment1();
//  	   travelComment12.setTd(list.get(0).getTd());
//  	   LoginData  ld=new LoginData();
//  	   ld.setLd(17);
//  	   travelComment12.setLd(ld);
//  	   travelComment12.setIdWith(0);
//  	   travelComment12.setIsend(0);
//  	   travelComment12.setCommentNotes("hello，你好的");
//  	   travelComment12.setCommentTime(new Date());
  	     Gson gson=new GsonBuilder() 
  			.setDateFormat("yyyy-MM-dd hh:mm:ss") 
  			.create();
  	   	   HttpUtils http=new HttpUtils();
  	   	   RequestParams params=new RequestParams();
  	   String s=gson.toJson(travelComment1);
//  	   String s1=gson.toJson(travelComment12);
  	   params.addBodyParameter("travelcomment",s);
//  	   params.addBodyParameter("travelcomment1",s1);
  	   http.send(HttpMethod.POST,"http://10.201.1.12:8080/travel/TravelCommentDelete",params,new RequestCallBack<String>() {

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
//						textView.setText(listContent.get(0).getTd().getLd().getUserName()+"发布的旅行队");
                       
//						travelCommentAdapter.i=1;
						adapter=new TravelCommentAdapter(Item_Activity.this,listContent);
						lv.setAdapter(adapter);
//						adapter.notifyDataSetChanged();
						}
		       }
	    });
  	   }

}
