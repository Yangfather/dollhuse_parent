package com.dollhouse.api.entity;

import java.math.BigDecimal;
import java.util.List;

/**
 * 财报表明细项
 * 
 * @author zbs
 *
 */
public class AccountItem {

	private String key; // 项目英文标识

	private String name; // 项目中文名

	private BigDecimal value; // 当期值

	private BigDecimal last_value; // 上期值

	private List<AccountItem> sub_items;

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getValue() {
		return value;
	}

	public void setValue(BigDecimal value) {
		this.value = value;
	}

	public BigDecimal getLast_value() {
		return last_value;
	}

	public void setLast_value(BigDecimal last_value) {
		this.last_value = last_value;
	}

	public List<AccountItem> getSub_items() {
		return sub_items;
	}

	public void setSub_items(List<AccountItem> sub_items) {
		this.sub_items = sub_items;
	}

}
