package com.stock.common.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetDate {
	public static String getDate() {
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(d);
	}
}	
