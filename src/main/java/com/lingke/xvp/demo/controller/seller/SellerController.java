package com.lingke.xvp.demo.controller.seller;

import java.time.Duration;
import java.time.Instant;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.request.XvpPhoneSendcodeRequest;
import com.Rop.api.request.XvpPhoneVerifycodeRequest;
import com.Rop.api.response.XvpPhoneSendcodeResponse;
import com.Rop.api.response.XvpPhoneVerifycodeResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.SellerCheckRequest;
import com.lingke.xvp.demo.controller.request.SellerRegisterRequest;
import com.lingke.xvp.demo.controller.request.SellerVerifyRequest;
import com.lingke.xvp.demo.controller.response.SellerCheckResponse;
import com.lingke.xvp.demo.controller.response.SellerVerifyResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.db.dao.Seller;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
import com.lingke.xvp.demo.utils.SessionUtil;

/**
 * Created by yuwb on 2017-03-13. 卖家相关业务处理
 */
/**
 * @author Administrator
 *
 */
@RestController("seller_seller")
@RequestMapping(value = "/seller/seller")
public class SellerController {
	private static final Logger logger = LoggerFactory.getLogger(SellerController.class);
	@Autowired
	private XvpRopClient ropClientAdapter;

	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public XvpResponse verify(@RequestBody SellerVerifyRequest request) throws Exception {
		XvpPhoneSendcodeRequest ropRequest = BeanCopyUtil.copy(request, XvpPhoneSendcodeRequest.class);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		XvpPhoneSendcodeResponse xvpUserCreateResponse = ropClientAdapter.ropInvoke(ropRequest);
		SellerVerifyResponse sellerVerifyResponse = new SellerVerifyResponse();
		sellerVerifyResponse.setSn(xvpUserCreateResponse.getPhoneresult().getSn());
		return sellerVerifyResponse;
	}
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public XvpResponse check(@RequestBody SellerCheckRequest request) throws Exception {
		SellerCheckResponse response = new SellerCheckResponse();
		Seller seller = Seller.getSellerByPhone(request.getPhone());
		if (seller != null) {
			response.setFlag("yes");
		}else{
			response.setFlag("no");
		}
		return response;
	}
	
	@RequestMapping(value = "/login/check", method = RequestMethod.POST)
	public XvpResponse loginCheck() throws Exception {
		SellerCheckResponse response = new SellerCheckResponse();
		if (SessionUtil.checkSellerLogin()) {
			response.setFlag("yes");
		}else{
			response.setFlag("no");
		}
		return response;
	}
	
	@RequestMapping(value = "/logout", method = RequestMethod.POST)
	public XvpResponse logout() throws Exception {
		SessionUtil.sellerLogout();
		return null;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public XvpResponse register(@RequestBody SellerRegisterRequest request) throws Exception {
		Seller seller = Seller.getSellerByPhone(request.getPhone());
		if (seller != null) {
			throw new RuntimeException("手机号已经被注册");
		}
		if (!checkCode(request)) {
			throw new RuntimeException("验证码输入错误");
		}
		Seller.addSeller(request.getPhone(), request.getPassword());
		return null;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public XvpResponse login(@RequestBody SellerRegisterRequest request) throws Exception {
		Instant sellerQueryStart = Instant.now();
		Seller seller = Seller.getSellerByPhoneAndPassword(request.getPhone(), request.getPassword());
		Instant sellerQueryEnd = Instant.now();
		logger.info("查询用户信息总共花费：" + (Duration.between(sellerQueryStart, sellerQueryEnd).toMillis()/ 1000) + "秒");
		if (seller == null) {
			throw new RuntimeException("用户名或者密码错误");
		}
		Instant sellerLogoutStart = Instant.now();
		SessionUtil.sellerLogout();
		Instant sellerLogoutEnd = Instant.now();
		logger.info("用户退出总共花费：" + (Duration.between(sellerLogoutStart, sellerLogoutEnd).toMillis()/ 1000) + "秒");
		Instant sellerLoginStart = Instant.now();
		SessionUtil.sellerLogin(seller.getId());
		Instant sellerLoginEnd = Instant.now();
		logger.info("用户登录总共花费：" + (Duration.between(sellerLoginStart, sellerLoginEnd).toMillis()/ 1000) + "秒");
		if(!StringUtils.isEmpty(seller.getXvpStoreId())){
			Instant sellerStoreIdStart = Instant.now();
			SessionUtil.sellerSetStoreId(seller.getXvpStoreId().toString());
			Instant sellerStoreIdEnd = Instant.now();
			logger.info("用户设置storeId到session总共花费：" + (Duration.between(sellerStoreIdStart, sellerStoreIdEnd).toMillis() / 1000) + "秒");
		}
		return null;
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public XvpResponse reset(@RequestBody SellerRegisterRequest request) throws Exception {
		Seller seller = Seller.getSellerByPhone(request.getPhone());
		if (seller == null) {
			throw new RuntimeException("手机号输入错误");
		}
		if (!checkCode(request)) {
			throw new RuntimeException("验证码输入错误");
		}
		Boolean isSuccess = Seller.updateSellerPasswordByPhone(request.getPhone(), request.getPassword());
		if (!isSuccess) {
			throw new RuntimeException("设置密码失败");
		}
		return null;

	}

	/**
	 * 校验验证码是否正确
	 * 
	 * @param request
	 *            用户输入信息
	 * @return
	 * @throws Exception 
	 */
	private Boolean checkCode(SellerRegisterRequest request) throws Exception {
		XvpPhoneVerifycodeRequest ropRequest = BeanCopyUtil.copy(request, XvpPhoneVerifycodeRequest.class);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		XvpPhoneVerifycodeResponse response = ropClientAdapter.ropInvoke(ropRequest);
		return Boolean.valueOf(response.getPhoneresult().getFlag());
	}
}
