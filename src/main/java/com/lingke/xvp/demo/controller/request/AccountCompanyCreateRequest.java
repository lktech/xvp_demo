package com.lingke.xvp.demo.controller.request;

public class AccountCompanyCreateRequest implements XvpRequest {
	private String company_name;
	private String buslince;
	private String buslince_pic;
	public String getCompany_name() {
		return company_name;
	}
	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}
	public String getBuslince() {
		return buslince;
	}
	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}
	public String getBuslince_pic() {
		return buslince_pic;
	}
	public void setBuslince_pic(String buslince_pic) {
		this.buslince_pic = buslince_pic;
	}
	@Override
	public String toString() {
		return "AccountCompanyCreateRequest [company_name=" + company_name + ", buslince=" + buslince
				+ ", buslince_pic=" + buslince_pic + "]";
	}
	
	
}
