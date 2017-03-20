package com.yiche.activity5.mine;

import android.os.Bundle;
import android.view.View;

import com.yiche.R;
import com.yiche.main.BaseAct;

public class ActivityIdentification extends BaseAct {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_identification);
        setHeader(true,"实名认证",null,null);
    }

    @Override
    public void onClick(View v) {

    }
}
