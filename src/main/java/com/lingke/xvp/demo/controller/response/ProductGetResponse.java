package com.lingke.xvp.demo.controller.response;

import java.util.List;

public class ProductGetResponse implements XvpResponse {
	private Long id;
	private String product_name;
	private String pics;
	private String product_detail;
	private Integer pay_type;
	private String product_desc;
	private List<SkuGetResponse> sku;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
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

	public List<SkuGetResponse> getSku() {
		return sku;
	}

	public void setSku(List<SkuGetResponse> sku) {
		this.sku = sku;
	}

}
