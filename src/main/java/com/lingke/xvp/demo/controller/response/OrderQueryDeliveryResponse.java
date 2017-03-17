package com.lingke.xvp.demo.controller.response;

/**
 * @author gaoyh
 *
 */
public class OrderQueryDeliveryResponse implements XvpResponse {
	/**
	 * id 主键
	 */
	public String id;
	
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
	public String need_logistics_flg;

	/**
	 * 创建时间
	 */
	public String create_time;

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
	 * @return the need_logistics_flg
	 */
	public String getNeed_logistics_flg() {
		return need_logistics_flg;
	}

	/**
	 * @param need_logistics_flg the need_logistics_flg to set
	 */
	public void setNeed_logistics_flg(String need_logistics_flg) {
		this.need_logistics_flg = need_logistics_flg;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "OrderQueryDeliveryResponse [id=" + id + ", logistics_company_code=" + logistics_company_code
				+ ", logistics_company_name=" + logistics_company_name + ", logistics_num=" + logistics_num
				+ ", need_logistics_flg=" + need_logistics_flg + ", create_time=" + create_time + "]";
	}

	
}
