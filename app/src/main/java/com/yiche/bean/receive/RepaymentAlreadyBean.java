package com.yiche.bean.receive;

/**
 * Created by Administrator on 2017/2/28.
 */

public class RepaymentAlreadyBean {
    private String date;//日期
    private int orderNo;//订单号
    private String period;//期数
    private double money;//分期金额

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(int orderNo) {
        this.orderNo = orderNo;
    }

    public String getPeriod() {
        return period;
    }

    public void setPeriod(String period) {
        this.period = period;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }
}
