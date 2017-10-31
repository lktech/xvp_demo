package com.lingke.xvp.demo.controller.qiniu;

import com.lingke.xvp.demo.controller.response.XvpResponse;

public class QiniuResponse implements XvpResponse {
	private String url;

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "QiniuResponse [url=" + url + "]";
	}

}
