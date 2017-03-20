package com.yiche.activity5.mine;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.yiche.R;
import com.yiche.main.BaseAct;
import com.yiche.view.SimpleViewPagerIndicator;

import java.util.ArrayList;
import java.util.List;

public class ActivityRepayment extends BaseAct {
private SimpleViewPagerIndicator indicator;
    private ViewPager viewPager;
    private String[] mTitles;
    private List<Fragment> listViews; // Tab页面列表
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repayment);
        setHeader(true,"还款计划",null,null);
        init();
        InitViewPager();
        initIndicator();
    }

    @Override
    public void onClick(View v) {

    }

    /**
     * 初始化控件
     */
    private void init(){
        indicator= (SimpleViewPagerIndicator) findViewById(R.id.indicator);
        viewPager= (ViewPager) findViewById(R.id.viewPager);
    }
    /**
     * 初始化ViewPager
     */
    private void InitViewPager() {
        listViews = new ArrayList<Fragment>();
        FragmentAlreadyBill fragmentAlreadyBill=new FragmentAlreadyBill();
        FragmentNoOutBill fragmentNoOutBill=new FragmentNoOutBill();
        listViews.add(fragmentAlreadyBill);
        listViews.add(fragmentNoOutBill);
        MyAdapter myAdapter=new MyAdapter(getSupportFragmentManager());
        viewPager.setAdapter(myAdapter);
        viewPager.setOffscreenPageLimit(2);//设置左右滑动的有效格数
        viewPager.setCurrentItem(0);//设置当前显示页
    }
    /**
     * 初始化选择器
     */
    private void initIndicator() {
        mTitles = getResources().getStringArray(R.array.mine_repayment);
        indicator.setTitles(mTitles);
        indicator.setViewPager(viewPager);
    }



    /**
     * 自定义适配器
     */
    private class MyAdapter extends FragmentStatePagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
            // TODO Auto-generated constructor stub
        }

        @Override
        public Fragment getItem(int arg0) {
            // TODO Auto-generated method stub
            return listViews.get(arg0);
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return listViews.size();
        }

    }
}
