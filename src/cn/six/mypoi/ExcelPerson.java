package cn.six.mypoi;

import java.util.Date;

public class ExcelPerson {

	private String name;
	private Integer age;
	private String abc;
	private String abc1;
	private String abc2;
	private String abc3;
	private String abc4;
	private String abc5;
	private Date fligtDate;
	
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public String getAbc() {
		return abc;
	}

	public void setAbc(String abc) {
		this.abc = abc;
	}

	public String getAbc1() {
		return abc1;
	}

	public void setAbc1(String abc1) {
		this.abc1 = abc1;
	}

	public String getAbc2() {
		return abc2;
	}

	public void setAbc2(String abc2) {
		this.abc2 = abc2;
	}

	public String getAbc3() {
		return abc3;
	}

	public void setAbc3(String abc3) {
		this.abc3 = abc3;
	}

	public String getAbc4() {
		return abc4;
	}

	public void setAbc4(String abc4) {
		this.abc4 = abc4;
	}

	public String getAbc5() {
		return abc5;
	}

	public void setAbc5(String abc5) {
		this.abc5 = abc5;
	}
	

	public Date getFligtDate() {
		return fligtDate;
	}

	public void setFligtDate(Date fligtDate) {
		this.fligtDate = fligtDate;
	}


	@Override
	public String toString() {
		return "ExcelPerson [name=" + name + ", age=" + age + ", abc=" + abc
				+ ", abc1=" + abc1 + ", abc2=" + abc2 + ", abc3=" + abc3
				+ ", abc4=" + abc4 + ", abc5=" + abc5 + ", fligtDate="
				+ fligtDate + "]";
	}


}
