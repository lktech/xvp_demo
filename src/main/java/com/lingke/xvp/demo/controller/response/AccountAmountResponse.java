package com.lingke.xvp.demo.controller.response;

public class AccountAmountResponse implements XvpResponse {
	/**
	 * 可提现金额(分)
	 */
	private Long withdrawals_amount;

	/**
	 * 今日收入金额(分)
	 */
	public Long today_income_amount;

	/**
	 * 累计收入金额(分)
	 */
	public Long income_amount;

	public Long getWithdrawals_amount() {
		return withdrawals_amount;
	}

	public void setWithdrawals_amount(Long withdrawals_amount) {
		this.withdrawals_amount = withdrawals_amount;
	}

	public Long getToday_income_amount() {
		return today_income_amount;
	}

	public void setToday_income_amount(Long today_income_amount) {
		this.today_income_amount = today_income_amount;
	}

	public Long getIncome_amount() {
		return income_amount;
	}

	public void setIncome_amount(Long income_amount) {
		this.income_amount = income_amount;
	}

	@Override
	public String toString() {
		return "AccountAmountResponse [withdrawals_amount=" + withdrawals_amount + ", today_income_amount="
				+ today_income_amount + ", income_amount=" + income_amount + "]";
	}
	
	

}
