package com.yiche.bean.send;

public class ResetPwdBean {

	private String phone_or_email;// 手机号码
	private String pwd;// 密码
	private String session_id;//会话id重置密码凭证

	public String getPhone_or_email() {
		return phone_or_email;
	}
	public void setPhone_or_email(String phone_or_email) {
		this.phone_or_email = phone_or_email;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getSession_id() {
		return session_id;
	}
	public void setSession_id(String session_id) {
		this.session_id = session_id;
	}



}
