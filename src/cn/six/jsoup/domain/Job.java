package cn.six.jsoup.domain;

import java.util.Date;

/**
 * lg job 信息
 * 
 * @ClassName: Job
 * @Description: TODO
 * @author iwantfly
 * @date 2017年3月9日 下午4:27:20
 * 
 */
public class Job {

	private Integer id;
	private String title; // 职位标题
	private String companyName; // 公司名称
	private String industry; // 介绍
	private Integer moneyLeft; // 最低工资
	private Integer moneyRight; // 最高工资
	private String createTime; // 发布时间
	private String area;  //地区
	private String experience; //经验
	private String education;  //学历
	private Date inputTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getIndustry() {
		return industry;
	}

	public void setIndustry(String industry) {
		this.industry = industry;
	}

	public Integer getMoneyLeft() {
		return moneyLeft;
	}

	public void setMoneyLeft(Integer moneyLeft) {
		this.moneyLeft = moneyLeft;
	}

	public Integer getMoneyRight() {
		return moneyRight;
	}

	public void setMoneyRight(Integer moneyRight) {
		this.moneyRight = moneyRight;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public Date getInputTime() {
		return inputTime;
	}

	public void setInputTime(Date inputTime) {
		this.inputTime = inputTime;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = experience;
	}

	public String getEducation() {
		return education;
	}

	public void setEducation(String education) {
		this.education = education;
	}

	
	@Override
	public String toString() {
		return "Job [id=" + id + ", title=" + title + ", companyName="
				+ companyName + ", industry=" + industry + ", moneyLeft="
				+ moneyLeft + ", moneyRight=" + moneyRight + ", createTime="
				+ createTime + ", area=" + area + ", experience=" + experience
				+ ", education=" + education + ", inputTime=" + inputTime + "]";
	}

}
