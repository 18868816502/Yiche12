package com.yiche.adapter;

import java.util.List;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.yiche.bean.receive.BannersBean;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

public class LunboAdapter extends PagerAdapter {
    private BannersBean bean;
    private List mPaths;
    private Context mContext;

    public LunboAdapter(Context cx) {
        mContext = cx;
    }

    public void change(List paths) {
        mPaths = paths;
    }

    @Override
    public int getCount() {
        return Integer.MAX_VALUE;
    }

    @Override
    public boolean isViewFromObject(View view, Object obj) {
        return view == (View) obj;
    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView iv = new ImageView(mContext);
        ViewHolder holder;
        bean = (BannersBean) mPaths.get(position % mPaths.size());
//		Drawable bitmapDrawable = mContext.getResources().getDrawable((Integer) mPaths.get(position));
//		iv.setImageDrawable(bitmapDrawable);
        ImageLoader.getInstance().displayImage(bean.getPic(), iv);//从后台后取到banner图片

//			  String imgUrl=bean.getPic();
//	        ImageLoaderUtil.getInstance().displayListItemImage(imgUrl, holder.);

        iv.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
//				bean=(BannersBean) mPaths.get(position%mPaths.size());
//                  if(bean.getPicUrl()!=null&&bean.getPicUrl().length()>0){
//                	  Intent intent = new Intent(mContext,BannerAdverAct.class);
//                	  intent.putExtra("url", bean.getPicUrl());
//                	  intent.putExtra("title", bean.getTitle());
//                	  mContext.startActivity(intent);
//                  }
            }
        });

        ((ViewPager) container).addView(iv, 0);
        return iv;
    }

    public static class ViewHolder {


        ViewHolder(View view) {


        }

    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
