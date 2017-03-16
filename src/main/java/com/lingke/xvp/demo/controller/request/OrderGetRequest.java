package com.lingke.xvp.demo.controller.request;

/**
 * @author gaoyh
 *
 */
public class OrderGetRequest implements XvpRequest {
	/**
	 * 订单id
	 */
	private String order_id;

	private String user_id;
	/**
	 * @return the order_id
	 */
	public String getOrder_id() {
		return order_id;
	}

	/**
	 * @param order_id the order_id to set
	 */
	public void setOrder_id(String order_id) {
		this.order_id = order_id;
	}

	public String getUser_id() {
		return user_id;
	}

	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderGetRequest [order_id=" + order_id + "]";
	}
	
	


}
