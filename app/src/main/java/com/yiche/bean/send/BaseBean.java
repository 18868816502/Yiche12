package com.yiche.bean.send;


import com.yiche.app.MyApplication;
import com.yiche.util.SharedpreferencesUitls;

public class BaseBean {

	private String user_id = SharedpreferencesUitls.getUserId(MyApplication
			.getInstance().getApplicationContext());
	private String oauth_token = SharedpreferencesUitls.getToken(MyApplication
			.getInstance().getApplicationContext());// （需要授权的接口都需要user_id和oauth_token）
    public   UserBean userBean;
    public  String noAgree;
    
    
    
    
    
    
	public String getNoAgree() {
		return noAgree;
	}

	public void setNoAgree(String noAgree) {
		this.noAgree = noAgree;
	}

	public UserBean getUserBean() {
		return userBean;
	}

	public void setUserBean(UserBean userBean) {
		this.userBean = userBean;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	public String getOauth_token() {
		return oauth_token;
	}

	public void setOauth_token(String oauth_token) {
		this.oauth_token = oauth_token;
	}

}
