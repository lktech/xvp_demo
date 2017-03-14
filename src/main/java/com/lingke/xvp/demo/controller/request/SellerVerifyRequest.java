package com.lingke.xvp.demo.controller.request;

public class SellerVerifyRequest implements XvpRequest {
	private String phone;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public String toString() {
		return "SellerVerifyRequest [phone=" + phone + "]";
	}
	
	
}
