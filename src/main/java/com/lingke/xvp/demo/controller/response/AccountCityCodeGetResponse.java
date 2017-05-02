package com.lingke.xvp.demo.controller.response;

/**
 * @author gaoyh
 *
 */
public class AccountCityCodeGetResponse implements XvpResponse {
	/**
	 * 代码
	 */
	private String code;
	/**
	 * 名称
	 */
	private String name;
	/**
	 * @return the code
	 */
	public String getCode() {
		return code;
	}
	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "CommonResponse [code=" + code + ", name=" + name + "]";
	}
	

}
