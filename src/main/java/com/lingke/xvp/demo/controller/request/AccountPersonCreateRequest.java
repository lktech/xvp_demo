package com.lingke.xvp.demo.controller.request;

public class AccountPersonCreateRequest implements XvpRequest {
	private String user_name;
	private String certificate_type;
	private String certificate_number;
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getCertificate_type() {
		return certificate_type;
	}
	public void setCertificate_type(String certificate_type) {
		this.certificate_type = certificate_type;
	}
	public String getCertificate_number() {
		return certificate_number;
	}
	public void setCertificate_number(String certificate_number) {
		this.certificate_number = certificate_number;
	}
	@Override
	public String toString() {
		return "AccountPersonCreateRequest [user_name=" + user_name + ", certificate_type=" + certificate_type
				+ ", certificate_number=" + certificate_number + "]";
	}
	
}
