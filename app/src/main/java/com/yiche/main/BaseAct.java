package com.yiche.main;

import com.yiche.R;

import android.annotation.SuppressLint;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View.OnClickListener;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public abstract class BaseAct extends FragmentActivity implements
		OnClickListener {
	public TextView tv_title;// 中间标题
	public TextView tv_rightText;// 右边文本
	public ImageView iv_leftImage;// 左边图片
	public ImageView iv_rightImage;// 右边图片
	public LinearLayout ll_backLayout;// 整个回退布局

	public RelativeLayout app_tite;// 整个标题

	@SuppressLint("InlinedApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE); // 设置隐藏标题

		// 支持android4.4以上的沉浸式状态栏
//		if (Build.VERSION.SDK_INT >= 19) {
//			getWindow().addFlags(
//					WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//		}

		setContentView(R.layout.app_header);
	}

	/**
	 * 初始化控件
	 */
	public void initHeader() {
		tv_title = (TextView) findViewById(R.id.tv_title);
		tv_rightText = (TextView) findViewById(R.id.tv_rightText);
		iv_leftImage = (ImageView) findViewById(R.id.iv_leftImage);
		iv_rightImage = (ImageView) findViewById(R.id.iv_rightImage);
		ll_backLayout = (LinearLayout) findViewById(R.id.ll_backLayout);
	}

	/**
	 * 设置标题栏
	 */
	public void setHeader(Boolean showBack, String title,
			OnClickListener ensureListener, String rightText) {
		initHeader();
		if (showBack) {
			ll_backLayout.setVisibility(View.VISIBLE);
			// 设置返回
			ll_backLayout.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					finish();
				}
			});
		} else {
			ll_backLayout.setVisibility(View.GONE);
		}
		if (tv_title != null) {
			tv_title.setText(title);
		}
		if (rightText != null) {
			tv_rightText.setVisibility(View.VISIBLE);
			tv_rightText.setText(rightText);
			tv_rightText.setOnClickListener(ensureListener);
		} else {
			tv_rightText.setVisibility(View.GONE);
		}
	}

	/**
	 * 给组件添加点击事件
	 * 
	 * @param view
	 */
	public void setOnClick(View... view) {
		for (View v : view) {
			v.setOnClickListener(this);
		}
	}
}
