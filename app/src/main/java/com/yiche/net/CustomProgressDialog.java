package com.yiche.net;




import android.app.Dialog;
import android.content.Context;
import android.widget.TextView;

import com.yiche.R;


public class CustomProgressDialog extends Dialog {
	private static CustomProgressDialog customProgressDialog = null;

	public CustomProgressDialog(Context context) {
		super(context);
	}

	public CustomProgressDialog(Context context, int theme) {
		super(context, theme);
	}

	public static CustomProgressDialog createDialog(Context context) {
		customProgressDialog = new CustomProgressDialog(context,
				R.style.CustomProgressDialog);
		customProgressDialog.setContentView(R.layout.dialog_loading);
		return customProgressDialog;
	}

	public void onWindowFocusChanged(boolean hasFocus) {

		if (customProgressDialog == null) {
			return;
		}
	}

	/**
	 * 
	 * [Summary] setTitile 弹出框
	 * 
	 * @param strTitle
	 * @return
	 * 
	 */
	public CustomProgressDialog setTitile(String strTitle) {
		return customProgressDialog;
	}

	private TextView tvMsg = null;

	/**
	 * 
	 * [Summary] setMessage设置显示信息
	 * 
	 * @param strMessage
	 * @return
	 * 
	 */
	public CustomProgressDialog setMessage(String strMessage) {
		if (null == tvMsg) {
			tvMsg = (TextView) customProgressDialog
					.findViewById(R.id.id_tv_loadingmsg);
		}
		tvMsg.setText(strMessage);
		return customProgressDialog;
	}
}