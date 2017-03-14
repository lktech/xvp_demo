package com.lingke.xvp.demo.controller.request;

public class SellerRegisterRequest implements XvpRequest {
	private String phone;
	private String password;
	private String sn;
	private String code;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "SellerRegisterRequest [phone=" + phone + ", password=" + password + ", sn=" + sn + ", code=" + code
				+ "]";
	}
	
	
}
