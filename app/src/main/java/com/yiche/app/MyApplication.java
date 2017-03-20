package com.yiche.app;

import android.app.Activity;
import android.app.Application;

import com.lidroid.xutils.HttpUtils;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yiche.R;

import java.util.ArrayList;

public class MyApplication extends Application {
	private static ArrayList<Activity> activityList = new ArrayList<Activity>();
	private static MyApplication instance;

	public static boolean isFirst = true;


	public static MyApplication getInstance() {
		if (instance == null) {
			instance = new MyApplication();
		}
		return instance;
	}

	private HttpUtils http;

	@Override
	public void onCreate() {
		super.onCreate();
//		SDKInitializer.initialize(this);
//		JPushInterface.setDebugMode(true); // 设置开启日志,发布时请关闭日志
//		JPushInterface.init(this);
		instance = this;
		http = new HttpUtils();
		http.configSoTimeout(50000);
		http.configUserAgent("Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");
		initImageLoader();
	}

	public HttpUtils getHttp() {
		return http;
	}

	public void setHttp(HttpUtils http) {
		this.http = http;
	}

	/**
	 * 退出系统 退出系统时，很方便地清楚掉所有的activity
	 *
	 */
	public void exit() {
		for (Activity activity : activityList) {
			if (!activity.isFinishing()) {
				activity.finish();
			}
		}
	}

//	public LockPatternUtils getLockPatternUtils() {
//		if (mLockPatternUtils == null) {
//			mLockPatternUtils = new LockPatternUtils(this);
//		}
//		return mLockPatternUtils;
//	}

	private void initImageLoader() {
		// 网络图片例子,结合常用的图片缓存库UIL,你可以根据自己需求自己换其他网络图片�?
		DisplayImageOptions defaultOptions = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.mipmap.ic_default_image)
				.cacheInMemory(true).cacheOnDisk(true).build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(
				getApplicationContext())
				.defaultDisplayImageOptions(defaultOptions)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.diskCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO).build();
		ImageLoader.getInstance().init(config);
	}

}
