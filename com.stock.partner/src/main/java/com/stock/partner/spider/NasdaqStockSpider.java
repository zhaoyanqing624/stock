package com.stock.partner.spider;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.stock.common.util.DateToStamp;
import com.stock.database.helper.StockInfoHelper;
import com.stock.database.helper.StockMarketHelper;
import com.stock.database.model.StockInfoPersistence;
import com.stock.database.model.StockMarketPersistence;

public class NasdaqStockSpider {
	public static void main(String[] args) throws ParseException, MalformedURLException, IOException {
		getNasdaqStockSpider();
	}
	public static void getNasdaqStockSpider() throws ParseException, MalformedURLException, IOException {
		String url = "";
		String fileName = "";
		List<StockMarketPersistence> stockMarketPersistences = StockMarketHelper.getStockMarketList("NASDAQ");
		String startData = "10000";
		DateToStamp dateToStamp = new DateToStamp();
		String endData = dateToStamp.dateToStamp();
		List<StockInfoPersistence> StockInfoList = StockInfoHelper.getStockInfoByBelongId(stockMarketPersistences.get(0).getSTOCKMARKETID());
		for(int i=0;i<2;i++) {
			url = "https://query1.finance.yahoo.com/v7/finance/download/"+StockInfoList.get(i).getSTOCKSYMBOL()+"?period1="+startData+"&period2="+endData+"&interval=1d&events=history&crumb=9w25cDGzdp8";
			System.out.println(url);
			
			fileName = StockInfoList.get(i).getSTOCKSYMBOL()+".csv";
			FileUtils.copyURLToFile(new URL(url), new File("E:/stockData/"+fileName));
		}
	}
}
