package cn.six.mybeanutils;

import java.util.List;
import java.util.Map;

class TestBean{
	private String beanName;
	private Integer beanYear;
	private List<String> list;
	private Map<String,String> map;
	
	
	public TestBean() {
	}
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public Integer getBeanYear() {
		return beanYear;
	}
	public void setBeanYear(Integer beanYear) {
		this.beanYear = beanYear;
	}
	public List<String> getList() {
		return list;
	}
	public void setList(List<String> list) {
		this.list = list;
	}
	public Map<String, String> getMap() {
		return map;
	}
	public void setMap(Map<String, String> map) {
		this.map = map;
	}
}