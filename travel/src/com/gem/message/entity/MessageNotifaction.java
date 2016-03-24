package com.gem.message.entity;

public class MessageNotifaction {
	private String imgUrl;//图片
	private String userName;//用户名
	private String notifaction;//通知
	private String time;//时间
	private int state;//1表示聊天 2表示请求
	
	public MessageNotifaction(String userName, String notifaction, String time,
			int state) {
		super();
		this.userName = userName;
		this.notifaction = notifaction;
		this.time = time;
		this.state=state;
	}

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

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	@Override
	public String toString() {
		return "MessageNotifaction [userName=" + userName + ", notifaction="
				+ notifaction + ", time=" + time + ", state=" + state + "]";
	}


	
	
}
