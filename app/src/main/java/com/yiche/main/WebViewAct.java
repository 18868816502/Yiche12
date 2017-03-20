package com.yiche.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.TextView;

import com.yiche.R;

public class WebViewAct extends BaseAct {
	private WebView webView;
	private String title = "";
//	private Button bt_operate;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_webview);
		init();

	}

	/**
	 * 控件初始化
	 */
	private void init() {

		String url = getIntent().getStringExtra("url");
		title = getIntent().getStringExtra("title");
		
		webView = (WebView) findViewById(R.id.webView);
//		bt_operate = (Button) findViewById(R.id.bt_operate);
		String urlData = getIntent().getStringExtra("urlData");
		if (getIntent().getBooleanExtra("isShowButton", false)) {
//			bt_operate.setVisibility(View.VISIBLE);
//			bt_operate.setText(getIntent().getStringExtra("buttonData"));
//			bt_operate.setOnClickListener(this);
		}
		if (url != null) {
			webView.loadUrl(url);
		}
		if (urlData != null) {
			webView.loadData(urlData, "text/html;charset=UTF-8",null);
		}

		setHeader(true, title, null, null);
		// WebView加载web资源
		// String a=
		// "<p><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right; white-space: normal;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right; white-space: normal;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right; white-space: normal;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right; white-space: normal;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right; white-space: normal;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right; line-height: 30px;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right;\">内容</span><span style=\"color: rgb(0, 0, 0); font-size: 14px; text-align: right;\">内容<img src=\"http://static.yuanxc.com/articleEditor/1604270465161105-FFD8FF/view.html\" _src=\"http://static.yuanxc.com/articleEditor/1604270465161105-FFD8FF/view.html\"/></span></p>";
		// webView.loadData(a, "text/html;charset=UTF-8",null);

		// 覆盖WebView默认使用第三方或系统默认浏览器打开网页的行为，使网页用WebView打开
		webView.setWebViewClient(new WebViewClient() {
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				// 返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
				view.loadUrl(url);
				return true;
			}
		});
		// 启用支持javascript
		WebSettings settings = webView.getSettings();
		// 设置加载进来的页面自适应手机屏幕
		settings.setUseWideViewPort(true);
		settings.setLoadWithOverviewMode(true);
		settings.setJavaScriptEnabled(true);
		webView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);// 优先使用缓存
		// LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
		// LOAD_DEFAULT: 根据cache-control决定是否从网络上取数据。
		// LOAD_CACHE_NORMAL: API level 17中已经废弃, 从API level
		// 11开始作用同LOAD_DEFAULT模式
		// LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
		// LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
	}

//	public boolean onKeyDown(int keyCode, KeyEvent event) {
//		// 禁用回退按钮
//		if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
//			return false;
//		}
//		return false;
//	}
	/*
	 * 如果希望浏览的网页后退而不是退出浏览器，需要WebView覆盖URL加载，让它自动生成历史访问记录，那样就可以通过前进或后退访问已访问过的站点。
	 */
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			if (webView.canGoBack()) {
				webView.goBack();// 返回上一页面
				return true;
			}
			// else
			// {
			// System.exit(0);//退出程序
			// super.onBackPressed();
			// }
		}
		return super.onKeyDown(keyCode, event);
	}


	@Override
	public void onClick(View arg0) {
		Intent intent;
		switch (arg0.getId()) {
		// 同意协议
//		case R.id.bt_operate:
//			intent=new Intent(WebViewAct.this,CertificationSuccessAct.class);
//			startActivity(intent);
//			finish();
//			break;
		default:
			break;
		}

	}
}
