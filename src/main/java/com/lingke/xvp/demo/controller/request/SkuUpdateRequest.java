package com.lingke.xvp.demo.controller.request;

public class SkuUpdateRequest implements XvpRequest {
	private Long id;
	private Long price;
	private Long stock;
	private String sku_str;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
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
