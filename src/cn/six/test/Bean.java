package cn.six.test;

import java.io.Serializable;

public class Bean implements Serializable{
	private Long id;
	private String name;
	private Bean bean;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	
	public Bean getBean() {
		return bean;
	}

	public void setBean(Bean bean) {
		this.bean = bean;
	}

	@Override
	public String toString() {
		return "bean [id=" + id + ", name=" + name + "]";
	}
	
	
}