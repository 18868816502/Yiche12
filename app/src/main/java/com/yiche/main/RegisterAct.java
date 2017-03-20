package com.yiche.main;


import com.yiche.R;
import com.yiche.app.AppConfig;
import com.yiche.model.NetRequestClass;
import com.yiche.util.AppTools;
import com.yiche.util.StringCheck;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.Spannable;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class RegisterAct extends BaseAct {
    private EditText et_phoneNumber;
    private EditText et_setPwd;
    private EditText et_authCode;
    private ImageView iv_showPwd;
    private ImageView iv_checkBox;
    private TextView tv_setAutoCode;
    private TextView tv_agreement;
    private Button bt_register;

    private String phoneNumber;// 手机号码
    private String password;// 密码
    private String autoCode;// 验证码
    private Boolean isHidden = false;// 是否显示密码 默认为隐藏密码
    private Boolean isAgree = true;// 是否同意协议,默认为true

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_register);
        init();
    }

    /**
     * 初始化控件
     */
    private void init() {
        setHeader(true, "注册", null, null);
        et_phoneNumber = (EditText) findViewById(R.id.et_phoneNumber);
        et_setPwd = (EditText) findViewById(R.id.et_setPwd);
        et_authCode = (EditText) findViewById(R.id.et_authCode);
        iv_showPwd = (ImageView) findViewById(R.id.iv_showPwd);
        iv_showPwd.setOnClickListener(this);
        iv_checkBox = (ImageView) findViewById(R.id.iv_checkBox);
        iv_checkBox.setOnClickListener(this);
        tv_setAutoCode = (TextView) findViewById(R.id.tv_setAutoCode);
        tv_setAutoCode.setOnClickListener(this);
        tv_agreement = (TextView) findViewById(R.id.tv_agreement);
        tv_agreement.setOnClickListener(this);
        bt_register = (Button) findViewById(R.id.bt_register);
        bt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            // 确认注册
            case R.id.bt_register:
                CheckRigister();
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
                NetRequestClass.sendAutoCode(phoneNumber,"regist",this,tv_setAutoCode);
                break;
            // 合同框打钩或取消
            case R.id.iv_checkBox:
                isAgree = !isAgree;
                ChangeAgreeIc();
                break;
            // 查看合同
            case R.id.tv_agreement:
                intent = new Intent(RegisterAct.this, WebViewAct.class);
                intent.putExtra("title", "注册协议");
                intent.putExtra("url", AppConfig.WEBSUDAI);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    /**
     * 验证注册
     */
    private void CheckRigister() {
        phoneNumber = et_phoneNumber.getText().toString().trim();
        password = et_setPwd.getText().toString().trim();
        autoCode = et_authCode.getText().toString().trim();
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
        if (!StringCheck.isPwd(password)) {
            AppTools.toast("密码需6~16位，由字母数字组成");
            return;
        }
        if (StringCheck.emptyOrNull(autoCode)) {
            AppTools.toast("请输入短信验证码");
            return;
        }
       NetRequestClass.register(phoneNumber, password, autoCode, this);
    }

    /**
     * 密码隐藏与显示
     */
    private void showPassword() {

        if (!isHidden) {
            // 设置EditText文本为可见的
            et_setPwd.setTransformationMethod(HideReturnsTransformationMethod
                    .getInstance());
            iv_showPwd.setImageResource(R.mipmap.password_hide);
        } else {
            // 设置EditText文本为隐藏的
            et_setPwd.setTransformationMethod(PasswordTransformationMethod
                    .getInstance());
            iv_showPwd.setImageResource(R.mipmap.password_show);
        }
        et_setPwd.postInvalidate();// 刷新视图，必须在UI线程中调用
        // 切换后将EditText光标置于末尾
        CharSequence charSequence = et_setPwd.getText();
        if (charSequence instanceof Spannable) {
            Spannable spanText = (Spannable) charSequence;
            Selection.setSelection(spanText, charSequence.length());
        }
        isHidden = !isHidden;

    }

    /**
     * 改变是否同意协议的图标
     */
    private void ChangeAgreeIc() {
        if (isAgree) {
            iv_checkBox.setImageResource(R.mipmap.ic_agree);
        } else {
            iv_checkBox.setImageResource(R.mipmap.ic_disagree);
        }
    }







    /**
     * 获取用户各种信息并发送给后台
     */
//	private void getUserInfoAndSend() {
//		Tools.getUserInfoAndSend(RegisterAct.this, 1);
//	}
}
