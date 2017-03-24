package com.lingke.xvp.demo.utils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;
import org.springframework.util.StringUtils;

public class BeanCopyUtil {
	private static final Map<String, BeanCopier> cache = new ConcurrentHashMap<>();
	private static final XvpConverter xvpConverter = new XvpConverter();

	public static <T> T copy(Object from, Class<T> c) throws Exception {
		if (from == null) {
			return null;
		}
		T to = c.newInstance();
		String beanKey = generateKey(from.getClass(), c);
		BeanCopier copier = null;
		if (!cache.containsKey(beanKey)) {
			copier = BeanCopier.create(from.getClass(), to.getClass(), true);
			cache.put(beanKey, copier);
		} else {
			copier = cache.get(beanKey);
		}
		copier.copy(from, to, xvpConverter);
		return to;
	}

	public static void copy(Object from, Object to) throws Exception {
		if (from == null) {
			return;
		}
		String beanKey = generateKey(from.getClass(), to.getClass());
		BeanCopier copier = null;
		if (!cache.containsKey(beanKey)) {
			copier = BeanCopier.create(from.getClass(), to.getClass(), true);
			cache.put(beanKey, copier);
		} else {
			copier = cache.get(beanKey);
		}
		copier.copy(from, to, xvpConverter);
	}

	public static <T, Y> List<T> copyList(List<Y> from, Class<T> c) throws Exception {
		if (from == null || from.size() == 0) {
			return null;
		}
		List<T> result = new ArrayList<T>();
		for (Y o : from) {
			result.add(copy(o, c));
		}
		return result;
	}

	private static String generateKey(Class<?> class1, Class<?> class2) {
		return class1.toString() + class2.toString();
	}

	private static class XvpConverter implements Converter {
		@SuppressWarnings("rawtypes")
		@Override
		public Object convert(Object value, Class target, Object context) {
			if (target.equals(Long.class)) {
				if (value instanceof String) {
					return Long.parseLong(value.toString());
				}
			} else if (target.equals(Integer.class)) {
				if (value instanceof String) {
					return Integer.parseInt(value.toString());
				}
			}
			if ("setUpdate_time".equals(context) || "setCreate_time".equals(context)
					|| "setConfirm_receive_time".equals(context) || "setPay_time".equals(context)) {
				if(!StringUtils.isEmpty(value)){
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					return sdf.format(new Date(Long.valueOf(value.toString())));
				}
				return "";
			}
			if (value instanceof List) {
				return null;
			}
			return value;
		}
	}
}
