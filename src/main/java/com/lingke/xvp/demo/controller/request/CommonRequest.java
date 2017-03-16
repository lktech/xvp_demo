package com.lingke.xvp.demo.controller.request;

/**
 * @author gaoyh
 *
 */
public class CommonRequest implements XvpRequest {
	/**
	 * 省编号
	 */
	private String province_code;
	/**
	 * 市编号
	 */
	private String city_code;
	/**
	 * @return the province_code
	 */
	public String getProvince_code() {
		return province_code;
	}
	/**
	 * @param province_code the province_code to set
	 */
	public void setProvince_code(String province_code) {
		this.province_code = province_code;
	}
	/**
	 * @return the city_code
	 */
	public String getCity_code() {
		return city_code;
	}
	/**
	 * @param city_code the city_code to set
	 */
	public void setCity_code(String city_code) {
		this.city_code = city_code;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommonRequest [province_code=" + province_code + ", city_code=" + city_code + "]";
	}
	
}
