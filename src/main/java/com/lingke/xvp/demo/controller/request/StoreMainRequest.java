package com.lingke.xvp.demo.controller.request;

import java.util.List;

import com.lingke.xvp.demo.controller.response.ProductResponse;

public class StoreMainRequest implements XvpRequest {
	private Long store_id;

	private List<ProductResponse> product;
	
	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}

	public List<ProductResponse> getProduct() {
		return product;
	}

	public void setProduct(List<ProductResponse> product) {
		this.product = product;
	}

}
