package com.lingke.xvp.demo.controller.response;


public class ProductSkuGetResponse implements XvpResponse {
	/**
	 * id
	 */
	private String id;
	/**
	 * 价格
	 */
	private String price;
	/**
	 * 库存
	 */
	private String stock;
	/**
	 * 库存str
	 */
	private String sku_str;
	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}
	/**
	 * @return the price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return the stock
	 */
	public String getStock() {
		return stock;
	}
	/**
	 * @param stock the stock to set
	 */
	public void setStock(String stock) {
		this.stock = stock;
	}
	/**
	 * @return the sku_str
	 */
	public String getSku_str() {
		return sku_str;
	}
	/**
	 * @param sku_str the sku_str to set
	 */
	public void setSku_str(String sku_str) {
		this.sku_str = sku_str;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "ProductSkuGetResponse [id=" + id + ", price=" + price + ", stock=" + stock + ", sku_str=" + sku_str
				+ "]";
	}

}
