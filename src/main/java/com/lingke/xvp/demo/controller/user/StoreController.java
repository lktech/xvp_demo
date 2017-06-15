package com.lingke.xvp.demo.controller.user;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.domain.ExtendField;
import com.Rop.api.domain.Store;
import com.Rop.api.request.XvpProductQueryRequest;
import com.Rop.api.request.XvpStoreGetRequest;
import com.Rop.api.response.XvpProductQueryResponse;
import com.Rop.api.response.XvpStoreGetResponse;
import com.lingke.xvp.demo.XvpAspect;
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
	private final Logger logger = LoggerFactory.getLogger(StoreController.class);
	@Autowired
	private XvpRopClient ropClientAdapter;
	@RequestMapping(path="/main",method=RequestMethod.POST)
	@ResponseBody
	public XvpResponse storeMain(@RequestBody StoreMainRequest request) throws Exception{
		XvpStoreGetRequest ropRequest = BeanCopyUtil.copy(request, XvpStoreGetRequest.class);
		XvpStoreGetResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		Store store = ropResponse.getStore();
		StoreMainResponse response = BeanCopyUtil.copy(store, StoreMainResponse.class);
		response.setLogo(getLogo(store.getExtendfields()));
		XvpProductQueryRequest ropProductRequest= new XvpProductQueryRequest();
		ropProductRequest.setStore_id(request.getStore_id());
		ropProductRequest.setPage_no(XvpConstants.PAGE_NO);
		ropProductRequest.setPage_size(XvpConstants.PAGE_SIZE);
		List<ProductResponse> products = new ArrayList<>();
		try {
			XvpProductQueryResponse ropProductResponse = ropClientAdapter.ropInvoke(ropProductRequest);
			products = BeanCopyUtil.copyList(ropProductResponse.getProducts(), ProductResponse.class);
		} catch (Exception e) {
			logger.error("查询商品失败,失败消息：", e);
		}
		response.setProducts(products);
		SessionUtil.userSetStoreId(String.valueOf(request.getStore_id()));
		return response;
	}
	
	private String getLogo(List<ExtendField> extendFields){
		if(extendFields==null||extendFields.size()==0){
			return null;
		}
		for(ExtendField extend : extendFields){
			if("logo".equals(extend.getColumn_name())){
				return extend.getColumn_value();
			}
		}
		return null;
	}
}
