package com.dollhouse.api.entity;

/**
 * 资产负债表
 * 
 * @author zbs
 *
 */
public class BalanceReportDto extends BaseReport {

	private AccountItem asset_item; // 资产类明细

	private AccountItem liability_item; // 负债类明细

	public AccountItem getAsset_item() {
		return asset_item;
	}

	public void setAsset_item(AccountItem asset_item) {
		this.asset_item = asset_item;
	}

	public AccountItem getLiability_item() {
		return liability_item;
	}

	public void setLiability_item(AccountItem liability_item) {
		this.liability_item = liability_item;
	}

}
