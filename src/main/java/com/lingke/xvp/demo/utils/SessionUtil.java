package com.lingke.xvp.demo.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

public class SessionUtil {
	public final static String SELLER_LOGIN_ID= "SELLER_LOGIN_ID";
	public final static String STORELOGIN_ID= "STORELOGIN_ID";
	public static void sellerLogin(Long value){
		RequestContextHolder.getRequestAttributes().setAttribute(SELLER_LOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
	}
	public static boolean checkSellerLogin(){
		return RequestContextHolder.getRequestAttributes().getAttribute(SELLER_LOGIN_ID, RequestAttributes.SCOPE_SESSION)!=null;	
	}
	
	public static void setStoreId(String value){
		RequestContextHolder.getRequestAttributes().setAttribute(STORELOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
	}
	public static Object getStoreId(){
		return RequestContextHolder.getRequestAttributes().getAttribute(STORELOGIN_ID, RequestAttributes.SCOPE_SESSION)	;	
	}
}
