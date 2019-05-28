package com.dollhouse.core.editor;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.dollhouse.core.utils.StringUtils;

public class DyCustomDateEditor extends PropertyEditorSupport {
	private String dateFormatType = "yyyy-MM-dd";
	private String dateTimeFormatType = "yyyy-MM-dd HH:mm:ss";
	
	public DyCustomDateEditor(String dateFormatType) {
		if(dateFormatType != null) this.dateFormatType = dateFormatType;
	}
	
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(StringUtils.isEmpty(text)) return;
		
		Date date = null;
		SimpleDateFormat simpleDateFormat = null;
		try {
			if(text.length() == dateFormatType.length())
				simpleDateFormat = new SimpleDateFormat(dateFormatType);
			else if(text.length() == dateTimeFormatType.length())
				simpleDateFormat = new SimpleDateFormat(dateTimeFormatType);
			date = simpleDateFormat.parse(text);
		} catch(ParseException e) {
			e.printStackTrace();
		}
		setValue(date);
	}
}