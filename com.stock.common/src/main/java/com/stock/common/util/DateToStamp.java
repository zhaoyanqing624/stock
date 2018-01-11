package com.stock.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

public class DateToStamp {
	/**
	 * 
	 * @desc 将时间转换成时间戳
	 * @throws ParseException
	 */
	public static String dateToStamp() throws ParseException{
		Date day = new Date();  
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String s = simpleDateFormat.format(day);
        Date date = simpleDateFormat.parse(s);
        long ts = date.getTime();
        String res = String.valueOf(ts);
        return res;
    }
	/**
	 * @throws ParseException 
	 * @desc 将时间戳转换成时间
	 */
	public static String TimeStampToDate(String timestampString){  
	    Long timestamp = Long.parseLong(timestampString)*1000;  
	    String date = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date(timestamp));
	    return date;  
	} 
	/**
	 * @desc 将中国时间转换成美国东部时间
	 * @param inputDate:中国时间
	 * @return
	 */
	public static String CNdateToUSdate(String inputDate) {
		TimeZone timeZoneSH = TimeZone.getTimeZone("Asia/Shanghai");
		TimeZone timeZoneNY = TimeZone.getTimeZone("America/New_York");
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		inputFormat.setTimeZone(timeZoneSH);
		Date date = null;
		try{
		    date = inputFormat.parse(inputDate);
		} 
		catch (ParseException e){
		}
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US);
		outputFormat.setTimeZone(timeZoneSH);
		//System.out.println("Asia/Shanghai:" + outputFormat.format(date));
		outputFormat.setTimeZone(timeZoneNY);
		//System.out.println("America/New_York:" + outputFormat.format(date));
		return outputFormat.format(date);
	}
}
