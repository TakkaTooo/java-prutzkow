package ru.rsreu.astashkin0804;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateStringConverter {
	private SimpleDateFormat dateFormat;
	
	public DateStringConverter(String datePattern) {
		this.dateFormat = new SimpleDateFormat(datePattern);
	}
	
	public String convertDateToString(Date date) {
		return this.dateFormat.format(date);
	}
	
	public Date convertStringToDate(String stringDate) throws ParseException {
		return this.dateFormat.parse(stringDate);
	}
}
