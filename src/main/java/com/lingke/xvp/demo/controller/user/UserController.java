package com.lingke.xvp.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.request.XvpUserAddaddressRequest;
import com.Rop.api.request.XvpUserGetdefaultaddressRequest;
import com.Rop.api.request.XvpUserSetdefaultaddressRequest;
import com.Rop.api.request.XvpUserUpdateaddressRequest;
import com.Rop.api.response.XvpUserAddaddressResponse;
import com.Rop.api.response.XvpUserGetdefaultaddressResponse;
import com.alibaba.druid.util.StringUtils;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.UserAddressSetRequest;
import com.lingke.xvp.demo.controller.request.UserLoginRequest;
import com.lingke.xvp.demo.controller.response.IsvInfoResponse;
import com.lingke.xvp.demo.controller.response.UserAddressResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
import com.lingke.xvp.demo.utils.SessionUtil;

/**
 * Created by yuwb on 2017-03-13. 用户相关业务处理
 */
@RestController("user_user")
@RequestMapping(value = "/user/user")
public class UserController {
	@Value("${com.lingke.xvp.rop.key}")
	private String appId;
	@Value("${com.xiaovpu.openapi.isv.url}")
	private String isvUrl;
	@Autowired
	private XvpRopClient ropClientAdapter;

	@RequestMapping(path = "/address/default", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse getDefaultAddress() throws Exception {
		XvpUserGetdefaultaddressRequest ropRequest = new XvpUserGetdefaultaddressRequest();
		ropRequest.setXvp_uid(SessionUtil.userGetUserId());
		XvpUserGetdefaultaddressResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		UserAddressResponse response = BeanCopyUtil.copy(ropResponse.getAddressee(), UserAddressResponse.class);
		return response;
	}

	@RequestMapping(path = "/address/set", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse setDefaultAddress(@RequestBody UserAddressSetRequest request) throws Exception {
		Long addresssId = null;
		if (request.getId() != null) {
			XvpUserUpdateaddressRequest ropRequest = BeanCopyUtil.copy(request, XvpUserUpdateaddressRequest.class);
			ropRequest.setXvp_uid(SessionUtil.userGetUserId());
			ropClientAdapter.ropInvoke(ropRequest);
			addresssId = request.getId();
		} else {
			XvpUserAddaddressRequest ropRequest = BeanCopyUtil.copy(request, XvpUserAddaddressRequest.class);
			ropRequest.setXvp_uid(SessionUtil.userGetUserId());
			XvpUserAddaddressResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
			addresssId = Long.parseLong(ropResponse.getAddressee().getId());
		}

		XvpUserSetdefaultaddressRequest ropRequest = new XvpUserSetdefaultaddressRequest();
		ropRequest.setId(addresssId);
		ropRequest.setXvp_uid(SessionUtil.userGetUserId());
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	@RequestMapping(path = "/login", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse setDefaultAddress(@RequestBody UserLoginRequest request) throws Exception {
		if (!StringUtils.isEmpty(request.getXvp_uid())) {
			SessionUtil.userLogin(request.getXvp_uid());
		}
		return null;
	}

	@RequestMapping(path = "/getIsvInfo", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse getIsvInfo() throws Exception {
		IsvInfoResponse openIdResponse = new IsvInfoResponse();
		openIdResponse.setAppId(appId);
		openIdResponse.setIsvUrl(isvUrl);
		return openIdResponse;
	}
}