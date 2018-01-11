package com.stock.partner.spider;

import java.text.ParseException;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stock.common.util.DateToStamp;
import com.stock.common.util.GetDate;
import com.stock.database.helper.StockDataHelper;
import com.stock.database.helper.StockInfoHelper;
import com.stock.database.model.StockDataPersistence;
import com.stock.database.model.StockInfoPersistence;
import com.stock.partner.util.DateTimeFormatter;
import com.stock.partner.util.ReadCSV;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.processor.PageProcessor;

public class NasdaqStockSpider implements PageProcessor{

    private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setTimeOut(10000);

    @Override
    public void process(Page page) {
    	String result = StringUtils.substringBetween(page.getHtml().toString(), "root.App.main = ",";\n}(this)");
    	JsonObject returnData = new JsonParser().parse(result).getAsJsonObject();
    	JsonElement symbol = returnData.getAsJsonObject("context").getAsJsonObject("dispatcher").getAsJsonObject("stores").getAsJsonObject("PageStore").getAsJsonObject("pageData").get("symbol");
    	List<StockInfoPersistence> stockInfoList = StockInfoHelper.getStockInfoBySymbol(symbol.getAsString());
    	if (!stockInfoList.isEmpty()) {
    		//获取时间
			DateToStamp dateToStamp = new DateToStamp();
			JsonObject jsonObject = returnData.getAsJsonObject("context").getAsJsonObject("dispatcher").getAsJsonObject("stores").getAsJsonObject("HistoricalPriceStore").getAsJsonArray("prices").get(0).getAsJsonObject();
			String date = dateToStamp.CNdateToUSdate(dateToStamp.TimeStampToDate(jsonObject.get("date").toString()));
			List<StockDataPersistence> sList = StockDataHelper.getStockDataByStockInfoId(stockInfoList.get(0).getSTOCKINFOID());
			String open = jsonObject.get("open").toString();
			String high = jsonObject.get("high").toString();
			String low = jsonObject.get("low").toString();
			String close = jsonObject.get("close").toString();
			String adjclose = jsonObject.get("adjclose").toString();
			String volume = jsonObject.get("volume").toString();
			StockDataPersistence dataPersistence = new StockDataPersistence();
			dataPersistence.setSTOCKINFOID(stockInfoList.get(0).getSTOCKINFOID());
			dataPersistence.setDATE(date);
			dataPersistence.setOPEN(open);
			dataPersistence.setHIGH(high);
			dataPersistence.setLOW(low);
			dataPersistence.setCLOSE(close);
			dataPersistence.setADJCLOSE(adjclose);
			dataPersistence.setVOLUME(volume);
			if(sList.isEmpty()) {
				dataPersistence.setSTOCKDATAID(UUID.randomUUID().toString());
				StockDataHelper.saveStockData(dataPersistence);
			}else {
				List<StockDataPersistence> stockDataList = StockDataHelper.getStockDataByStockInfoId_Date(stockInfoList.get(0).getSTOCKINFOID(), date);
				if(stockDataList.isEmpty()) {
					dataPersistence.setSTOCKDATAID(sList.get(0).getSTOCKDATAID());
					StockDataHelper.updateStockData(dataPersistence);
				}
			}
			String[] strings = {date.split(" ")[0],open,high,low,close,adjclose,volume};
			ReadCSV readCSV = new ReadCSV();
			readCSV.readCSV_NasdaqStock(stockInfoList.get(0).getSTOCKSYMBOL(), strings);
		}
    }

    @Override
    public Site getSite() {
        return site;
    }
    public static void startSpider() throws ParseException {
    	GetDate getDate = new GetDate();
    	String date1 = getDate.getDate();
    	List<StockInfoPersistence> sList = StockInfoHelper.getStockInfoByBelongId("3a2bb6e5-98a6-4d87-844b-6f94b608e9c7");
    	for(int i=0;i<sList.size();i++) {
    		String symbol = sList.get(i).getSTOCKSYMBOL();
    		Spider.create(new NasdaqStockSpider()).addUrl("https://finance.yahoo.com/quote/"+symbol+"/history?p="+symbol).thread(20).run();
    	}
    	String date2 = getDate.getDate();
    	DateTimeFormatter dateTimeFormatter = new DateTimeFormatter();
    	dateTimeFormatter.DateTime(date1, date2);
//    	String symbol = "AAAP";
//    	Spider.create(new NasdaqStockSpider()).addUrl("https://finance.yahoo.com/quote/"+symbol+"/history?p="+symbol).thread(15).run();
    }
    public static void main(String[] args) throws ParseException {
		startSpider();
	}
}
