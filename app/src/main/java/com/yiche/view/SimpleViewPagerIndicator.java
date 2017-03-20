package com.yiche.view;



import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;


public class SimpleViewPagerIndicator extends LinearLayout {

	private int COLOR_TEXT_NORMAL =Color.rgb(128,128,128);//0xFF999999;
	private int COLOR_INDICATOR_COLOR =Color.rgb(255,154,27);//0xFFF09026;

	private String[] mTitles;
	private int mTabCount = 1;
	private int mIndicatorColor = COLOR_INDICATOR_COLOR;
	private float mTranslationX;
	private Paint mPaint = new Paint();
	private int mTabWidth;
	private ViewPager viewPager;
	private int oldPosition = 0;
	private boolean isFirst=true;
	public SimpleViewPagerIndicator(Context context) {
		this(context, null);
	}

	public SimpleViewPagerIndicator(Context context, AttributeSet attrs) {
		super(context, attrs);
		mPaint.setColor(mIndicatorColor);
		mPaint.setStrokeWidth(5.0F);
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		if(isFirst){
			mTabWidth = w / mTabCount;
			isFirst=false;
		}		
	}

	public void setTitles(String[] titles) {
		mTitles = titles;
		mTabCount = titles.length;
		generateTitleView();

	}

	public void setIndicatorColor(int indicatorColor) {
		this.mIndicatorColor = indicatorColor;
	}

	@SuppressWarnings("deprecation")
	public void setViewPager(ViewPager viewPager){
		this.viewPager = viewPager;
		this.viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
			
			@Override
			public void onPageSelected(int arg0) {
				((TextView) getChildAt(oldPosition)).setTextColor(COLOR_TEXT_NORMAL);
				((TextView) getChildAt(arg0)).setTextColor(COLOR_INDICATOR_COLOR);
				oldPosition = arg0;
				if (onPageChangeListener != null)  
                {  
                    onPageChangeListener.onPageSelected(arg0);  
                }  
			}
			
			@Override
			public void onPageScrolled(int arg0, float arg1, int arg2) {
				scroll(arg0, arg1);
			}
			
			@Override
			public void onPageScrollStateChanged(int arg0) {
				
			}
		});
	}
	
	//滑动的时候设置颜色
	@Override
	protected void dispatchDraw(Canvas canvas) {
		super.dispatchDraw(canvas);
		canvas.save();
		canvas.translate(mTranslationX, getHeight() - 2);
		canvas.drawLine(0, 0, mTabWidth, 0, mPaint);
		canvas.restore();
	}

	public void scroll(int position, float offset) {
		/**
		 * <pre>
		 *  0-1:position=0 ;1-0:postion=0;
		 * </pre>
		 */
		mTranslationX = getWidth()/mTabCount * (position + offset);
		invalidate();
		
	}

	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		return super.dispatchTouchEvent(ev);
	}

	private void generateTitleView() {
		if (getChildCount() > 0)
			this.removeAllViews();
		int count = mTitles.length;

		setWeightSum(count);
		for (int i = 0; i < count; i++) {
			TextView tv = new TextView(getContext());
			LayoutParams lp = new LayoutParams(0, LayoutParams.MATCH_PARENT);
			lp.weight = 1;
			tv.setTag(i);
			tv.setGravity(Gravity.CENTER);
			if (i == 0) {
				tv.setTextColor(COLOR_INDICATOR_COLOR);
			}else {
				tv.setTextColor(COLOR_TEXT_NORMAL);
			}
			//tv.setBackgroundColor(R.color.asset_holding_gray);
			tv.setText(mTitles[i]);
			tv.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16);
			tv.setLayoutParams(lp);

			tv.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					int position = (Integer) arg0.getTag();
                    if (viewPager != null&&viewPager.getVisibility()!=View.GONE)  
                    {  
                        viewPager.setCurrentItem(position);  
                        
                    }else{
                    	
                    }  
				}
			});
			addView(tv);
		}
		//setIndicator(0);
	}
	
	 private OnPageChangeListener onPageChangeListener;  
	  
	    public void setOnPageChangeListener(OnPageChangeListener onPageChangeListener)  
	    {  
	        this.onPageChangeListener = onPageChangeListener;  
	    }  
	  
	    public interface OnPageChangeListener  
	    {  
	        public void onPageSelected(int position);  
	    }  

	 public void setCurrentItem(int position)  
	    {  
	        oldPosition = position;  
	        if (viewPager != null)  
	        {  
	            viewPager.setCurrentItem(position);  
	        }  
	    }  
}
