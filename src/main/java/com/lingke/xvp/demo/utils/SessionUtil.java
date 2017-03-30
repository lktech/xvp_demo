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
		logger.info("SessionUtil->sellerLogin 调用开始：{},",value);
		RequestContextHolder.getRequestAttributes().setAttribute(
				SELLER_LOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
		logger.info("SessionUtil->sellerLogin 调用结束：{},",value);
	}

	public static boolean checkSellerLogin() {
		logger.info("SessionUtil->checkSellerLogin 调用开始");
		boolean sellerLoginIdIsNOtNull = getSellerLoginId() != null;
		logger.info("SessionUtil->checkSellerLogin 调用结束");
		return sellerLoginIdIsNOtNull;
	}

	public static void sellerLogout() {
		logger.info("SessionUtil->sellerLogout 调用开始");
		RequestContextHolder.getRequestAttributes().removeAttribute(SELLER_LOGIN_ID, RequestAttributes.SCOPE_SESSION);
		RequestContextHolder.getRequestAttributes().removeAttribute(SELLER_STORELOGIN_ID, RequestAttributes.SCOPE_SESSION);
		logger.info("SessionUtil->sellerLogout 调用结束");
	}
	
	public static Long getSellerLoginId() {
		logger.info("SessionUtil->getSellerLoginId 调用开始");
		Long sellerLoginId = (Long)RequestContextHolder.getRequestAttributes().getAttribute(
				SELLER_LOGIN_ID, RequestAttributes.SCOPE_SESSION);
		logger.info("SessionUtil->getSellerLoginId 调用结束");
		return sellerLoginId;
	}

	public static void sellerSetStoreId(String value) {
		logger.info("SessionUtil->sellerSetStoreId 调用开始");
		RequestContextHolder.getRequestAttributes().setAttribute(
				SELLER_STORELOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
		logger.info("SessionUtil->sellerSetStoreId 调用结束");
	}

	public static String sellerGetStoreId() {
		logger.info("SessionUtil->sellerGetStoreId 调用开始");
		Object ob = RequestContextHolder.getRequestAttributes().getAttribute(
				SELLER_STORELOGIN_ID, RequestAttributes.SCOPE_SESSION);
		if (ob == null) {
			throw new RuntimeException(XvpConstants.ERROR_MESSAGE04);
		}
		logger.info("SessionUtil->sellerGetStoreId 调用结束");
		return ob.toString();
	}

	public static void userLogin(String value) {
		logger.info("SessionUtil->userLogin 调用开始：{}",value);
		RequestContextHolder.getRequestAttributes().setAttribute(USER_LOGIN_ID,
				value, RequestAttributes.SCOPE_SESSION);
		logger.info("SessionUtil->userLogin 调用结束：{}",value);
	}

	
	public static boolean checkUserLogin() {
		logger.info("SessionUtil->checkUserLogin 调用开始");
		boolean isUserLogin = RequestContextHolder.getRequestAttributes().getAttribute(
				USER_LOGIN_ID, RequestAttributes.SCOPE_SESSION) != null;
		logger.info("SessionUtil->checkUserLogin 调用结束");
		return isUserLogin;
	}
	
	public static String userGetUserId() {
		logger.info("SessionUtil->userGetUserId 调用开始");
		Object ob = RequestContextHolder.getRequestAttributes().getAttribute(
				USER_LOGIN_ID, RequestAttributes.SCOPE_SESSION);
		if (ob == null) {
			throw new RuntimeException(XvpConstants.ERROR_MESSAGE05);
		}
		logger.info("SessionUtil->userGetUserId 调用结束");
		return ob.toString();
	}

	public static void userSetStoreId(String value) {
		logger.info("SessionUtil->userSetStoreId 调用开始：{}",value);
		RequestContextHolder.getRequestAttributes().setAttribute(
				USER_STORELOGIN_ID, value, RequestAttributes.SCOPE_SESSION);
		logger.info("SessionUtil->userSetStoreId 调用结束{}",value);
	}

	public static String userGetStoreId() {
		logger.info("SessionUtil->userGetStoreId 调用开始");
		Object ob = RequestContextHolder.getRequestAttributes().getAttribute(
				USER_STORELOGIN_ID, RequestAttributes.SCOPE_SESSION);
		if (ob == null) {
			throw new RuntimeException(XvpConstants.ERROR_MESSAGE04);
		}
		logger.info("SessionUtil->userGetStoreId 调用结束");
		return ob.toString();
	}
}
