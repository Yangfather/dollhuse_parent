package com.dollhouse.api.entity;

import java.math.BigDecimal;

public class CulturalArtifactRecoverDto {

	private String project_record_no;
	private String project_order_no;
	private BigDecimal pickup_qty;
	private BigDecimal pickup_amount;
	private String pickup_time;
	private String pickup_name;
	private Integer pickup_certificate;
	private String pickup_identification;
	private String pickup_phone;
	private BigDecimal left_inventory;


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

	public BigDecimal getPickup_qty() {
		return pickup_qty;
	}

	public void setPickup_qty(BigDecimal pickup_qty) {
		this.pickup_qty = pickup_qty;
	}

	public BigDecimal getPickup_amount() {
		return pickup_amount;
	}

	public void setPickup_amount(BigDecimal pickup_amount) {
		this.pickup_amount = pickup_amount;
	}

	public String getPickup_time() {
		return pickup_time;
	}

	public void setPickup_time(String pickup_time) {
		this.pickup_time = pickup_time;
	}

	public String getPickup_name() {
		return pickup_name;
	}

	public void setPickup_name(String pickup_name) {
		this.pickup_name = pickup_name;
	}

	public Integer getPickup_certificate() {
		return pickup_certificate;
	}

	public void setPickup_certificate(Integer pickup_certificate) {
		this.pickup_certificate = pickup_certificate;
	}

	public String getPickup_identification() {
		return pickup_identification;
	}

	public void setPickup_identification(String pickup_identification) {
		this.pickup_identification = pickup_identification;
	}

	public String getPickup_phone() {
		return pickup_phone;
	}

	public void setPickup_phone(String pickup_phone) {
		this.pickup_phone = pickup_phone;
	}

	public BigDecimal getLeft_inventory() {
		return left_inventory;
	}

	public void setLeft_inventory(BigDecimal left_inventory) {
		this.left_inventory = left_inventory;
	}

}