package com.lingke.xvp.demo.controller.seller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.druid.util.StringUtils;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.SellerRegisterRequest;
import com.lingke.xvp.demo.controller.request.SellerVerifyRequest;
import com.lingke.xvp.demo.controller.response.SellerVerifyResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.db.dao.Seller;

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
	@Autowired
	private XvpRopClient ropClientAdapter;
	private final Logger logger = LoggerFactory.getLogger(SellerController.class);

	@RequestMapping(value = "/verify", method = RequestMethod.POST)
	public XvpResponse verify(SellerVerifyRequest request) throws Exception {
		Seller seller = Seller.getSellerByPhone(request.getPhone());
		if (seller != null) {
			throw new RuntimeException("手机号已经被注册");
		}
		// XvpUserCreateRequest ropRequest = new XvpUserCreateRequest();
		// ropRequest.setPhone(request.getPhone());
		// XvpUserCreateResponse xvpUserCreateResponse =
		// ropClientAdapter.ropInvoke(ropRequest);
		SellerVerifyResponse sellerVerifyResponse = new SellerVerifyResponse();
		// if (xvpUserCreateResponse.isSuccess()) {
		// sellerVerifyResponse.setCode("SUCESS");
		// // TODO
		// String sn = "";
		// sellerVerifyResponse.getResult().setSn(sn);
		// } else {
		// sellerVerifyResponse.setCode(xvpUserCreateResponse.getErrorCode());
		// sellerVerifyResponse.setMessage(xvpUserCreateResponse.getMsg());
		// }
		// TODO
		sellerVerifyResponse.setSn("");
		return sellerVerifyResponse;
	}

	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public XvpResponse register(SellerRegisterRequest request) throws Exception {
		if (!checkCode(request.getCode(), request.getSn())) {
			throw new RuntimeException("验证码输入错误");
		}
		Seller.addSeller(request.getPhone(), request.getPassword());
		return null;
	}

	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public XvpResponse login(SellerRegisterRequest request) throws Exception {
		Seller seller = Seller.getSellerByPhoneAndPassword(request.getPhone(), request.getPassword());
		if (seller == null) {
			throw new RuntimeException("用户名或者密码错误");
		}
		return null;
	}

	@RequestMapping(value = "/reset", method = RequestMethod.POST)
	public XvpResponse reset(SellerRegisterRequest request) throws Exception {
		Seller seller = Seller.getSellerByPhone(request.getPhone());
		if (seller == null) {
			throw new RuntimeException("手机号输入错误");
		}
		if (!checkCode(request.getCode(), request.getSn())) {
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
	 * @param code
	 *            用户输入code
	 * @param sn
	 *            系统产生code
	 * @return
	 */
	private Boolean checkCode(String code, String sn) {
		// TODO
		return StringUtils.equals(code, sn);
	}
}
