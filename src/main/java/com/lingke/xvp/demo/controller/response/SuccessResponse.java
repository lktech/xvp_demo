package com.lingke.xvp.demo.controller.response;

public class SuccessResponse implements XvpResponse {
	private String code;
	private Object result;
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}

	
	
	
}
