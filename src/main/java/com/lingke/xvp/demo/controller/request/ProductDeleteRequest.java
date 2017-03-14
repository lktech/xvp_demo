package com.lingke.xvp.demo.controller.request;

public class ProductDeleteRequest implements XvpRequest {
	private Long id;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
}
