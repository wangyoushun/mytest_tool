package cn.six.designpattern.strategy;

public class Dog implements Comparable<Dog> {

	private String name;
	private Double weight;
	private Double height;

	public Dog() {
		super();
	}

	public Dog(double weight) {
		this.weight = weight;
	}

	public Dog(double height,double weight) {
		this.height = height;
		this.weight = weight;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public Double getHeight() {
		return height;
	}

	public void setHeight(Double height) {
		this.height = height;
	}

	@Override
	public String toString() {
		return "Dog [name=" + name + ", weight=" + weight + ", height="
				+ height + "]";
	}

	@Override
	public int compareTo(Dog o) {
		if (o instanceof Dog) {
			if (this.getWeight() > o.getWeight()) {
				return 1;
			} else if (this.getWeight() < o.getWeight()) {
				return -1;
			} else {
				return 0;
			}

		}
		return 100;
	}

}
