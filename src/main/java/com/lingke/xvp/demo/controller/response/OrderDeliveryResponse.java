package com.lingke.xvp.demo.controller.response;

/**
 * @author gaoyh
 *
 */
public class OrderDeliveryResponse implements XvpResponse {
	/**
	 * 是否成功
	 */
	private String is_success;

	/**
	 * @return the is_success
	 */
	public String getIs_success() {
		return is_success;
	}

	/**
	 * @param is_success the is_success to set
	 */
	public void setIs_success(String is_success) {
		this.is_success = is_success;
	}
}
