package cn.six.designpattern.strategy;

import java.util.Comparator;

public class MyComparator implements Comparator<Dog>{

	@Override
	public int compare(Dog o1, Dog o2) {
		return (int) (o1.getHeight()-o2.getHeight());
	}

}
