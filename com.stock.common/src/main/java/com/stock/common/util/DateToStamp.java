package com.stock.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

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
}
