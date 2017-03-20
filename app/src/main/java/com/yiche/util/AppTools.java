package com.yiche.util;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Enumeration;
import java.util.List;


import android.annotation.SuppressLint;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.Gravity;
import android.widget.Toast;

import com.yiche.app.MyApplication;

@SuppressLint("SimpleDateFormat")
public class AppTools {

	// 接口访问加密
	@SuppressLint("DefaultLocale")
	// public static String httpPermission(long time) {
	// return encryption(
	// encryption(String.valueOf(BaseParam.AILC_SEC + time))
	// + BaseParam.AILC_APPKEY).toUpperCase();
	// }
	//
	/**
	 * toast 过滤3s内禁止重复出现
	 *
	 */
	private static final long Interval = 3 * 1000;
	private static SoftMap<String, Long> map = new SoftMap<String, Long>();

	private static Toast CURR_TOAST;

	public static void toast(String msg) {
		long preTime = 0;
		if (map.containsKey(msg)) {
			preTime = map.get(msg);
		}
		final long now = System.currentTimeMillis();
		if (now >= preTime + Interval) {
			if (CURR_TOAST != null) {
				CURR_TOAST.cancel();
			}
			Toast toast = Toast.makeText(MyApplication.getInstance()
					.getApplicationContext(), msg, Toast.LENGTH_SHORT);
			toast.setGravity(Gravity.CENTER, 0, 0);
			toast.show();
			map.put(msg, now);

			CURR_TOAST = toast;
		}
	}

	/**
	 * 判断网络连接状况
	 *
	 */
	public static boolean isConnect(Context context) {
		// 获取手机所有连接管理对象（包括对wi-fi,net等连接的管理）
		try {
			ConnectivityManager connectivity = (ConnectivityManager) context
					.getSystemService(Context.CONNECTIVITY_SERVICE);
			if (connectivity != null) {
				// 获取网络连接管理的对象
				NetworkInfo info = connectivity.getActiveNetworkInfo();
				if (info != null && info.isConnected()) {
					// 判断当前网络是否已经连接
					if (info.getState() == NetworkInfo.State.CONNECTED) {
						return true;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		toast("网络不给力,请稍后再试!");
		return false;
	}

	/**
	 * 获取版本号
	 *
	 * @return 当前应用的版本号
	 */
	public static String getVersion() {
		Context context = MyApplication.getInstance();
		try {
			PackageManager manager = context.getPackageManager();
			PackageInfo info = manager.getPackageInfo(context.getPackageName(),
					0);
			String version = info.versionName;
			return "当前版本  V" + version;
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	/**
	 * 连续2s按返回键退出程序
	 */
	private static long lastExitPressedMills = 0;
	private static final long MAX_DOUBLE_EXIT_MILLS = 2000;

	public static void onExit() {
		final long now = System.currentTimeMillis();
		if (now <= lastExitPressedMills + MAX_DOUBLE_EXIT_MILLS) {
			System.exit(0);
		} else {
			AppTools.toast("再按一次退出应用");
			lastExitPressedMills = now;
		}
	}

	/**
	 * 获取当前位置经纬度
	 */
//	public static Location getLocationInfo(Context context) {
//		String locationProvider;
//		// 获取地理位置管理器
//		LocationManager locationManager = (LocationManager) context
//				.getSystemService(Context.LOCATION_SERVICE);
//		// 获取所有可用的位置提供器
//		List<String> providers = locationManager.getProviders(true);
//		if (providers.contains(LocationManager.GPS_PROVIDER)) {
//			// 如果是GPS
//			locationProvider = LocationManager.GPS_PROVIDER;
//		} else if (providers.contains(LocationManager.NETWORK_PROVIDER)) {
//			// 如果是Network
//			locationProvider = LocationManager.NETWORK_PROVIDER;
//		} else {
//			return null;
//		}
//		// 获取Location
//		Location location = locationManager
//				.getLastKnownLocation(locationProvider);
//		return location;
//	}

	/**
	 * 获取手机ip地址
	 */
	public static String getPhoneIp() {
		try {
			for (Enumeration<NetworkInterface> en = NetworkInterface
					.getNetworkInterfaces(); en.hasMoreElements();) {
				NetworkInterface intf = en.nextElement();
				for (Enumeration<InetAddress> enumIpAddr = intf
						.getInetAddresses(); enumIpAddr.hasMoreElements();) {
					InetAddress inetAddress = enumIpAddr.nextElement();
					if (!inetAddress.isLoopbackAddress()
							&& inetAddress instanceof Inet4Address) {
						// if (!inetAddress.isLoopbackAddress() && inetAddress
						// instanceof Inet6Address) {
						return inetAddress.getHostAddress().toString();
					}
				}
			}
		} catch (Exception e) {
			System.out.println("无线网状态下");
		}
		return "127.0.0.1";
	}

	// 32MD5加密
	public static String encryption(String plainText) {
		String re_md5 = new String();
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(plainText.getBytes());
			byte b[] = md.digest();
			int i;
			StringBuffer buf = new StringBuffer("");
			for (int offset = 0; offset < b.length; offset++) {
				i = b[offset];
				if (i < 0)
					i += 256;
				if (i < 16)
					buf.append("0");
				buf.append(Integer.toHexString(i));
			}
			re_md5 = buf.toString();
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return re_md5;
	}
}
