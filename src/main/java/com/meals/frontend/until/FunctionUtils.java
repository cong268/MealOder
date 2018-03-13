package com.meals.frontend.until;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FunctionUtils {
	public static Date convertDateByFormatLocal(String date, String format) {
		if (date == null || date.isEmpty() || format == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		try {
			return sdf.parse(date);
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}
	}
}
