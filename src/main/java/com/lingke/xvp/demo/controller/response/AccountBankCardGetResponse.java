package com.lingke.xvp.demo.controller.response;

public class AccountBankCardGetResponse implements XvpResponse {
	/**
	 * id
	 */
	private Long id;

	/**
	 * 店铺 id
	 */
	private String store_id;
	/**
	 * 银行卡所属人姓名
	 */
	private String user_name;

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
	 * WRZ:认证中 RZCG:认证成功, RZSB: 认证失败
	 */
	private String card_auth_status;

	/**
	 * 企业名称
	 */
	private String company_name;

	/**
	 * 企业营业执照
	 */
	private String buslince;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getStore_id() {
		return store_id;
	}

	public void setStore_id(String store_id) {
		this.store_id = store_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
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

	public String getCard_auth_status() {
		return card_auth_status;
	}

	public void setCard_auth_status(String card_auth_status) {
		this.card_auth_status = card_auth_status;
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

	@Override
	public String toString() {
		return "AccountBankCardGetResponse [id=" + id + ", store_id=" + store_id + ", user_name=" + user_name
				+ ", card_no=" + card_no + ", bank_code=" + bank_code + ", bank_name=" + bank_name
				+ ", bank_branch_code=" + bank_branch_code + ", bank_branch_name=" + bank_branch_name
				+ ", bank_province_code=" + bank_province_code + ", bank_city_code=" + bank_city_code
				+ ", card_auth_status=" + card_auth_status + ", company_name=" + company_name + ", buslince=" + buslince
				+ "]";
	}

}
