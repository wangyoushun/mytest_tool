package cn.six.designpattern.simpleFactory;

public class Triger implements Shape {

	@Override
	public void draw() {
		System.out.println("Triger draw");
	}

	@Override
	public void eraser() {
		System.out.println("Triger eraser");
	}

}
