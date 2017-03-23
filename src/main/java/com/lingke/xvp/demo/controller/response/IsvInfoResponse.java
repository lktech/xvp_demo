package com.lingke.xvp.demo.controller.response;


public class IsvInfoResponse implements XvpResponse {

	private String isvUrl;
	private String appId;
	public String getIsvUrl() {
		return isvUrl;
	}
	public void setIsvUrl(String isvUrl) {
		this.isvUrl = isvUrl;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	
}
