package com.gem.home.until;

import java.io.Serializable;
import java.util.Date;



/*
 * 表名 traveldata
主键 td
队名 teamname
全程天数 allday
出发地 statpoint
目的地 destination
性别 sex
城市 city
出发时间 starttime
预计到达时间 arrivetime
生活照 lifepicture
景点 viewpoint
简介 intro
创建时间：createtime
 */

/*
 * 发布旅行队
 */
public class PublishTravel implements Serializable{
	private long td;//主键
	private String teamName;//队名
	private int allDay;//全程天数
	private String  statPoint;//出发地
	private String destination;//目的地
	private int sex;//性别，1表示男。0，表示女，2不限
	private int city;//0表示同城，1表示非同城，2表示不限
	private int peopleNumber;//人数
	private Date startTime;//出发时间
	private Date arriveTime;//预计到达时间
	private String urlLifePicture;//生活照
	private String viewPoint;//景点
	private String intro;//简介
	private Date createTime;//创建时间
    private LoginData ld;
    
    
	public PublishTravel() {
	}
	public PublishTravel(String teamName, int allDay, String statPoint,
			String destination, int sex, int city, int peopleNumber,
			Date startTime, Date arriveTime, String viewPoint, String intro,
			Date createTime, LoginData ld) {
		super();
		this.teamName = teamName;
		this.allDay = allDay;
		this.statPoint = statPoint;
		this.destination = destination;
		this.sex = sex;
		this.city = city;
		this.peopleNumber = peopleNumber;
		this.startTime = startTime;
		this.arriveTime = arriveTime;
		this.viewPoint = viewPoint;
		this.intro = intro;
		this.createTime = createTime;
		this.ld = ld;
	}
	public String getTeamName() {
		return teamName;
	}
	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}
	public int getAllDay() {
		return allDay;
	}
	public void setAllDay(int allDay) {
		this.allDay = allDay;
	}
	public String getStatPoint() {
		return statPoint;
	}
	public void setStatPoint(String statPoint) {
		this.statPoint = statPoint;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public Date getStartTime() {
		return startTime;
	}
	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}
	public Date getArriveTime() {
		return arriveTime;
	}
	public void setArriveTime(Date arriveTime) {
		this.arriveTime = arriveTime;
	}
	public String getViewPoint() {
		return viewPoint;
	}
	public void setViewPoint(String viewPoint) {
		this.viewPoint = viewPoint;
	}
	public String getIntro() {
		return intro;
	}
	public void setIntro(String intro) {
		this.intro = intro;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getUrlLifePicture() {
		return urlLifePicture;
	}
	public void setUrlLifePicture(String urlLifePicture) {
		this.urlLifePicture = urlLifePicture;
	}
    
	public int getCity() {
		return city;
	}

	public void setCity(int city) {
		this.city = city;
	}
	public long getTd() {
		return td;
	}
	public void setTd(long td) {
		this.td = td;
	}
	public LoginData getLd() {
		return ld;
	}
	public void setLd(LoginData ld) {
		this.ld = ld;
	}
	public int getPeopleNumber() {
		return peopleNumber;
	}
	public void setPeopleNumber(int peopleNumber) {
		this.peopleNumber = peopleNumber;
	}
	@Override
	public String toString() {
		return "PublishTravel [td=" + td + ", teamName=" + teamName
				+ ", allDay=" + allDay + ", statPoint=" + statPoint
				+ ", destination=" + destination + ", sex=" + sex + ", city="
				+ city + ", peopleNumber=" + peopleNumber + ", startTime="
				+ startTime + ", arriveTime=" + arriveTime
				+ ", urlLifePicture=" + urlLifePicture + ", viewPoint="
				+ viewPoint + ", intro=" + intro + ", createTime=" + createTime
				+ ", ld=" + ld + "]";
	}
	
	
//	@Override
//	public String toString() {
//		return "PublishTravel [td=" + td + ", teamName=" + teamName
//				+ ", allDay=" + allDay + ", statPoint=" + statPoint
//				+ ", destination=" + destination + ", sex=" + sex + ", city="
//				+ city + ", startTime=" + ToolDao.setTimedate1(startTime) + ", arriveTime="
//				+ ToolDao.setTimedate1(arriveTime) + ", urlLifePicture=" + urlLifePicture
//				+ ", viewPoint=" + viewPoint + ", intro=" + intro
//				+ ", createTime=" +ToolDao.setTimedate(createTime) + "]";
//	}
    
	
}
