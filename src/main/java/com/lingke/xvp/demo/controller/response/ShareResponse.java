package com.lingke.xvp.demo.controller.response;

/**
 * @author gaoyh
 *
 */
public class ShareResponse implements XvpResponse {
	private String signature;
	private String appId;
	private String nonceStr;
	private String timestamp;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getNonceStr() {
		return nonceStr;
	}

	public void setNonceStr(String nonceStr) {
		this.nonceStr = nonceStr;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ShareResponse [signature=" + signature + ", appId=" + appId + ", nonceStr=" + nonceStr + ", timestamp="
				+ timestamp + "]";
	}
	

}
