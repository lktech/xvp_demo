package com.lingke.xvp.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.ApiException;
import com.Rop.api.domain.XvpSku;
import com.Rop.api.request.XvpProductGetRequest;
import com.Rop.api.request.XvpSkuGetskuRequest;
import com.Rop.api.response.XvpProductGetResponse;
import com.Rop.api.response.XvpSkuGetskuResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.ProductGetRequest;
import com.lingke.xvp.demo.controller.request.ProductSkuGetRequest;
import com.lingke.xvp.demo.controller.response.ProductResponse;
import com.lingke.xvp.demo.controller.response.ProductSkuGetListResponse;
import com.lingke.xvp.demo.controller.response.ProductSkuGetResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
import com.lingke.xvp.demo.utils.SessionUtil;
/**
 * Created by yuwb on 2017-03-13. 商品相关业务处理
 */
@RestController("user_product")
@RequestMapping(value = "/user/product")
public class ProductController {
	@Autowired
	private XvpRopClient ropClientAdapter;

	
	@RequestMapping(path="/get",method=RequestMethod.POST)
	@ResponseBody
	public XvpResponse getProduct(@RequestBody ProductGetRequest request) throws Exception{
		XvpProductGetRequest ropRequest = new XvpProductGetRequest();
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setProduct_id(request.getId());
		XvpProductGetResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		ProductResponse response= BeanCopyUtil.copy(ropResponse.getProduct(), ProductResponse.class);
		return response;
	}
	
	/**
	 * 获得商品sku
	 * 
	 * @param request
	 * @return
	 * @throws ApiException
	 */
	@RequestMapping(path = "/sku/get", method = RequestMethod.POST)
	public XvpResponse getProductSku(@RequestBody ProductSkuGetRequest request) throws Exception {
		XvpSkuGetskuRequest ropRequest = BeanCopyUtil.copy(request, XvpSkuGetskuRequest.class);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setStore_id(Long.valueOf(SessionUtil.userGetStoreId()));
		XvpSkuGetskuResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		ProductSkuGetListResponse response = new ProductSkuGetListResponse();
		for (XvpSku sku : ropResponse.getXvpskus()) {
			ProductSkuGetResponse prodcutResponse = BeanCopyUtil.copy(sku, ProductSkuGetResponse.class);
			response.add(prodcutResponse);
		}
		return response;
	}
	
}
