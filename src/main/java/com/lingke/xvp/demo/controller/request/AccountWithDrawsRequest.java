package com.lingke.xvp.demo.controller.request;

public class AccountWithDrawsRequest implements XvpRequest {
	private Long amount;
	private Long commission;
	public Long getAmount() {
		return amount;
	}
	public void setAmount(Long amount) {
		this.amount = amount;
	}
	public Long getCommission() {
		return commission;
	}
	public void setCommission(Long commission) {
		this.commission = commission;
	}
	@Override
	public String toString() {
		return "AccountWithDrawsRequest [amount=" + amount + ", commission=" + commission + "]";
	}
	
}
