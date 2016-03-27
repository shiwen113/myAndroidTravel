package com.gem.home.until;

import java.util.Date;

/*
 * 5、旅行队的多级评论
 */
public class TravelComment1 {
    private  int tcm;            //主键 记录有多少条评论，每条记录是唯一的
    private  PublishTravel td;   //旅行队的主键，表示对哪个旅行队的评论
    private  LoginData ld;       //用户注册表主键，表示哪个用户对旅行队进行的评论
    
    private  int idWith;      //0表示对旅行队的评论(一级评论)
                              //其他值表示对某个评论的评论（也就是旅行队评论的主键tcm）
    
    private int isend;           //表示是否还有子评论 0表示没有 1表示有
    private String commentNotes; //评论的内容
    private Date commentTime;    //评论的时间
    
	public int getTcm() {
		return tcm;
	}
	public void setTcm(int tcm) {
		this.tcm = tcm;
	}
	public PublishTravel getTd() {
		return td;
	}
	public void setTd(PublishTravel td) {
		this.td = td;
	}
	public LoginData getLd() {
		return ld;
	}
	public void setLd(LoginData ld) {
		this.ld = ld;
	}
	public int getIdWith() {
		return idWith;
	}
	public void setIdWith(int idWith) {
		this.idWith = idWith;
	}
	public int getIsend() {
		return isend;
	}
	public void setIsend(int isend) {
		this.isend = isend;
	}
	public String getCommentNotes() {
		return commentNotes;
	}
	public void setCommentNotes(String commentNotes) {
		this.commentNotes = commentNotes;
	}
	public Date getCommentTime() {
		return commentTime;
	}
	public void setCommentTime(Date commentTime) {
		this.commentTime = commentTime;
	}
	
	@Override
	public String toString() {
		return "TravelComment1 [tcm=" + tcm + ", td=" + td + ", ld=" + ld + ", idWith=" + idWith + ", isend=" + isend
				+ ", commentNotes=" + commentNotes + ", commentTime=" + ToolDao.setTimedate(commentTime) + "]";
	}
	
	public TravelComment1() {
		super();
	}
	public TravelComment1(int tcm, PublishTravel td, LoginData ld, int idWith, int isend, String commentNotes,
			Date commentTime) {
		super();
		this.tcm = tcm;
		this.td = td;
		this.ld = ld;
		this.idWith = idWith;
		this.isend = isend;
		this.commentNotes = commentNotes;
		this.commentTime = commentTime;
	}
    
}
