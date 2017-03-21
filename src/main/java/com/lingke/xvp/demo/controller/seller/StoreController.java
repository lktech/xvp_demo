package com.lingke.xvp.demo.controller.seller;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.ApiException;
import com.Rop.api.domain.ExtendField;
import com.Rop.api.request.XvpStoreCreateRequest;
import com.Rop.api.request.XvpStoreGetRequest;
import com.Rop.api.request.XvpStoreUpdateRequest;
import com.Rop.api.response.XvpStoreCreateResponse;
import com.Rop.api.response.XvpStoreGetResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.ExtendFieldRequest;
import com.lingke.xvp.demo.controller.request.StoreCreateRequest;
import com.lingke.xvp.demo.controller.request.StoreUpdateRequest;
import com.lingke.xvp.demo.controller.response.StoreResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.db.dao.Seller;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
import com.lingke.xvp.demo.utils.SessionUtil;

import tech.nodex.tutils2.jackson.JsonUtils;

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
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		String extendFields = getExtendField(request.getRegion_code(),request.getDetail_address(),request.getQq());
		ropRequest.setExtend_fields(extendFields);
		XvpStoreCreateResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		SessionUtil.sellerSetStoreId(ropResponse.getStore().getId());
		Seller.updateSellerStoreId();
		return null;
	}
	private String getExtendField(String region_code,String detail_address,String qq){
		List<ExtendFieldRequest> extendFields = new ArrayList<ExtendFieldRequest>();
		if(!StringUtils.isEmpty(region_code)){
			ExtendFieldRequest extendField = new ExtendFieldRequest();
			extendField.setColumnName("region_code");
			extendField.setColumnValue(region_code);
			extendFields.add(extendField);
		}
		if(!StringUtils.isEmpty(detail_address)){
			ExtendFieldRequest extendField = new ExtendFieldRequest();
			extendField.setColumnName("detail_address");
			extendField.setColumnValue(detail_address);
			extendFields.add(extendField);
		}
		if(!StringUtils.isEmpty(qq)){
			ExtendFieldRequest extendField = new ExtendFieldRequest();
			extendField.setColumnName("qq");
			extendField.setColumnValue(qq);
			extendFields.add(extendField);
		}
		return JsonUtils.toJson(extendFields);
	}
	
	private void copyRopExtendFieldToResponse(XvpStoreGetResponse ropResponse,StoreResponse response) throws Exception{
		if( ropResponse.getStore().getExtendfields()==null|| ropResponse.getStore().getExtendfields().size()==0){
			return ;
		}
		for(ExtendField extendField : ropResponse.getStore().getExtendfields()){
			Field declaredField = response.getClass().getDeclaredField(extendField.getColumn_name());
			declaredField.setAccessible(true);
			declaredField.set(response, extendField.getColumn_value());
		}
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
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setStore_id(Long.parseLong(SessionUtil.sellerGetStoreId()));
		String extendFields = getExtendField(request.getRegion_code(),request.getDetail_address(),request.getQq());
		ropRequest.setExtend_fields(extendFields);
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
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		String storeId= SessionUtil.sellerGetStoreId();
		if(StringUtils.isEmpty(storeId)){
			return null;
		}
		ropRequest.setStore_id(Long.parseLong(storeId));
		XvpStoreGetResponse ropResponse= ropClientAdapter.ropInvoke(ropRequest);
		StoreResponse response = BeanCopyUtil.copy(ropResponse.getStore(), StoreResponse.class);
		copyRopExtendFieldToResponse(ropResponse,response);
		return response;
	}
	
	
}
