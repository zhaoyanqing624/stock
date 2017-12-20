package com.stock.partner.util;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import org.jsoup.Connection;
import org.jsoup.Connection.Method;
import org.jsoup.Connection.Response;
import org.jsoup.Jsoup;

public class test {

	public static void main(String[] args) throws IOException {
		 Connection conn = Jsoup.connect("https://finance.yahoo.com/quote/AAPL/history?period1=345398400&period2=1513699200&interval=1d&filter=history&frequency=1d");     
		 conn.method(Method.GET);     
		 conn.followRedirects(false);     
		 Response response = conn.execute();     
		 System.out.println(response.cookies());  
	}

}
