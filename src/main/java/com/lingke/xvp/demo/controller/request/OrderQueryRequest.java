package com.lingke.xvp.demo.controller.request;

/**
 * @author gaoyh
 *
 */
public class OrderQueryRequest implements XvpRequest {
	/**
	 * 订单状态
	 */
	private String order_status;
	
	private String user_id;

	/**
	 * @return the order_status
	 */
	public String getOrder_status() {
		return order_status;
	}

	/**
	 * @param order_status the order_status to set
	 */
	public void setOrder_status(String order_status) {
		this.order_status = order_status;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	} 
	
	
}
