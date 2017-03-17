package com.lingke.xvp.demo.controller.request;

public class ProductSkuGetRequest implements XvpRequest {
	private Long product_id;

	/**
	 * @return the product_id
	 */
	public Long getProduct_id() {
		return product_id;
	}

	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}
	
}
