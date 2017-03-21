package cn.six.jsoup.main;

import java.sql.Connection;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.six.jsoup.domain.Job;
import cn.six.jsoup.util.DBUtil;

public class JobThread implements Runnable{

	private  BlockingQueue<String> queue;
	private String url;
	
	public JobThread(BlockingQueue<String> queue, String url) {
		super();
		this.queue = queue;
		this.url=url;
	}

	@Override
	public void run() {
			try {
					getJob();	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}

	public void getJob() throws Exception {
		Connection con=null;
//		Document doc = Jsoup.connect(url).data("query", "Java")
//				.userAgent("Mozilla").cookie("auth", "token").timeout(3000)
//				.post();
		 Document doc = Jsoup.connect(url).proxy("218.82.112.4",
				 8118).userAgent("Mozilla").timeout(30000).get();
		Thread.sleep(1000);
//		System.out.println(doc.title());
		Elements divE = doc
				.select("#s_position_list > ul > li > div.list_item_top");
		for (Element element : divE) {
			Job job = new Job();
			Elements aE = element.select("div.position > div.p_top > a");
			Elements h2E = aE.select("h2");
			Elements emE = aE.select("span > em");
			job.setTitle(h2E.text());
			job.setArea(emE.text());
			
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
				String[] split = text.split("-");
				split[0] = split[0].substring(0,split[0].length()-1);
				split[1] = split[1].substring(0,split[1].length()-1);
				try {
					job.setMoneyLeft(Integer.parseInt(split[0]));
					job.setMoneyRight(Integer.parseInt(split[1]));
				} catch (Exception e) {
					System.out.println("==========="+split[0]+","+split[1]);
					e.printStackTrace();
				}
			}
		
			job.setInputTime(new Date());
//			System.out.println(job);
			
			//save job
			con = DBUtil.openConnection();
			Integer jobId = DBUtil.saveReturnKey(con, job);
			con.close();
			
			String substring = aE.attr("href").substring(2);
			System.out.println("put  size "+queue.size());
//			queue.put("https://"+substring+"-"+1);
			
			queue.put("https://"+substring+"-"+jobId);
			
		}
	}
	
}
