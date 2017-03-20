package com.yiche.main;

import org.json.JSONException;
import org.json.JSONObject;


import com.google.gson.Gson;
import com.yiche.R;
import com.yiche.app.AppConfig;
import com.yiche.bean.send.LoginBean;
import com.yiche.model.NetRequestClass;
import com.yiche.net.EasyRequset;
import com.yiche.net.NetUtils;
import com.yiche.util.AppTools;
import com.yiche.util.StringCheck;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class LoginAct extends BaseAct implements TextWatcher {
	private EditText et_phoneNumber;
	private EditText et_pwd;
	private ImageView iv_clearPhone;
	private ImageView iv_clearPwd;
	private Button bt_login;
	private TextView tv_forgetPwd;

	private String phoneNumber;// 手机号码
	private String password;// 密码

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_login);
		init();
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		setHeader(true, "登录", new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(LoginAct.this, RegisterAct.class);
				startActivityForResult(intent, 1);
			}
		}, "注册");

		et_phoneNumber = (EditText) findViewById(R.id.et_phoneNumber);
		
		et_phoneNumber.addTextChangedListener(this);
		et_pwd = (EditText) findViewById(R.id.et_pwd);
		et_pwd.addTextChangedListener(this);
		tv_forgetPwd = (TextView) findViewById(R.id.tv_forgetPwd);
		tv_forgetPwd.setOnClickListener(this);
		iv_clearPhone = (ImageView) findViewById(R.id.iv_clearPhone);
		iv_clearPhone.setOnClickListener(this);
		iv_clearPwd = (ImageView) findViewById(R.id.iv_clearPwd);
		iv_clearPwd.setOnClickListener(this);
		bt_login = (Button) findViewById(R.id.bt_login);
		bt_login.setOnClickListener(this);
	}

//	// 设置极光推送的别名
//	public void setAlias() {
//		JPushInterface.setAlias(getApplicationContext(), et_phoneNumber.getText()
//				.toString(), null);
//		// ToastUtil.showToast(getApplicationContext(), "YES");
//	}
	@Override
	public void onClick(View v) {
		Intent intent;
		switch (v.getId()) {
		// 登录
		case R.id.bt_login:
			CheckLogin();
			break;
		// 清空登录账号
		case R.id.iv_clearPhone:
			et_phoneNumber.setText("");
			break;
		// 清空密码
		case R.id.iv_clearPwd:
			et_pwd.setText("");
			break;
		// 忘记密码
		case R.id.tv_forgetPwd:
			intent = new Intent(LoginAct.this, FindPwdAct.class);
			startActivity(intent);
			break;

		default:
			break;
		}

	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) {
			String mobile = data.getStringExtra("mobile");
			String password = data.getStringExtra("password");
			NetRequestClass.login(mobile, password,this.getParent(),true);
		}
	}

	/**
	 * 验证登录
	 */
	private void CheckLogin() {
		phoneNumber = et_phoneNumber.getText().toString().trim();
		password = et_pwd.getText().toString().trim();
		if (StringCheck.emptyOrNull(phoneNumber)) {
			AppTools.toast("请输入手机号");
			return;
		}
		if (!StringCheck.isMobileNO(phoneNumber)) {
			AppTools.toast("手机号不正确");
			return;
		}
		if (StringCheck.emptyOrNull(password)) {
			AppTools.toast("请输入密码");
			return;
		}
		NetRequestClass.login(phoneNumber, password,this.getParent(),false);
	}

	@Override
	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {

	}

	@Override
	public void onTextChanged(CharSequence s, int start, int before, int count) {

	}

	@Override
	public void afterTextChanged(Editable s) {
		phoneNumber = et_phoneNumber.getText().toString().trim();
		password = et_pwd.getText().toString().trim();
		if (phoneNumber.length() > 0) {
			
			iv_clearPhone.setVisibility(View.VISIBLE);
		} else {
			iv_clearPhone.setVisibility(View.INVISIBLE);
		}
		if (password.length() > 0) {
			iv_clearPwd.setVisibility(View.VISIBLE);
		} else {
			iv_clearPwd.setVisibility(View.INVISIBLE);
		}

	}


	
	/**
	 * 获取用户各种信息并发送给后台
	 */
//	private void getUserInfoAndSend() {
//		Tools.getUserInfoAndSend(LoginAct.this, 2);
//	}


}
