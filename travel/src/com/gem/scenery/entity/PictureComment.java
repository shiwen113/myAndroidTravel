package com.gem.scenery.entity;

import java.util.Date;

import com.gem.home.until.LoginData;

/*
 * 表名 picturecomment
主键 pc
    图片 sp
(用户 commentname)ld
（回复或评论）replyorcomment
评论记录 commentnotes
时间：phototime
 */

/*
 * 分享旅图的评论
 */
public class PictureComment {
	private int pc;//
	private LoginData ld;//
//	private int replyComment;//1表示评论，2表示回复
	private String commentNotes;//
	private Date photoTime;//
	private SharePicture sp;
	public int getPc() {
		return pc;
	}
	public void setPc(int pc) {
		this.pc = pc;
	}
//	public int getReplyComment() {
//		return replyComment;
//	}
//	public void setReplyComment(int replyComment) {
//		this.replyComment = replyComment;
//	}
	public String getCommentNotes() {
		return commentNotes;
	}
	public void setCommentNotes(String commentNotes) {
		this.commentNotes = commentNotes;
	}
	public Date getPhotoTime() {
		return photoTime;
	}
	public void setPhotoTime(Date photoTime) {
		this.photoTime = photoTime;
	}
	public LoginData getLd() {
		return ld;
	}
	public void setLd(LoginData ld) {
		this.ld = ld;
	}
	
    public SharePicture getSp() {
		return sp;
	}
	public void setSp(SharePicture sp) {
		this.sp = sp;
	}
	//	@Override
//	public String toString() {
//		return "PictureComment [pc=" + pc + ", ld=" + ld + ", replyComment="
//				+ replyComment + ", commentNotes=" + commentNotes
//				+ ", photoTime=" + photoTime + "]";
//	}
//	@Override
//	public String toString() {
//		return "PictureComment [pc=" + pc + ", ld=" + ld + ", commentNotes=" + commentNotes + ", photoTime=" + photoTime
//				+ "]";
//	}
//    
	@Override
	public String toString() {
		return "PictureComment [pc=" + pc + ", ld=" + ld + ", commentNotes=" + commentNotes + ", photoTime=" + photoTime
				+ ", sp=" + sp + "]";
	}
    
}
