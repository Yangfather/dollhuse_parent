package com.dollhouse.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 投资待收计算结果 
 */
public class Tender extends BaseEntity implements Serializable {
	private static final long serialVersionUID = -2266542156249797713L;

	private BigDecimal amountAll;
	
	private BigDecimal principalAll;
	
	private BigDecimal interestAll;
	
	private List<TenderDetail> repayDetailList;

	public BigDecimal getAmountAll() {
		return amountAll;
	}

	public void setAmountAll(BigDecimal amountAll) {
		this.amountAll = amountAll;
	}

	public BigDecimal getPrincipalAll() {
		return principalAll;
	}

	public void setPrincipalAll(BigDecimal principalAll) {
		this.principalAll = principalAll;
	}

	public BigDecimal getInterestAll() {
		return interestAll;
	}

	public void setInterestAll(BigDecimal interestAll) {
		this.interestAll = interestAll;
	}

	public List<TenderDetail> getRepayDetailList() {
		return repayDetailList == null ? new ArrayList<TenderDetail>() : repayDetailList;
	}

	public void setRepayDetailList(List<TenderDetail> repayDetailList) {
		this.repayDetailList = repayDetailList;
	}
}
