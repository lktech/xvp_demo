package com.lingke.xvp.demo.controller.seller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.domain.XvpOrder;
import com.Rop.api.request.XvpOrderDeliverRequest;
import com.Rop.api.request.XvpOrderDiscountRequest;
import com.Rop.api.request.XvpOrderGetRequest;
import com.Rop.api.request.XvpOrderQueryRequest;
import com.Rop.api.response.XvpOrderGetResponse;
import com.Rop.api.response.XvpOrderQueryResponse;
import com.lingke.xvp.demo.XvpConstants;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.OrderDeliveryRequest;
import com.lingke.xvp.demo.controller.request.OrderDiscountRequest;
import com.lingke.xvp.demo.controller.request.OrderGetRequest;
import com.lingke.xvp.demo.controller.request.OrderQueryRequest;
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
	 * @throws Exception
	 */
	@RequestMapping(path = "/query", method = RequestMethod.POST)
	public XvpResponse query(@RequestBody OrderQueryRequest request) throws Exception {
		XvpOrderQueryRequest ropRequest = BeanCopyUtil.copy(request, XvpOrderQueryRequest.class);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropRequest.setStore_id(Long.valueOf(SessionUtil.sellerGetStoreId()));
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
	 * @throws Exception
	 *             异常
	 */
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public XvpResponse get(@RequestBody OrderGetRequest request) throws Exception {
		XvpOrderGetRequest ropRequest = BeanCopyUtil.copy(request, XvpOrderGetRequest.class);
		ropRequest.setApp_id(ropClientAdapter.getAppId());
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
	 * @throws Exception
	 */
	@RequestMapping(path = "/delivery", method = RequestMethod.POST)
	public XvpResponse delivery(@RequestBody OrderDeliveryRequest request) throws Exception {
		XvpOrderDeliverRequest ropRequest = BeanCopyUtil.copy(request, XvpOrderDeliverRequest.class);
		ropRequest.setLogistic_flg(request.getNeed_logistics_flg());
		StringBuilder itemIds = new StringBuilder();
		request.getOrder_item_id_list().forEach(x -> itemIds.append(x).append(","));
		ropRequest.setOrder_item_id_list(itemIds.deleteCharAt(itemIds.length() - 1).toString());
		ropRequest.setApp_id(ropClientAdapter.getAppId());
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 更新订单优惠金额
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/discount", method = RequestMethod.POST)
	public XvpResponse discount(@RequestBody OrderDiscountRequest request) throws Exception {
		XvpOrderDiscountRequest ropRequest = BeanCopyUtil.copy(request, XvpOrderDiscountRequest.class);
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
	 * @throws Exception
	 */
	private void copyXvpOrderToXvpResponse(XvpOrder xvpOrder, OrderQueryResponse response) throws Exception {
		response = BeanCopyUtil.copy(xvpOrder, OrderQueryResponse.class);
		List<OrderQueryDeliveryResponse> orderdeliverys = BeanCopyUtil.copyList(xvpOrder.getOrderdeliverys(),
				OrderQueryDeliveryResponse.class);
		response.setOrderdeliverys(orderdeliverys);
		List<OrderQueryItemResponse> xvporderitems = BeanCopyUtil.copyList(xvpOrder.getOrderdeliverys(),
				OrderQueryItemResponse.class);
		response.setXvporderitems(xvporderitems);
	}

}
