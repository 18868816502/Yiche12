package library.internal;

import library.PullToRefreshBase.Mode;
import library.PullToRefreshBase.Orientation;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;

public class SlimeLoadingLayout extends LoadingLayout{

	public SlimeLoadingLayout(Context context, Mode mode,
			Orientation scrollDirection, TypedArray attrs) {
		super(context, mode, scrollDirection, attrs);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected int getDefaultDrawableResId() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	protected void onLoadingDrawableSet(Drawable imageDrawable) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void onPullImpl(float scaleOfLayout) {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void pullToRefreshImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void refreshingImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void releaseToRefreshImpl() {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void resetImpl() {
		// TODO Auto-generated method stub
		
	}

}
