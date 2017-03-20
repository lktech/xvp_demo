package com.lingke.xvp.demo.controller.request;

import java.util.List;

public class OrderCreateRequest implements XvpRequest {
	private Integer logistic_flg;
	private Long logistic_fee;
	private Long pay_amount;
	private Long addressee_id;
	private List<OrderItemRequest> buy_sku_list;


	public Integer getLogistic_flg() {
		return logistic_flg;
	}

	public void setLogistic_flg(Integer logistic_flg) {
		this.logistic_flg = logistic_flg;
	}


	public Long getLogistic_fee() {
		return logistic_fee;
	}

	public void setLogistic_fee(Long logistic_fee) {
		this.logistic_fee = logistic_fee;
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
