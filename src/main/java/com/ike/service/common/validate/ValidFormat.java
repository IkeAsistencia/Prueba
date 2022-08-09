package com.ike.service.common.validate;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ValidFormat {

	SimpleDateFormat format = new SimpleDateFormat("yyyy'-'MM'-'dd'T'HH':'mm':'ss");
	SimpleDateFormat formatYearMonth = new SimpleDateFormat("YYYYMM");
	
	public boolean date(String date) {
		try {
			format.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public boolean dateFormatYearMonth(String date) {
		try {
			formatYearMonth.parse(date);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
