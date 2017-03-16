package com.lingke.xvp.demo.controller.response;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.Rop.api.domain.OrderDelivery;
import com.Rop.api.domain.XvpOrderItem;

/**
 * @author gaoyh
 *
 */
public class OrderQueryResponse implements XvpResponse {
	/**
	 * 订单ID
	 */
	private String order_id;
	/**
	 * 支付类型, 默认 ONLINE: 在线支付 POINTS: 积分 INSTALLMENTS: 分期付
	 */
	private String pay_type;
	/**
	 * 订单去除运费和优惠的商品金额
	 */
	private Long items_total_fee;
	/**
	 * 优惠金额
	 */
	private Long seller_discount_fee;
	/**
	 * 运费金额
	 */
	private Long logistic_fee;
	/**
	 * 最终支付的金额 final_amount = items_total_fee + logistic_fee -
	 * seller_discount_fee
	 */
	private Long pay_amount;
	/**
	 * 用户的备注
	 */
	private String user_remark;
	/**
	 * 卖家的备注
	 */
	private String seller_remark;
	/**
	 * 订单状态 DFK：待付款 DFH：待发货 DSH：待收货 YSH： 已收货 YGB： 已关闭
	 */
	private String order_status;
	/**
	 * 物流配送地址
	 */
	private String address;
	/**
	 * 收货人
	 */
	private String logistic_user;
	/**
	 * 收货人联系电话
	 */
	private String logistic_phone;
	/**
	 * 创建时间
	 */
	private Date create_time;
	/**
	 * 支付时间
	 */
	private Date pay_time;
	/**
	 * 最后确认收货时间
	 */
	private Date confirm_receive_time;
	/**
	 * 订单项列表
	 */
	private List<XvpOrderItem> xvporderitems = new ArrayList<>();
	/**
	 * 订单项列表
	 */
	private List<OrderDelivery> orderdeliverys = new ArrayList<>();

	/**
	 * @return the order_id
	 */
	public String getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id
	 *            the order_id to set
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	/**
	 * @return the pay_type
	 */
	public String getPay_type() {
		return pay_type;
	}

	/**
	 * @param pay_type
	 *            the pay_type to set
	 */
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}

	/**
	 * @return the items_total_fee
	 */
	public Long getItems_total_fee() {
		return items_total_fee;
	}

	/**
	 * @param items_total_fee
	 *            the items_total_fee to set
	 */
	public void setItems_total_fee(Long items_total_fee) {
		this.items_total_fee = items_total_fee;
	}

	/**
	 * @return the seller_discount_fee
	 */
	public Long getSeller_discount_fee() {
		return seller_discount_fee;
	}

	/**
	 * @param seller_discount_fee
	 *            the seller_discount_fee to set
	 */
	public void setSeller_discount_fee(Long seller_discount_fee) {
		this.seller_discount_fee = seller_discount_fee;
	}

	/**
	 * @return the logistic_fee
	 */
	public Long getLogistic_fee() {
		return logistic_fee;
	}

	/**
	 * @param logistic_fee
	 *            the logistic_fee to set
	 */
	public void setLogistic_fee(Long logistic_fee) {
		this.logistic_fee = logistic_fee;
	}

	/**
	 * @return the pay_amount
	 */
	public Long getPay_amount() {
		return pay_amount;
	}

	/**
	 * @param pay_amount
	 *            the pay_amount to set
	 */
	public void setPay_amount(Long pay_amount) {
		this.pay_amount = pay_amount;
	}

	/**
	 * @return the user_remark
	 */
	public String getUser_remark() {
		return user_remark;
	}

	/**
	 * @param user_remark
	 *            the user_remark to set
	 */
	public void setUser_remark(String user_remark) {
		this.user_remark = user_remark;
	}

	/**
	 * @return the seller_remark
	 */
	public String getSeller_remark() {
		return seller_remark;
	}

	/**
	 * @param seller_remark
	 *            the seller_remark to set
	 */
	public void setSeller_remark(String seller_remark) {
		this.seller_remark = seller_remark;
	}

	/**
	 * @return the order_status
	 */
	public String getOrder_status() {
		return order_status;
	}

	/**
	 * @param order_status
	 *            the order_status to set
	 */
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	/**
	 * @param address
	 *            the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the logistic_user
	 */
	public String getLogistic_user() {
		return logistic_user;
	}

	/**
	 * @param logistic_user
	 *            the logistic_user to set
	 */
	public void setLogistic_user(String logistic_user) {
		this.logistic_user = logistic_user;
	}

	/**
	 * @return the logistic_phone
	 */
	public String getLogistic_phone() {
		return logistic_phone;
	}

	/**
	 * @param logistic_phone
	 *            the logistic_phone to set
	 */
	public void setLogistic_phone(String logistic_phone) {
		this.logistic_phone = logistic_phone;
	}

	/**
	 * @return the create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}

	/**
	 * @param create_time
	 *            the create_time to set
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	/**
	 * @return the pay_time
	 */
	public Date getPay_time() {
		return pay_time;
	}

	/**
	 * @param pay_time
	 *            the pay_time to set
	 */
	public void setPay_time(Date pay_time) {
		this.pay_time = pay_time;
	}

	/**
	 * @return the confirm_receive_time
	 */
	public Date getConfirm_receive_time() {
		return confirm_receive_time;
	}

	/**
	 * @param confirm_receive_time
	 *            the confirm_receive_time to set
	 */
	public void setConfirm_receive_time(Date confirm_receive_time) {
		this.confirm_receive_time = confirm_receive_time;
	}

	/**
	 * @return the xvporderitems
	 */
	public List<XvpOrderItem> getXvporderitems() {
		return xvporderitems;
	}

	/**
	 * @param xvporderitems
	 *            the xvporderitems to set
	 */
	public void setXvporderitems(List<XvpOrderItem> xvporderitems) {
		this.xvporderitems = xvporderitems;
	}

	/**
	 * @return the orderdeliverys
	 */
	public List<OrderDelivery> getOrderdeliverys() {
		return orderdeliverys;
	}

	/**
	 * @param orderdeliverys
	 *            the orderdeliverys to set
	 */
	public void setOrderdeliverys(List<OrderDelivery> orderdeliverys) {
		this.orderdeliverys = orderdeliverys;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderQueryResponse [order_id=" + order_id + ", pay_type=" + pay_type + ", items_total_fee="
				+ items_total_fee + ", seller_discount_fee=" + seller_discount_fee + ", logistic_fee=" + logistic_fee
				+ ", pay_amount=" + pay_amount + ", user_remark=" + user_remark + ", seller_remark=" + seller_remark
				+ ", order_status=" + order_status + ", address=" + address + ", logistic_user=" + logistic_user
				+ ", logistic_phone=" + logistic_phone + ", create_time=" + create_time + ", pay_time=" + pay_time
				+ ", confirm_receive_time=" + confirm_receive_time + ", xvporderitems=" + xvporderitems
				+ ", orderdeliverys=" + orderdeliverys + "]";
	}

}
