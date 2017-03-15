package com.lingke.xvp.demo.controller.seller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.ApiException;
import com.Rop.api.request.XvpStoreCreateRequest;
import com.Rop.api.request.XvpStoreGetRequest;
import com.Rop.api.request.XvpStoreUpdateRequest;
import com.Rop.api.response.XvpStoreGetResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.StoreCreateRequest;
import com.lingke.xvp.demo.controller.request.StoreUpdateRequest;
import com.lingke.xvp.demo.controller.response.StoreResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.utils.BeanCopyUtil;

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
	public XvpResponse createStore(@RequestBody StoreCreateRequest request) throws Exception {
		XvpStoreCreateRequest ropRequest = BeanCopyUtil.copy(request, XvpStoreCreateRequest.class);
		//TODO 
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropClientAdapter.ropInvoke(ropRequest);
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
	public XvpResponse updateStore(@RequestBody StoreUpdateRequest request) throws Exception {
		XvpStoreUpdateRequest ropRequest = BeanCopyUtil.copy(request, XvpStoreUpdateRequest.class);
		// TODO
		ropRequest.setApp_id(ropClientAdapter.getAppId());
//		ropRequest.setStore_id(request.getStore_id());
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}
	
	/**
	 * 获取店铺信息
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse getStore() throws Exception {
		XvpStoreGetRequest ropRequest = new XvpStoreGetRequest();
		// TODO
		ropRequest.setApp_id(ropClientAdapter.getAppId());
//		ropRequest.setStore_id(request.getStore_id());
		XvpStoreGetResponse ropResponse= ropClientAdapter.ropInvoke(ropRequest);
		StoreResponse response = BeanCopyUtil.copy(ropResponse.getStore(), StoreResponse.class);
		return response;
	}
	
	
}
