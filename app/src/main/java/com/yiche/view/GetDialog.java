package com.yiche.view;

import com.yiche.R;
import com.yiche.net.CustomProgressDialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

public class GetDialog {

	private CustomProgressDialog progressDialog = null;

	public CustomProgressDialog getLoginDialog(Context paramActivity,
			String paramMess) {
		if (progressDialog == null)
			progressDialog = CustomProgressDialog.createDialog(paramActivity);
		progressDialog.setMessage(paramMess);
		progressDialog.setCancelable(true);
		return progressDialog;
	}

	public EditText et_carType;
	public EditText et_phone;

	public Dialog orderDialog;

	public Dialog getOrderDialog(final Context context,
			final OnClickListener submit) {
		orderDialog = new Dialog(context, R.style.dialog_style) {
			LinearLayout layout;

			@Override
			protected void onCreate(Bundle savedInstanceState) {
				super.onCreate(savedInstanceState);
				LayoutInflater inflater = (LayoutInflater) context
						.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
				layout = (LinearLayout) inflater.inflate(R.layout.dialog_order,
						null);
				
				et_carType = (EditText) layout.findViewById(R.id.et_carType);
				et_phone = (EditText) layout.findViewById(R.id.et_phone);
				Button bt_submit = (Button) layout.findViewById(R.id.bt_submit);
				bt_submit.setOnClickListener(submit);

				setContentView(layout);
			}
		};
		return orderDialog;

	}

}
