package com.dollhouse.core.dao.query;

import java.util.List;

import com.dollhouse.core.entity.NameValue;

public class Where extends NameValue {
	private static final long serialVersionUID = 3445010794026801045L;
	
	public static final String EQ = " = ";
	
	public static final String NEQ = " != ";
	
	public static final String GT = " > ";
	
	public static final String GE = " >= ";
	
	public static final String LT = " < ";
	
	public static final String LE = " <= ";
	
	public static final String IN = " in ";
	
	public static final String NOT_IN = " not in ";
	
	public static final String LIKE = " like ";
	
	public static final String BETWEEN = "between";
	
	public static final String IS_NULL = " is null";
	
	public static final String IS_NOT_NULL = " is not null";

	public Where(String name, Object value) {
		super(name, value);
	}
	
	public Where(String name, Object value, String condition) {
		super(name, value);
		this.condition = condition;
	}
	
	private boolean isOr = false;
	
	private String condition = EQ;
	
	private List<Where> whereList;
	
	public boolean isOr() {
		return isOr;
	}

	public String getCondition() {
		return condition;
	}

	public List<Where> getWhereList() {
		return whereList;
	}
	
	public static Where eq(String name, Object value) {
		return eq(name, value, false);
	}
	
	public static Where eq(String name, Object value, boolean isOr) {
		Where where = new Where(name, value);
		where.isOr = isOr;
		
		return where;
	}
	
	public static Where notEq(String name, Object value) {
		return notEq(name, value, false);
	}
	
	public static Where notEq(String name, Object value, boolean isOr) {
		Where where = new Where(name, value);
		where.isOr = isOr;
		where.condition = NEQ;
		
		return where;
	}
	
	public static Where gt(String name, Object value) {
		return gt(name, value, false);
	}
	
	public static Where gt(String name, Object value, boolean isOr) {
		Where where = new Where(name, value);
		where.isOr = isOr;
		where.condition = GT;
		
		return where;
	}
	
	public static Where ge(String name, Object value) {
		return ge(name, value, false);
	}
	
	public static Where ge(String name, Object value, boolean isOr) {
		Where where = new Where(name, value);
		where.isOr = isOr;
		where.condition = GE;
		
		return where;
	}
	
	public static Where lt(String name, Object value) {
		return lt(name, value, false);
	}
	
	public static Where lt(String name, Object value, boolean isOr) {
		Where where = new Where(name, value);
		where.isOr = isOr;
		where.condition = LT;
		
		return where;
	}
	
	public static Where le(String name, Object value) {
		return le(name, value, false);
	}
	
	public static Where le(String name, Object value, boolean isOr) {
		Where where = new Where(name, value);
		where.isOr = isOr;
		where.condition = LE;
		
		return where;
	}
	
	public static Where in(String name, Object[] values) {
		return in(name, values, false);
	}
	
	public static Where in(String name, Object[] values, boolean isOr) {
		Where where = new Where(name, null);
		where.isOr = isOr;
		where.condition = IN;
		where.setValues(values);
		
		return where;
	}
	
	public static Where notIn(String name, Object[] values) {
		return notIn(name, values, false);
	}
	
	public static Where notIn(String name, Object[] values, boolean isOr) {
		Where where = new Where(name, null);
		where.isOr = isOr;
		where.condition = NOT_IN;
		where.setValues(values);
		
		return where;
	}
	
	public static Where like(String name, String value) {
		return like(name, value, false);
	}
	
	public static Where like(String name, String value, boolean isOr) {
		Where where = new Where(name, value == null ? "" : value + "%");
		where.isOr = isOr;
		where.condition = LIKE;
		
		return where;
	}
	
	public static Where likeAll(String name, String value) {
		return like(name, "%" + value, false);
	}
	
	public static Where likeAll(String name, String value, boolean isOr) {
		Where where = new Where(name, value == null ? "" : "%" + value + "%");
		where.isOr = isOr;
		where.condition = LIKE;
		
		return where;
	}
	
	public static Where isNull(String name) {
		return isNull(name, false);
	}
	
	public static Where isNull(String name, boolean isOr) {
		Where where = new Where(name, null);
		where.isOr = isOr;
		where.condition = IS_NULL;
		
		return where;
	}
	
	public static Where isNotNull(String name) {
		return isNotNull(name, false);
	}
	
	public static Where isNotNull(String name, boolean isOr) {
		Where where = new Where(name, null);
		where.isOr = isOr;
		where.condition = IS_NOT_NULL;
		
		return where;
	}
	
	public static Where between(String name, Object startValue, Object endValue) {
		return between(name, startValue, endValue, true, true);
	}
	
	public static Where between(String name, Object startValue, Object endValue, boolean isStartEqual, boolean isEndEqual) {
		if(startValue == null && endValue == null)
			throw new IllegalArgumentException("startValue and endValue can't be empty at same time");
		
		Where where = new Where(name, null);
		where.setValues(new Object[]{startValue, endValue, isStartEqual ? " >= " : " > ", isEndEqual ? " <= " : " < "});
		where.condition = BETWEEN;
		
		return where;
	}
	
	public static Where list(List<Where> whereList) {
		if(whereList == null || whereList.size() <= 0)
			throw new IllegalArgumentException("whereList can't not be empty");
		
		Where where = new Where(null, null);
		where.whereList = whereList;
		
		return where;
	}
}