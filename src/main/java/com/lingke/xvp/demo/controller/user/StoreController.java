package com.lingke.xvp.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.ApiException;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.StoreCreateRequest;
import com.lingke.xvp.demo.controller.response.XvpResponse;

/**
 * Created by yuwb on 2017-03-13. 店铺相关业务处理
 */
@RestController("user_store")
@RequestMapping(value = "/user/store")
public class StoreController {
	@Autowired
	private XvpRopClient ropClientAdapter;
	@RequestMapping(path="/create",method=RequestMethod.POST)
	@ResponseBody
	public XvpResponse ctreatStore(@RequestBody StoreCreateRequest request) throws ApiException{
		return null;
	}
}
