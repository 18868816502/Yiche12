package com.yiche.bean.send;

public class UserBean {
	private String name;
	private String id;
	/**
	 * 授权口令
	 */
	private String oauth_token;//
	/**
	 * 刷新授权口令
	 */
	private String refresh_token;//
	public String getOauth_token() {
		return oauth_token;
	}
	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}
	public String getRefresh_token() {
		return refresh_token;
	}
	public void setRefresh_token(String refresh_token) {
		this.refresh_token = refresh_token;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

}
