package com.lingke.xvp.demo.controller.request;

import java.util.List;

/**
 * @author gaoyh
 *
 */
public class OrderDeliveryRequest implements XvpRequest {
	/**
	 * order id
	 */	
	public String order_id;
	

	/**
	 * 快递公司编码
	 */
	public String logistics_company_code;

	/**
	 * 快递公司名称
	 */
	public String logistics_company_name;

	/**
	 * 运单号
	 */
	public String logistics_num;
	
	/**
	 * 是否需要物流, 0 不要物流 1 需要物流
	 */
	public Integer need_logistics_flg;

	/**
	 * 包裹包含的订单商品的id列表
	 */
	public List<String> order_item_id_list;

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
	 * @return the logistics_company_code
	 */
	public String getLogistics_company_code() {
		return logistics_company_code;
	}

	/**
	 * @param logistics_company_code the logistics_company_code to set
	 */
	public void setLogistics_company_code(String logistics_company_code) {
		this.logistics_company_code = logistics_company_code;
	}

	/**
	 * @return the logistics_company_name
	 */
	public String getLogistics_company_name() {
		return logistics_company_name;
	}

	/**
	 * @param logistics_company_name the logistics_company_name to set
	 */
	public void setLogistics_company_name(String logistics_company_name) {
		this.logistics_company_name = logistics_company_name;
	}

	/**
	 * @return the logistics_num
	 */
	public String getLogistics_num() {
		return logistics_num;
	}

	/**
	 * @param logistics_num the logistics_num to set
	 */
	public void setLogistics_num(String logistics_num) {
		this.logistics_num = logistics_num;
	}

	/**
	 * @return the order_item_id_list
	 */
	public List<String> getOrder_item_id_list() {
		return order_item_id_list;
	}

	/**
	 * @param order_item_id_list the order_item_id_list to set
	 */
	public void setOrder_item_id_list(List<String> order_item_id_list) {
		this.order_item_id_list = order_item_id_list;
	}

	/**
	 * @return the need_logistics_flg
	 */
	public Integer getNeed_logistics_flg() {
		return need_logistics_flg;
	}

	/**
	 * @param need_logistics_flg the need_logistics_flg to set
	 */
	public void setNeed_logistics_flg(Integer need_logistics_flg) {
		this.need_logistics_flg = need_logistics_flg;
	}
	

}
