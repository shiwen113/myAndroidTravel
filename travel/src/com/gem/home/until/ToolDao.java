package com.gem.home.until;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public abstract class ToolDao {
	
	public abstract void sava(Object o);//添加
	 
	public abstract void delete(Object o);//删除
	
	public abstract void update(Object o);//修改

	//prep.setString
	public static void prepSetString(PreparedStatement prep,int start,int end,String...x){
		try {
			int j=0;
			for (int i = start; i < end; i++) {
				prep.setString(i, x[j]);
				j++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//prep.setInt
	public static void prepSetInt(PreparedStatement prep,int start,int end,int...x){
		try {
			int j=0;
			for (int i = start; i <end; i++) {
				prep.setInt(i, x[j]);
				j++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	//prep.setDate
	public static void prepSetDate(PreparedStatement prep,int start,int end,long...x){
		try {
			int j=0;
			for (int i = start; i < end; i++) {
				prep.setDate(i, new Date(x[j]));
				j++;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//格式转换
	public static String getDateTime(java.util.Date day){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(day);
	}
	
	public static void release(Connection conn, PreparedStatement prep, ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (prep != null) {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void release(Connection conn, PreparedStatement prep) {
		if (prep != null) {
			try {
				prep.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
/*	public AddressList addressList(ResultSet rs,AddressList a){
		try {
			a.setAdl(rs.getInt("ald"));
		} catch (SQLException e) {
			e.printStackTrace();
		};
		
		return a;
	}*/
	
	public static  java.util.Date getTimedate(String s){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		java.util.Date d=null;
		try {
			d=sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	public static  java.util.Date getTimedate1(String s){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		java.util.Date d=null;
		try {
			d=sdf.parse(s);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return d;
	}
	
	public static String setTimedate( java.util.Date d){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String s=null;
		s=sdf.format(d);
		return s;
	}
	public static String setTimedate1( java.util.Date d){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		String s=null;
		s=sdf.format(d);
		return s;
	}
}
