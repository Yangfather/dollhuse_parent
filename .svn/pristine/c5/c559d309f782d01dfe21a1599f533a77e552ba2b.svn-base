package com.dollhouse.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 投资待收计算结果明细 
 */
public class TenderDetail extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 7315509892243529447L;

	private BigDecimal amount;
	
	private BigDecimal principal;
	
	private BigDecimal interest;
	
	private BigDecimal repayMonth;
	
	private Long repayTime;

	public BigDecimal getAmount() {
		return amount == null ? BigDecimal.ZERO : amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getPrincipal() {
		return principal == null ? BigDecimal.ZERO : principal;
	}

	public void setPrincipal(BigDecimal principal) {
		this.principal = principal;
	}

	public BigDecimal getInterest() {
		return interest == null ? BigDecimal.ZERO : interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getRepayMonth() {
		return repayMonth;
	}

	public void setRepayMonth(BigDecimal repayMonth) {
		this.repayMonth = repayMonth;
	}

	public Long getRepayTime() {
		return repayTime;
	}

	public void setRepayTime(Long repayTime) {
		this.repayTime = repayTime;
	}
}