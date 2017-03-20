package com.yiche.activity1.recommend;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.yiche.R;
import com.yiche.adapter.GridViewAdapter;
import com.yiche.adapter.LunboAdapter;
import com.yiche.app.AppConfig;
import com.yiche.bean.receive.BannersBean;
import com.yiche.bean.receive.CarBean;
import com.yiche.bean.send.BaseBean;
import com.yiche.bean.send.EmptyBean;
import com.yiche.net.EasyRequset;
import com.yiche.net.NetUtils;
import com.yiche.util.AppTools;
import com.yiche.util.CheckSystemRight;
import com.yiche.view.GetDialog;
import com.google.gson.Gson;
import com.yiche.view.MyGridView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class Activity1 extends Activity {
    private List<BannersBean> listBanner = new ArrayList<BannersBean>();
    private LunboAdapter adapter;
    private ViewPager viewPagerTop;
    private LinearLayout viewGroupLayout;
    private ImageView dot, dots[];
    private int CHANGETIME = 3000;// 轮播图片时间
    private Runnable runnable;
    private WindowManager mWindowManager = null;
    private WindowManager.LayoutParams wmParams = null;
    // 用于显示右下角浮动图标
    private ImageView img_Float;
    private Dialog dialog = null;
    private GetDialog orderDialog = new GetDialog();
    private MyGridView gridView1,gridView2,gridView3,gridView4;
    private List<CarBean> list1,list2,list3,list4;
    private GridViewAdapter gridAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 设置标题隐藏模式
        setContentView(R.layout.activity_1);
        // System.out.println("是否有悬浮框权限:"
        // + CheckSystemRight.getAppOps(getApplicationContext()));
        // 如果没有悬浮框权限 请求去打开权限
        if (!CheckSystemRight.getAppOps(getApplicationContext())) {
            Uri packageURI = Uri.parse("package:" + "com.example.yiche");
            Intent intent = new Intent(
                    Settings.ACTION_APPLICATION_DETAILS_SETTINGS, packageURI);
            startActivity(intent);
        }

        init();
        requestBannerTop();
        initFloatImage();
        list1=initData();
        list2=initData();
        list3=initData();
        list4=initData();
//        System.out.println("list:"+list.size());
        gridAdapter = new GridViewAdapter(this, list1);
        gridAdapter = new GridViewAdapter(this, list2);
        gridAdapter = new GridViewAdapter(this, list3);
        gridAdapter = new GridViewAdapter(this, list4);
        gridView1.setAdapter(gridAdapter);
        gridView2.setAdapter(gridAdapter);
        gridView3.setAdapter(gridAdapter);
        gridView4.setAdapter(gridAdapter);
    }
    private List<CarBean> initData(){
        list1 = new ArrayList<CarBean>();
        for (int i = 0; i < 4; i++) {
            CarBean bean = new CarBean();
            bean.setName("第"+i+"个");
            list1.add(bean);
        }
        return list1;
    }
    @Override
    protected void onResume() {
        // TODO Auto-generated method stub
        super.onResume();
        createFloatView();
    }

    @Override
    protected void onPause() {
        // TODO Auto-generated method stub
        super.onPause();
        if (mWindowManager != null && img_Float != null) {
            mWindowManager.removeView(img_Float);
        }
    }

    /**
     * 初始化控件
     */
    private void init() {
        viewPagerTop = (ViewPager) findViewById(R.id.viewPagerTop);
        gridView1= (MyGridView) findViewById(R.id.gridView1);
        gridView2= (MyGridView) findViewById(R.id.gridView2);
        gridView3= (MyGridView) findViewById(R.id.gridView3);
        gridView4= (MyGridView) findViewById(R.id.gridView4);
    }

    /**
     * 初始化悬浮图片
     */
    private void initFloatImage() {
        // 获取WindowManager
        mWindowManager = (WindowManager) getApplicationContext()
                .getSystemService(Context.WINDOW_SERVICE);
        // 设置LayoutParams(全局变量）相关参数
        wmParams = new WindowManager.LayoutParams();

        wmParams.type = LayoutParams.TYPE_PHONE; // 设置window type
        wmParams.format = PixelFormat.RGBA_8888; // 设置图片格式，效果为背景透明
        // 设置Window flag
        wmParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
                | LayoutParams.FLAG_NOT_FOCUSABLE;

        // 以屏幕左上角为原点，设置x、y初始值
        wmParams.x = 10;
        wmParams.y = 150;
        System.out.println("*************" + wmParams.y);
        // 设置悬浮窗口长宽数据
        wmParams.width = 180;
        wmParams.height = 180;
    }

    /**
     * 创建悬浮图片按钮
     */
    private void createFloatView() {
        img_Float = new ImageView(this);
        img_Float.setImageResource(R.mipmap.ic_launcher);
        img_Float.setAlpha(200);
        img_Float.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                // 点击悬浮图片的事件
                if (dialog != null && dialog.isShowing()) {
                    return;
                }
                showOrderDialog();
            }
        });
        // 调整悬浮窗口
        wmParams.gravity = Gravity.RIGHT | Gravity.BOTTOM;
        // 显示myFloatView图像
        mWindowManager.addView(img_Float, wmParams);

    }

    /**
     * 显示预约对话框
     */
    private void showOrderDialog() {

        dialog = orderDialog.getOrderDialog(this, new OnClickListener() {
            @Override
            public void onClick(View v) {
                AppTools.toast(orderDialog.et_carType.getText() + "");

            }
        });
        dialog.setCanceledOnTouchOutside(true);// 点击外部消失
        dialog.show();
    }

    /**
     * 请求顶部轮播图片
     */
    // private void requestBannerTop() {
    // HttpUtils httpUtils = new HttpUtils();
    // RequestParams params = new RequestParams();
    // httpUtils.send(HttpMethod.POST, AppConfig.BANNERPIC, params,
    // new RequestCallBack<String>() {
    //
    // public void onFailure(HttpException arg0, String arg1) {
    // System.out.println("请求失败");
    // }
    //
    // public void onSuccess(ResponseInfo<String> arg0) {
    // String data = arg0.result;// 这里是返回值
    // // System.out.println("Banner图片返回值:" + data);
    // JSONObject jsonobject;
    // Gson gson = new Gson();
    // int res_code = 0;
    // try {
    // jsonobject = new JSONObject(data);
    // res_code = jsonobject.getInt("result");
    // if (res_code == 1) {
    // JSONArray datajson = jsonobject
    // .getJSONArray("Info");
    // for (int i = 0; i < datajson.length(); i++) {
    // BannersBean bean = gson.fromJson(datajson
    // .getJSONObject(i).toString(),
    // BannersBean.class);
    // bean.setPic(AppConfig.urlpic
    // + datajson.getJSONObject(i)
    // .getString("pic"));
    // listBanner.add(bean);
    // }
    // initViewPager();
    // }
    // } catch (Exception e) {
    // }
    // }
    // });
    //
    // }

    /**
     * 请求顶部轮播图片
     */
    private void requestBannerTop() {
        EmptyBean bean = new EmptyBean();
        NetUtils.send("http://www.ydcylc.com/mobile/app_getBanners", bean, new EasyRequset(this) {
            @Override
            protected void onData(JSONObject data) throws JSONException {
                int res_code = 0;
                JSONObject res_data = null;
                String res_msg = null;
                Gson gson = new Gson();
                try {
                    res_code = data.getInt("result");
                    if (res_code == 1) {
                        JSONArray datajson = data.getJSONArray("Info");
                        for (int i = 0; i < datajson.length(); i++) {
                            BannersBean bean = gson.fromJson(datajson
                                            .getJSONObject(i).toString(),
                                    BannersBean.class);
                            bean.setPic(AppConfig.urlpic
                                    + datajson.getJSONObject(i)
                                    .getString("pic"));
                            listBanner.add(bean);
                        }
                        initViewPager();
                    }
                } catch (Exception e) {
                }
            }
        });
    }

    /**
     * 显示服务器获取过来的banner图片并设置下方的小点
     */
    private void initViewPager() {
        adapter = new LunboAdapter(this);
        if (listBanner.size() == 0) {
            return;
        } else {
            adapter.change(listBanner);

            // 从布局文件中获取ViewPager父容器
            // LinearLayout gundongshitu = (LinearLayout)
            // findViewById(R.id.gundongshitu);
            // //创建ViewPager
            // viewPager = new ViewPager(this);
            //
            // //获取屏幕像素相关信息
            // DisplayMetrics dm = new DisplayMetrics();
            // getWindowManager().getDefaultDisplay().getMetrics(dm);
            //
            // //根据屏幕信息设置ViewPager广告容器的宽高
            // viewPager.setLayoutParams(new LayoutParams(dm.widthPixels,
            // dm.heightPixels ));
            //
            // //将ViewPager容器设置到布局文件父容器中
            // gundongshitu.addView(viewPager);
            viewPagerTop.setAdapter(adapter);
            viewPagerTop.setOnPageChangeListener(myOnPageChangeListener);
            initDot();

            runnable = new Runnable() {
                @Override
                public void run() {
                    int next = viewPagerTop.getCurrentItem() + 1;
                    if (next >= adapter.getCount()) {
                        next = 0;
                    }
                    viewHandler.sendEmptyMessage(next);
                }
            };
            viewHandler.postDelayed(runnable, CHANGETIME);
        }
    }

    /**
     * 初始化dot视图
     */
    private void initDot() {
        viewGroupLayout = (LinearLayout) findViewById(R.id.viewGroup);
        // 设置圆点的大小
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                20, 20);
        layoutParams.setMargins(4, 3, 24, 3);
        dots = new ImageView[listBanner.size()];
        for (int i = 0; i < listBanner.size(); i++) {
            dot = new ImageView(this);
            dot.setLayoutParams(layoutParams);
            dots[i] = dot;
            dots[i].setTag(i);
            // dots[i].setOnClickListener(onClick);
            if (i == 0) {
                dots[i].setBackgroundResource(R.drawable.dot_focused);
            } else {
                dots[i].setBackgroundResource(R.drawable.dot_normal);
            }

            viewGroupLayout.addView(dots[i]);
        }
    }

    ViewPager.OnPageChangeListener myOnPageChangeListener = new ViewPager.OnPageChangeListener() {

        @Override
        public void onPageScrollStateChanged(int arg0) {
        }

        @Override
        public void onPageScrolled(int arg0, float arg1, int arg2) {
        }

        @Override
        public void onPageSelected(int arg0) {
            arg0 = arg0 % listBanner.size();
            setCurDot(arg0);
            viewHandler.removeCallbacks(runnable);
            viewHandler.postDelayed(runnable, CHANGETIME);
        }

    };

    /**
     * 实现dot点击响应功能
     */
    // OnClickListener onClick = new OnClickListener() {
    // @Override
    // public void onClick(View v) {
    // int position = (Integer) v.getTag();
    // setCurView(position);
    // }
    //
    // };

    /**
     * 设置当前的引导页
     */
    private void setCurView(int position) {
        if (position < 0 || position > adapter.getCount()) {
            return;
        }
        viewPagerTop.setCurrentItem(position);
    }

    /**
     * 选中当前引导小点
     */
    private void setCurDot(int position) {
        for (int i = 0; i < dots.length; i++) {
            if (position == i) {
                dots[i].setBackgroundResource(R.drawable.dot_focused);
            } else {
                dots[i].setBackgroundResource(R.drawable.dot_normal);
            }
        }
    }

    /**
     * 每隔固定时间切换广告栏图片
     */
    @SuppressLint("HandlerLeak")
    private final Handler viewHandler = new Handler() {

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            setCurView(msg.what);
        }

    };
}
