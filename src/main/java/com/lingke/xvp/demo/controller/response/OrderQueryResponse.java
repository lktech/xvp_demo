package com.lingke.xvp.demo.controller.response;

import java.util.ArrayList;
import java.util.List;

/**
 * @author gaoyh
 *
 */
public class OrderQueryResponse implements XvpResponse {
	/**
	 * 订单ID
	 */
	private String orderNum;
	/**
	 * 支付类型, 默认 ONLINE: 在线支付 POINTS: 积分 INSTALLMENTS: 分期付
	 */
	private String pay_type;
	/**
	 * 订单去除运费和优惠的商品金额
	 */
	private String items_total_fee;
	/**
	 * 优惠金额
	 */
	private String seller_discount_fee;
	/**
	 * 运费金额
	 */
	private String logistic_fee;
	/**
	 * 最终支付的金额 final_amount = items_total_fee + logistic_fee -
	 * seller_discount_fee
	 */
	private String pay;
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
	private String orderStatus;

	/**
	 * 物流配送地址
	 */
	private String address;
	/**
	 * 收货人
	 */
	private String address_name;
	/**
	 * 收货人联系电话
	 */
	private String address_phone;
	/**
	 * 创建时间
	 */
	private String create_time;
	/**
	 * 支付时间
	 */
	private String pay_time;
	/**
	 * 最后确认收货时间
	 */
	private String confirm_receive_time;
	/**
	 * 订单项列表
	 */
	private List<OrderQueryItemResponse> goods = new ArrayList<>();
	/**
	 * 订单项列表
	 */
	private List<OrderQueryDeliveryResponse> orderdeliverys = new ArrayList<>();
	/**
	 * @return the orderNum
	 */
	public String getOrderNum() {
		return orderNum;
	}
	/**
	 * @param orderNum the orderNum to set
	 */
	public void setOrderNum(String orderNum) {
		this.orderNum = orderNum;
	}
	/**
	 * @return the pay_type
	 */
	public String getPay_type() {
		return pay_type;
	}
	/**
	 * @param pay_type the pay_type to set
	 */
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	/**
	 * @return the items_total_fee
	 */
	public String getItems_total_fee() {
		return items_total_fee;
	}
	/**
	 * @param items_total_fee the items_total_fee to set
	 */
	public void setItems_total_fee(String items_total_fee) {
		this.items_total_fee = items_total_fee;
	}
	/**
	 * @return the seller_discount_fee
	 */
	public String getSeller_discount_fee() {
		return seller_discount_fee;
	}
	/**
	 * @param seller_discount_fee the seller_discount_fee to set
	 */
	public void setSeller_discount_fee(String seller_discount_fee) {
		this.seller_discount_fee = seller_discount_fee;
	}
	/**
	 * @return the logistic_fee
	 */
	public String getLogistic_fee() {
		return logistic_fee;
	}
	/**
	 * @param logistic_fee the logistic_fee to set
	 */
	public void setLogistic_fee(String logistic_fee) {
		this.logistic_fee = logistic_fee;
	}
	/**
	 * @return the pay
	 */
	public String getPay() {
		return pay;
	}
	/**
	 * @param pay the pay to set
	 */
	public void setPay(String pay) {
		this.pay = pay;
	}
	/**
	 * @return the user_remark
	 */
	public String getUser_remark() {
		return user_remark;
	}
	/**
	 * @param user_remark the user_remark to set
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
	 * @param seller_remark the seller_remark to set
	 */
	public void setSeller_remark(String seller_remark) {
		this.seller_remark = seller_remark;
	}
	/**
	 * @return the orderStatus
	 */
	public String getOrderStatus() {
		return orderStatus;
	}
	/**
	 * @param orderStatus the orderStatus to set
	 */
	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the address_name
	 */
	public String getAddress_name() {
		return address_name;
	}
	/**
	 * @param address_name the address_name to set
	 */
	public void setAddress_name(String address_name) {
		this.address_name = address_name;
	}
	/**
	 * @return the address_phone
	 */
	public String getAddress_phone() {
		return address_phone;
	}
	/**
	 * @param address_phone the address_phone to set
	 */
	public void setAddress_phone(String address_phone) {
		this.address_phone = address_phone;
	}
	/**
	 * @return the create_time
	 */
	public String getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	/**
	 * @return the pay_time
	 */
	public String getPay_time() {
		return pay_time;
	}
	/**
	 * @param pay_time the pay_time to set
	 */
	public void setPay_time(String pay_time) {
		this.pay_time = pay_time;
	}
	/**
	 * @return the confirm_receive_time
	 */
	public String getConfirm_receive_time() {
		return confirm_receive_time;
	}
	/**
	 * @param confirm_receive_time the confirm_receive_time to set
	 */
	public void setConfirm_receive_time(String confirm_receive_time) {
		this.confirm_receive_time = confirm_receive_time;
	}
	/**
	 * @return the goods
	 */
	public List<OrderQueryItemResponse> getGoods() {
		return goods;
	}
	/**
	 * @param goods the goods to set
	 */
	public void setGoods(List<OrderQueryItemResponse> goods) {
		this.goods = goods;
	}
	/**
	 * @return the orderdeliverys
	 */
	public List<OrderQueryDeliveryResponse> getOrderdeliverys() {
		return orderdeliverys;
	}
	/**
	 * @param orderdeliverys the orderdeliverys to set
	 */
	public void setOrderdeliverys(List<OrderQueryDeliveryResponse> orderdeliverys) {
		this.orderdeliverys = orderdeliverys;
	}

}
