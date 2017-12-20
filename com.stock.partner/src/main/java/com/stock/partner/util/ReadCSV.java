package com.stock.partner.util;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import com.csvreader.CsvReader;
import com.stock.database.helper.StockInfoHelper;
import com.stock.database.helper.StockMarketHelper;
import com.stock.database.model.StockInfoPersistence;
import com.stock.database.model.StockMarketPersistence;

public class ReadCSV {
	public static void main(String[] args) {
		readCSV_NASDAQ();
	}
	public static void readCSV_NASDAQ() {
		String filePath = "E:/companyList_nasdaq.csv";
		List<StockMarketPersistence> stockMarketPersistences = StockMarketHelper.getStockMarketList("NASDAQ");
        try {
            // 创建CSV读对象
            CsvReader csvReader = new CsvReader(filePath);
            // 读表头
            csvReader.readHeaders();
            List<String> dataList = new ArrayList<String>();
            dataList.add("Symbol");
            dataList.add("Name");
            dataList.add("LastSale");
            dataList.add("MarketCap");
            dataList.add("IPOyear");
            dataList.add("Sector");
            dataList.add("industry");
            dataList.add("Summary Quote");
            
            int length = csvReader.getHeaderCount()-1;
            List<String> list = new ArrayList<String>();
            for(int i=0;i<length;i++) {
            	list.add(csvReader.getHeader(i));
            }
            if(dataList.containsAll(list)) {
            	while (csvReader.readRecord()){
            		List<StockInfoPersistence> stockInfoList = StockInfoHelper.getStockInfoBySymbol(csvReader.get("Symbol"));
            		StockInfoPersistence stockInfoPersistence = new StockInfoPersistence();
            		if(stockInfoList.isEmpty()) {
                		stockInfoPersistence.setSTOCKINFOID(UUID.randomUUID().toString());
                		stockInfoPersistence.setSTOCKSYMBOL(csvReader.get("Symbol"));
                		stockInfoPersistence.setSTOCKNAME(csvReader.get("Name"));
                		stockInfoPersistence.setLASTSALE(csvReader.get("LastSale"));
                		stockInfoPersistence.setMARKETCAP(csvReader.get("MarketCap"));
                		stockInfoPersistence.setIPOYEAR(csvReader.get("IPOyear"));
                		stockInfoPersistence.setSECTOR(csvReader.get("Sector"));
                		stockInfoPersistence.setINDUSTRY(csvReader.get("industry"));
                		stockInfoPersistence.setSUMMARYQUOTE(csvReader.get("Summary Quote"));
                		stockInfoPersistence.setBELONGMARKETID(stockMarketPersistences.get(0).getSTOCKMARKETID());
                		StockInfoHelper.saveStockInfo(stockInfoPersistence);
            		}else {
						if(stockInfoList.get(0).getLASTSALE()==csvReader.get("LastSale") && stockInfoList.get(0).getMARKETCAP()==csvReader.get("MarketCap")
								&& stockInfoList.get(0).getIPOYEAR()==csvReader.get("IPOyear")) {
							
						}else {
							stockInfoPersistence.setSTOCKINFOID(stockInfoList.get(0).getSTOCKINFOID());
	                		stockInfoPersistence.setSTOCKSYMBOL(stockInfoList.get(0).getSTOCKSYMBOL());
	                		stockInfoPersistence.setSTOCKNAME(stockInfoList.get(0).getSTOCKNAME());
	                		stockInfoPersistence.setLASTSALE(csvReader.get("LastSale"));
	                		stockInfoPersistence.setMARKETCAP(csvReader.get("MarketCap"));
	                		stockInfoPersistence.setIPOYEAR(csvReader.get("IPOyear"));
	                		stockInfoPersistence.setSECTOR(stockInfoList.get(0).getSECTOR());
	                		stockInfoPersistence.setINDUSTRY(stockInfoList.get(0).getINDUSTRY());
	                		stockInfoPersistence.setSUMMARYQUOTE(stockInfoList.get(0).getSUMMARYQUOTE());
	                		stockInfoPersistence.setBELONGMARKETID(stockMarketPersistences.get(0).getSTOCKMARKETID());
							StockInfoHelper.updataStockInfo(stockInfoPersistence);
						}
					}
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
	}
}
