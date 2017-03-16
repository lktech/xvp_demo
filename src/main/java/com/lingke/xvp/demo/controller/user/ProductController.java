package com.lingke.xvp.demo.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.request.XvpProductGetRequest;
import com.Rop.api.response.XvpProductGetResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.ProductGetRequest;
import com.lingke.xvp.demo.controller.response.ProductResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
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
		//TODO
		//ropRequest.setStore_id(arg0);
		XvpProductGetResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		ProductResponse response= BeanCopyUtil.copy(ropResponse.getProduct(), ProductResponse.class);
		//TODO
		//response.setSku( BeanCopyUtil.copyList(ropResponse.getProduct().getSkus(), SkuResponse.class));
		return response;
	}
	
	
	
}
