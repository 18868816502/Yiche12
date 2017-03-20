package com.yiche.adapter;

import java.util.List;
import java.util.zip.Inflater;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.yiche.R;
import com.yiche.bean.receive.CarBean;

public class GridViewAdapter extends BaseAdapter {
	private Context context;
	private List<CarBean> list;

	public GridViewAdapter(Context context, List<CarBean> list) {
		this.context = context;
		this.list = list;
		System.out.println("list.size():"+list.size());
		// TODO Auto-generated constructor stub
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		System.out.println("list.size():"+list.size());
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Holder holder=null;
		if(convertView==null){
			convertView=LayoutInflater.from(context).inflate(R.layout.item_car_display,null);
			holder=new Holder();
			holder.iv_carPic=(ImageView) convertView.findViewById(R.id.iv_carPic);
			holder.tv_name=(TextView) convertView.findViewById(R.id.tv_name);
			holder.tv_introduction=(TextView) convertView.findViewById(R.id.tv_introduction);
			holder.tv_price=(TextView) convertView.findViewById(R.id.tv_price);
			convertView.setTag(holder);
		}else{
			holder=(Holder) convertView.getTag();
		}
		CarBean bean= list.get(position);
		holder.tv_name.setText(bean.getName());
		return convertView;
	}

	private class Holder {

		ImageView iv_carPic;
		TextView tv_name;
		TextView tv_introduction;
		TextView tv_price;

		public ImageView getIv_carPic() {
			return iv_carPic;
		}

		public void setIv_carPic(ImageView iv_carPic) {
			this.iv_carPic = iv_carPic;
		}

		public TextView getTv_name() {
			return tv_name;
		}

		public void setTv_name(TextView tv_name) {
			this.tv_name = tv_name;
		}

		public TextView getTv_introduction() {
			return tv_introduction;
		}

		public void setTv_introduction(TextView tv_introduction) {
			this.tv_introduction = tv_introduction;
		}

		public TextView getTv_price() {
			return tv_price;
		}

		public void setTv_price(TextView tv_price) {
			this.tv_price = tv_price;
		}

	}
}
