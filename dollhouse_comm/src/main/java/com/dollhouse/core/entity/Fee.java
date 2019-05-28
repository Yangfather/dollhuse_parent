package com.dollhouse.core.entity;

import java.io.Serializable;
import java.math.BigDecimal;

public class Fee extends BaseEntity implements Serializable {
	private static final long serialVersionUID = 4601335595065659306L;

	private Long feeId;
	
	private BigDecimal amount;
	
	private String feeName;
	
	private Integer payTargetMember;//1 代表 借款人  2 投资人  3网站
	
	private Integer deductTargetMember; 

	public Long getFeeId() {
		return feeId;
	}

	public void setFeeId(Long feeId) {
		this.feeId = feeId;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getFeeName() {
		return feeName;
	}

	public void setFeeName(String feeName) {
		this.feeName = feeName;
	}

	public Integer getPayTargetMember() {
		return payTargetMember;
	}

	public void setPayTargetMember(Integer payTargetMember) {
		this.payTargetMember = payTargetMember;
	}

	public Integer getDeductTargetMember() {
		return deductTargetMember;
	}

	public void setDeductTargetMember(Integer deductTargetMember) {
		this.deductTargetMember = deductTargetMember;
	}
}