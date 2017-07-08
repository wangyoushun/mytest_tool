package cn.six.mypoi;

import java.util.Date;

public class User {
	private String name;
	private Integer age;
	private String address;
	private Double doubleValue;
	private Float floadValue;
	private Date dateValue;

	public Double getDoubleValue() {
		return doubleValue;
	}

	public void setDoubleValue(Double doubleValue) {
		this.doubleValue = doubleValue;
	}

	public Float getFloadValue() {
		return floadValue;
	}

	public void setFloadValue(Float floadValue) {
		this.floadValue = floadValue;
	}

	public Date getDateValue() {
		return dateValue;
	}

	public void setDateValue(Date dateValue) {
		this.dateValue = dateValue;
	}

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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "User [name=" + name + ", age=" + age + ", address=" + address
				+ ", doubleValue=" + doubleValue + ", floadValue=" + floadValue
				+ ", dateValue=" + dateValue + "]";
	}


}
