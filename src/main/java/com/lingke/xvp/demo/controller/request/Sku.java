package com.lingke.xvp.demo.controller.request;

public class Sku implements XvpRequest {
	private Long price;
	private Long logistics_fee;
	private Long stock;
	private String sku_str;

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public Long getLogistics_fee() {
		return logistics_fee;
	}

	public void setLogistics_fee(Long logistics_fee) {
		this.logistics_fee = logistics_fee;
	}

	public Long getStock() {
		return stock;
	}

	public void setStock(Long stock) {
		this.stock = stock;
	}

	public String getSku_str() {
		return sku_str;
	}

	public void setSku_str(String sku_str) {
		this.sku_str = sku_str;
	}

}
