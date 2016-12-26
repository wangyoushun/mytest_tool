package cn.six.test;

import java.util.Date;

public class User {

	private Integer id;
	private String userCode;
	private Date loginDate;
	private String userPassword;
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getUserCode() {
		return userCode;
	}
	public void setUserCode(String userCode) {
		this.userCode = userCode;
	}
	public Date getLoginDate() {
		return loginDate;
	}
	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", userCode=" + userCode + ", loginDate="
				+ loginDate + ", userPassword=" + userPassword + "]";
	}
	
}
