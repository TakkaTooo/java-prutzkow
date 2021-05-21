package ru.rsreu.astashkin0804;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.prutzkow.resourcer.Resourcer;

public class DateStringConverter {
	private static SimpleDateFormat dateFormat = new SimpleDateFormat(Resourcer.getString("demo.datePattern"));
	public static String convertDateToString(Date date) {
		return dateFormat.format(date);
	}
	
	public static Date convertStringToDate(String stringDate) throws ParseException {
		return dateFormat.parse(stringDate);
	}
}
