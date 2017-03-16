package com.lingke.xvp.demo.controller.request;

public class SellerRegisterRequest implements XvpRequest {
	private String phone;
	private String password;
	private String sn;
	private String verfiy_code;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	/**
	 * @return the verfiy_code
	 */
	public String getVerfiy_code() {
		return verfiy_code;
	}

	/**
	 * @param verfiy_code the verfiy_code to set
	 */
	public void setVerfiy_code(String verfiy_code) {
		this.verfiy_code = verfiy_code;
	}

	@Override
	public String toString() {
		return "SellerRegisterRequest [phone=" + phone + ", password=" + password + ", sn=" + sn + ", verfiy_code=" + verfiy_code
				+ "]";
	}
	
	
}
