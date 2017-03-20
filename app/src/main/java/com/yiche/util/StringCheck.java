package com.yiche.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringCheck {
	/**
	 * 判断字符串是否为空  true表示空
	 */
	public static Boolean emptyOrNull(String str) {
		if (str == null) {
			return true;
		} else {
			if (str.length() == 0) {
				return true;
			}
		}
		return false;
	}
	/**
	 * 判断电话号码格式  true表示是电话号码
	 */
	public static boolean isMobileNO(String mobiles) {
		boolean flag = false;
		try {
			// Pattern p = Pattern
			// .compile("^((13[0-9])|(15[^4,\\D])|(18[0,0-9]))\\d{8}$");
			Pattern p = Pattern.compile("^1[3456789]\\d{9}$");
			Matcher m = p.matcher(mobiles);
			flag = m.matches();
		} catch (Exception e) {
			flag = false;
		}
		return flag;
	}

	/**
	 * 判断密码是否是6-16位且是数字和字符的组合
	 */
	public static boolean isPwd(String pwd) {
		try {
			Pattern p = Pattern
					.compile("^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{6,16}$");
			Matcher m = p.matcher(pwd);
			return m.matches();
		} catch (Exception e) {
			return false;
		}
	}
	/**
	 * 判断密码是否是QQ号
	 */
	public static boolean isQq(String qq) {
		try {
			Pattern p = Pattern
					.compile("[1-9][0-9]{4,14}");
			Matcher m = p.matcher(qq);
			return m.matches();
		} catch (Exception e) {
			return false;
		}
	}
}
