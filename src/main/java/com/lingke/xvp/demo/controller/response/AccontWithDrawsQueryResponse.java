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
	 * 提现金额
	 */
	private Long withdraw_cash_amount;
	/**
	 * 提现手续费金额
	 */

	private Long withdraw_cash_commission;
	
	/**
	 * 创建时间
	 */
	private String create_time;
	
	/**
	 * 提现状态
	 */
	private String status;

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

	public Long getWithdraw_cash_amount() {
		return withdraw_cash_amount;
	}

	public void setWithdraw_cash_amount(Long withdraw_cash_amount) {
		this.withdraw_cash_amount = withdraw_cash_amount;
	}

	public Long getWithdraw_cash_commission() {
		return withdraw_cash_commission;
	}

	public void setWithdraw_cash_commission(Long withdraw_cash_commission) {
		this.withdraw_cash_commission = withdraw_cash_commission;
	}

	public String getCreate_time() {
		return create_time;
	}

	public void setCreate_time(String create_time) {
		this.create_time = create_time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "AccontWithDrawsQueryResponse [id=" + id + ", store_id=" + store_id + ", withdraw_cash_amount="
				+ withdraw_cash_amount + ", withdraw_cash_commission=" + withdraw_cash_commission + ", create_time="
				+ create_time + ", status=" + status + "]";
	}

}
