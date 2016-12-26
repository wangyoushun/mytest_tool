package cn.six.designpattern.prototype;

import java.io.Serializable;

public class GodRindB implements Serializable{

	private static final long serialVersionUID = 6181368252373743411L;
	private Integer height;
	private Integer width;

	public Integer getHeight() {
		return height;
	}

	public GodRindB(Integer height, Integer width) {
		super();
		this.height = height;
		this.width = width;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

	public Integer getWidth() {
		return width;
	}

	public void setWidth(Integer width) {
		this.width = width;
	}

	@Override
	public String toString() {
		return "GodRindB [height=" + height + ", width=" + width + "]";
	}

}
