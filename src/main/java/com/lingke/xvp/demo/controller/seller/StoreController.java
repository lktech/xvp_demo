package com.lingke.xvp.demo.controller.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.ApiException;
import com.Rop.api.request.XvpStoreCreateRequest;
import com.Rop.api.request.XvpStoreUpdateRequest;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.StoreCreateRequest;
import com.lingke.xvp.demo.controller.request.StoreUpdateRequest;
import com.lingke.xvp.demo.controller.response.ExceptionResponse;
import com.lingke.xvp.demo.controller.response.SuccessResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;

/**
 * Created by yuwb on 2017-03-13. 店铺相关业务处理
 */
@RestController("seller_store")
@RequestMapping(value = "/seller/store")
public class StoreController {
	@Autowired
	private XvpRopClient ropClientAdapter;

	/**
	 * 添加店铺信息
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse createStore(@RequestBody StoreCreateRequest request) throws ApiException {
		XvpStoreCreateRequest ropRequst = new XvpStoreCreateRequest();
		ropRequst.setApp_id(ropClientAdapter.getAppId());
		ropRequst.setCustomer_service_phone(request.getCustomer_service_phone());
		ropRequst.setOwner_real_name(request.getOwner_real_name());
		ropRequst.setStore_name(request.getStore_name());
		ropClientAdapter.ropInvoke(ropRequst);
		return null;
	}

	/**
	 * 修改店铺信息
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/update", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse updateStore(@RequestBody StoreUpdateRequest request) throws ApiException {
		XvpStoreUpdateRequest ropRequest = new XvpStoreUpdateRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		// TODO
//		ropRequest.setStore_id(request.getStore_id());
		ropRequest.setStore_name(request.getStore_name());
		ropRequest.setOwner_real_name(request.getOwner_real_name());
		ropRequest.setCustomer_service_phone(request.getCustomer_service_phone());
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}
}
