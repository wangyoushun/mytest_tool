package cn.six.designpattern.simpleFactory;

public class Square implements Shape{

	@Override
	public void draw() {
		System.out.println("square draw");
	}

	@Override
	public void eraser() {
		System.out.println("square eraser");
	}

}
