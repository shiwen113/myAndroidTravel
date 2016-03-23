package com.gem.home.until;

/*
 * 表名 logindata
主键 ld
账号 account
密码 password
 */
/*
 * *注册信息表：
 */
public class LoginData {
	private int ld;//主键
	private String account;//账号
	private String userName;//用户名
	private String passWord;//密码
	public String getAccount() {
		return account;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public String getPassWord() {
		return passWord;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	
	public int getLd() {
		return ld;
	}
	public void setLd(int ld) {
		this.ld = ld;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String toString() {
		return "LoginData [ld=" + ld + ", account=" + account + ", userName="
				+ userName + ", passWord=" + passWord + "]";
	}

	
}
