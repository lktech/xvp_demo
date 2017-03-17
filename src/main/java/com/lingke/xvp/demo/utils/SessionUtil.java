package com.lingke.xvp.demo.utils;

import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.lingke.xvp.demo.XvpConstants;

public class SessionUtil {
	public final static String SELLER_LOGIN_ID = "SELLER_LOGIN_ID";
	public final static String USER_LOGIN_ID = "USER_LOGIN_ID";
	public final static String SELLER_STORELOGIN_ID = "SELLER_STORELOGIN_ID";
	public final static String USER_STORELOGIN_ID = "USER_STORELOGIN_ID";

	public static void sellerLogin(Long value) {
		RequestContextHolder.getRequestAttributes().setAttribute(
				SELLER_LOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
	}

	public static boolean checkSellerLogin() {
		return RequestContextHolder.getRequestAttributes().getAttribute(
				SELLER_LOGIN_ID, RequestAttributes.SCOPE_SESSION) != null;
	}

	public static void sellerSetStoreId(String value) {
		RequestContextHolder.getRequestAttributes().setAttribute(
				SELLER_STORELOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
	}

	public static String sellerGetStoreId() {
		Object ob = RequestContextHolder.getRequestAttributes().getAttribute(
				SELLER_STORELOGIN_ID, RequestAttributes.SCOPE_SESSION);
		if (ob == null) {
			throw new RuntimeException(XvpConstants.ERROR_MESSAGE04);
		}
		return ob.toString();
	}

	public static void userLogin(String value) {
		RequestContextHolder.getRequestAttributes().setAttribute(USER_LOGIN_ID,
				value, RequestAttributes.SCOPE_SESSION);
	}

	public static boolean checkUserLogin() {
		return RequestContextHolder.getRequestAttributes().getAttribute(
				USER_LOGIN_ID, RequestAttributes.SCOPE_SESSION) != null;
	}
	
	public static String userGetUserId() {
		Object ob = RequestContextHolder.getRequestAttributes().getAttribute(
				USER_LOGIN_ID, RequestAttributes.SCOPE_SESSION);
		if (ob == null) {
			throw new RuntimeException(XvpConstants.ERROR_MESSAGE05);
		}
		return ob.toString();
	}

	public static void userSetStoreId(String value) {
		RequestContextHolder.getRequestAttributes().setAttribute(
				USER_STORELOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
	}

	public static String userGetStoreId() {
		Object ob = RequestContextHolder.getRequestAttributes().getAttribute(
				USER_STORELOGIN_ID, RequestAttributes.SCOPE_SESSION);
		if (ob == null) {
			throw new RuntimeException(XvpConstants.ERROR_MESSAGE04);
		}
		return ob.toString();
	}
}
