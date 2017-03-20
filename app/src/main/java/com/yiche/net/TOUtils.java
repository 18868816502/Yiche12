package com.yiche.net;

import java.lang.reflect.Field;
import java.util.TreeMap;

/**
 * bean值转化为HashMap
 * @author xst
 */
public class TOUtils {
	public static TreeMap<String, Object> convertBeanToMap(Object bean) {
		Field[] fields = bean.getClass().getDeclaredFields();
		TreeMap<String, Object> data = new TreeMap<String, Object>();
		for (Field field : fields) {
			field.setAccessible(true);
			try {
				if(field.get(bean)!=null){
					data.put(field.getName(), field.get(bean));
				}
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			}
		}
//		System.out.println("TreeMap:"+data.toString());
		return data;
	}

}
