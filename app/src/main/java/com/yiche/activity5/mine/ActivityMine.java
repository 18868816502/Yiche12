package com.yiche.activity5.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.yiche.R;
import com.yiche.view.CircularImage;

public class ActivityMine extends AppCompatActivity implements View.OnClickListener {
    private CircularImage imageCircular;
    private RelativeLayout rl_myOrder, rl_repayment, rl_moneyRecord, rl_inviteFriends, safetySetting, rl_feedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        init();
    }

    /**
     * 初始化控件
     */
    private void init() {
        imageCircular = (CircularImage) findViewById(R.id.imageCircular);
        imageCircular.setOnClickListener(this);
        rl_myOrder = (RelativeLayout) findViewById(R.id.rl_myOrder);
        rl_myOrder.setOnClickListener(this);
        rl_repayment = (RelativeLayout) findViewById(R.id.rl_repayment);
        rl_repayment.setOnClickListener(this);
        rl_moneyRecord = (RelativeLayout) findViewById(R.id.rl_moneyRecord);
        rl_moneyRecord.setOnClickListener(this);
        rl_inviteFriends = (RelativeLayout) findViewById(R.id.rl_inviteFriends);
        rl_inviteFriends.setOnClickListener(this);
        safetySetting = (RelativeLayout) findViewById(R.id.safetySetting);
        safetySetting.setOnClickListener(this);
        rl_feedback = (RelativeLayout) findViewById(R.id.rl_feedback);
        rl_feedback.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //设置中心
            case R.id.imageCircular:
                intent=new Intent(ActivityMine.this,ActivitySettingCenter.class);
                startActivity(intent);
                break;
            //我的订单
            case R.id.rl_myOrder:
                break;
            //还款计划
            case R.id.rl_repayment:
                intent=new Intent(ActivityMine.this,ActivityRepayment.class);
                startActivity(intent);
                break;
            //资金记录
            case R.id.rl_moneyRecord:
                break;
            //邀请好友
            case R.id.rl_inviteFriends:
                break;
            //安全设置
            case R.id.safetySetting:
                intent=new Intent(ActivityMine.this,ActivitySafetySetting.class);
                startActivity(intent);
                break;
            //意见反馈
            case R.id.rl_feedback:
                break;
            default:
                break;
        }
    }
}
