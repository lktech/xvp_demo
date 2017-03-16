package com.lingke.xvp.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
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
import com.lingke.xvp.demo.controller.request.UserAddressGetRequest;
import com.lingke.xvp.demo.controller.request.UserAddressSetRequest;
import com.lingke.xvp.demo.controller.request.UserLoginRequest;
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
	@Autowired
	private XvpRopClient ropClientAdapter;
	@RequestMapping(path="/address/default",method=RequestMethod.POST)
	@ResponseBody
	public XvpResponse getDefaultAddress(@RequestBody UserAddressGetRequest request) throws Exception{
		XvpUserGetdefaultaddressRequest ropRequest = new XvpUserGetdefaultaddressRequest();
		ropRequest.setXvp_uid(request.getXvp_uid());
		XvpUserGetdefaultaddressResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		UserAddressResponse response = BeanCopyUtil.copy(ropResponse.getAddressee(), UserAddressResponse.class);
		return response;
	}
	
	@RequestMapping(path="/address/set",method=RequestMethod.POST)
	@ResponseBody
	public XvpResponse setDefaultAddress(@RequestBody UserAddressSetRequest request) throws Exception{
		Long addresssId = null;
		if(request.getId()!=null){
			XvpUserUpdateaddressRequest ropRequest = BeanCopyUtil.copy(request, XvpUserUpdateaddressRequest.class);
			ropClientAdapter.ropInvoke(ropRequest);
			addresssId = request.getId();
		}else{
			XvpUserAddaddressRequest ropRequest = BeanCopyUtil.copy(request, XvpUserAddaddressRequest.class);
			XvpUserAddaddressResponse  ropResponse = ropClientAdapter.ropInvoke(ropRequest);
			addresssId = Long.parseLong(ropResponse.getAddressee().getId());
		}
		
		XvpUserSetdefaultaddressRequest ropRequest = new XvpUserSetdefaultaddressRequest();
		ropRequest.setId(addresssId);
		ropRequest.setXvp_uid(request.getXvp_uid());
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}
	
	@RequestMapping(path="/login",method=RequestMethod.POST)
	@ResponseBody
	public XvpResponse setDefaultAddress(@RequestBody UserLoginRequest request) throws Exception{
		if(!StringUtils.isEmpty(request.getXvp_uid())){
			SessionUtil.userLogin(request.getXvp_uid());
		}
		return null;
	}
}