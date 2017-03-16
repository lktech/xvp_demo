package com.lingke.xvp.demo.controller.request;

public class OrderItemRequest implements XvpRequest {
	private Long sku_id;
	
	private Long price;
	
	private Integer count;

	public Long getSku_id() {
		return sku_id;
	}

	public void setSku_id(Long sku_id) {
		this.sku_id = sku_id;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}
	
	
	
	
}
