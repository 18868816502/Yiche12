package com.yiche.net;

import java.io.File;
import java.lang.reflect.Field;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.TreeMap;

import android.app.Application;

import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.HttpHandler;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lidroid.xutils.http.client.HttpRequest.HttpMethod;
import com.yiche.app.AppConfig;
import com.yiche.app.MyApplication;
import com.yiche.util.AppTools;
import com.yiche.util.SharedpreferencesUitls;

/**
 * @author xst
 */
public final class NetUtils {
	/**
	 * Xutils网络请求类
	 */
	public static void send(final String url, final Object sBean,
			EasyRequset xRequset) {
		try {
			send(HttpMethod.POST, url,
					TOUtils.convertBeanToMap(sBean), xRequset);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void send(final HttpMethod method, String url,
			final TreeMap<String, Object> map, RequestCallBack<String> xRequset) {
		final HttpUtils hUtils = MyApplication.getInstance().getHttp();
		final RequestParams params = getRequestParams(map);
		hUtils.send(method, url, params, xRequset);
	}

	/**
	 * bean转化为TreeMap
	 * 
	 * @author xst
	 */

	public static TreeMap<String, Object> convertBeanToMap(Object bean) {
		Field[] fields = bean.getClass().getDeclaredFields();
		TreeMap<String, Object> data = new TreeMap<String, Object>();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				data.put(field.getName(), field.get(bean));
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
		return data;
	}

	/**
	 * map中的键值对加入到请求队列中
	 * 
	 * @param map
	 * @return
	 */
	public static RequestParams getRequestParams(
			final TreeMap<String, Object> map) {
		final RequestParams params = new RequestParams();
		map.put("appkey", AppConfig.APPKEY);
		String ts = String.valueOf(System.currentTimeMillis());
		ts = ts.substring(0, ts.length() - 3);
		map.put("ts", ts);
		if(SharedpreferencesUitls.getUserId(MyApplication.getInstance().getApplicationContext())!=null&&SharedpreferencesUitls.getToken(MyApplication.getInstance().getApplicationContext())!=null){
			map.put("user_id", SharedpreferencesUitls.getUserId(MyApplication
					.getInstance().getApplicationContext()));
			map.put("oauth_token", SharedpreferencesUitls.getToken(MyApplication.getInstance().getApplicationContext()));
		}
		map.put("signa", getSign(ts));
		final LinkedList<String> sortedlist = new LinkedList<String>(
				map.keySet());
		Collections.sort(sortedlist);
		final HashMap<String, Object> sortedMap = new HashMap<String, Object>();
		for (String key : sortedlist) {
			sortedMap.put(key, map.get(key));
			if (key.contains("file")) {
				params.addBodyParameter(key, (File) map.get(key), "image/jpg");// 上传文件
			} else {
				params.addBodyParameter(key, map.get(key).toString());
			}
		} 
//		params.addBodyParameter("signa", getSign(ts));
//		System.out.println("appkey:"+AppConfig.APPKEY+"\nts:"+ts+"\nsigna:"+getSign(ts));
		return params;
	}

	public static String getSign(String ts) {
		String sign = AppTools.encryption(AppConfig.APPSECRET + ts)
				+ AppConfig.APPKEY;
		sign = AppTools.encryption(sign).toUpperCase();
		return sign;
	}
}
