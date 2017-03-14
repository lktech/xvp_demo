package com.lingke.xvp.demo.controller.response;

import java.util.List;

public class ProductQueryResponse implements XvpResponse {
	private List<ProductResponse> list;

	public List<ProductResponse> getList() {
		return list;
	}

	public void setList(List<ProductResponse> list) {
		this.list = list;
	}
	
}
