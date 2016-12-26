package cn.six.designpattern.prototype;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class Monkey implements Cloneable, Serializable {

	private static final long serialVersionUID = -4090587235725305713L;
	private String name;
	private Integer age;
	private GodRindB godrin;

	// 浅拷贝
	@Override
	protected Object clone() throws CloneNotSupportedException {
		// TODO Auto-generated method stub
		return super.clone();
	}

	// 深拷贝
	public Object deepClone() throws IOException, ClassNotFoundException {
		ByteArrayOutputStream bo = new ByteArrayOutputStream();
		ObjectOutputStream oo = new ObjectOutputStream(bo);
		oo.writeObject(this);
		ByteArrayInputStream bi = new ByteArrayInputStream(bo.toByteArray());
		ObjectInputStream oi = new ObjectInputStream(bi);
		return oi.readObject();
	}

	public String getName() {
		return name;
	}

	public Monkey(String name, Integer age, GodRindB godrin) {
		super();
		this.name = name;
		this.age = age;
		this.godrin = godrin;
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

	public GodRindB getGodrin() {
		return godrin;
	}

	public void setGodrin(GodRindB godrin) {
		this.godrin = godrin;
	}

	@Override
	public String toString() {
		return "Monkey [name=" + name + ", age=" + age + ", godrin=" + godrin
				+ "]";
	}

}
