package com.yiche.net;


import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;

import com.google.gson.Gson;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.yiche.R;
import com.yiche.app.AppConfig;
import com.yiche.bean.send.BaseBean;
import com.yiche.bean.send.UserBean;
import com.yiche.util.AppTools;

import library.PullToRefreshBase;

/**
 * @author xst
 */
public abstract class EasyRequset extends RequestCallBack<String> {
    private static final String PROGRESS_HIT = "努力加载中......";
    private SwipeRefreshLayout swipeView = null;
    private View view = null;
    private CustomProgressDialog progressDialog = null;
    private Dialog dialog = null;
    private Class<? extends Activity> clazz;
    private Activity activity;
    private RefreshListener listener;

    /**
     * @param activity
     * @param ptrView
     * @param isShow
     */
    public EasyRequset(final Activity activity,
                       final PullToRefreshBase<? extends View> ptrView,
                       final SwipeRefreshLayout swipeView, final View view,
                       final boolean isShow, final Class<? extends Activity> clazz,
                       final RefreshListener listener) {
        this.activity = activity;
        this.swipeView = swipeView;
        this.view = view;
        this.clazz = clazz;
        this.listener = listener;
        if (isShow) {
            createProgressDialog(activity);
        }
    }

    public EasyRequset(final Activity activity, final RefreshListener listener) {
        this(activity, null, null, null, true, null, listener);
    }

    public EasyRequset(final Activity activity, final boolean isShow,
                       final RefreshListener listener) {
        this(activity, null, null, null, isShow, null, listener);
    }

    public EasyRequset(final Activity activity) {
        this(activity, null, null, null, true, null, null);
    }

    public EasyRequset() {
        this(null, null, null, null, false, null, null);
    }

    private void createProgressDialog(final Activity activity) {
        progressDialog = CustomProgressDialog.createDialog(activity);
        progressDialog.setMessage(PROGRESS_HIT);
            progressDialog.show();
//        progressDialog.setCanceledOnTouchOutside(false);
    }

    /**
     * 取消对话框
     */
    public void destroyProgressDialog() {
        try {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public final void onSuccess(ResponseInfo<String> responseInfo) {
        destroyProgressDialog();
        JSONObject jsonObject = null;
        try {
            jsonObject = new JSONObject(responseInfo.result);
            System.out.println("jsonObject:" + jsonObject.toString());
            onData(jsonObject);
        } catch (Exception e) {
            e.printStackTrace();
        }

//		if (jsonObject != null) {
//			String resultCode = null;
//			String resultMsg = null;
//			JSONObject data = null;
//			try {
//				resultCode = jsonObject.getString("res_code");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			try {
//				resultMsg = jsonObject.getString("res_msg");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			try {
//				data = jsonObject.getJSONObject("res_data");
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
        /**
         * 0103 token过期（原3） 0104 token不存在，无效的token（原4） 0105
         *
         * 丢失（缺失）的token（一般为需要token的情况下没有传） 0106 长期不在线,token失效
         * 0201用户不存在，无效的用户id
         */

//			if ("0103".equals(resultCode)) {
//				refreshToken();
//			} else if ("0104".equals(resultCode) || "0105".equals(resultCode)
//					|| "0106".equals(resultCode)) {
//				dialog = new GetDialog().getOverdueDialog(activity, resultMsg,
//						new OnClickListener() {
//							@Override
//							public void onClick(View arg0) {
//								Intent intent = new Intent(activity,
//										LoginAct.class);
//								activity.startActivity(intent);
//								dialog.dismiss();
//
//							}
//						}, true);
//				dialog.show();
//			}
//			else if ("0201".equals(resultCode)) {
//				dialog = new GetDialog().getRegisterDialog(activity, resultMsg,
//						new OnClickListener() {
//							@Override
//							public void onClick(View arg0) {
//								Intent intent = new Intent(activity,
//										RegisterAct.class);
//								activity.startActivityForResult(intent, 1);
//								dialog.dismiss();
//
//							}
//						}, true);
//				dialog.show();
//			}
//		}
        try {
            this.onFinal();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected void onFinal() {
    }

    protected final void showErrToast(final String detailMsg, boolean show) {
        final Context context = com.yiche.app.MyApplication.getInstance();

        if (show) {
            AppTools.toast(detailMsg);
        } else if (AppConfig.DEBUG) {
            AppTools.toast("出错了");
        } else {
            AppTools.toast("出错了");
        }
    }

    private void refreshToken() {
        UserBean bean = new UserBean();
        bean.setRefresh_token(bean.getRefresh_token());
        NetUtils.send(AppConfig.API_REFRESH_TOKEN, bean, new EasyRequset() {

            @Override
            protected void onData(JSONObject data) throws JSONException {
                // TODO Auto-generated method stub
                BaseBean bean = new BaseBean();
                Gson gson = new Gson();
                UserBean userBean = gson.fromJson(data.toString(),
                        UserBean.class);
                bean.setUserBean(userBean);
                if (listener != null)
                    listener.refreshListener();
            }
        });
    }

    @Override
    public final void onFailure(final HttpException he, final String s) {
        destroyProgressDialog();
        Log.d("vivi", "请求失败" + s);
//		AppTools.toast("请求失败");
    }

    protected abstract void onData(JSONObject data) throws JSONException;
}
