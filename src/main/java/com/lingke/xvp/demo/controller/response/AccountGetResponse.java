package com.lingke.xvp.demo.controller.response;

public class AccountGetResponse implements XvpResponse {
	/**
	 * 账户类型 0:个人账户,1: 企业账户
	 */
	private String account_type; 
	/**
	 * 企业名称
	 */
	private String company_name;
	/**
	 * 营业执照
	 */
	private String buslince;
	
	/**
	 * 营业执照图片
	 */
	private String buslince_pic;

	/**
	 * 姓名
	 */
	private String user_name; 
	/**
	 * 证件类型, 0身份证;1护照;2军官证;3士兵证;4回乡证;5户口本;6外国护照;7其它
	 */
	private String certificate_type; 
	/**
	 * 证件号
	 */
	private String certificate_number;
	
	/**
	 * 创建时间
	 */
	private String create_time;

	public String getAccount_type() {
		return account_type;
	}

	public void setAccount_type(String account_type) {
		this.account_type = account_type;
	}

	public String getCompany_name() {
		return company_name;
	}

	public void setCompany_name(String company_name) {
		this.company_name = company_name;
	}

	public String getBuslince() {
		return buslince;
	}

	public void setBuslince(String buslince) {
		this.buslince = buslince;
	}

	public String getBuslince_pic() {
		return buslince_pic;
	}

	public void setBuslince_pic(String buslince_pic) {
		this.buslince_pic = buslince_pic;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCertificate_type() {
		return certificate_type;
	}

	public void setCertificate_type(String certificate_type) {
		this.certificate_type = certificate_type;
	}

	public String getCertificate_number() {
		return certificate_number;
	}

	public void setCertificate_number(String certificate_number) {
		this.certificate_number = certificate_number;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "AccountGetResponse [account_type=" + account_type + ", company_name=" + company_name + ", buslince="
				+ buslince + ", buslince_pic=" + buslince_pic + ", user_name=" + user_name + ", certificate_type="
				+ certificate_type + ", certificate_number=" + certificate_number + ", create_time=" + create_time
				+ "]";
	}
	
}
