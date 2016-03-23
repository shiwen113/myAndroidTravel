package com.gem.message.entity;

import java.util.Date;

public class MessageNotifaction {
	private String imgUrl;//图片
	private String userName;//用户名
	private String notifaction;//通知
	private String time;//时间
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNotifaction() {
		return notifaction;
	}
	public void setNotifaction(String notifaction) {
		this.notifaction = notifaction;
	}
	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "MessageNotifaction [imgUrl=" + imgUrl + ", userName="
				+ userName + ", notifaction=" + notifaction + ", time=" + time
				+ "]";
	}
	
	
}
