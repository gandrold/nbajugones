package es.nbajugones.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateFormatter {

	public static String formatDate(String pattern, Date date){
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String formattedDate = formatter.format(date);
        return formattedDate;
	}
	
	public static Date convertToDate(String pattern, String date) throws ParseException{
		SimpleDateFormat formatter = new SimpleDateFormat(pattern);
		return formatter.parse(date);
	}
	
}
