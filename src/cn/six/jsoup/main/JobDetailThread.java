package cn.six.jsoup.main;

import java.sql.Connection;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import cn.six.jsoup.domain.JobInfo;
import cn.six.jsoup.util.DBUtil;

public class JobDetailThread implements Runnable {

	private BlockingQueue<String> queue;
//	private static final Log log = LogFactory.getLog(JobDetailThread.class);

	public JobDetailThread() {
		super();
	}

	public JobDetailThread(BlockingQueue<String> queue) {
		super();
		this.queue = queue;
	}

	@Override
	public void run() {
		while (true) {
			try {
				getJobDetail();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void getJobDetail() throws Exception {
		Connection con = null;

		String url = queue.take();
		String[] split = url.split("-");
		url = split[0];
//		Document doc = Jsoup.connect(url).data("query", "Java")
//				.userAgent("Mozilla").cookie("auth", "token").timeout(3000)
//				.post();
		System.out.println(url);
		 Document doc = Jsoup.connect(url).proxy("115.29.2.139",
				 80).userAgent("Mozilla").timeout(30000).get();
		 Thread.sleep(1000);
		JobInfo jobInfo = new JobInfo();
		jobInfo.setJobId(Integer.parseInt(split[1]));
		String jobAdvantage = doc.select("#job_detail > dd.job-advantage > p")
				.text();
		jobInfo.setJobAdvantage(jobAdvantage);
		String description = doc.select("#job_detail > dd.job_bt > div").text();
		jobInfo.setDescription(description);
		String address = doc.select(
				"#job_detail > dd.job-address.clearfix > div.work_addr").text();
		jobInfo.setAddress(address);
		String hrName = doc.select(
				"#job_detail > dd.jd_publisher > div > div.publisher_name > a")
				.text();
		jobInfo.setHrName(hrName);
		System.out.println(jobInfo);
		System.out.println(Thread.currentThread().getName() + "  get  "
				+ queue.size());
		con = DBUtil.openConnection();
		DBUtil.saveReturnKey(con, jobInfo);
		con.close();
	}

	public static void main(String[] args) throws Exception {
		String url = "https://www.lagou.com/jobs/2552408.html";
		BlockingQueue<String> queue = new LinkedBlockingQueue<>();
		queue.put(url);
		JobDetailThread jobDetailThread = new JobDetailThread(queue);
		jobDetailThread.getJobDetail();
	}

}
