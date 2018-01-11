package com.stock.partner.util;

import java.io.BufferedReader;
import java.io. InputStreamReader;
import java.text.ParseException;
import java.io.FileOutputStream;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.BufferedOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;

import org.apache.http.client.HttpClient;
import org.apache.http.client.CookieStore;
import org.apache.http.client.utils.HttpClientUtils;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.HttpClientBuilder;

import com.stock.common.util.DateToStamp;
import com.stock.database.helper.ErrorHelper;
import com.stock.database.helper.StockInfoHelper;
import com.stock.database.model.ErrorPersistence;
import com.stock.database.model.StockInfoPersistence;

import org.apache.http.client.methods.HttpGet;

import org.apache.commons.lang3.StringEscapeUtils;


public class GetYahooQuotes {

    HttpClient client = HttpClientBuilder.create().build();
    HttpClientContext context = HttpClientContext.create();

    public GetYahooQuotes() {
        CookieStore cookieStore = new BasicCookieStore();
        client = HttpClientBuilder.create().build();
        context = HttpClientContext.create();
        context.setCookieStore(cookieStore);
    }

    public String getPage(String symbol) {
        String rtn = null;
        String url = String.format("https://finance.yahoo.com/quote/%s/?p=%s", symbol, symbol);
        HttpGet request = new HttpGet(url);
        System.out.println(url);

        request.addHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
        try {
            HttpResponse response = client.execute(request, context);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());

            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            rtn = result.toString();
            HttpClientUtils.closeQuietly(response);
        } catch (Exception ex) {
            System.out.println("Exception");
            System.out.println(ex);
        }
        System.out.println("returning from getPage");
        return rtn;
    }

    public List<String> splitPageData(String page) {
        // Return the page as a list of string using } to split the page
        return Arrays.asList(page.split("}"));
    }

    public String findCrumb(List<String> lines) {
        String crumb = "";
        String rtn = "";
        for (String l : lines) {
            if (l.indexOf("CrumbStore") > -1) {
                rtn = l;
                break;
            }
        }
        // ,"CrumbStore":{"crumb":"OKSUqghoLs8"        
        if (rtn != null && !rtn.isEmpty()) {
            String[] vals = rtn.split(":");                 // get third item
            crumb = vals[2].replace("\"", "");              // strip quotes
            crumb = StringEscapeUtils.unescapeJava(crumb);  // unescape escaped values (particularly, \u002f
            }
        return crumb;
    }

    public String getCrumb(String symbol) {
        return findCrumb(splitPageData(getPage(symbol)));
    }
                  

    public void downloadData(String symbol, long startDate, String endDate, String crumb) throws ParseException {
        String filename = String.format("%s.csv", symbol);
        String url = String.format("https://query1.finance.yahoo.com/v7/finance/download/%s?period1=%s&period2=%s&interval=1d&events=history&crumb=%s", symbol, startDate, endDate, crumb);
        HttpGet request = new HttpGet(url);
        System.out.println(url);

        request.addHeader("User-Agent2", "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.15) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
        try {
            HttpResponse response = client.execute(request, context);
            System.out.println("Response Code : " + response.getStatusLine().getStatusCode());
            HttpEntity entity = response.getEntity();

            String reasonPhrase = response.getStatusLine().getReasonPhrase();
            int statusCode = response.getStatusLine().getStatusCode();
            
            System.out.println(String.format("statusCode: %d", statusCode));
            System.out.println(String.format("reasonPhrase: %s", reasonPhrase));

            if (entity != null) {
                BufferedInputStream bis = new BufferedInputStream(entity.getContent());
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("E:/data/nasdaq",filename)));
                int inByte;
                while((inByte = bis.read()) != -1) 
                    bos.write(inByte);
                bis.close();
                bos.close();
            }
            HttpClientUtils.closeQuietly(response);

        } catch (Exception ex) {
            System.out.println("Exception");
            System.out.println(ex);
            List<ErrorPersistence> errorList = ErrorHelper.getErrorBySymbol(symbol);
            if(errorList.isEmpty()) {
            	ErrorPersistence errorPersistence = new ErrorPersistence();
    	        errorPersistence.setERRORID(UUID.randomUUID().toString());
    	        errorPersistence.setERRORSYMBOL(symbol);
    	        errorPersistence.setERRORDESC(ex.toString());
    	        ErrorHelper.saveError(errorPersistence);
            }
        }
    }
    

    public static void getData (String symbol) {
        GetYahooQuotes c = new GetYahooQuotes();
    	String crumb;
    	try {
			crumb = c.getCrumb(symbol);
			if (crumb != null && !crumb.isEmpty()) {
                System.out.println(String.format("Downloading data to %s", symbol));
                System.out.println("Crumb: " + crumb);
                //c.downloadData(symbol, 0, System.currentTimeMillis(), crumb);
                c.downloadData(symbol, 0, "1514422215", crumb);
            } else {
                System.out.println(String.format("Error retreiving data for %s", symbol));
            }
		} catch (Exception e) {
            List<ErrorPersistence> errorList = ErrorHelper.getErrorBySymbol(symbol);
            if(errorList.isEmpty()) {
            	ErrorPersistence errorPersistence = new ErrorPersistence();
    	        errorPersistence.setERRORID(UUID.randomUUID().toString());
    	        errorPersistence.setERRORSYMBOL(symbol);
    	        errorPersistence.setERRORDESC(e.toString());
    	        ErrorHelper.saveError(errorPersistence);
            }
		}
    }
    
    public static void main(String[] args) {
    	List<StockInfoPersistence> list = StockInfoHelper.getStockInfoByBelongId("3a2bb6e5-98a6-4d87-844b-6f94b608e9c7");
    	for(int i=0;i<list.size();i++) {
    		String symbol="";
    		if(list.get(i).getSTOCKSYMBOL()=="PRN") {
    			symbol = "_PRN";
    		}else {
				symbol = list.get(i).getSTOCKSYMBOL();
			}
        	File file = new File("E:/data/nasdaq/"+symbol+".csv");
        	if(file.exists()) {
        		
        	}else {
        		getData(list.get(i).getSTOCKSYMBOL());
			}
    	}
	}
}
