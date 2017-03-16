package com.lingke.xvp.demo.controller.response;

import java.util.Date;

/**
 * @author gaoyh
 *
 */
public class OrderQueryItemResponse implements XvpResponse {
	/**
	 * id 主键
	 */
	public Long id;

	/**
	 * 商品id
	 */
	public Long product_id;

	/**
	 * 商品Sku id
	 */
	public Long sku_id;

	/**
	 * 商品名称
	 */
	public String product_name;

	/**
	 * 价格
	 */
	public Long price;

	/**
	 * sku串 比如: 175 纯棉,180 非纯棉
	 */
	public String sku_str;

	/**
	 * 购买数量
	 */
	public Long item_count;
	/**
	 * 物流id
	 */
	public Long delivery_id;
	/**
	 * 创建时间
	 */
	public Date create_time;
	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}
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
	/**
	 * @return the sku_id
	 */
	public Long getSku_id() {
		return sku_id;
	}
	/**
	 * @param sku_id the sku_id to set
	 */
	public void setSku_id(Long sku_id) {
		this.sku_id = sku_id;
	}
	/**
	 * @return the product_name
	 */
	public String getProduct_name() {
		return product_name;
	}
	/**
	 * @param product_name the product_name to set
	 */
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	/**
	 * @return the price
	 */
	public Long getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(Long price) {
		this.price = price;
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
	/**
	 * @return the item_count
	 */
	public Long getItem_count() {
		return item_count;
	}
	/**
	 * @param item_count the item_count to set
	 */
	public void setItem_count(Long item_count) {
		this.item_count = item_count;
	}
	/**
	 * @return the delivery_id
	 */
	public Long getDelivery_id() {
		return delivery_id;
	}
	/**
	 * @param delivery_id the delivery_id to set
	 */
	public void setDelivery_id(Long delivery_id) {
		this.delivery_id = delivery_id;
	}
	/**
	 * @return the create_time
	 */
	public Date getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderItemResponse [id=" + id + ", product_id=" + product_id + ", sku_id=" + sku_id + ", product_name="
				+ product_name + ", price=" + price + ", sku_str=" + sku_str + ", item_count=" + item_count
				+ ", delivery_id=" + delivery_id + ", create_time=" + create_time + "]";
	}
	
}
