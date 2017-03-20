package com.yiche.app;

public class AppConfig {


	public static final String URL = "http://192.168.1.9:8090/"; // 青青
	public final static String urlpic = "http://www.ydcylc.com"; // url地址

	public final static String GETAUTO=URL+"app/account/sendcode";//获取短信验证码
	public final static String REGISTER=URL+"app/account/regist";//用户注册
	public final static String LOGIN=URL+"app/account/login";//用户登录
	public final static String RECOMMENDINFO=URL+"app";//用户注册


	public static final  boolean DEBUG = true;
	public static final String API_REFRESH_TOKEN =URL+ "user/refreshToken.html";// 刷新登录接口



	public static final  String BANNERPIC = URL + "app_getBanners";

	public final static String WEBSUDAI="http://app.51quickenloans.com/51sudai/";
	public final static String ISAVALIABLE=URL+"app/user/checkPhoneAvailable.html";//验证手机是否可用

	public final static String GETRESETPWDCODE=URL+"app/user/getResetPwdCode.html";//获取忘记密码验证码
	public final static String CHECKRESETPWDCODE=URL+"app/user/checkResetPwdCode.html";//验证获取忘记密码验证码
	public final static String RESETPWD=URL+"app/user/resetPwd.html";//重置密码

	public final static String APPKEY = "CB2275A3529121C61D071C627C261223";
	public final static String APPSECRET = "529121C61D071C62";
}
