package com.lingke.xvp.demo.controller.request;

import java.util.List;


public class ProductUpdateRequest implements XvpRequest {
	private Long product_id;
	private String name;
	private String pics;
	private String product_detail;
	private Integer pay_type;
	private Long logistics_fee;
	private String product_desc;
	private List<SkuUpdateRequest>  sku;
	
	public Long getProduct_id() {
		return product_id;
	}

	public void setProduct_id(Long product_id) {
		this.product_id = product_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPics() {
		return pics;
	}

	public void setPics(String pics) {
		this.pics = pics;
	}

	public String getProduct_detail() {
		return product_detail;
	}

	public void setProduct_detail(String product_detail) {
		this.product_detail = product_detail;
	}

	public Integer getPay_type() {
		return pay_type;
	}

	public void setPay_type(Integer pay_type) {
		this.pay_type = pay_type;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

	public Long getLogistics_fee() {
		return logistics_fee;
	}

	public void setLogistics_fee(Long logistics_fee) {
		this.logistics_fee = logistics_fee;
	}

	public List<SkuUpdateRequest> getSku() {
		return sku;
	}

	public void setSku(List<SkuUpdateRequest> sku) {
		this.sku = sku;
	}
	
	

}
