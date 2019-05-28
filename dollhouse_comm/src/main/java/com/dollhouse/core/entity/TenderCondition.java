package com.dollhouse.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 投资待收计算条件
 */
public class TenderCondition extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 8805193980833236596L;

	private BigDecimal apr;
	
	private BigDecimal amount;
	
	private Integer period;
	
	private Long currentTime;

	private Integer repayType;

	public BigDecimal getApr() {
		return apr;
	}

	public void setApr(BigDecimal apr) {
		this.apr = apr;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public Integer getPeriod() {
		return period;
	}

	public void setPeriod(Integer period) {
		this.period = period;
	}

	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}

	public Integer getRepayType() {
		return repayType;
	}

	public void setRepayType(Integer repayType) {
		this.repayType = repayType;
	}
}