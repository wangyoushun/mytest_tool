package cn.six.mypoi;

public class City {
	private Integer id;
	private Integer year;
	private Double money;
	private Integer month;
	private String city;

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		this.money = money;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	@Override
	public String toString() {
		return "City [id=" + id + ", year=" + year + ", money=" + money + ", month=" + month
				+ ", city=" + city + "]";
	}

}
