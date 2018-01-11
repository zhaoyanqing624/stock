package com.stock.partner.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.zip.GZIPInputStream;

public class testURL {  
	public static void main(String[] args) throws IOException {
		String urlString = "https://finan";
		URL weatherAPI = new URL(urlString);
	    HttpURLConnection apiConnection = (HttpURLConnection) weatherAPI.openConnection();
	    apiConnection.setRequestMethod("GET");
	    apiConnection.setRequestProperty("Accept-Encoding", "gzip");
	    apiConnection.setRequestProperty("User-Agent", "Mozilla/5.0 (X11; U; Linux x86_64; en-US; rv:1.9.2.13) Gecko/20101206 Ubuntu/10.10 (maverick) Firefox/3.6.13");
	    apiConnection.connect();
	    InputStream gzippedResponse = apiConnection.getInputStream();
	    InputStream decompressedResponse = new GZIPInputStream(gzippedResponse);
	    Reader reader = new InputStreamReader(decompressedResponse, "UTF-8");
	    StringWriter writer = new StringWriter();
	    char[] buffer = new char[10240];
	    for(int length = 0; (length = reader.read(buffer)) > 0;){
	        writer.write(buffer, 0, length);
	    }
	    writer.close();
	    reader.close();
	    decompressedResponse.close();
	    gzippedResponse.close();
	    apiConnection.disconnect();
	    System.out.println(writer.toString());
	}
    
}
