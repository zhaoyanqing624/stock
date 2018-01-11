package com.stock.partner.util;

import java.io.BufferedReader;
import java.io. InputStreamReader;
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

import com.stock.database.helper.ErrorHelper;
import com.stock.database.model.ErrorPersistence;

import org.apache.http.client.methods.HttpGet;
import org.apache.commons.lang3.StringEscapeUtils;


public class FinanceYahoo {
    HttpClient client = HttpClientBuilder.create().build();
    HttpClientContext context = HttpClientContext.create();

    public FinanceYahoo() {
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
            String[] vals = rtn.split(":");                 
            crumb = vals[2].replace("\"", "");             
            crumb = StringEscapeUtils.unescapeJava(crumb);  
            }
        return crumb;
    }

    public String getCrumb(String symbol) {
        return findCrumb(splitPageData(getPage(symbol)));
    }
                  

    public void downloadData(String symbol, long startDate, long endDate, String crumb) {
        String filename = String.format("%s.csv", symbol);
        String url = String.format("https://query1.finance.yahoo.com/v7/finance/download/%s?period1=%s&period2=%s&interval=1d&events=history&crumb=%s", symbol, startDate, endDate, crumb);
        HttpGet request = new HttpGet(url);
        System.out.println(url);

        request.addHeader("User-Agent", "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
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
                BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File("E://stockData/",filename)));
                byte[] buffer = new byte[10240];
                for(int length=0;(length=bis.read(buffer))>0;) {
                	bos.write(buffer,0,length);
                }
//                int inByte;
//                while((inByte = bis.read()) != -1) 
//                    bos.write(inByte);
                bis.close();
                bos.close();
            }
            HttpClientUtils.closeQuietly(response);
        } catch (Exception ex) {
            System.out.println("Exception");
            System.out.println(ex);
            ErrorPersistence errorPersistence = new ErrorPersistence();
            errorPersistence.setERRORID(UUID.randomUUID().toString());
            errorPersistence.setERRORSYMBOL(symbol);
            ErrorHelper.saveError(errorPersistence);
        }
    }
    
    public static void getStockFromYahoo(List<String> aList) {
        if (aList.size() > 0 ) {
            FinanceYahoo c = new FinanceYahoo();
            for (String symbol: aList) {
                String crumb;
				try {
					crumb = c.getCrumb(symbol);
					if (crumb != null && !crumb.isEmpty()) {
	                    System.out.println(String.format("Downloading data to %s", symbol));
	                    System.out.println("Crumb: " + crumb);
	                    c.downloadData(symbol, 0, System.currentTimeMillis(), crumb);
	                } else {
	                    System.out.println(String.format("Error retreiving data for %s", symbol));
	                }
				} catch (Exception e) {
					
				}
            }
        } else {
            System.out.println("Usage: java -classpath $CLASSPATH GetYahooQuotes SYMBOL");
        }
    }
    public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("CART");
		getStockFromYahoo(list);
	}
}
