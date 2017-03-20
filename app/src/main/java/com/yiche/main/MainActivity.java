package com.yiche.main;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TabHost;
import android.widget.TextView;

import com.yiche.R;
import com.yiche.activity1.recommend.Activity1;
import com.yiche.activity2.choose.Activity2;
import com.yiche.activity5.mine.ActivityMine;
import com.yiche.model.NetRequestClass;

public class MainActivity extends TabActivity implements View.OnClickListener {
    private TabHost mTabHost;
    // 界面底部的菜单布局
    private RelativeLayout mRl[] = new RelativeLayout[5];
    // 界面底部菜单栏的图片
    private ImageView mIv[] = new ImageView[5];
    // 界面底部菜单栏的文字
    private TextView mTv[] = new TextView[5];
    // 界面底部菜单栏的布局id
    private int[] rl_id = new int[] { R.id.rl_homepage, R.id.rl_classify,
            R.id.rl_shopping_cart, R.id.rl_mytreasure, R.id.rl_more };
    // 界面底部菜单栏的图片id
    private int[] iv_id = new int[] { R.id.iv_homepage, R.id.iv_classify,
            R.id.iv_shopping_cart, R.id.iv_mytreasure, R.id.iv_more };
    // 界面底部菜单栏的图片资源
    private int[] iv_drawable_id = new int[] { R.drawable.bottom_home_style,
            R.drawable.bottom_home_style, R.drawable.bottom_home_style, R.drawable.bottom_home_style,
            R.drawable.bottom_home_style };
    // 界面底部菜单栏的文字id
    private int[] tv_id = new int[] { R.id.tv_homepage, R.id.tv_classify,
            R.id.tv_shopping_cart, R.id.tv_mytreasure, R.id.tv_more };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.main_tabhost);
//        YdcyApplication.getInstance().addActivity(this);//Application这个类复制到工程里面，然后在每个Acitivity的oncreate方法里面通过Application.getInstance().addActivity(this); 添加当前Acitivity到ancivitylist里面去，最后在想退出的时候调用SysApplication.getInstance().exit();可直接关闭所有的Acitivity并退出应用程序。
        initViews();
        addTabHost();
        NetRequestClass.recommendInfo(this);
    }



    //当Activity完全加载完毕后会触发此方法
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }


    /**
     *
     * @TODO ：给tabHost的rl,图片，文字控件初始化
     * @author ：jonny.Xiang
     * @date : 2016年3月30日
     * @params ：
     * @return ：void
     */
    private void initViews() {
        for (int i = 0; i < mRl.length; i++) {
            mRl[i] = (RelativeLayout) findViewById(rl_id[i]);
            mRl[i].setOnClickListener(this);
        }
        for (int i = 0; i < mIv.length; i++) {
            mIv[i] = (ImageView) findViewById(iv_id[i]);
        }
        for (int i = 0; i < mTv.length; i++) {
            mTv[i] = (TextView) findViewById(tv_id[i]);
            // 默认为第一个为选中状态.改变其颜色状态
            if (i == 0) {
                mTv[i].setTextColor(Color.rgb(255, 76, 131));
            } else {
                mTv[i].setTextColor(0xff8b8b8b);
            }
        }
    }

    /**
     * 加载activity到自定义的TabHost
     *
     * @author: jonny.Xiang
     * @data: 2016年3月30日
     * @return:void
     */
    private void addTabHost() {
        mTabHost = getTabHost();
        mTabHost.addTab(mTabHost.newTabSpec("homePage").setIndicator("homePage")
                .setContent(new Intent(this, Activity1.class)));
        mTabHost.addTab(mTabHost.newTabSpec("classify").setIndicator("classify")
                .setContent(new Intent(this, Activity2.class)));
        mTabHost.addTab(mTabHost.newTabSpec("shoppingCart").setIndicator("shoppingCart")
                .setContent(new Intent(this, Activity1.class)));
        mTabHost.addTab(mTabHost.newTabSpec("myTreasure").setIndicator("myTreasure")
                .setContent(new Intent(this, Activity2.class)));
        mTabHost.addTab(mTabHost.newTabSpec("mine").setIndicator("mine")
                .setContent(new Intent(this, ActivityMine.class)));

        mTabHost.setCurrentTabByTag("homePage");// 设置默认当前页
    }

    public void onUserInteraction() {
        super.onUserInteraction();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.rl_homepage:
                changeIvAndText(1);
                mTabHost.setCurrentTabByTag("homePage");
                break;
            case R.id.rl_classify:
                changeIvAndText(2);
                mTabHost.setCurrentTabByTag("classify");
                break;
            case R.id.rl_shopping_cart:
                changeIvAndText(3);
                mTabHost.setCurrentTabByTag("shoppingCart");
                break;
            case R.id.rl_mytreasure:
                changeIvAndText(4);
                mTabHost.setCurrentTabByTag("myTreasure");
                break;
            case R.id.rl_more:
                changeIvAndText(5);
                mTabHost.setCurrentTabByTag("mine");
                break;
            default:
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    /**
     * @TODO ：改变底部导航的 图片和文字的颜色
     * @author ：jonny.Xiang
     * @date : 2016年3月30日
     * @params ：@param i
     * @return ：void
     */
    public void changeIvAndText(int i) {
        switch (i) {
            case 1:
                for (int j = 0; j < mRl.length; j++) {
                    if (j + 1 == 1) {
                        mIv[j].setBackgroundResource(R.drawable.bottom_home_style);
                        mTv[j].setTextColor(Color.rgb(255, 76, 131));
                        continue;
                    }
                    mIv[j].setBackgroundResource(iv_drawable_id[j]);
                    mTv[j].setTextColor(0xff8b8b8b);
                }
                break;

            case 2:
                for (int j = 0; j < mRl.length; j++) {
                    if (j + 1 == 2) {
                        mIv[j].setBackgroundResource(R.drawable.bottom_home_style);
                        mTv[j].setTextColor(Color.rgb(255, 76, 131));
                        continue;
                    }
                    mIv[j].setBackgroundResource(iv_drawable_id[j]);
                    mTv[j].setTextColor(0xff8b8b8b);
                }
                break;

            case 3:
                for (int j = 0; j < mRl.length; j++) {
                    if (j + 1 == 3) {
                        mIv[j].setBackgroundResource(R.drawable.bottom_home_style);
                        mTv[j].setTextColor(Color.rgb(255, 76, 131));
                        continue;
                    }
                    mIv[j].setBackgroundResource(iv_drawable_id[j]);
                    mTv[j].setTextColor(0xff8b8b8b);
                }
                break;

            case 4:
                for (int j = 0; j < mRl.length; j++) {
                    if (j + 1 == 4) {
                        mIv[j].setBackgroundResource(R.drawable.bottom_home_style);
                        mTv[j].setTextColor(Color.rgb(255, 76, 131));
                        continue;
                    }
                    mIv[j].setBackgroundResource(iv_drawable_id[j]);
                    mTv[j].setTextColor(0xff8b8b8b);
                }
                break;
            case 5:
                for (int j = 0; j < mRl.length; j++) {
                    if (j + 1 == 5) {
                        mIv[j].setBackgroundResource(R.drawable.bottom_home_style);
                        mTv[j].setTextColor(Color.rgb(255, 76, 131));
                        continue;
                    }
                    mIv[j].setBackgroundResource(iv_drawable_id[j]);
                    mTv[j].setTextColor(0xff8b8b8b);
                }
                break;
            default:
                break;
        }
    }

    // 处理在非默认主页back后回到主页
    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getKeyCode() == KeyEvent.KEYCODE_BACK
                && event.getAction() == KeyEvent.ACTION_DOWN) {
            if (!"homePage".equals(mTabHost.getCurrentTabTag())) {
                changeIvAndText(1);
                mTabHost.setCurrentTabByTag("homePage");
                return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

}

