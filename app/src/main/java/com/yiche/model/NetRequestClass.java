package com.yiche.model;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yiche.app.AppConfig;
import com.yiche.app.MyApplication;
import com.yiche.bean.send.AutoCodeBean;
import com.yiche.bean.send.EmptyBean;
import com.yiche.bean.send.LoginBean;
import com.yiche.bean.send.RegisterBean;
import com.yiche.net.EasyRequset;
import com.yiche.net.NetUtils;
import com.yiche.util.AppTools;
import com.yiche.util.CutDownTimer;
import com.yiche.util.SharedpreferencesUitls;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by xst on 2017/3/20.
 * 网络请求类
 */

public final class NetRequestClass {
    //    private static void requestBannerTop(String moblie) {
//        HttpUtils httpUtils = new HttpUtils();
//        RequestParams params = new RequestParams();
//        params.addQueryStringParameter("mobile",moblie);
//        params.addQueryStringParameter("type","regist");
////        params.addBodyParameter("mobile",moblie);
////        params.addBodyParameter("type","regist");
//        httpUtils.send(HttpRequest.HttpMethod.GET, AppConfig.GETAUTO,params,
//                new RequestCallBack<String>() {
//
//                    public void onFailure(HttpException arg0, String arg1) {
//                        System.out.println("请求失败");
//                    }
//
//                    public void onSuccess(ResponseInfo<String> arg0) {
//                        String data = arg0.result;// 这里是返回值
//                         System.out.println("返回值:" + data);
////                        JSONObject jsonobject;
////                        Gson gson = new Gson();
////                        int res_code = 0;
//
//                    }
//                });
//
//    }

    /**
     * 发送短信验证码
     */
    public static void sendAutoCode(String phone, String type, Activity activity, final TextView tv) {
        AutoCodeBean autoCodeBean = new AutoCodeBean();
        autoCodeBean.setMobile(phone);
        autoCodeBean.setType(type);
        NetUtils.send(AppConfig.GETAUTO, autoCodeBean, new EasyRequset(activity) {
            @Override
            protected void onData(JSONObject data) throws JSONException {
                int res_code = 0;
                JSONObject res_data = null;
                String res_msg = null;
                Gson gson = new Gson();
                try {
                    res_code = data.getInt("code");
                    if (res_code == 0) {
                        AppTools.toast("短信验证码已发送");
                        new CutDownTimer(tv, 60, 2);
                    } else {
                        res_msg = data.getString("msg");
                        AppTools.toast(res_msg);
                    }
                } catch (Exception e) {
                }

            }
        });
    }

    /**
     * 注册
     */
    public static void register(final String mobile, final String password, String captcha, final Activity activity) {
        RegisterBean registerBean = new RegisterBean();
        registerBean.setMobile(mobile);
        registerBean.setCaptcha(captcha);
        NetUtils.send(AppConfig.REGISTER, registerBean, new EasyRequset(activity) {
            @Override
            protected void onData(JSONObject data) throws JSONException {
                int res_code = 0;
                String res_msg = null;
                try {
                    res_code = data.getInt("code");
                    if (res_code == 0) {
                        AppTools.toast("注册成功");
                        Intent intent = new Intent();
                        intent.putExtra("mobile", mobile);
                        intent.putExtra("password", password);
                        activity.setResult(1, intent);
//						JPushInterface.setAlias(getApplicationContext(), phone,
//								null);// 设置极光推送的别名
//						getUserInfoAndSend();
                        activity.finish();
                    } else {
                        res_msg = data.getString("msg");
                        AppTools.toast(res_msg);
                    }
                } catch (Exception e) {
                }

            }
        });
    }

    /**
     * 登录请求
     */
    public static void login(String mobile, String password, final Activity activity, final Boolean isReg) {
        LoginBean loginBean = new LoginBean();
        loginBean.setMobile(mobile);
        loginBean.setPassword(password);
        NetUtils.send(AppConfig.LOGIN, loginBean, new EasyRequset(activity) {
            @Override
            protected void onData(JSONObject data) throws JSONException {
                int res_code = 0;
                JSONObject res_data = null;
                String res_msg = null;
                Gson gson = new Gson();
                System.out.println("isReg:" + isReg);
                try {
                    res_code = data.getInt("code");
                    if (res_code == 0) {
                        res_data = data.getJSONObject("tpl_data");
                        String token = res_data.getString("token");
//						LoginBackBean loginBackBean = gson.fromJson(
//								res_data.toString(), LoginBackBean.class);
                        SharedpreferencesUitls.saveToken(
                                activity,
                                token);
                        System.out.println("token:"+SharedpreferencesUitls.getToken(activity));
//						SharedpreferencesUitls.saveUserId(
//								getApplicationContext(),
//								loginBackBean.getUser_id());
//
//						SharedpreferencesUitls.saveUserPhoneNumber(
//								getApplicationContext(),
//								loginBackBean.getUsername());
//						Intent intent=new Intent(LoginAct.this,MainAct.class);
//						intent.putExtra("isReg", isReg);
//						startActivity(intent);

//                        if(isReg){
//                            activity.setResult(200);
//                        }else{
//                            activity.setResult(1);
//                        }
//                        activity.finish();
                        AppTools.toast("登录成功");
                    } else {
                        res_msg = data.getString("msg");
                        AppTools.toast(res_msg);
                    }
                } catch (Exception e) {
                }

            }
        });
    }

    /**
     * 推荐首页
     */
    public static void recommendInfo(final Activity activity) {
        EmptyBean bean = new EmptyBean();
        NetUtils.send(AppConfig.RECOMMENDINFO, bean, new EasyRequset(activity) {
            @Override
            protected void onData(JSONObject data) throws JSONException {
                int res_code = 0;
                String res_msg = null;

            }
        });
    }
}
