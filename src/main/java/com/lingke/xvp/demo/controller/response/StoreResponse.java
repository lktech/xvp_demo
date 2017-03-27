package com.lingke.xvp.demo.controller.response;



public class StoreResponse implements XvpResponse {
	private String id;
	private String store_name;
	private String owner_real_name;
	private String customer_service_phone;
	private String region_code;
	private String detail_address;
	private String qq;
	private String logo;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getRegion_code() {
		return region_code;
	}

	public void setRegion_code(String region_code) {
		this.region_code = region_code;
	}

	public String getDetail_address() {
		return detail_address;
	}

	public void setDetail_address(String detail_address) {
		this.detail_address = detail_address;
	}

	public String getQq() {
		return qq;
	}

	public void setQq(String qq) {
		this.qq = qq;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	
	

}
