package com.lingke.xvp.demo.controller.request;

/**
 * @author gaoyh
 *
 */
public class OrderDiscountRequest implements XvpRequest {
	/**
	 * 订单id
	 */
	private String order_id;
	/**
	 * 优惠金额
	 */
	private Long discount_amount;
	
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
	/**
	 * @return the discount_amount
	 */
	public Long getDiscount_amount() {
		return discount_amount;
	}
	/**
	 * @param discount_amount the discount_amount to set
	 */
	public void setDiscount_amount(Long discount_amount) {
		this.discount_amount = discount_amount;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderDiscountRequest [order_id=" + order_id + ", discount_amount=" + discount_amount + "]";
	}
	
}
