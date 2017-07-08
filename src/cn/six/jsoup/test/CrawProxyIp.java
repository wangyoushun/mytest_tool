package cn.six.jsoup.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Test;

import cn.six.utils.FileTool;

public class CrawProxyIp {
	Document doc=null;
	String url = "http://www.xicidaili.com/";
	String proxyIp="121.204.165.27";
	int proxyPort=8118;
	
	public Document init() throws IOException{
		return Jsoup.connect(url).proxy(proxyIp, proxyPort)
				.userAgent("Mozilla").timeout(30000).get();
	}
	
	public Document getDoc(String url,String proxyIp, int proxyPort) throws IOException{
		return Jsoup.connect(url).proxy(proxyIp, proxyPort)
				.userAgent("Mozilla").timeout(8000).get();
	}
	
	@Test
	public void getIPAdress() throws Exception {
		doc=init();
		String title = doc.title();
		System.out.println(title);
		Elements ips = doc.select("#ip_list > tbody > tr > td:nth-child(2)");
		Elements ports = doc.select("#ip_list > tbody > tr > td:nth-child(3)");
		List<String> ipsList = new ArrayList<String>();
		for(int i=0;i<ips.size(); i++){
			String str=ips.get(i).text()+":"+ports.get(i).text();
			ipsList.add(str);
		}
		
		boolean writeAppend = FileTool.writeAppend(new File("D://ip.txt"), ipsList);
		System.out.println(writeAppend);
	}
	
	@Test
	public void validataIp() throws Exception {
		String url="https://www.lagou.com/";
		
		List<String> lines = FileTool.lines(new File("D://ip.txt"));
		for (String string : lines) {
			String[] split = string.split(":");
			try {
				long start = getTimems();
				Document doc2 = getDoc(url, split[0], Integer.parseInt(split[1]));
				String title = doc2.title();
				long end = getTimems();
				System.out.println(string+"  su"+title+"----"+(end-start));
			} catch (Exception e) {
				System.out.println(string+"  error");
			}
			
		}
	}
	
	public long getTimems(){
		return System.currentTimeMillis();
	}

}
