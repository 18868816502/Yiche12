package com.yiche.activity2.choose;

import android.app.TabActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.widget.TabHost;
import android.widget.TextView;

import com.yiche.R;
import com.yiche.activity1.recommend.Activity1;
import com.yiche.activity3.message.Activity3;
import com.yiche.main.LoginAct;

public class Activity2 extends TabActivity implements View.OnClickListener {
    private TabHost mTabHost;
    // 界面底部菜单栏的文字
    private TextView mTv[] = new TextView[4];
    // 界面底部菜单栏的文字id
    private int[] tv_id = new int[] { R.id.tv_passengerCar, R.id.tv_commercialCar,
            R.id.tv_importsCar, R.id.tv_usedCar};

    @SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_2);
        initViews();
        addTabHost();
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
        for (int i = 0; i < mTv.length; i++) {
            mTv[i] = (TextView) findViewById(tv_id[i]);
            mTv[i].setOnClickListener(this);
            // 默认为第一个为选中状态.改变其颜色状态
            if (i == 0) {
                mTv[i].setTextColor(Color.rgb(255, 76, 131));
                mTv[i].setTextSize(18);
            } else {
                mTv[i].setTextColor(Color.BLACK);
                mTv[i].setTextSize(14);
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
        mTabHost.addTab(mTabHost.newTabSpec("passengerPage").setIndicator("passengerPage")
                .setContent(new Intent(this, ActivityPassenger.class)));
        mTabHost.addTab(mTabHost.newTabSpec("classify").setIndicator("classify")
                .setContent(new Intent(this, Activity3.class)));
        mTabHost.addTab(mTabHost.newTabSpec("shoppingCart").setIndicator("shoppingCart")
                .setContent(new Intent(this, LoginAct.class)));
        mTabHost.addTab(mTabHost.newTabSpec("myTreasure").setIndicator("myTreasure")
                .setContent(new Intent(this, Activity3.class)));

        mTabHost.setCurrentTabByTag("passengerPage");// 设置默认当前页
    }

    public void onUserInteraction() {
        super.onUserInteraction();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_passengerCar:
                changeIvAndText(1);
                mTabHost.setCurrentTabByTag("passengerPage");
                break;
            case R.id.tv_commercialCar:
                changeIvAndText(2);
                mTabHost.setCurrentTabByTag("classify");
                break;
            case R.id.tv_importsCar:
                changeIvAndText(3);
                mTabHost.setCurrentTabByTag("shoppingCart");
                break;
            case R.id.tv_usedCar:
                changeIvAndText(4);
                mTabHost.setCurrentTabByTag("myTreasure");
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
                for (int j = 0; j < mTv.length; j++) {
                    if (j + 1 == 1) {
                        mTv[j].setTextColor(Color.rgb(255, 76, 131));
                        mTv[j].setTextSize(18);
                        continue;
                    }
                    mTv[j].setTextColor(Color.BLACK);
                    mTv[j].setTextSize(14);
                }
                break;

            case 2:
                for (int j = 0; j < mTv.length; j++) {
                    if (j + 1 == 2) {
                        mTv[j].setTextColor(Color.rgb(255, 76, 131));
                        mTv[j].setTextSize(18);
                        continue;
                    }
                    mTv[j].setTextColor(Color.BLACK);
                    mTv[j].setTextSize(14);
                }
                break;

            case 3:
                for (int j = 0; j < mTv.length; j++) {
                    if (j + 1 == 3) {
                        mTv[j].setTextColor(Color.rgb(255, 76, 131));
                        mTv[j].setTextSize(18);
                        continue;
                    }
                    mTv[j].setTextColor(Color.BLACK);
                    mTv[j].setTextSize(14);
                }
                break;

            case 4:
                for (int j = 0; j < mTv.length; j++) {
                    if (j + 1 == 4) {
                        mTv[j].setTextColor(Color.rgb(255, 76, 131));
                        mTv[j].setTextSize(18);
                        continue;
                    }
                    mTv[j].setTextColor(Color.BLACK);
                    mTv[j].setTextSize(14);
                }
                break;
            default:
                break;
        }
    }
}