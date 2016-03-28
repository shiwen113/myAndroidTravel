package com.gem.home.until;

import java.util.Date;

/*
 * 表名 travelmember
主键 tm
旅行队：td
(成员 tname)ld
申请状态：applystate
加入时间：addtime
 */

public class TravelMember {
	private int tm;//主键
	private PublishTravel td;//旅行队
	private LoginData ld;//成员
//	private int rank;//表示成员在旅行队的级别  1表示是发布者  0表示是普通成员 
	private int applyState;//申请状态 1表示申请通过，0表示申请不通过,2表示待审
	private int stateDel;//1表示删除，2表示没有删除
	private Date addTime;//加入时间
	public int getTm() {
		return tm;
	}
	public void setTm(int tm) {
		this.tm = tm;
	}
	public PublishTravel getTd() {
		return td;
	}
	public void setTd(PublishTravel td) {
		this.td = td;
	}
	
	public int getApplyState() {
		return applyState;
	}
	public void setApplyState(int applyState) {
		this.applyState = applyState;
	}
	public Date getAddTime() {
		return addTime;
	}
	public void setAddTime(Date addTime) {
		this.addTime = addTime;
	}

	public int getStateDel() {
		return stateDel;
	}
	public void setStateDel(int stateDel) {
		this.stateDel = stateDel;
	}
	public LoginData getLd() {
		return ld;
	}
	public void setLd(LoginData ld) {
		this.ld = ld;
	}
//	public int getRank() {
//		return rank;
//	}
//	public void setRank(int rank) {
//		this.rank = rank;
//	}
//	
//	@Override
//	public String toString() {
//		return "TravelMember [tm=" + tm + ", td=" + td + ", ld=" + ld + ", rank=" + rank + ", applyState=" + applyState
//				+ ", stateDel=" + stateDel + ", addTime=" + addTime + "]";
//	}
	
	@Override
	public String toString() {
		return "TravelMember [tm=" + tm + ", td=" + td + ", ld=" + ld + ", applyState=" + applyState + ", stateDel="
				+ stateDel + ", addTime=" + addTime + "]";
	}
	
	
	
}
