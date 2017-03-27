package com.lingke.xvp.demo.controller.response;

import java.util.List;


public class StoreMainResponse implements XvpResponse {
	private  String store_name;
	private String logo;
	private  List<ProductResponse> products;
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public List<ProductResponse> getProducts() {
		return products;
	}
	public void setProducts(List<ProductResponse> products) {
		this.products = products;
	}
	
	
	

}
