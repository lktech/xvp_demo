package com.lingke.xvp.demo.controller.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import tech.nodex.tutils2.jackson.JsonUtils;

import com.Rop.api.ApiException;
import com.Rop.api.domain.XvpOrder;
import com.Rop.api.request.XvpOrderAcceptgoodsRequest;
import com.Rop.api.request.XvpOrderCreateRequest;
import com.Rop.api.request.XvpOrderGetRequest;
import com.Rop.api.request.XvpOrderQueryRequest;
import com.Rop.api.request.XvpPayGetpayurlRequest;
import com.Rop.api.response.XvpOrderCreateResponse;
import com.Rop.api.response.XvpOrderGetResponse;
import com.Rop.api.response.XvpOrderQueryResponse;
import com.Rop.api.response.XvpPayGetpayurlResponse;
import com.lingke.xvp.demo.XvpConstants;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.OrderConfirmRequest;
import com.lingke.xvp.demo.controller.request.OrderCreateRequest;
import com.lingke.xvp.demo.controller.request.OrderGetRequest;
import com.lingke.xvp.demo.controller.request.OrderPayUrlRequest;
import com.lingke.xvp.demo.controller.request.OrderQueryRequest;
import com.lingke.xvp.demo.controller.response.OrderCreateResponse;
import com.lingke.xvp.demo.controller.response.OrderPayUrlResponse;
import com.lingke.xvp.demo.controller.response.OrderQueryDeliveryResponse;
import com.lingke.xvp.demo.controller.response.OrderQueryItemResponse;
import com.lingke.xvp.demo.controller.response.OrderQueryListResponse;
import com.lingke.xvp.demo.controller.response.OrderQueryResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
import com.lingke.xvp.demo.utils.SessionUtil;
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
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setPay_type(XvpConstants.DEFAULT_PAY_TYPE);
		String buySkuListStr = JsonUtils.toJson(request.getBuy_sku_list());
		ropRequest.setBuy_sku_list(buySkuListStr);
		XvpOrderCreateResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		OrderCreateResponse response = new OrderCreateResponse();
		response.setOrder_id(ropResponse.getXvporder().getOrder_id());
		return response;
	}
	
	@RequestMapping(path="/payurl",method=RequestMethod.POST)
	public XvpResponse payurl(@RequestBody OrderPayUrlRequest request) throws Exception{
		XvpPayGetpayurlRequest ropRequest = new XvpPayGetpayurlRequest();
		XvpPayGetpayurlResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		OrderPayUrlResponse response = new OrderPayUrlResponse();
		response.setUrl(ropResponse.getUrl().getPay_url());
		return response;
	}
	/**
	 * 订单管理查询
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws ApiException
	 *             Api异常
	 */
	@RequestMapping(path = "/query", method = RequestMethod.POST)
	public XvpResponse query(@RequestBody OrderQueryRequest request) throws ApiException {
		XvpOrderQueryRequest ropRequest = new XvpOrderQueryRequest();
		BeanUtils.copyProperties(request, ropRequest);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setStore_id(Long.valueOf(SessionUtil.userGetStoreId()));
		ropRequest.setUser_id(request.getUser_id());
		ropRequest.setPage_no(XvpConstants.PAGE_NO);
		ropRequest.setPage_size(XvpConstants.PAGE_SIZE);
		XvpOrderQueryResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		OrderQueryListResponse list = new OrderQueryListResponse();
		for (XvpOrder xvpOrder : ropResponse.getXvporders()) {
			OrderQueryResponse response = new OrderQueryResponse();
			copyXvpOrderToXvpResponse(xvpOrder, response);
			list.add(response);
		}
		return list;
	}

	/**
	 * 订单详情查询
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws ApiException
	 *             Api异常
	 */
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public XvpResponse get(@RequestBody OrderGetRequest request) throws ApiException {
		XvpOrderGetRequest ropRequest = new XvpOrderGetRequest();
		BeanUtils.copyProperties(request, ropRequest);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		XvpOrderGetResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		OrderQueryResponse response = new OrderQueryResponse();
		copyXvpOrderToXvpResponse(ropResponse.getXvporder(), response);
		return response;
	}
	/**
	 * 买家确认收货
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws ApiException
	 *             Api异常
	 */
	@RequestMapping(path = "/confirm", method = RequestMethod.POST)
	public XvpResponse confirm(@RequestBody OrderConfirmRequest request) throws ApiException {
		XvpOrderAcceptgoodsRequest ropRequest = new XvpOrderAcceptgoodsRequest();
		BeanUtils.copyProperties(request, ropRequest);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}
	/**
	 * 从XvpOrder复制到XvpResponse
	 * 
	 * @param xvpOrder
	 *            xvpOrder
	 * 
	 * @param response
	 *            response
	 */
	private void copyXvpOrderToXvpResponse(XvpOrder xvpOrder, OrderQueryResponse response) {
		BeanUtils.copyProperties(xvpOrder, response);
		if (!CollectionUtils.isEmpty(xvpOrder.getOrderdeliverys())) {
			List<OrderQueryDeliveryResponse> orderdeliverys = xvpOrder.getOrderdeliverys().stream().map(x -> {
				OrderQueryDeliveryResponse orderQueryDeliveryResponse = new OrderQueryDeliveryResponse();
				BeanUtils.copyProperties(x, orderQueryDeliveryResponse);
				return orderQueryDeliveryResponse;
			}).collect(Collectors.toList());
			response.setOrderdeliverys(orderdeliverys);
		}
		if (!CollectionUtils.isEmpty(xvpOrder.getXvporderitems())) {
			List<OrderQueryItemResponse> orderitems = xvpOrder.getXvporderitems().stream().map(x -> {
				OrderQueryItemResponse orderQueryItemResponse = new OrderQueryItemResponse();
				BeanUtils.copyProperties(x, orderQueryItemResponse);
				return orderQueryItemResponse;
			}).collect(Collectors.toList());
			response.setXvporderitems(orderitems);
		}
	}
}
