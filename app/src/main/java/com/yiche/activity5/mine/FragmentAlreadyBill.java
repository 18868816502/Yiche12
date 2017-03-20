package com.yiche.activity5.mine;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.TextViewCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.yiche.R;
import com.yiche.adapter.RepaymentAlreadyAdapter;
import com.yiche.bean.receive.RepaymentAlreadyBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import library.PullToRefreshBase;
import library.PullToRefreshListView;


public class FragmentAlreadyBill extends Fragment implements View.OnClickListener {
    private PullToRefreshListView refreshListView;
    private RepaymentAlreadyAdapter repaymentAlreadyAdapter;
    private List<RepaymentAlreadyBean> alreadyList = new ArrayList<RepaymentAlreadyBean>();
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_already_bill, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        modeData();
        bindEvent();
    }

    /**
     * 模拟List数据
     */
    private void modeData(){
        for (int i = 0; i <3 ; i++) {
            RepaymentAlreadyBean bean = new RepaymentAlreadyBean();
            bean.setDate(i*1+"");
            bean.setOrderNo(i*123);
            bean.setPeriod("48/"+i);
            bean.setMoney(i*452);
            alreadyList.add(bean);
        }

}
    /**
     * 绑定刷新事件
     */
    private void bindEvent(){
        refreshListView= (PullToRefreshListView) getActivity().findViewById(R.id.refreshListView);
        repaymentAlreadyAdapter=new RepaymentAlreadyAdapter(getActivity(),alreadyList);
        refreshListView.setAdapter(repaymentAlreadyAdapter);
//        refreshListView.setOnItemClickListener(this);
        refreshListView.setMode(PullToRefreshBase.Mode.BOTH);
        refreshListView.setOnRefreshListener(new PullToRefreshBase.OnRefreshListener2<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {

            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                modeData();
//                Timer time =new Timer();
//                TimerTask tk=new TimerTask() {
//                    @Override
//                    public void run() {
//                        repaymentAlreadyAdapter.notifyDataSetChanged();
//                    }
//                };
//                time.schedule(tk, 2000);
//                TimerTask tk2=new TimerTask() {
//                    @Override
//                    public void run() {
//                        refreshListView.onRefreshComplete();
//                    }
//                };
//                time.schedule(tk2, 1000);
                repaymentAlreadyAdapter.notifyDataSetChanged();
                new Handler(new Handler.Callback() {
                    //处理接收到的消息的方法
                    @Override
                    public boolean handleMessage(Message arg0) {
                        //实现页面跳转
//                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
//                        return false;
                        refreshListView.onRefreshComplete();
                        return true;
                    }
                }).sendEmptyMessageDelayed(0, 2000); //表示延时三秒进行任务的执行

            }
        });
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
//            case R.id.tv:
//                System.out.println("点击了");
//                break;

            default:
                break;
        }
    }
}
