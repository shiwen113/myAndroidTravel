package com.gem.scenery.entity;

import com.gem.home.until.LoginData;
/*
 * 表名 personaldata
主键 fdd
上传图片uiruploadpicture
(用户名 username)ld
性别 sex
年龄 age
常在地 oftenpoint
 */

/**
 * 个人资料
 */
public class PersonalData {
	private int fdd;//主键
	private String uriUpLoadPicture;//上传的图标
	private LoginData ld;//用户
	private int sex;//性别 1表示男，0表示女
	private int age;//年龄
	private String oftenPoint;//常在地
	public int getFdd() {
		return fdd;
	}
	public void setFdd(int fdd) {
		this.fdd = fdd;
	}
	public String getUriUpLoadPicture() {
		return uriUpLoadPicture;
	}
	public void setUriUpLoadPicture(String uriUpLoadPicture) {
		this.uriUpLoadPicture = uriUpLoadPicture;
	}

	public int getSex() {
		return sex;
	}
	public void setSex(int sex) {
		this.sex = sex;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getOftenPoint() {
		return oftenPoint;
	}
	public void setOftenPoint(String oftenPoint) {
		this.oftenPoint = oftenPoint;
	}
	public LoginData getLd() {
		return ld;
	}
	public void setLd(LoginData ld) {
		this.ld = ld;
	}
	@Override
	public String toString() {
		return "PersonalData [fdd=" + fdd + ", uriUpLoadPicture="
				+ uriUpLoadPicture + ", ld=" + ld + ", sex=" + sex + ", age="
				+ age + ", oftenPoint=" + oftenPoint + "]";
	}
//	@Override
//	public String toString() {
//		return "PersonalData [fdd=" + fdd + ", uriUpLoadPicture=" + uriUpLoadPicture + ", sex=" + sex + ", age=" + age
//				+ ", oftenPoint=" + oftenPoint + "]";
//	}

	
	
}
