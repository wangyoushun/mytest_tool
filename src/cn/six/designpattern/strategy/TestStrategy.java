package cn.six.designpattern.strategy;

import java.util.Arrays;

/**
 * comparable and
 * 
 * @author 有顺
 * 
 */
public class TestStrategy {
	public static void main(String[] args) {
		// compareTest();
		compareTest2();
	}

	/**
	 * 第一种方式 排序单体对象实现comparable接口
	 */
	public static void compareTest() {
		Dog[] dogs = { new Dog(15), new Dog(12), new Dog(13), new Dog(15),
				new Dog(8), new Dog(14) };
		System.out.println("排序前");
		p(dogs);
		Arrays.sort(dogs);
		System.out.println("排序后");
		p(dogs);
	}

	/**
	 * 第二种方式 Comparator
	 */
	public static void compareTest2() {
		Dog[] dogs = { new Dog(15, 1), new Dog(12, 34), new Dog(13, 25),
				new Dog(15, 3), new Dog(8, 8), new Dog(14, 100) };
		System.out.println("排序前");
		p(dogs);

		Arrays.sort(dogs, new MyComparator());
		System.out.println("排序后");
		p(dogs);
	}

	public static void p(Dog[] dogs) {
		for (Dog dog : dogs) {
			System.out.println(dog.toString());
		}
	}

}
