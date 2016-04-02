package com.gem.home.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gem.home.until.ToolDao;
import com.gem.home.until.TravelComment1;
import com.gem.scenery.R;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
/**
 * 显示错乱，下滑在上拉
 * @author C5-0
 *
 */
public class TravelCommentAdapter extends BaseAdapter {
    Context context;
    List<TravelComment1> list;
    List<B> bs=new ArrayList<B>();
    int a=0;  //记住前一个评论的position
	
	int b=0;  //表示现在要显示的是几级评论
//	int b1=0; //表示上次是几级评论
	
	//前一个记录哪个评论,后一个记录是几级评论
//    Map<TravelComment1,Integer> map=new HashMap<TravelComment1, Integer>(); 
	@SuppressLint("UseSparseArrays")
	Map<Integer, Integer> map=new HashMap<Integer, Integer>();
	List<A> arr=new ArrayList<A>();
	int c=0;  //表示现在要显示的 他前面一个的主键
	int d=0;  //计录现在的主键
	public TravelCommentAdapter(Context context, List<TravelComment1> list) {
		super();
		this.context = context;
		this.list = list;
//		this.bs=bs;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder;
//		if(arr.size()>position)
		if(convertView==null){
		viewHolder=new ViewHolder();
		convertView=LayoutInflater.from(context).inflate(R.layout.travelcommnet_adapter,null);
		viewHolder.textView=(TextView) convertView.findViewById(R.id.travelcommnet_tv1);
		viewHolder.textView2=(TextView) convertView.findViewById(R.id.travelcommnet_tv2);
		convertView.setTag(viewHolder);
		}else {
			viewHolder=(ViewHolder) convertView.getTag();
		}
		TravelComment1 travelComment1=list.get(position);
		c=travelComment1.getIdWith();
		
		String string=null;
		
		if(travelComment1.getIdWith()==0){
			b=1;
			if(arr.size()!=0){
				arr.clear();
			}
			
			string=b+"级评论   "+travelComment1.getLd().getUserName()+"对"+travelComment1.getTd().getLd().getUserName()+"发布的旅行队评论："+travelComment1.getCommentNotes();
//			viewHolder.textView.setText(string);
			if(travelComment1.getIsend()==1){
				A a=new A(b,travelComment1.getLd().getUserName(),travelComment1.getTd().getLd().getUserName());
				arr.add(a);
//				map.put(travelComment1, b);
			}
			
		}else {
			String s=null;
			if(c==d){
				b=b+1;
				s=list.get(a).getLd().getUserName();
			}else {
//				b=b-1;
				int i=travelComment1.getIdWith();
				TravelComment1 tc1 = null;
				for(TravelComment1 tc:list){
					if(tc.getTcm() ==i){
						tc1=tc;
					}
				}
			   s=tc1.getLd().getUserName();
			   b=map.get(tc1.getTcm())+1;
			}
			
//			Set<TravelComment1> set=map.keySet();
//			for(TravelComment1 comment1:set){
//				int b1=map.get(comment1);
//				if(b1+1==b&&comment1.getLd().getUserName().equals(s)&&);
//			}
			string=b+"级评论   "+travelComment1.getLd().getUserName()+"对"+s+"的评论："+travelComment1.getCommentNotes();
			for(A tc:arr){
				if(tc.a==(b-1)&&tc.b.equals(s)&&tc.c.equals(travelComment1.getLd().getUserName())){
					string=b+"级评论   "+travelComment1.getLd().getUserName()+"对"+s+"的回复："+travelComment1.getCommentNotes();
				}
			}
			
			if(travelComment1.getIsend()==1){
				A a=new A(b,travelComment1.getLd().getUserName(),s);
				arr.add(a);
			}
//			viewHolder.textView.setText(string);		
		}
//		viewHolder.textView.setText(string);
		if(bs.size()<=position){
			bs.add(new B(b, string, ToolDao.setTimedate(travelComment1.getCommentTime())));
			viewHolder.textView.setText(string);
			viewHolder.textView2.setText(ToolDao.setTimedate(travelComment1.getCommentTime()));
		}else {
			viewHolder.textView.setText(bs.get(position).content);
			viewHolder.textView2.setText(bs.get(position).time);
		}
		map.put(travelComment1.getTcm(), b);
//		viewHolder.textView2.setText(ToolDao.setTimedate(travelComment1.getCommentTime()));
		a=position;
		d=travelComment1.getTcm();
		return convertView;
	}
    
	static class ViewHolder{
		TextView textView;
	    TextView textView2;
	}
	
}


  class A{
	  public int a;  //表示几级评论
	  public String b; //表示评论者
	  public String c; //表示被评论者
	public A(int a, String b, String c) {
		super();
		this.a = a;
		this.b = b;
		this.c = c;
	}
 }
  
  class B{
	  public int a;  //表示几级评论
	  public String content; //评论的内容  （包括评论者和被评论者及其内容）
	  public String time; //评论的时间
	public B(int a, String content, String time) {
		super();
		this.a = a;
		this.content = content;
		this.time = time;
	}
   }