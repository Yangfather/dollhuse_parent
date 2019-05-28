package com.dollhouse.api.entity;

/**
 * 现金流量表
 * 
 * @author zbs
 *
 */
public class CashFlowReportDto extends BaseReport {

	private AccountItem business_item; // 经营活动产生的现金流量
	private AccountItem invest_item; // 投资活动产生的现金流量
	private AccountItem financing_item; // 筹资活动产生的现金流量
	private AccountItem exchange_rate_item; // 汇率变动对现金及现金等价物的影响
	private AccountItem cash_increase_item; // 现金及现金等价物净增加额
	private AccountItem ending_cash_item; // 期末现金及现金等价物余额

	public AccountItem getBusiness_item() {
		return business_item;
	}

	public void setBusiness_item(AccountItem business_item) {
		this.business_item = business_item;
	}

	public AccountItem getInvest_item() {
		return invest_item;
	}

	public void setInvest_item(AccountItem invest_item) {
		this.invest_item = invest_item;
	}

	public AccountItem getFinancing_item() {
		return financing_item;
	}

	public void setFinancing_item(AccountItem financing_item) {
		this.financing_item = financing_item;
	}

	public AccountItem getExchange_rate_item() {
		return exchange_rate_item;
	}

	public void setExchange_rate_item(AccountItem exchange_rate_item) {
		this.exchange_rate_item = exchange_rate_item;
	}

	public AccountItem getCash_increase_item() {
		return cash_increase_item;
	}

	public void setCash_increase_item(AccountItem cash_increase_item) {
		this.cash_increase_item = cash_increase_item;
	}

	public AccountItem getEnding_cash_item() {
		return ending_cash_item;
	}

	public void setEnding_cash_item(AccountItem ending_cash_item) {
		this.ending_cash_item = ending_cash_item;
	}

}
