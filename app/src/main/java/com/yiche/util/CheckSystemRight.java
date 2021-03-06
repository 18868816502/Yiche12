package com.yiche.util;

import java.lang.reflect.Method;

import android.app.AppOpsManager;
import android.content.Context;
import android.os.Binder;

public class CheckSystemRight {
	/**
	 * 02. * 判断 悬浮窗口权限是否打开 03. * 04. * @param context 05. * @return true 允许
	 * false禁止 06.
	 */
	public static boolean getAppOps(Context context) {
		try {
			Object object = context.getSystemService("appops");
			if (object == null) {
				return false;
			}
			Class localClass = object.getClass();
			Class[] arrayOfClass = new Class[3];
			arrayOfClass[0] = Integer.TYPE;
			arrayOfClass[1] = Integer.TYPE;
			arrayOfClass[2] = String.class;
			Method method = localClass.getMethod("checkOp", arrayOfClass);
			if (method == null) {
				return false;
			}
			Object[] arrayOfObject1 = new Object[3];
			arrayOfObject1[0] = Integer.valueOf(24);
			arrayOfObject1[1] = Integer.valueOf(Binder.getCallingUid());
			arrayOfObject1[2] = context.getPackageName();
			int m = ((Integer) method.invoke(object, arrayOfObject1))
					.intValue();
			return m == AppOpsManager.MODE_ALLOWED;
		} catch (Exception ex) {

		}
		return false;
	}

}
