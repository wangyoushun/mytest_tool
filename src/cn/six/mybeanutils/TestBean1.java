package cn.six.mybeanutils;

public class TestBean1{
	private String beanName;
	private int verNo;
	private int aaa;
	public String getBeanName() {
		return beanName;
	}
	public void setBeanName(String beanName) {
		this.beanName = beanName;
	}
	public int getVerNo() {
		return verNo;
	}
	public void setVerNo(int verNo) {
		this.verNo = verNo;
	}
	public int getAaa() {
		return aaa;
	}
	public void setAaa(int aaa) {
		this.aaa = aaa;
	}
	@Override
	public String toString() {
		return "TestBean1 [beanName=" + beanName + ", verNo=" + verNo
				+ ", aaa=" + aaa + "]";
	}
	

	
}
