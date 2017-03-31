package cn.six.jsoup.main;

import java.sql.Connection;
import java.util.Date;
import java.util.concurrent.BlockingQueue;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jsoup.Jsoup;
import org.jsoup.helper.StringUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import cn.six.jsoup.domain.Job;
import cn.six.jsoup.util.DBUtil;

public class JobThread implements Runnable {

	private BlockingQueue<String> queue;
	private String url;
	private static final Log log = LogFactory.getLog(JobThread.class);

	public JobThread(BlockingQueue<String> queue, String url) {
		super();
		this.queue = queue;
		this.url = url;
	}

	@Override
	public void run() {
		try {
			getJob();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取工作职位信息
	 * @throws Exception
	 */
	public void getJob() throws Exception {
		Connection con = null;
		// Document doc = Jsoup.connect(url).data("query", "Java")
		// .userAgent("Mozilla").cookie("auth", "token").timeout(3000)
		// .post();
		Document doc = Jsoup.connect(url).proxy("115.29.2.139", 80)
				.userAgent("Mozilla").timeout(30000).get();

		Thread.sleep(1000);
		// System.out.println(doc.title());
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

			Integer[] validataMoney = validataMoney(text);
			if(validataMoney!=null){
				job.setMoneyLeft(validataMoney[0]);
				job.setMoneyRight(validataMoney[1]);
			}
			job.setInputTime(new Date());
			// save job
			con = DBUtil.openConnection();
			Integer jobId = DBUtil.saveReturnKey(con, job);
			con.close();
			String substring = aE.attr("href");
			System.out.println("put  size " + queue.size());
			queue.put(substring + "-" + jobId);
		}
	}

	/**
	 * 校验工资
	 * @param text
	 * @return
	 */
	public Integer[] validataMoney(String text) {
		Integer[] moneyArray = null;
		if (!StringUtil.isBlank(text)) {
			String[] split = text.split("-");
			if (split.length > 1) {
				if (split[0].length() > 1 && split[1].length() > 1) {
					split[0] = split[0].substring(0, split[0].length() - 1);
					split[1] = split[1].substring(0, split[1].length() - 1);
					moneyArray = new Integer[2];
					try {
						moneyArray[0] = Integer.parseInt(split[0]);
						moneyArray[1] = Integer.parseInt(split[1]);
					} catch (NumberFormatException e) {
						log.error("error---money format error"+e.getMessage());
						return null;
					}
				}
			}
		}
		return moneyArray;
	}

}
