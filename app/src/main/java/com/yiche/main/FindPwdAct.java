package com.yiche.main;

import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;
import com.yiche.R;
import com.yiche.app.AppConfig;
import com.yiche.bean.send.AutoCodeBean;
import com.yiche.bean.send.ResetPwdBean;
import com.yiche.model.NetRequestClass;
import com.yiche.net.EasyRequset;
import com.yiche.net.NetUtils;
import com.yiche.util.AppTools;
import com.yiche.util.CutDownTimer;
import com.yiche.util.StringCheck;

import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Base64;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class FindPwdAct extends BaseAct implements OnClickListener {
	private EditText et_phoneNumber;
	private EditText et_newPwd;
	private EditText et_authCode;
	private ImageView iv_showPwd;
	private TextView tv_setAutoCode;
	private Button bt_change;

	private String phoneNumber;// 手机号
	private String newPwd;// 新密码
	private String autoCode;// 验证码
	private Boolean isHidden = false;// 是否显示密码 默认为隐藏密码

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act_findpwd);
		init();
	}

	/**
	 * 初始化控件
	 */
	private void init() {
		setHeader(true, "找回登录密码", null, null);
		et_phoneNumber = (EditText) findViewById(R.id.et_phoneNumber);
		et_newPwd = (EditText) findViewById(R.id.et_newPwd);
		et_authCode = (EditText) findViewById(R.id.et_authCode);
		iv_showPwd = (ImageView) findViewById(R.id.iv_showPwd);
		iv_showPwd.setOnClickListener(this);
		tv_setAutoCode = (TextView) findViewById(R.id.tv_setAutoCode);
		tv_setAutoCode.setOnClickListener(this);
		bt_change = (Button) findViewById(R.id.bt_change);
		bt_change.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		// 重置
		case R.id.bt_change:
			CheckChange();
			break;
		// 显示或隐藏密码
		case R.id.iv_showPwd:
			showPassword();
			break;
		// 发送短信验证码
		case R.id.tv_setAutoCode:
			phoneNumber = et_phoneNumber.getText().toString().trim();
			if (!StringCheck.isMobileNO(phoneNumber)) {
				AppTools.toast("请输入正确的手机号");
				return;
			}

			NetRequestClass.sendAutoCode(phoneNumber,"forget",this,tv_setAutoCode);
			break;
		default:
			break;
		}
	}

	/**
	 * 验证格式
	 */
	private void CheckChange() {
		phoneNumber = et_phoneNumber.getText().toString().trim();
		newPwd = et_newPwd.getText().toString().trim();
		autoCode = et_authCode.getText().toString().trim();
		if (StringCheck.emptyOrNull(phoneNumber)) {
			AppTools.toast("请输入手机号");
			return;
		}
		if (!StringCheck.isMobileNO(phoneNumber)) {
			AppTools.toast("请输入正确的手机号");
			return;
		}
		if (StringCheck.emptyOrNull(newPwd)) {
			AppTools.toast("请输入新密码");
			return;
		}
		if (!StringCheck.isPwd(newPwd)) {
			AppTools.toast("密码需6~16位，由字母数字组成");
			return;
		}
		if (StringCheck.emptyOrNull(autoCode)) {
			AppTools.toast("请输入短信验证码");
			return;
		}
//		requestCheckAutoCode(phoneNumber, autoCode);
	}

	/**
	 * 密码隐藏与显示
	 */
	private void showPassword() {

		if (!isHidden) {
			// 设置EditText文本为可见的
			et_newPwd.setTransformationMethod(HideReturnsTransformationMethod
					.getInstance());
			iv_showPwd.setImageResource(R.mipmap.password_hide);
		} else {
			// 设置EditText文本为隐藏的
			et_newPwd.setTransformationMethod(PasswordTransformationMethod
					.getInstance());
			iv_showPwd.setImageResource(R.mipmap.password_show);
		}
		et_newPwd.postInvalidate();// 刷新视图，必须在UI线程中调用
		// 切换后将EditText光标置于末尾
		CharSequence charSequence = et_newPwd.getText();
		if (charSequence instanceof Spannable) {
			Spannable spanText = (Spannable) charSequence;
			Selection.setSelection(spanText, charSequence.length());
		}
		isHidden = !isHidden;

	}

	/**
	 * 获取验证码
	 */
//	public void requestAutoCode(String phone) {
//		System.out.println("获取验证码");
//		AutoCodeBean autoCodeBean = new AutoCodeBean();
//		autoCodeBean.setPhone_or_email(phone);
//		NetUtils.send(AppConfig.GETRESETPWDCODE, autoCodeBean, new EasyRequset(
//				this) {
//			@Override
//			protected void onData(JSONObject data) throws JSONException {
//				int res_code = 0;
//				JSONObject res_data = null;
//				String res_msg = null;
//				Gson gson = new Gson();
//				try {
//					res_code = data.getInt("res_code");
//					res_data = data.getJSONObject("res_data");
//					if (res_code == 9999) {
//
//					} else {
//						res_msg = data.getString("res_msg");
//						AppTools.toast(res_msg);
//					}
//				} catch (Exception e) {
//				}
//
//			}
//		});
//	}

	/**
	 * 验证码校验
	 */
//	public void requestCheckAutoCode(String phone, String code) {
//		AutoCodeBean autoCodeBean = new AutoCodeBean();
//		autoCodeBean.setPhone_or_email(phone);
//		autoCodeBean.setCode(code);
//		NetUtils.send(AppConfig.CHECKRESETPWDCODE, autoCodeBean,
//				new EasyRequset(this) {
//					@Override
//					protected void onData(JSONObject data) throws JSONException {
//						int res_code = 0;
//						String session_id = null;
//						String res_msg = null;
//						try {
//							res_code = data.getInt("res_code");
//							if (res_code == 9999) {
//								session_id = data.getString("session_id");
//								requestResetPwd(phoneNumber, newPwd, session_id);
//							} else {
//								res_msg = data.getString("res_msg");
//								AppTools.toast(res_msg);
//							}
//						} catch (Exception e) {
//						}
//					}
//				});
//	}

	/**
	 * 重置密码
	 */
	public void requestResetPwd(String phone, String pwd, String session_id) {
		System.out.println("重置密码");
		ResetPwdBean resetPwdBean = new ResetPwdBean();
		resetPwdBean.setPhone_or_email(phone);
		resetPwdBean.setPwd(Base64.encodeToString(pwd.getBytes(),
				Base64.DEFAULT));
		resetPwdBean.setSession_id(session_id);
		NetUtils.send(AppConfig.RESETPWD, resetPwdBean, new EasyRequset(this) {
			@Override
			protected void onData(JSONObject data) throws JSONException {
				int res_code = 0;
				String res_msg = null;
				try {
					res_code = data.getInt("res_code");
					res_msg = data.getString("res_msg");
					if (res_code == 9999) {
						AppTools.toast(res_msg);
						finish();
					} else {
						AppTools.toast(res_msg);
					}
				} catch (Exception e) {
				}

			}
		});
	}
}
