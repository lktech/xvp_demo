package com.lingke.xvp.demo.controller.request;

import java.util.List;

public class ProductCreateRequest implements XvpRequest {
	private String name;
	private String pics;
	private String product_detail;
	private Integer pay_type;
	private String product_desc;
	private Long logistics_fee;
	private Long virtual_flg;
	private List<SkuAddRequest> sku;
	
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

	public Long getLogistics_fee() {
		return logistics_fee;
	}

	public void setLogistics_fee(Long logistics_fee) {
		this.logistics_fee = logistics_fee;
	}

	public String getProduct_desc() {
		return product_desc;
	}

	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

	public List<SkuAddRequest> getSku() {
		return sku;
	}

	public void setSku(List<SkuAddRequest> sku) {
		this.sku = sku;
	}

	public Long getVirtual_flg() {
		return virtual_flg;
	}

	public void setVirtual_flg(Long virtual_flg) {
		this.virtual_flg = virtual_flg;
	}

}
