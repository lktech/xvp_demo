package com.lingke.xvp.demo.controller.response;

/**
 * @author gaoyh
 *
 */
public class ShareResponse implements XvpResponse {
	private String signature;
	private String appid;
	private String noncestr;
	private Long timestamp;

	public String getSignature() {
		return signature;
	}

	public void setSignature(String signature) {
		this.signature = signature;
	}


	public String getAppid() {
		return appid;
	}

	public void setAppid(String appid) {
		this.appid = appid;
	}

	public String getNoncestr() {
		return noncestr;
	}

	public void setNoncestr(String noncestr) {
		this.noncestr = noncestr;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

	@Override
	public String toString() {
		return "ShareResponse [signature=" + signature + ", appid=" + appid + ", noncestr=" + noncestr + ", timestamp="
				+ timestamp + "]";
	}
	

}
