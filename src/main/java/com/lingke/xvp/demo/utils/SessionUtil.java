package com.lingke.xvp.demo.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import com.lingke.xvp.demo.XvpConstants;

public class SessionUtil {
	public final static String SELLER_LOGIN_ID = "SELLER_LOGIN_ID";
	public final static String USER_LOGIN_ID = "USER_LOGIN_ID";
	public final static String SELLER_STORELOGIN_ID = "SELLER_STORELOGIN_ID";
	public final static String USER_STORELOGIN_ID = "USER_STORELOGIN_ID";
	private final static  Logger logger = LoggerFactory.getLogger(SessionUtil.class);
	public static void sellerLogin(Long value) {
		logger.info("SessionUtil->sellerLogin 调用：{}",value);
		RequestContextHolder.getRequestAttributes().setAttribute(
				SELLER_LOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
	}

	public static boolean checkSellerLogin() {
		logger.info("SessionUtil->checkSellerLogin 调用");
		return getSellerLoginId() != null;
	}

	public static void sellerLogout() {
		logger.info("SessionUtil->sellerLogout 调用");
		RequestContextHolder.getRequestAttributes().removeAttribute(SELLER_LOGIN_ID, RequestAttributes.SCOPE_SESSION);
		RequestContextHolder.getRequestAttributes().removeAttribute(SELLER_STORELOGIN_ID, RequestAttributes.SCOPE_SESSION);
	}
	
	public static Long getSellerLoginId() {
		logger.info("SessionUtil->getSellerLoginId 调用");
		return (Long)RequestContextHolder.getRequestAttributes().getAttribute(
				SELLER_LOGIN_ID, RequestAttributes.SCOPE_SESSION);
	}

	public static void sellerSetStoreId(String value) {
		logger.info("SessionUtil->sellerSetStoreId 调用");
		RequestContextHolder.getRequestAttributes().setAttribute(
				SELLER_STORELOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
	}

	public static String sellerGetStoreId() {
		logger.info("SessionUtil->sellerGetStoreId 调用");
		Object ob = RequestContextHolder.getRequestAttributes().getAttribute(
				SELLER_STORELOGIN_ID, RequestAttributes.SCOPE_SESSION);
		if (ob == null) {
			throw new RuntimeException(XvpConstants.ERROR_MESSAGE04);
		}
		return ob.toString();
	}

	public static void userLogin(String value) {
		logger.info("SessionUtil->userLogin 调用：{}",value);
		RequestContextHolder.getRequestAttributes().setAttribute(USER_LOGIN_ID,
				value, RequestAttributes.SCOPE_SESSION);
	}

	
	public static boolean checkUserLogin() {
		logger.info("SessionUtil->checkUserLogin 调用");
		return RequestContextHolder.getRequestAttributes().getAttribute(
				USER_LOGIN_ID, RequestAttributes.SCOPE_SESSION) != null;
	}
	
	public static String userGetUserId() {
		logger.info("SessionUtil->userGetUserId 调用");
		Object ob = RequestContextHolder.getRequestAttributes().getAttribute(
				USER_LOGIN_ID, RequestAttributes.SCOPE_SESSION);
		if (ob == null) {
			throw new RuntimeException(XvpConstants.ERROR_MESSAGE05);
		}
		return ob.toString();
	}

	public static void userSetStoreId(String value) {
		logger.info("SessionUtil->userSetStoreId 调用：{}",value);
		RequestContextHolder.getRequestAttributes().setAttribute(
				USER_STORELOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
	}

	public static String userGetStoreId() {
		logger.info("SessionUtil->userGetStoreId 调用");
		Object ob = RequestContextHolder.getRequestAttributes().getAttribute(
				USER_STORELOGIN_ID, RequestAttributes.SCOPE_SESSION);
		if (ob == null) {
			throw new RuntimeException(XvpConstants.ERROR_MESSAGE04);
		}
		return ob.toString();
	}
}
