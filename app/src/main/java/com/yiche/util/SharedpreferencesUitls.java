package com.yiche.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * 
 * @ClassName: SharedpreferensUitls
 * @Description:TODO(获取登陆或者注册后的权限)，保存想保存的变量。方便本地变量判断。持久化
 * @author: xst
 * @date: 2016年8月25日
 * 
 */
public class SharedpreferencesUitls {
	/**
	 * 保存用户id
	 */
	public static void saveUserId(Context context, String userId) {
		SharedPreferences sharepre = context.getSharedPreferences("userId",
				Context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.putString("userId", userId);
		ed.commit();
	}

	/**
	 * 获取用户id
	 */
	public static String getUserId(Context context) {
		SharedPreferences share = context.getSharedPreferences("userId",
				Context.MODE_PRIVATE);
		String userId = share.getString("userId", null);
		return userId;
	}
	/**
	 * 保存用户名称
	 * 
	 */
	public static void saveUserName(Context context, String userName) {
		// TODO Auto-generated method stub
		SharedPreferences sharepre = context.getSharedPreferences("userName",
				context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.putString("userName", userName);
		ed.commit();
	}

	/**
	 * 获取用户名称
	 * 
	 * @param context
	 */
	public static String getUserName(Context context) {
		SharedPreferences share = context.getSharedPreferences("userName",
				context.MODE_PRIVATE);
		String userId = share.getString("userName", null);
		return userId;
	}
	/**
	 * 保存用户token
	 */
	public static void saveToken(Context context, String token) {
		SharedPreferences sharepre = context.getSharedPreferences("token",
				Context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.putString("token", token);
		ed.commit();
	}

	/**
	 * 获取用户token
	 */
	public static String getToken(Context context) {
		SharedPreferences share = context.getSharedPreferences("token",
				Context.MODE_PRIVATE);
		String token = share.getString("token", null);
		return token;
	}

	/**
	 * 版本更新
	 * 
	 */

	public static void saveVersioninfo(Context context, String versioninfo) {
		// TODO Auto-generated method stub
		SharedPreferences sharepre = context.getSharedPreferences(
				"versioninfo", context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		// numberphone=numberphone.substring(0,3)+"****"+numberphone.substring(7,11);
		ed.putString("versioninfo", versioninfo);
		ed.commit();
	}

	public static String getVersionInfo(Context context) {
		SharedPreferences share = context.getSharedPreferences("versioninfo",
				context.MODE_PRIVATE);
		String userId = share.getString("versioninfo", "1.0");
		return userId;
	}
	/**
	 * 保存用户手机号
	 */
	public static void saveUserPhoneNumber(Context context, String phoneNumber) {
		SharedPreferences sharepre = context.getSharedPreferences(
				"phoneNumber", context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.putString("phoneNumber", phoneNumber);
		ed.commit();
	}

	/**
	 * 获取用户手机号
	 */
	public static String getUserPhoneNumber(Context context) {
		SharedPreferences share = context.getSharedPreferences("phoneNumber",
				context.MODE_PRIVATE);
		String phoneNumber = share.getString("phoneNumber", null);
		return phoneNumber;
	}

	/**
	 * 保存手势密码
	 */
	public static void saveGesturePwd(Context context, String gesturePwd) {
		SharedPreferences sharepre = context.getSharedPreferences("gesturePwd",
				context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.putString("gesturePwd", gesturePwd);
		ed.commit();
	}

	/**
	 * 清除用户电话号码
	 */
	public static void ClearUserNumberPhone(Context context) {
		SharedPreferences share = context.getSharedPreferences("phoneNumber",
				context.MODE_PRIVATE);
		Editor ed = share.edit();
		ed.clear();
		ed.commit();
	}

	/**
	 * 清除UserId
	 */
	public static void clearUserId(Context context) {
		SharedPreferences sharepre = context.getSharedPreferences("userId",
				context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.clear();
		ed.commit();
	}

	/**
	 * 清除token
	 */
	public static void clearToken(Context context) {
		SharedPreferences sharepre = context.getSharedPreferences("token",
				context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.clear();
		ed.commit();
	}

	/**
	 * 保存用户头像
	 * 
	 */
	public static void saveHeadPortrait(Context context, String userheadportrait) {
		// TODO Auto-generated method stub
		SharedPreferences sharepre = context.getSharedPreferences(
				"headPortrait", context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.putString("headPortrait", userheadportrait);
		ed.commit();
	}

	/**
	 * 获取用户头像
	 * 
	 * @param context
	 */
	public static String getHeadPortrait(Context context) {
		SharedPreferences share = context.getSharedPreferences("headPortrait",
				context.MODE_PRIVATE);
		String userId = share.getString("headPortrait", null);
		return userId;
	}

	/**
	 * 获取手势密码
	 */
	public static String getGesturePwd(Context context) {
		SharedPreferences share = context.getSharedPreferences("gesturePwd",
				context.MODE_PRIVATE);
		String gesturePwd = share.getString("gesturePwd", null);
		return gesturePwd;
	}

	/**
	 * 清除手势密码
	 */
	public static void clearGesturePwd(Context context) {
		SharedPreferences sharepre = context.getSharedPreferences("gesturePwd",
				context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.clear();
		ed.commit();
	}

	/**
	 * 保存用户是否是第一次登录
	 */
	public static void saveIsFirstLogin(Context context, Boolean isFirstLogin) {
		SharedPreferences sharepre = context.getSharedPreferences(
				"isFirstLogin", context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.putBoolean("isFirstLogin", isFirstLogin);
		ed.commit();
	}

	// 保存下载路径
	public static void saveLoadUrl(Context context, String loadUrl) {
		SharedPreferences sharepre = context.getSharedPreferences("loadUrl",
				context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.putString("loadUrl", loadUrl);
		ed.commit();
	}

	// 获取下载路径
	public static String getLoadUrl(Context context) {
		// TODO Auto-generated method stub
		SharedPreferences sharepre = context.getSharedPreferences("loadUrl",
				context.MODE_PRIVATE);
		String loadUrl = sharepre.getString("loadUrl", null);
		return loadUrl;
	}

	/**
	 * 获取用户是否是第一次登录
	 */
	public static Boolean getIsFirstLogin(Context context) {
		SharedPreferences share = context.getSharedPreferences("isFirstLogin",
				context.MODE_PRIVATE);
		Boolean isFirstLogin = share.getBoolean("isFirstLogin", true);
		return isFirstLogin;
	}
	/**
	 *  保存广告ID
	 */
	public static void saveAdvId(Context context, int advId) {
		SharedPreferences sharepre = context.getSharedPreferences("advId",
				context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.putInt("advId", advId);
		ed.commit();
	}

	/**
	 *  获取广告ID
	 */
	public static int getAdvId(Context context) {
		// TODO Auto-generated method stub
		SharedPreferences sharepre = context.getSharedPreferences("advId",
				context.MODE_PRIVATE);
		int advId = sharepre.getInt("advId", 0);
		return advId;
	}
	/**
	 *  保存身份证号码
	 */
	public static void saveIdNumber(Context context, String idNumber) {
		SharedPreferences sharepre = context.getSharedPreferences("idNumber",
				context.MODE_PRIVATE);
		Editor ed = sharepre.edit();
		ed.putString("idNumber", idNumber);
		ed.commit();
	}
	
	/**
	 *  获取身份证号码
	 */
	public static String getIdNumber(Context context) {
		// TODO Auto-generated method stub
		SharedPreferences sharepre = context.getSharedPreferences("idNumber",
				context.MODE_PRIVATE);
		String idNumber = sharepre.getString("idNumber", "");
		return idNumber;
	}
}
