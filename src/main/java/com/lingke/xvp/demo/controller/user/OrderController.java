package com.lingke.xvp.demo.controller.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Rop.api.domain.XvpOrder;
import com.Rop.api.domain.XvpOrderItem;
import com.Rop.api.request.XvpOrderAcceptgoodsRequest;
import com.Rop.api.request.XvpOrderCreateRequest;
import com.Rop.api.request.XvpOrderGetRequest;
import com.Rop.api.request.XvpOrderQueryRequest;
import com.Rop.api.request.XvpTlpayGetpayurlRequest;
import com.Rop.api.response.XvpOrderCreateResponse;
import com.Rop.api.response.XvpOrderGetResponse;
import com.Rop.api.response.XvpOrderQueryResponse;
import com.Rop.api.response.XvpTlpayGetpayurlResponse;
import com.lingke.xvp.demo.XvpConstants;
import com.lingke.xvp.demo.XvpRopClient;
import com.lingke.xvp.demo.controller.request.OrderConfirmRequest;
import com.lingke.xvp.demo.controller.request.OrderCreateRequest;
import com.lingke.xvp.demo.controller.request.OrderGetRequest;
import com.lingke.xvp.demo.controller.request.OrderQueryRequest;
import com.lingke.xvp.demo.controller.response.OrderCreateResponse;
import com.lingke.xvp.demo.controller.response.OrderPayUrlResponse;
import com.lingke.xvp.demo.controller.response.OrderQueryDeliveryResponse;
import com.lingke.xvp.demo.controller.response.OrderQueryItemResponse;
import com.lingke.xvp.demo.controller.response.OrderQueryListResponse;
import com.lingke.xvp.demo.controller.response.OrderQueryResponse;
import com.lingke.xvp.demo.controller.response.XvpResponse;
import com.lingke.xvp.demo.exception.UserNoLoginException;
import com.lingke.xvp.demo.utils.BeanCopyUtil;
import com.lingke.xvp.demo.utils.SessionUtil;

import tech.nodex.tutils2.jackson.JsonUtils;

/**
 * Created by yuwb on 2017-03-13. 订单相关业务处理
 */
@RestController("user_order")
@RequestMapping(value = "/user/order")
public class OrderController {
	@Autowired
	private XvpRopClient ropClientAdapter;

	@RequestMapping(path = "/add", method = RequestMethod.POST)
	@ResponseBody
	public XvpResponse addOrder(@RequestBody OrderCreateRequest request) throws Exception {
		XvpOrderCreateRequest ropRequest = BeanCopyUtil.copy(request, XvpOrderCreateRequest.class);
		ropRequest.setPay_type(XvpConstants.DEFAULT_PAY_TYPE);
		ropRequest.setUser_id(SessionUtil.userGetUserId());
		ropRequest.setStore_id(Long.parseLong(SessionUtil.userGetStoreId()));
		String buySkuListStr = JsonUtils.toJson(request.getBuy_sku_list());
		ropRequest.setBuy_sku_list(buySkuListStr);
		XvpOrderCreateResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		OrderCreateResponse response = new OrderCreateResponse();
		response.setOrder_id(ropResponse.getXvporder().getOrder_id());
		return response;
	}

	@RequestMapping(path = "/payurl", method = RequestMethod.POST)
	public XvpResponse payurl(@RequestBody OrderGetRequest request) throws Exception {
		XvpTlpayGetpayurlRequest ropRequest = new XvpTlpayGetpayurlRequest();
		XvpTlpayGetpayurlResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		OrderPayUrlResponse response = new OrderPayUrlResponse();
		response.setUrl(ropResponse.getUrl().getPay_url() + "?appId=" + ropClientAdapter.getAppId() + "&orderId="
				+ request.getOrder_id());
		return response;
	}

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
		ropRequest.setStore_id(Long.valueOf(SessionUtil.userGetStoreId()));
		ropRequest.setUser_id(SessionUtil.userGetUserId());
		ropRequest.setPage_no(XvpConstants.PAGE_NO);
		ropRequest.setPage_size(XvpConstants.PAGE_SIZE);
		XvpOrderQueryResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		OrderQueryListResponse list = new OrderQueryListResponse();
		for (XvpOrder xvpOrder : ropResponse.getXvporders()) {
			OrderQueryResponse response = copyXvpOrderToXvpResponse(xvpOrder);
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
	 */
	@RequestMapping(path = "/get", method = RequestMethod.POST)
	public XvpResponse get(@RequestBody OrderGetRequest request) throws Exception {
		XvpOrderGetRequest ropRequest = BeanCopyUtil.copy(request, XvpOrderGetRequest.class);
		XvpOrderGetResponse ropResponse = ropClientAdapter.ropInvoke(ropRequest);
		String store_id = ropResponse.getXvporder().getStore_id();
		String user_id = ropResponse.getXvporder().getXvp_uid();
		if (!store_id.equals(SessionUtil.userGetStoreId()) || !user_id.equals(SessionUtil.userGetUserId())) {
			throw new UserNoLoginException();
		}
		OrderQueryResponse response = copyXvpOrderToXvpResponse(ropResponse.getXvporder());
		return response;
	}

	/**
	 * 买家确认收货
	 * 
	 * @param request
	 *            前台参数
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(path = "/confirm", method = RequestMethod.POST)
	public XvpResponse confirm(@RequestBody OrderConfirmRequest request) throws Exception {
		XvpOrderAcceptgoodsRequest ropRequest = BeanCopyUtil.copy(request, XvpOrderAcceptgoodsRequest.class);
		ropRequest.setXvp_uid(SessionUtil.userGetUserId());
		ropClientAdapter.ropInvoke(ropRequest);
		return null;
	}

	/**
	 * 从XvpOrder复制到XvpResponse
	 * 
	 * @param xvpOrder
	 *            xvpOrder
	 * @return
	 * @throws Exception
	 */
	private OrderQueryResponse copyXvpOrderToXvpResponse(XvpOrder xvpOrder) throws Exception {
		OrderQueryResponse response = BeanCopyUtil.copy(xvpOrder, OrderQueryResponse.class);
		response.setOrderNum(xvpOrder.getOrder_id());
		response.setOrderStatus(xvpOrder.getOrder_status());
		response.setPay(xvpOrder.getPay_amount());
		List<OrderQueryDeliveryResponse> orderdeliverys = BeanCopyUtil.copyList(xvpOrder.getOrderdeliverys(),
				OrderQueryDeliveryResponse.class);
		response.setOrderdeliverys(orderdeliverys);
		List<OrderQueryItemResponse> xvporderitems = BeanCopyUtil.copyList(xvpOrder.getXvporderitems(),
				OrderQueryItemResponse.class);
		for (int i = 0; i < xvporderitems.size(); i++) {
			XvpOrderItem xvpOrderItem = xvpOrder.getXvporderitems().get(i);
			OrderQueryItemResponse orderQueryItemResponse = xvporderitems.get(i);
			orderQueryItemResponse.setId(xvpOrderItem.getId());
			orderQueryItemResponse.setGoodsName(xvpOrderItem.getProduct_name());
			orderQueryItemResponse.setGoodsUrl(xvpOrderItem.getThumbnail());
			orderQueryItemResponse.setSku(xvpOrderItem.getSku_str());
			orderQueryItemResponse.setNum(xvpOrderItem.getItem_count());
		}
		response.setGoods(xvporderitems);
		return response;
	}
}
