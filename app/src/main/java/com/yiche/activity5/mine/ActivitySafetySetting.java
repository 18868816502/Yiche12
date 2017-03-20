package com.yiche.activity5.mine;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.yiche.R;
import com.yiche.main.BaseAct;

public class ActivitySafetySetting extends BaseAct {
private RelativeLayout rl_identification,rl_changePwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safety_setting);
        setHeader(true,"安全设置",null,null);
        init();
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            //身份认证
            case R.id.rl_identification:
                intent=new Intent(ActivitySafetySetting.this,ActivityIdentification.class);
                startActivity(intent);
                break;
            //修改密码
            case R.id.rl_changePwd:
                intent=new Intent(ActivitySafetySetting.this,ActivityChangePwd.class);
                startActivity(intent);
                break;

            default:
                break;
        }
    }

    /**
     * 初始化控件
     */
    private void init(){
        rl_identification= (RelativeLayout) findViewById(R.id.rl_identification);
        rl_identification.setOnClickListener(this);
        rl_changePwd = (RelativeLayout) findViewById(R.id.rl_changePwd);
        rl_changePwd.setOnClickListener(this);
    }
}
