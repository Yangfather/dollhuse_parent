package com.dollhouse.api.entity;

import java.math.BigDecimal;

public class CulturalArtifactBizDto {

	private String project_record_no;
	private String project_order_no;
	private BigDecimal qty;
	private BigDecimal amount;
	private String trade_time;
	private String seller_name;
	private Integer seller_certificate;
	private String seller_identification;
	private String seller_phone;
	private String buyer_name;
	private Integer buyer_certificate;
	private String buyer_identification;
	private String buyer_phone;


	public String getProject_record_no() {
		return project_record_no;
	}

	public void setProject_record_no(String project_record_no) {
		this.project_record_no = project_record_no;
	}

	public String getProject_order_no() {
		return project_order_no;
	}

	public void setProject_order_no(String project_order_no) {
		this.project_order_no = project_order_no;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getTrade_time() {
		return trade_time;
	}

	public void setTrade_time(String trade_time) {
		this.trade_time = trade_time;
	}

	public String getSeller_name() {
		return seller_name;
	}

	public void setSeller_name(String seller_name) {
		this.seller_name = seller_name;
	}

	public Integer getSeller_certificate() {
		return seller_certificate;
	}

	public void setSeller_certificate(Integer seller_certificate) {
		this.seller_certificate = seller_certificate;
	}

	public String getSeller_identification() {
		return seller_identification;
	}

	public void setSeller_identification(String seller_identification) {
		this.seller_identification = seller_identification;
	}

	public String getSeller_phone() {
		return seller_phone;
	}

	public void setSeller_phone(String seller_phone) {
		this.seller_phone = seller_phone;
	}

	public String getBuyer_name() {
		return buyer_name;
	}

	public void setBuyer_name(String buyer_name) {
		this.buyer_name = buyer_name;
	}

	public Integer getBuyer_certificate() {
		return buyer_certificate;
	}

	public void setBuyer_certificate(Integer buyer_certificate) {
		this.buyer_certificate = buyer_certificate;
	}

	public String getBuyer_identification() {
		return buyer_identification;
	}

	public void setBuyer_identification(String buyer_identification) {
		this.buyer_identification = buyer_identification;
	}

	public String getBuyer_phone() {
		return buyer_phone;
	}

	public void setBuyer_phone(String buyer_phone) {
		this.buyer_phone = buyer_phone;
	}

}