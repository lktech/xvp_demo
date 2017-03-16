package com.lingke.xvp.demo.controller.response;


public class ProductResponse implements XvpResponse {
	private String id;
	private String name;
	private String pics;
	private String product_detail;
	private String pay_type;
	private String logistics_fee;
	private String product_desc;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getPay_type() {
		return pay_type;
	}
	public void setPay_type(String pay_type) {
		this.pay_type = pay_type;
	}
	public String getLogistics_fee() {
		return logistics_fee;
	}
	public void setLogistics_fee(String logistics_fee) {
		this.logistics_fee = logistics_fee;
	}
	public String getProduct_desc() {
		return product_desc;
	}
	public void setProduct_desc(String product_desc) {
		this.product_desc = product_desc;
	}

}
