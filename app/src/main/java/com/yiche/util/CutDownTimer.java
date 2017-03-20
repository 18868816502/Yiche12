package com.yiche.util;

import android.annotation.SuppressLint;
import android.os.Handler;
import android.os.Message;
import android.widget.Button;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class CutDownTimer {
	private Button bt;
	private TextView tv;
	private int time;

	/**
	 * 验证码倒计时(button)
	 */
	public CutDownTimer(Button bt, int time) {
		this.bt = bt;
		this.time = time;
		counterTimer();
	}

	public void counterTimer() {
		new Thread() {
			public void run() {
				while (time > 0) {
					bt.setClickable(false);
					Message msg = new Message();
					time--;
					msg.what = 0;
					msg.arg1 = time;
					mHandler.sendMessage(msg);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};

		}.start();
	}

	private Handler mHandler = new Handler() {
		public void handleMessage(Message msg) {
			if (0 == msg.what) {
				bt.setText("已发送..." + msg.arg1 + "s");
				if (0 == msg.arg1) {
					bt.setClickable(true);
					bt.setText("重新获取验证码");
				}
			}

		};
	};

	
	/**
	 * Text
	 * 1:广告页倒计时  2:验证码倒计时
	 * 
	 * @param tv
	 * @param data
	 */
	public CutDownTimer(TextView tv, int data,int type) {
		this.tv = tv;
		this.time = data;
		if(type==1){
			counterTimer1();
		}
		if(type==2){
			counterTimer2();
		}
		if(type==3){
			counterTimer3();
		}
	}

	public void counterTimer1() {
		new Thread() {
			public void run() {
				while (time > 0) {
					Message msg = new Message();
					time--;
					msg.what = 0;
					msg.arg1 = time;
					mHandler1.sendMessage(msg);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};

		}.start();
	}

	private Handler mHandler1 = new Handler() {
		public void handleMessage(Message msg) {
			if (0 == msg.what) {
				tv.setText(msg.arg1 + "s 跳过");
			}

		};
	};
	public void counterTimer2() {
		new Thread() {
			public void run() {
				while (time > 0) {
					Message msg = new Message();
					time--;
					msg.what = 0;
					msg.arg1 = time;
					mHandler2.sendMessage(msg);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};

		}.start();
	}

	private Handler mHandler2 = new Handler() {
		public void handleMessage(Message msg) {
			if (0 == msg.what) {
				tv.setText("已发送..." + msg.arg1 + "s");
				if (0 == msg.arg1) {
					tv.setClickable(true);
					tv.setText("重新获取");
				}
			}

		};
	};
	public void counterTimer3() {
		new Thread() {
			public void run() {
				while (time > 0) {
					Message msg = new Message();
					time--;
					msg.what = 0;
					msg.arg1 = time;
					mHandler3.sendMessage(msg);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}

				}
			};

		}.start();
	}

	private Handler mHandler3 = new Handler() {
		public void handleMessage(Message msg) {
			if (0 == msg.what) {
				tv.setText(msg.arg1 + "");
			}

		};
	};
}
