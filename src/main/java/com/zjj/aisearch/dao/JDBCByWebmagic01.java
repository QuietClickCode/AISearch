package com.zjj.aisearch.dao;

import com.zjj.aisearch.model.Cnblogs;
import com.zjj.aisearch.utils.MySQLDBUtil;
import us.codecraft.webmagic.Page;
import us.codecraft.webmagic.Request;
import us.codecraft.webmagic.Site;
import us.codecraft.webmagic.Spider;
import us.codecraft.webmagic.model.HttpRequestBody;
import us.codecraft.webmagic.processor.PageProcessor;
import us.codecraft.webmagic.utils.HttpConstant;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCByWebmagic01 implements PageProcessor {

	public static final String URL_LIST = "https://www.cnblogs.com/mvc/AggSite/PostList.aspx";
	//页码
	public static int pageNum = 1;
		
	public static int count = 0;
	
	
	private CnblogsDao cnblogsDao = new CnblogsDao();
	
	Connection con = null;
	
	private Site site = Site.me().setRetryTimes(3).setSleepTime(1000).setCharset("utf-8")
			.addHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.110 Safari/537.36");;
	
	public void process(Page page) {
		
		if (page.getUrl().regex("^https://www\\.cnblogs\\.com$").match()) {
			try {
				page.addTargetRequests(page.getHtml().xpath("//div[@class=\"post_item_body\"]/h3/a/@href").all());

				pageNum ++;
				
				//模拟POST请求
				Request request = new Request(URL_LIST);
				request.setMethod(HttpConstant.Method.POST);
				
				//点击post请求右键选择复制 post 数据
				request.setRequestBody(HttpRequestBody.json(
						"{CategoryType:'SiteHome',ParentCategoryId:0,CategoryId:808,PageIndex:"+
								pageNum +",TotalPostCount:4000,ItemListActionName:'PostList'}",
								"utf-8"));
				
				page.addTargetRequest(request);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			//要爬取的内容太多，将200页改成2页来测试。
		}else if (page.getUrl().regex(URL_LIST).match() && pageNum <= 20) {
			try {
				Thread.sleep(5000);
				List<String> urls = page.getHtml().xpath("//div[@class='post_item_body']/h3/a/@href").all();
				page.addTargetRequests(urls);
				
				//模拟POST请求
				Request request = new Request(URL_LIST);
				request.setMethod(HttpConstant.Method.POST);
				
				//点击post请求右键选择复制 post 数据
				request.setRequestBody(HttpRequestBody.json(
						"{CategoryType:'SiteHome',ParentCategoryId:0,CategoryId:808,PageIndex:"+
								++pageNum +",TotalPostCount:4000,ItemListActionName:'PostList'}",
								"utf-8"));
				
				page.addTargetRequest(request);
				
				System.out.println("爬取第:"+ pageNum + "*********************************");
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			
			Cnblogs cnblogs = new Cnblogs();
			
			//设置标题
			cnblogs.setTitle(page.getHtml().xpath("//a[@id='cb_post_title_url']/text()").toString());
			
			//设置内容
			cnblogs.setContext(page.getHtml().xpath("//div[@id='cnblogs_post_body']/allText()").toString());

			try {
				int addSum = 0;
				//连接
				con = MySQLDBUtil.getConnection();
				addSum = cnblogsDao.add(con, cnblogs);
				
				if (addSum > 0) {
					System.out.println("保存成功");
					System.out.println(cnblogs);
				} else {
					System.out.println("false");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
//			page.putField("抓取的标题", page.getHtml().xpath("//a[@id='cb_post_title_url']/text()").toString());
//			
//			//抓取文章内容的时候，要将模块内的所有文本都爬取。所以要用allText()
//			page.putField("抓取的内容", page.getHtml().xpath("//div[@id='cnblogs_post_body']/allText()").toString());

			count ++;
		}
		
	}

	public Site getSite() {
		// TODO Auto-generated method stub
		return site;
	}
	
	public static void main(String[] args) {
		Spider.create(new JDBCByWebmagic01()).addUrl("https://www.cnblogs.com").thread(3).run();
		
		System.out.println("抓取了"+ count +"条数据");
	}

}