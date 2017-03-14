package com.lingke.xvp.demo.controller.request;

public class StoreCreateRequest implements XvpRequest {
	private String store_name;
	private String owner_real_name;
	private String customer_service_phone;
	public String getStore_name() {
		return store_name;
	}
	public void setStore_name(String store_name) {
		this.store_name = store_name;
	}
	public String getOwner_real_name() {
		return owner_real_name;
	}
	public void setOwner_real_name(String owner_real_name) {
		this.owner_real_name = owner_real_name;
	}
	public String getCustomer_service_phone() {
		return customer_service_phone;
	}
	public void setCustomer_service_phone(String customer_service_phone) {
		this.customer_service_phone = customer_service_phone;
	}
	
}
