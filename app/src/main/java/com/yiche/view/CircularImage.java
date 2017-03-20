package com.yiche.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;

/**
 * 
 * @类描述：圆形图片处理类,将图片转换成字符串
 * @项目名称：beautyZheJiang
 * @包名： com.einshow.beautyzhejiang.custom
 * @类名称：CircularImage
 * @创建人：Administrator
 * @创建时间：2014-10-16上午11:04:36
 * @修改人：Administrator
 * @修改时间：2014-10-16上午11:04:36
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright go3c
 * @mail *@qq.com
 */
public class CircularImage extends MyBaseImageView {

	public CircularImage(Context paramContext) {
		super(paramContext);
	}

	public CircularImage(Context paramContext, AttributeSet paramAttributeSet) {
		super(paramContext, paramAttributeSet);
	}

	public CircularImage(Context paramContext, AttributeSet paramAttributeSet,
			int paramInt) {
		super(paramContext, paramAttributeSet, paramInt);
	}

	public Bitmap createMask() {
		int i = getWidth();
		int j = getHeight();
		Bitmap.Config localConfig = Bitmap.Config.ARGB_8888;
		Bitmap localBitmap = Bitmap.createBitmap(i, j, localConfig);
		Canvas localCanvas = new Canvas(localBitmap);
		Paint localPaint = new Paint(1);
		localPaint.setColor(Color.BLUE);
		float f1 = getWidth();
		float f2 = getHeight();
		RectF localRectF = new RectF(0.0F, 0.0F, f1, f2);
		localCanvas.drawOval(localRectF, localPaint);
		return localBitmap;
	}

}
