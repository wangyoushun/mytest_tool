package cn.six.jsoup.main;

import java.io.IOException;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.six.jsoup.domain.Job;

public class ParseLg {

	String url="https://www.lagou.com/zhaopin/Java/?labelWords=label";
	
	
	public static void main(String[] args) {
		ParseLg parseLg = new ParseLg();
		try {
			parseLg.getMain();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public void getMain() throws IOException{
		Document doc = Jsoup.connect(url).data("query", "Java")
				.userAgent("Mozilla").cookie("auth", "token").timeout(3000)
				.post();
		System.out.println(doc.title());
		Elements divE = doc.select("#s_position_list > ul > li > div.list_item_top");
		for (Element element : divE) {
			Job job = new Job();
			Elements aE = element.select("div.position > div.p_top > a");
			System.out.println(aE.attr("href"));
			Elements h2E = aE.select("h2");
			Elements emE = aE.select("span > em");
			job.setTitle(h2E.text());
			job.setArea(emE.text());
			
			Elements spanE = element.select("div.position > div.p_top > span");
			job.setCreateTime(spanE.text());
			
			Elements moenySpanE = element.select("div.position > div.p_bot > div > span");
			String text = moenySpanE.text();
			if(!StringUtil.isBlank(text)){
				String[] split = text.split("-");
				
			}
			
			System.out.println(job);
//			System.out.println(h2E.text());
			
		}
		
		//#s_position_list > ul > li:nth-child(1) > div.list_item_top > div.position > div.p_bot > div > span
		//#s_position_list > ul > li:nth-child(1) > div.list_item_top > div.position > div.p_top > span
		//#s_position_list > ul > li:nth-child(3) > div.list_item_top > div.position > div.p_top > a > span > em
		//#s_position_list > ul > li:nth-child(2) > div.list_item_top > div.position > div.p_top > a
		//#s_position_list > ul > li:nth-child(1) > div.list_item_top > div.position > div.p_top > a > h2
		
		
	}
}
