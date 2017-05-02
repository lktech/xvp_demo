package com.lingke.xvp.demo.controller.response;

public class AccountBankInfoResponse implements XvpResponse {

	/**
	 * 银行code
	 */
	private String bankcode;
	/**
	 * 银行名称
	 */
	private String bankname;
	public String getBankcode() {
		return bankcode;
	}
	public void setBankcode(String bankcode) {
		this.bankcode = bankcode;
	}
	public String getBankname() {
		return bankname;
	}
	public void setBankname(String bankname) {
		this.bankname = bankname;
	}
	@Override
	public String toString() {
		return "AccountBankInfoResponse [bankcode=" + bankcode + ", bankname=" + bankname + "]";
	}
	
}
