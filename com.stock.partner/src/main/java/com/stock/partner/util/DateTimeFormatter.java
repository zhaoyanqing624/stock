package com.stock.partner.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTimeFormatter {
	public static void DateTime(String date1,String date2) throws ParseException {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		 Date now =df.parse(date1);  
		 Date date=df.parse(date2);  
		 long l=now.getTime()-date.getTime();  
		 long day=l/(24*60*60*1000);  
		 long hour=(l/(60*60*1000)-day*24);  
		 long min=((l/(60*1000))-day*24*60-hour*60);  
		 long s=(l/1000-day*24*60*60-hour*60*60-min*60);  
		 System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");  
	}
}
