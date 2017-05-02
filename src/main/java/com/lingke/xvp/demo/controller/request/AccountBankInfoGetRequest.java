package com.lingke.xvp.demo.controller.request;

public class AccountBankInfoGetRequest implements XvpRequest {
	/**
	 * 银行code
	 */
	private String bankcode;
	/**
	 * 银行city code
	 */
	private String citycode;
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getCitycode() {
		return citycode;
	}
	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}
	@Override
	public String toString() {
		return "AccountBankInfoGetRequest [bankcode=" + bankcode + ", citycode=" + citycode + "]";
	}
	
	
	
}
