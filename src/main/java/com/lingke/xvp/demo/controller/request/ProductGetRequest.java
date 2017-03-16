package com.lingke.xvp.demo.controller.request;

public class ProductGetRequest implements XvpRequest {
	private Long id;
	private Long store_id;
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
	}
	
	
	
	
}
