package com.lingke.xvp.demo.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.cglib.beans.BeanCopier;

public class BeanCopyUtil {
	private static final Map<String, BeanCopier> cache = new ConcurrentHashMap<>();

	public static <T> T copy(Object from, Class<T> c)
			throws Exception {
		if(from==null){
			return null;
		}
		T to = c.newInstance();
		String beanKey = generateKey(from.getClass(), c);
		BeanCopier copier = null;
		if (!cache.containsKey(beanKey)) {
			copier = BeanCopier.create(from.getClass(), to.getClass(), false);
			cache.put(beanKey, copier);
		} else {
			copier = cache.get(beanKey);
		}
		copier.copy(from, to, null);
		return to;
	}
	public static void copy(Object from,Object to)
			throws Exception {
		if(from==null){
			return;
		}
		String beanKey = generateKey(from.getClass(), to.getClass());
		BeanCopier copier = null;
		if (!cache.containsKey(beanKey)) {
			copier = BeanCopier.create(from.getClass(), to.getClass(), false);
			cache.put(beanKey, copier);
		} else {
			copier = cache.get(beanKey);
		}
		copier.copy(from, to, null);
	}
	
	public static <T,Y> List<T> copyList(List<Y> from, Class<T> c)
			throws Exception {
		if(from==null||from.size()==0){
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

}
