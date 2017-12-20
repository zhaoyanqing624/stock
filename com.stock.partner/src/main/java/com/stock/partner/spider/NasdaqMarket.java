package com.stock.partner.spider;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import org.apache.commons.io.FileUtils;


/**
 * @param url:下载的URL地址
 * @desc 将下载的cvs文件存入Mysql数据库
 */
public class NasdaqMarket {
	
	public static void main(String[] args) throws IOException {
		FileUtils.copyURLToFile(new URL("http://www.nasdaq.com/screening/companies-by-name.aspx?letter=0&exchange=nasdaq&render=download"), new File("E:/companyList_nasdaq.csv"));
	}
	
	
}
