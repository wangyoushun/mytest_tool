package cn.six.designpattern.simpleFactory;

public class Circle implements Shape {

	@Override
	public void draw() {
		System.out.println("cicrle draw");
	}

	@Override
	public void eraser() {
		System.out.println("circle eraser");
	}

}
