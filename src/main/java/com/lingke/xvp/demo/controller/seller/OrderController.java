package com.lingke.xvp.demo.controller.seller;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.ApiException;
import com.Rop.api.domain.XvpOrder;
import com.Rop.api.request.XvpOrderDeliverRequest;
import com.Rop.api.request.XvpOrderDiscountRequest;
import com.Rop.api.request.XvpOrderGetRequest;
import com.Rop.api.request.XvpOrderQueryRequest;
import com.Rop.api.response.XvpOrderGetResponse;
import com.Rop.api.response.XvpOrderQueryResponse;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.OrderDeliveryRequest;
import com.lingke.xvp.demo.controller.request.OrderDiscountRequest;
import com.lingke.xvp.demo.controller.request.OrderGetRequest;
import com.lingke.xvp.demo.controller.request.OrderQueryRequest;
import com.lingke.xvp.demo.controller.response.OrderQueryListResponse;
import com.lingke.xvp.demo.controller.response.OrderQueryResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;

/**
 * Created by yuwb on 2017-03-13. 订单相关业务处理
 */
@RestController("seller_order")
@RequestMapping(value = "/seller/order")
public class OrderController {
	@Autowired
	private XvpRopClient ropClientAdapter;

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
		// TODO
		ropRequest.setApp_id("329509F6-B7B8-4A9E-8817-D2EDCBE5559D");
		ropRequest.setUser_id("1c527151a3e44009949616e316f757d0");
		ropRequest.setStore_id(620L);
		ropRequest.setPage_no(1);
		ropRequest.setPage_size(10000);
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
		// TODO
		ropRequest.setApp_id("329509F6-B7B8-4A9E-8817-D2EDCBE5559D");
		XvpOrderGetResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		OrderQueryResponse response = new OrderQueryResponse();
		copyXvpOrderToXvpResponse(ropResponse.getXvporder(), response);
		return response;
	}

	/**
	 * 订单发货
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws ApiException
	 *             Api异常
	 */
	@RequestMapping(path = "/delivery", method = RequestMethod.POST)
	public XvpResponse delivery(@RequestBody OrderDeliveryRequest request) throws ApiException {
		XvpOrderDeliverRequest ropRequest = new XvpOrderDeliverRequest();
		BeanUtils.copyProperties(request, ropRequest);
		ropRequest.setLogistic_flg(request.getNeed_logistics_flg());
		StringBuilder itemIds = new StringBuilder();
		request.getOrder_item_id_list().forEach(x -> itemIds.append(x).append(","));
		ropRequest.setOrder_item_id_list(itemIds.deleteCharAt(itemIds.length() - 1).toString());
		// TODO
		ropRequest.setApp_id("329509F6-B7B8-4A9E-8817-D2EDCBE5559D");
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 更新订单优惠金额
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws ApiException
	 *             Api异常
	 */
	@RequestMapping(path = "/discount", method = RequestMethod.POST)
	public XvpResponse discount(@RequestBody OrderDiscountRequest request) throws ApiException {
		XvpOrderDiscountRequest ropRequest = new XvpOrderDiscountRequest();
		BeanUtils.copyProperties(request, ropRequest);
		// TODO
		ropRequest.setApp_id("2593F225-7D37-45FD-A064-B55DAF1A5ECF");
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
			response.getOrderdeliverys().addAll(xvpOrder.getOrderdeliverys());
		}
		if (!CollectionUtils.isEmpty(xvpOrder.getXvporderitems())) {
			response.getXvporderitems().addAll(xvpOrder.getXvporderitems());
		}
	}

}
