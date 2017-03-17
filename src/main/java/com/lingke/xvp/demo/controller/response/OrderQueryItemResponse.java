package com.lingke.xvp.demo.controller.response;

/**
 * @author gaoyh
 *
 */
public class OrderQueryItemResponse implements XvpResponse {
	/**
	 * id 主键
	 */
	public String id;

	/**
	 * 商品id
	 */
	public String product_id;

	/**
	 * 商品Sku id
	 */
	public String sku_id;

	/**
	 * 商品名称
	 */
	public String goodsName;

	/**
	 * 价格
	 */
	public String price;

	/**
	 * sku串 比如: 175 纯棉,180 非纯棉
	 */
	public String sku;

	/**
	 * 购买数量
	 */
	public String num;
	/**
	 * 物流id
	 */
	public String delivery_id;
	/**
	 * 创建时间
	 */
	public String create_time;
	/**
	 * 缩略图
	 */
	private String goodsUrl;
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
	 * @return the product_id
	 */
	public String getProduct_id() {
		return product_id;
	}
	/**
	 * @param product_id the product_id to set
	 */
	public void setProduct_id(String product_id) {
		this.product_id = product_id;
	}
	/**
	 * @return the sku_id
	 */
	public String getSku_id() {
		return sku_id;
	}
	/**
	 * @param sku_id the sku_id to set
	 */
	public void setSku_id(String sku_id) {
		this.sku_id = sku_id;
	}
	/**
	 * @return the goodsName
	 */
	public String getGoodsName() {
		return goodsName;
	}
	/**
	 * @param goodsName the goodsName to set
	 */
	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
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
	 * @return the sku
	 */
	public String getSku() {
		return sku;
	}
	/**
	 * @param sku the sku to set
	 */
	public void setSku(String sku) {
		this.sku = sku;
	}
	/**
	 * @return the num
	 */
	public String getNum() {
		return num;
	}
	/**
	 * @param num the num to set
	 */
	public void setNum(String num) {
		this.num = num;
	}
	/**
	 * @return the delivery_id
	 */
	public String getDelivery_id() {
		return delivery_id;
	}
	/**
	 * @param delivery_id the delivery_id to set
	 */
	public void setDelivery_id(String delivery_id) {
		this.delivery_id = delivery_id;
	}
	/**
	 * @return the create_time
	 */
	public String getCreate_time() {
		return create_time;
	}
	/**
	 * @param create_time the create_time to set
	 */
	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}
	/**
	 * @return the goodsUrl
	 */
	public String getGoodsUrl() {
		return goodsUrl;
	}
	/**
	 * @param goodsUrl the goodsUrl to set
	 */
	public void setGoodsUrl(String goodsUrl) {
		this.goodsUrl = goodsUrl;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderQueryItemResponse [id=" + id + ", product_id=" + product_id + ", sku_id=" + sku_id + ", goodsName="
				+ goodsName + ", price=" + price + ", sku=" + sku + ", num=" + num + ", delivery_id=" + delivery_id
				+ ", create_time=" + create_time + ", goodsUrl=" + goodsUrl + "]";
	}
	
}
