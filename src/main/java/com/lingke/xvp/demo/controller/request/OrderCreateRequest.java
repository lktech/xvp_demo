package com.lingke.xvp.demo.controller.request;

import java.util.List;

public class OrderCreateRequest implements XvpRequest {
	private Long store_id;
	private Long user_id;
	private Long logistic_flg;
	private Long logistics_fee;
	private Long pay_amount;
	private Long addressee_id;
	private List<OrderItemRequest> buy_sku_list;

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public Long getLogistic_flg() {
		return logistic_flg;
	}

	public void setLogistic_flg(Long logistic_flg) {
		this.logistic_flg = logistic_flg;
	}

	public Long getLogistics_fee() {
		return logistics_fee;
	}

	public void setLogistics_fee(Long logistics_fee) {
		this.logistics_fee = logistics_fee;
	}

	public Long getPay_amount() {
		return pay_amount;
	}

	public void setPay_amount(Long pay_amount) {
		this.pay_amount = pay_amount;
	}

	public Long getAddressee_id() {
		return addressee_id;
	}

	public void setAddressee_id(Long addressee_id) {
		this.addressee_id = addressee_id;
	}

	public List<OrderItemRequest> getBuy_sku_list() {
		return buy_sku_list;
	}

	public void setBuy_sku_list(List<OrderItemRequest> buy_sku_list) {
		this.buy_sku_list = buy_sku_list;
	}

}
