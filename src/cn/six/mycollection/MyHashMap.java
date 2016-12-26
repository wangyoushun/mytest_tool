package cn.six.mycollection;

import java.util.HashMap;

public class MyHashMap<K, V> extends HashMap<K, V>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4292508483132673263L;

	public MyHashMap(int initialCapacity) {
		super(100);
	}
	
	public MyHashMap() {
		super();
	}

}
