package com.lingke.xvp.demo.controller.response;

public class SkuResponse implements XvpResponse {
	private String id;
	private String price;
	private String stock;
	private String sku_str;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getStock() {
		return stock;
	}
	public void setStock(String stock) {
		this.stock = stock;
	}
	public String getSku_str() {
		return sku_str;
	}
	public void setSku_str(String sku_str) {
		this.sku_str = sku_str;
	}

	
}
