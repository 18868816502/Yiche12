package com.yiche.adapter;


import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yiche.R;
import com.yiche.activity5.mine.ActivitySettingCenter;
import com.yiche.bean.receive.RepaymentAlreadyBean;

import java.util.List;

public class RepaymentAlreadyAdapter extends BaseAdapter implements View.OnClickListener {
    private List<RepaymentAlreadyBean> list;
    private Context context;

    public RepaymentAlreadyAdapter(Context context, List<RepaymentAlreadyBean> list) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list == null ? 0 : list.size();
    }

    @Override
    public RepaymentAlreadyBean getItem(int arg0) {
        return list.get(arg0);
    }

    @Override
    public long getItemId(int arg0) {
        return arg0;
    }

    @Override
    public View getView(int arg0, View arg1, ViewGroup arg2) {
        ViewHolder viewHolder=null;
       if(arg1==null){
           arg1= LayoutInflater.from(context).inflate(R.layout.item_repayment_already,null);
           viewHolder=new ViewHolder();
           viewHolder.tv_date= (TextView) arg1.findViewById(R.id.tv_date);
           viewHolder.tv_orderNo= (TextView) arg1.findViewById(R.id.tv_orderNo);
           viewHolder.tv_periods= (TextView) arg1.findViewById(R.id.tv_periods);
           viewHolder.tv_money= (TextView) arg1.findViewById(R.id.tv_money);
           viewHolder.bt_repayment= (Button) arg1.findViewById(R.id.bt_repayment);
           viewHolder.bt_repayment.setOnClickListener(this);
           arg1.setTag(viewHolder);
       }else{
           viewHolder= (ViewHolder) arg1.getTag();
       }
        viewHolder.tv_date.setText(list.get(arg0).getDate());
        viewHolder.tv_orderNo.setText("订单号:"+list.get(arg0).getOrderNo());
        viewHolder.tv_periods.setText("期数:"+list.get(arg0).getPeriod());
        viewHolder.tv_money.setText("分期金额:"+list.get(arg0).getMoney()+"");
        return arg1;
    }

    @Override
    public void onClick(View v) {
switch (v.getId()){
    case R.id.bt_repayment:
        Toast.makeText(context,"还款",Toast.LENGTH_SHORT).show();
        Intent intent =new Intent(context, ActivitySettingCenter.class);
        context.startActivity(intent);
        break;
}
    }


    class ViewHolder {
            TextView tv_date;
            TextView tv_orderNo;
            TextView tv_periods;
            TextView tv_money;
            Button bt_repayment;

        }

}
