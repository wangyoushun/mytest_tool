package cn.six.mybeanutils;

import java.util.Date;
import java.util.List;

public class Student {

	private String name;

	private int age;

	private Date birth;
	
	private Student stu;

	private List<String> list;
	
	
	
	public List<String> getList() {
		return list;
	}

	public void setList(List<String> list) {
		this.list = list;
	}

	public Student getStu() {
		return stu;
	}

	public void setStu(Student stu) {
		this.stu = stu;
	}

	public String getName() {

		return name;

	}

	public void setName(String name) {

		this.name = name;

	}

	public int getAge() {

		return age;

	}

	public void setAge(int age) {

		this.age = age;

	}

	public Date getBirth() {

		return birth;

	}

	public void setBirth(Date birth) {

		this.birth = birth;

	}

	public Student(String name, int age, Date birth) {
		super();
		this.name = name;
		this.age = age;
		this.birth = birth;
	}

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
