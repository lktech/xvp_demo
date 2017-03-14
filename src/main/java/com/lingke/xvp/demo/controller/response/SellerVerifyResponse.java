package com.lingke.xvp.demo.controller.response;

public class SellerVerifyResponse implements XvpResponse {
	private String sn;

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	@Override
	public String toString() {
		return "SellerVerifyResponse [sn=" + sn + "]";
	}
	
}
