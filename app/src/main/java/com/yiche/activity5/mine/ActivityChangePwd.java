package com.yiche.activity5.mine;

import android.os.Bundle;
import android.view.View;

import com.yiche.R;
import com.yiche.main.BaseAct;

public class ActivityChangePwd extends BaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_pwd);
        setHeader(true,"修改密码",null,null);
    }

    @Override
    public void onClick(View v) {

    }
}
