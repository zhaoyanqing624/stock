package com.stock.partner.spider;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import com.stock.partner.util.GetYahooQuotes;

import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.ConsolePageModelPipeline;
import us.codecraft.webmagic.model.OOSpider;
import us.codecraft.webmagic.model.annotation.ExtractBy;
import us.codecraft.webmagic.model.annotation.HelpUrl;
import us.codecraft.webmagic.model.annotation.TargetUrl;
import us.codecraft.webmagic.pipeline.FilePipeline;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.scheduler.RedisScheduler;
import us.codecraft.webmagic.selector.Html;

public class github implements PageProcessor {

    private Site site = Site.me().setCycleRetryTimes(5).setRetryTimes(5).setSleepTime(500).setTimeOut(3 * 60 * 1000)
            .setUserAgent("Mozilla/5.0 (Windows NT 6.1; WOW64; rv:38.0) Gecko/20100101 Firefox/38.0")
            .addHeader("Accept", "text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8")
            .addHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3")
            .setCharset("UTF-8");

    private static final int voteNum = 1000;


    @Override
    public void process(Page page) {
    	System.out.println(page);

    }

    @Override
    public Site getSite() {
        return site;
    }

    public static void main(String[] args) throws Exception {
    	GetYahooQuotes getYahooQuotes = new GetYahooQuotes();
    	String crumb =  getYahooQuotes.getCrumb("IPAS");
    	try {
			if (crumb == null) {
				System.out.println("NULL");
			} else {
				System.out.println(crumb);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("ERROR");
		}
    	String urlString = "https://query1.finance.yahoo.com/v7/finance/download/IPAS?period1=1511764257&period2=1514356257&interval=1d&events=history&crumb="+crumb;
    	download(urlString, "1", "D:\\webmagic\\");
//		Spider.create(new github()).
//                addUrl("https://query1.finance.yahoo.com/v7/finance/download/IPAS?period1=1511764257&period2=1514356257&interval=1d&events=history&crumb="+crumb).
//                addPipeline(new FilePipeline("D:\\webmagic\\")).
//                thread(5).
//                run();
    }
    
    
    public static void download(String urlString, String filename,String savePath) throws Exception {
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection con = url.openConnection();
        //设置请求超时为5s
        con.setConnectTimeout(5*1000);
        // 输入流
        InputStream is = con.getInputStream();

        // 1K的数据缓冲
        byte[] bs = new byte[1024];
        // 读取到的数据长度
        int len;
        // 输出的文件流
        File sf=new File(savePath);
        if(!sf.exists()){
            sf.mkdirs();
        }
        OutputStream os = new FileOutputStream(sf.getPath()+"\\"+filename);
        // 开始读取
        while ((len = is.read(bs)) != -1) {
            os.write(bs, 0, len);
        }
        // 完毕，关闭所有链接
        os.close();
        is.close();
    }
}
