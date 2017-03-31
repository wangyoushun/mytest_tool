package cn.six.jsoup.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import javax.net.ssl.HttpsURLConnection;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.junit.Test;

import cn.six.jsoup.domain.Job;

public class ParseLg {

	// String url = "https://www.lagou.com/zhaopin/Java/2/?filterOption=2";
	private String url = "https://www.lagou.com/zhaopin/Java/";
	private int size = 30;
	private BlockingQueue<String> queue = new LinkedBlockingQueue<>();

	public static void main(String[] args) {

		ParseLg parseLg = new ParseLg();
		parseLg.getMain();

	}

	public void getMain() {
		System.out.println("job main start------------------------");
		int cpuNum = Runtime.getRuntime().availableProcessors();
		ExecutorService jobThreadPool = Executors.newFixedThreadPool(cpuNum/2);
		ExecutorService jobInfoThreadPool = Executors.newFixedThreadPool(cpuNum);

		List<String> pageList = getPageList();

		for (String strulr : pageList) {
			jobThreadPool.execute(new JobThread(queue, strulr));
		}
		
		for(int i=0; i<cpuNum; i++){
			jobInfoThreadPool.execute(new JobDetailThread(queue));
		}

		jobThreadPool.shutdown();
		jobInfoThreadPool.shutdown();
		System.out.println("job main end------------------------");
		
//		new Thread(new JobDetailThread(queue)).start();
//		new Thread(new JobDetailThread(queue)).start();
//		new Thread(new JobDetailThread(queue)).start();
//		new Thread(new JobDetailThread(queue)).start();
	}

	// https://www.lagou.com/zhaopin/Java/20/

	public List<String> getPageList() {
		long start = System.currentTimeMillis();
		// 将每一页连接加入list
		List<String> pageList = new ArrayList<String>(size);
		for (int i = size; i > 0; i--) {
			System.out.println("add ulr --" + url + i);
			pageList.add(url + i);
		}
		long end = System.currentTimeMillis();
		System.out.println("add ulr end--" + (end - start));
		return pageList;
	}

	@Test
	public void getJob() throws IOException {
		 Document doc = Jsoup.connect(url).proxy("115.29.2.139",
				 80).userAgent("Mozilla").timeout(30000).get();
//		Document doc = Jsoup.connect(url).data("query", "Java")
//				.userAgent("Mozilla").cookie("auth", "token").timeout(30000)
//				.get();
		System.out.println(doc.title());
		Elements divE = doc
				.select("#s_position_list > ul > li > div.list_item_top");
		for (Element element : divE) {
			Job job = new Job();
			Elements aE = element.select("div.position > div.p_top > a");
			Elements h2E = aE.select("h2");
			Elements emE = aE.select("span > em");
			job.setTitle(h2E.text());
			job.setArea(emE.text());
			String substring = aE.attr("href");
		

			System.out.println(substring);

			Elements spanE = element.select("div.position > div.p_top > span");
			job.setCreateTime(spanE.text());

			Elements companyE = element
					.select("div.company > div.company_name > a");
			job.setCompanyName(companyE.text());

			Elements industryE = element.select("div.company > div.industry");
			job.setIndustry(industryE.text());

			Elements botDivE = element.select("div.position > div.p_bot > div");
			String[] split2 = botDivE.text().split(" ");
			// System.out.println(Arrays.toString(split2));
			job.setExperience(split2[1]);
			job.setEducation(split2[3]);

			String text = split2[0];
			if (!StringUtil.isBlank(text)) {
				text = text.replaceAll("k", "");
				String[] split = text.split("-");
				job.setMoneyLeft(Integer.parseInt(split[0]));
				job.setMoneyRight(Integer.parseInt(split[1]));
			}
			job.setInputTime(new Date());
			System.out.println(job);
		}
	}

	// 测试HttpsURLConnection代理ip
	@Test
	public void getDocByJsoup() {
		String href = url;
		String ip = "218.82.112.4";

		int port = 8188;

		try {

			Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip,
					port));

			URL url = new URL(href);

			HttpsURLConnection urlcon = (HttpsURLConnection) url
					.openConnection(proxy);
			urlcon.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			urlcon.connect(); // 获取连接

			InputStream is = urlcon.getInputStream();

			BufferedReader buffer = new BufferedReader(
					new InputStreamReader(is));

			StringBuffer bs = new StringBuffer();

			String l = null;

			while ((l = buffer.readLine()) != null) {

				bs.append(l);

			}

			System.out.println(bs.toString());

			Document doc = Jsoup.parse(bs.toString());
			System.out.println(doc.title());
		} catch (Exception e) {

			e.printStackTrace();

		}

	}
}

class TestThread implements Runnable {

	private String url;

	public TestThread(String url) {
		this.url = url;
	}

	@Override
	public void run() {
		try {
			Document doc = Jsoup.connect(url).proxy("218.82.112.4", 8118)
					.userAgent("Mozilla").timeout(30000).get();
			// Document doc = Jsoup.connect(url).data("query", "Java")
			// .userAgent("Mozilla").cookie("auth", "token")
			// .timeout(30000).get();
			System.out.println(doc.title());
			Elements divE = doc
					.select("#s_position_list > ul > li > div.list_item_top");
			for (Element element : divE) {
				Job job = new Job();
				Elements aE = element.select("div.position > div.p_top > a");
				Elements h2E = aE.select("h2");
				Elements emE = aE.select("span > em");
				job.setTitle(h2E.text());
				job.setArea(emE.text());
				String substring = aE.attr("href").substring(2);

				System.out.println(substring);

				Elements spanE = element
						.select("div.position > div.p_top > span");
				job.setCreateTime(spanE.text());

				Elements companyE = element
						.select("div.company > div.company_name > a");
				job.setCompanyName(companyE.text());

				Elements industryE = element
						.select("div.company > div.industry");
				job.setIndustry(industryE.text());

				Elements botDivE = element
						.select("div.position > div.p_bot > div");
				String[] split2 = botDivE.text().split(" ");
				// System.out.println(Arrays.toString(split2));
				job.setExperience(split2[1]);
				job.setEducation(split2[3]);

				String text = split2[0];
				if (!StringUtil.isBlank(text)) {
					text = text.replaceAll("k", "");
					String[] split = text.split("-");
					job.setMoneyLeft(Integer.parseInt(split[0]));
					job.setMoneyRight(Integer.parseInt(split[1]));
				}
				job.setInputTime(new Date());
				System.out.println(job);
			}
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
