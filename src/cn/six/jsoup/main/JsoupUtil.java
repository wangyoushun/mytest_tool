package cn.six.jsoup.main;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Random;

import org.apache.commons.lang.math.RandomUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.six.utils.FileTool;

public class JsoupUtil {
	private static List<String> lines=null;
	
	static{
		 lines = FileTool.lines(new File("D://ip.txt"));
	}
	
	public static Document getDoc(String url,String proxyIp, int proxyPort) throws IOException{
		return Jsoup.connect(url).proxy(proxyIp, proxyPort)
				.userAgent("Mozilla").timeout(8000).get();
	}
	
	public static synchronized Document getDoc(String url) throws IOException{
		int index = RandomUtils.nextInt(lines.size()-1);
		String str = lines.get(index);
		String[] split = str.split(":");
		String proxyIp=split[0];
		int proxyPort = Integer.parseInt(split[1]);
		System.out.println(proxyIp);
		return getDoc(url, proxyIp, proxyPort);
	}
	
	
}
