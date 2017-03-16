package com.lingke.xvp.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tech.nodex.tutils2.jackson.JsonUtils;

import com.Rop.api.request.XvpOrderCreateRequest;
import com.Rop.api.response.XvpOrderCreateResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.OrderCreateRequest;
import com.lingke.xvp.demo.controller.response.OrderCreateResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
/**
 * Created by yuwb on 2017-03-13. 订单相关业务处理
 */
@RestController("user_order")
@RequestMapping(value = "/user/order")
public class OrderController {
	@Autowired
	private XvpRopClient ropClientAdapter;


	
	@RequestMapping(path="/add",method=RequestMethod.POST)
	@ResponseBody
	public XvpResponse addOrder(@RequestBody OrderCreateRequest request) throws Exception{
		XvpOrderCreateRequest ropRequest = BeanCopyUtil.copy(request, XvpOrderCreateRequest.class);
		// TODO
		ropRequest.setApp_id("REMOVE");
		ropRequest.setPay_type("ONLINE");
		String buySkuListStr = JsonUtils.toJson(request.getBuy_sku_list());
		ropRequest.setBuy_sku_list(buySkuListStr);
		XvpOrderCreateResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		OrderCreateResponse response = new OrderCreateResponse();
		response.setOrder_id(ropResponse.getXvporder().getOrder_id());
		return response;
	}
}
