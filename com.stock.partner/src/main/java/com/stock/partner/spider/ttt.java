package com.stock.partner.spider;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.commons.lang.StringUtils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.stock.common.util.DateToStamp;
import com.stock.database.helper.StockDataHelper;
import com.stock.database.helper.StockInfoHelper;
import com.stock.database.model.StockDataPersistence;
import com.stock.database.model.StockInfoPersistence;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.Task;
import us.codecraft.webmagic.example.OschinaBlog;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.pipeline.PageModelPipeline;
import us.codecraft.webmagic.processor.PageProcessor;

public class ttt {
	public static void main(String[] args) throws ParseException {
		 SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		 Date now =df.parse("2004-03-26 13:31:40");  
		 Date date=df.parse("2004-01-02 11:30:24");  
		 long l=now.getTime()-date.getTime();  
		 long day=l/(24*60*60*1000);  
		 long hour=(l/(60*60*1000)-day*24);  
		 long min=((l/(60*1000))-day*24*60-hour*60);  
		 long s=(l/1000-day*24*60*60-hour*60*60-min*60);  
		 System.out.println(""+day+"天"+hour+"小时"+min+"分"+s+"秒");  
	}

}
