package com.dollhouse.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 费用计算条件
 */
public class FeeCondition extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 7976018001968101208L;

	private BigDecimal rankFee;
	
	private Integer period;
	
	private BigDecimal amount;
	
	private BigDecimal interest;
	
	private BigDecimal principal;
	
	private Integer vipStatus;
	
	private Integer delayDays;
	
	private BigDecimal vipFeeScale;

	public BigDecimal getRankFee() {
		return rankFee;
	}

	public void setRankFee(BigDecimal rankFee) {
		this.rankFee = rankFee;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getPrincipal() {
		return principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public Integer getVipStatus() {
		return vipStatus;
	}

	public void setVipStatus(Integer vipStatus) {
		this.vipStatus = vipStatus;
	}

	public Integer getDelayDays() {
		return delayDays == null ? 1 : delayDays;
	}

	public void setDelayDays(Integer delayDays) {
		this.delayDays = delayDays;
	}

	public BigDecimal getVipFeeScale() {
		return vipFeeScale == null ? new BigDecimal(100) : vipFeeScale;
	}

	public void setVipFeeScale(BigDecimal vipFeeScale) {
		this.vipFeeScale = vipFeeScale;
	}
}