package com.lingke.xvp.demo.controller.request;

public class AccountWithDrawsRequest implements XvpRequest {
	private long amount;
	private long commission;
	public long getAmount() {
		return amount;
	}
	public void setAmount(long amount) {
		this.amount = amount;
	}
	public long getCommission() {
		return commission;
	}
	public void setCommission(long commission) {
		this.commission = commission;
	}
	@Override
	public String toString() {
		return "AccountWithDrawsRequest [amount=" + amount + ", commission=" + commission + "]";
	}
	
}
