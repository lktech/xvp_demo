package com.lingke.xvp.demo.controller.request;

import java.util.Date;

public class AccountWithDrawsQueryRequest implements XvpRequest {
	private Date begin;
	private Date end;
	public Date getBegin() {
		return begin;
	}
	public void setBegin(Date begin) {
		this.begin = begin;
	}
	public Date getEnd() {
		return end;
	}
	public void setEnd(Date end) {
		this.end = end;
	}
	@Override
	public String toString() {
		return "AccountWithDrawsQueryRequest [begin=" + begin + ", end=" + end + "]";
	}
	
}
