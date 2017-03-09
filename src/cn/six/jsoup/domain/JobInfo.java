package cn.six.jsoup.domain;

/**
 * lg job 详细信息
* @ClassName: JobInfo 
* @Description: TODO 
* @author iwantfly 
* @date 2017年3月9日 下午4:29:15 
*
 */
public class JobInfo {

	private Integer id;
	private Integer jobId;
	private String jobAdvantage; // 工作优势
	private String description; // 描述
	private String address; // 地址
	private String hrName; // hr名字
	private String url; 

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getJobId() {
		return jobId;
	}

	public void setJobId(Integer jobId) {
		this.jobId = jobId;
	}

	public String getJobAdvantage() {
		return jobAdvantage;
	}

	public void setJobAdvantage(String jobAdvantage) {
		this.jobAdvantage = jobAdvantage;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHrName() {
		return hrName;
	}

	public void setHrName(String hrName) {
		this.hrName = hrName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "JobInfo [id=" + id + ", jobId=" + jobId + ", jobAdvantage="
				+ jobAdvantage + ", description=" + description + ", address="
				+ address + ", hrName=" + hrName + ", url=" + url + "]";
	}
}
