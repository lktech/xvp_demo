package com.lingke.xvp.demo.controller.response;

import java.util.Date;

/**
 * @author gaoyh
 *
 */
public class AccontWithDrawsQueryResponse implements XvpResponse {
	/**
	 * 主键 id
	 */
	private Long id;
	/**
	 * 店铺 id
	 */
	private Long store_id;
	/**
	 * 提现银行卡号
	 */
	private String card_no;

	/**
	 * 提现银行卡银行代码
	 */
	private String bank_code;

	/**
	 * 提现银行卡银行名称
	 */
	private String bank_name;

	/**
	 * 提现金额
	 */
	private Long withdraw_amount;
	/**
	 * 提现手续费金额
	 */

	private Long withdraw_commission;

	/**
	 * 创建时间
	 */
	private Date create_time;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getStore_id() {
		return store_id;
	}

	public void setStore_id(Long store_id) {
		this.store_id = store_id;
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

	public Long getWithdraw_amount() {
		return withdraw_amount;
	}

	public void setWithdraw_amount(Long withdraw_amount) {
		this.withdraw_amount = withdraw_amount;
	}

	public Long getWithdraw_commission() {
		return withdraw_commission;
	}

	public void setWithdraw_commission(Long withdraw_commission) {
		this.withdraw_commission = withdraw_commission;
	}

	public Date getCreate_time() {
		return create_time;
	}

	public void setCreate_time(Date create_time) {
		this.create_time = create_time;
	}

	@Override
	public String toString() {
		return "AccontWithDrawsQueryResponse [id=" + id + ", store_id=" + store_id + ", card_no=" + card_no
				+ ", bank_code=" + bank_code + ", bank_name=" + bank_name + ", withdraw_amount=" + withdraw_amount
				+ ", withdraw_commission=" + withdraw_commission + ", create_time=" + create_time + "]";
	}
	
	
}
