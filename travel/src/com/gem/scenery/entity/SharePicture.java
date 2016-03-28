package com.gem.scenery.entity;

import java.util.Date;

import com.gem.home.until.LoginData;
import com.gem.home.until.PublishTravel;

/*
 * 表名 sharepicture
主键 sp
(队名 tname)td
(用户 username)ld
景/地点 viewpoint
时间：time
 */
/*
 * 分享旅图
 */
public class SharePicture {
	private int sp;//主键
	private PublishTravel td;//队名
	private LoginData ld;//用户
	private String urlPhotos;//图片地址
	private String viewPoint;//景或地点
	private Date time;//时间
	public int getSp() {
		return sp;
	}
	public void setSp(int sp) {
		this.sp = sp;
	}
	public PublishTravel getTd() {
		return td;
	}
	public void setTd(PublishTravel td) {
		this.td = td;
	}
	
	public String getViewPoint() {
		return viewPoint;
	}
	public void setViewPoint(String viewPoint) {
		this.viewPoint = viewPoint;
	}
	public Date getTime() {
		return time;
	}
	public void setTime(Date time) {
		this.time = time;
	}
	public LoginData getLd() {
		return ld;
	}
	public void setLd(LoginData ld) {
		this.ld = ld;
	}
	public String getUrlPhotos() {
		return urlPhotos;
	}
	public void setUrlPhotos(String urlPhotos) {
		this.urlPhotos = urlPhotos;
	}
	@Override
	public String toString() {
		return "SharePicture [sp=" + sp + ", td=" + td + ", ld=" + ld
				+ ", urlPhotos=" + urlPhotos + ", viewPoint=" + viewPoint
				+ ", time=" + time + "]";
	}
	
}
