package com.lingke.xvp.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.request.XvpProductQueryRequest;
import com.Rop.api.request.XvpStoreGetRequest;
import com.Rop.api.response.XvpProductQueryResponse;
import com.Rop.api.response.XvpStoreGetResponse;
import com.lingke.xvp.demo.XvpConstants;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.StoreMainRequest;
import com.lingke.xvp.demo.controller.response.ProductResponse;
import com.lingke.xvp.demo.controller.response.StoreMainResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
import com.lingke.xvp.demo.utils.SessionUtil;

/**
 * Created by yuwb on 2017-03-13. 店铺相关业务处理
 */
@RestController("user_store")
@RequestMapping(value = "/user/store")
public class StoreController {
	@Autowired
	private XvpRopClient ropClientAdapter;
	@RequestMapping(path="/main",method=RequestMethod.POST)
	@ResponseBody
	public XvpResponse storeMain(@RequestBody StoreMainRequest request) throws Exception{
		XvpStoreGetRequest ropRequest = BeanCopyUtil.copy(request, XvpStoreGetRequest.class);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		XvpStoreGetResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		StoreMainResponse response = BeanCopyUtil.copy(ropResponse.getStore(), StoreMainResponse.class);
		XvpProductQueryRequest ropProductRequest= new XvpProductQueryRequest();
		ropProductRequest.setApp_id(ropClientAdapter.getAppId());
		ropProductRequest.setStore_id(request.getStore_id());
		ropProductRequest.setPage_no(XvpConstants.PAGE_NO);
		ropProductRequest.setPage_size(XvpConstants.PAGE_SIZE);
		XvpProductQueryResponse ropProductResponse = ropClientAdapter.ropInvoke(ropProductRequest);
		List<ProductResponse> products = BeanCopyUtil.copyList(ropProductResponse.getProducts(), ProductResponse.class);
		response.setProducts(products);
		SessionUtil.userSetStoreId(String.valueOf(request.getStore_id()));
		return response;
	}
}
