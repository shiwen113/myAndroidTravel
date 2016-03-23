package com.gem.message.entity;
/**
 * 消息类，虚拟信息
 * @author Administrator
 *
 */
public class Message {
	public static final int TYPE_RECEIVED=0;//收到消息
	public static final int TYPE_SENT=1;//发出消息
	private String content;//记录
	private int type;//消息的类型

	public Message(String content,int type){
		this.content=content;
		this.setType(type);
	}
	
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
