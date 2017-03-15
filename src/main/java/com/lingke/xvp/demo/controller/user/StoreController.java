package com.lingke.xvp.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.request.XvpProductQueryRequest;
import com.Rop.api.request.XvpSkuGetskuRequest;
import com.Rop.api.request.XvpStoreGetRequest;
import com.Rop.api.response.XvpProductQueryResponse;
import com.Rop.api.response.XvpSkuGetskuResponse;
import com.Rop.api.response.XvpStoreGetResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.StoreMainRequest;
import com.lingke.xvp.demo.controller.response.ProductResponse;
import com.lingke.xvp.demo.controller.response.SkuResponse;
import com.lingke.xvp.demo.controller.response.StoreMainResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.utils.BeanCopyUtil;

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
	public XvpResponse ctreatStore(@RequestBody StoreMainRequest request) throws Exception{
		XvpStoreGetRequest ropRequest = BeanCopyUtil.copy(request, XvpStoreGetRequest.class);
		//TODO
		ropRequest.setApp_id("TOBEREMOVE");
		XvpStoreGetResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		StoreMainResponse response = BeanCopyUtil.copy(ropResponse.getStore(), StoreMainResponse.class);
		XvpProductQueryRequest ropProductRequest= new XvpProductQueryRequest();
		ropProductRequest.setApp_id("TOBEREMOVE");
		ropProductRequest.setStore_id(request.getStore_id());
		XvpProductQueryResponse ropProductResponse = ropClientAdapter.ropInvoke(ropProductRequest);
		List<ProductResponse> products = BeanCopyUtil.copyList(ropProductResponse.getProducts(), ProductResponse.class);
		for(ProductResponse product : products){
			XvpSkuGetskuRequest  ropSkuRequest = new XvpSkuGetskuRequest();
			ropSkuRequest.setApp_id("TOBEREMOVE");
			ropSkuRequest.setProduct_id(product.getId());
			ropSkuRequest.setStore_id(ropSkuRequest.getStore_id());
			XvpSkuGetskuResponse ropSkuResponse = ropClientAdapter.ropInvoke(ropSkuRequest);
			product.setSku(BeanCopyUtil.copyList(ropSkuResponse.getXvpskus(), SkuResponse.class));
		}
		return response;
	}
}
