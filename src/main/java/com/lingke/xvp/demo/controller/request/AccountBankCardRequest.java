package com.lingke.xvp.demo.controller.request;

public class AccountBankCardRequest implements XvpRequest {
	/**
	 * 银行卡所属人姓名
	 */
	private String user_name;
	/**
	 * 身份证号码
	 */
	private String certificate_number;

	/**
	 * 银行卡号
	 */
	private String card_no;

	/**
	 * 银行代码
	 */
	private String bank_code;

	/**
	 * 银行名称
	 */
	private String bank_name;
	/**
	 * 银行分行代码
	 */
	private String bank_branch_code;
	/**
	 * 银行分行名称
	 */
	private String bank_branch_name;
	/**
	 * 银行所在省行政区划码
	 */
	private String bank_province_code;
	/**
	 * 银行所在市行政区划码
	 */
	private String bank_city_code;

	/**
	 * 企业名称
	 */
	private String company_name;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * sn
	 */
	private String sn;
	/**
	 * 验证码
	 */
	private String verfiy_code;

	/**
	 * 企业营业执照
	 */
	private String buslince;

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getCertificate_number() {
		return certificate_number;
	}

	public void setCertificate_number(String certificate_number) {
		this.certificate_number = certificate_number;
	}

	public String getCard_no() {
		return card_no;
	}

	public void setCard_no(String card_no) {
		this.card_no = card_no;
	}

	public String getBank_code() {
		return bank_code;
	}

	public void setBank_code(String bank_code) {
		this.bank_code = bank_code;
	}

	public String getBank_name() {
		return bank_name;
	}

	public void setBank_name(String bank_name) {
		this.bank_name = bank_name;
	}

	public String getBank_branch_code() {
		return bank_branch_code;
	}

	public void setBank_branch_code(String bank_branch_code) {
		this.bank_branch_code = bank_branch_code;
	}

	public String getBank_branch_name() {
		return bank_branch_name;
	}

	public void setBank_branch_name(String bank_branch_name) {
		this.bank_branch_name = bank_branch_name;
	}

	public String getBank_province_code() {
		return bank_province_code;
	}

	public void setBank_province_code(String bank_province_code) {
		this.bank_province_code = bank_province_code;
	}

	public String getBank_city_code() {
		return bank_city_code;
	}

	public void setBank_city_code(String bank_city_code) {
		this.bank_city_code = bank_city_code;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getSn() {
		return sn;
	}

	public void setSn(String sn) {
		this.sn = sn;
	}

	public String getVerfiy_code() {
		return verfiy_code;
	}

	public void setVerfiy_code(String verfiy_code) {
		this.verfiy_code = verfiy_code;
	}

	@Override
	public String toString() {
		return "AccountBankCardRequest [user_name=" + user_name + ", certificate_number=" + certificate_number
				+ ", card_no=" + card_no + ", bank_code=" + bank_code + ", bank_name=" + bank_name
				+ ", bank_branch_code=" + bank_branch_code + ", bank_branch_name=" + bank_branch_name
				+ ", bank_province_code=" + bank_province_code + ", bank_city_code=" + bank_city_code
				+ ", company_name=" + company_name + ", phone=" + phone + ", sn=" + sn + ", verfiy_code=" + verfiy_code
				+ ", buslince=" + buslince + "]";
	}

}
