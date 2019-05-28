package com.dollhouse.core.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.dollhouse.core.annotation.Column;
import com.dollhouse.core.annotation.Table;

@Table(name="XXXX")
public class BaseEntity implements Serializable {
	private static final long serialVersionUID = -8881700301689781811L;
	
	@Column(name="id")
	private Long id;
	
	private transient Map<String, String> expression;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Map<String, String> getExpression() {
		return expression == null ? new HashMap<String, String>() : expression;
	}

	public void addExpression(String field, String expression) {
		this.expression.put(field, expression);
	}
}