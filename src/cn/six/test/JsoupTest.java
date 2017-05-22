package cn.six.test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;


/**
 * 刷csdn博客阅读量
* @ClassName: JsoupTest 
* @Description: TODO 
* @author iwantfly 
* @date 2017年3月2日 下午1:57:06 
*
 */
public class JsoupTest {
	public static void main(String[] args) throws IOException {
		for (int i = 0; i < 20; i++) {
			vistiCsdn();
			System.out.println(i + "-----");
		}
	}

	private static void vistiCsdn() throws IOException {
		String url = "http://blog.csdn.net/smaloveyj/article/details/48344359";

		Document doc = Jsoup.connect(url).data("query", "Java")
				.userAgent("Mozilla").cookie("auth", "token").timeout(3000)
				.post();

		// String title = doc.title();
		// System.out.println(title);
	}

	@Test
	public void testName() throws Exception {
		String url = "http://api.xicidaili.com/free2016.txt";
//		String url2 = "http://blog.csdn.net/smaloveyj/article/details/48344359";
		String url2 = "http://blog.csdn.net/chengyabingfeiqi/article/details/50017475";
//		String url2 = "http://blog.csdn.net/jiuqiyuliang/article/details/45286191";
//		List<MyIp> ipList = getIp(url);
		MyIp myIp2 = new MyIp("183.32.88.14", "808");
		List<MyIp> ipList=new ArrayList<MyIp>();
		ipList.add(myIp2);
		String oldCount="";
		
		for(int i=0; i<100; i++){
			MyIp myIp = ipList.get(0);
			System.setProperty("http.maxRedirects", "50"); 
			System.getProperties().setProperty("proxySet", "true"); 
			System.getProperties().setProperty("http.proxyHost", myIp.getAddress());
			System.getProperties().setProperty("http.proxyPort", myIp.getPort());	
			
			Document doc = Jsoup.connect(url2).userAgent("Mozilla")
					.cookie("auth", "token").timeout(9000).get();
//			String text = doc.select("#article_details > div.article_manage.clearfix > div.article_r > span.link_view").text();
			String text = doc.select("			#skin_center > div.skin_center_t > div.skin_list > dl > dd > div.list_c_Title > p.read_r > label:nth-child(1) > span").text();
			if(text.equals(oldCount)){
				System.out.println("=============ggg==="+text);
				break;
			}else{
				oldCount=text;
			}
			System.out.println(doc.title()+"---"+text);
		}
	}
	
	
	public static List<MyIp> getIp(String url) {
		List<MyIp> ipList = null;
		try {
			// 1.向ip代理地址发起get请求，拿到代理的ip

			Document doc = Jsoup.connect(url).userAgent("Mozilla")
					.cookie("auth", "token").timeout(3000).get();
			// 2,将得到的ip地址解析除字符串
			String ipStr = doc.body().text().trim().toString();
			System.out.println(ipStr);
			// 3.用正则表达式去切割所有的ip
			String[] ips = ipStr.split("\\s+");
			// 4.循环遍历得到的ip字符串，封装成MyIp的bean
			ipList = new ArrayList<MyIp>();
			for (final String ip : ips) {
				MyIp myIp = new MyIp();
				String[] temp = ip.split(":");
				myIp.setAddress(temp[0].trim());
				myIp.setPort(temp[1].trim());
				ipList.add(myIp);
			}
		} catch (IOException e) {
			System.out.println("加载文档出粗");
		}
		return ipList;
	}

}

class MyIp {
	private String address;
	private String port;

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public MyIp(String address, String port) {
		super();
		this.address = address;
		this.port = port;
	}

	public MyIp() {
		
	}
}
