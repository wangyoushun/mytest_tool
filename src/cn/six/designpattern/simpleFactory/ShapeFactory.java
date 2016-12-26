package cn.six.designpattern.simpleFactory;

public class ShapeFactory {

	public static Shape factory(String alter) throws BadException{
		if("square".equals(alter)){
			return new Square();
		}else if("circle".equals(alter)){
			return new Circle();
		}else if("triger".equals(alter)){
			return new Triger();
		}else{
			throw new BadException();
		}
	}
}
