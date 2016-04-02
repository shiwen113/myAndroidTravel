package com.gem.scenery.entity;

import com.gem.home.until.LoginData;
/*
 * 表名：clickpraise
主键：ckp
用户名：ld
状态：photostate
图：sp
 */

/*
 * 点赞表
 */
public class ClickPraise {
	private int ckp;//主键
	private LoginData ld;//
	private int photoState;//0表示没有点赞，1表示点赞，2表示取消点赞
	private SharePicture sp;//赞的图
	
	public ClickPraise(LoginData ld, int photoState, SharePicture sp) {
		super();
		this.ld = ld;
		this.photoState = photoState;
		this.sp = sp;
	}
	public int getCkp() {
		return ckp;
	}
	public void setCkp(int ckp) {
		this.ckp = ckp;
	}
	public int getPhotoState() {
		return photoState;
	}
	public void setPhotoState(int photoState) {
		this.photoState = photoState;
	}
	public SharePicture getSp() {
		return sp;
	}
	public void setSp(SharePicture sp) {
		this.sp = sp;
	}
	public LoginData getLd() {
		return ld;
	}
	public void setLd(LoginData ld) {
		this.ld = ld;
	}
	@Override
	public String toString() {
		return "ClickPraise [ckp=" + ckp + ", ld=" + ld + ", photoState="
				+ photoState + ", sp=" + sp + "]";
	}

	
}
