package cn.six.mycollection;

import java.util.concurrent.ConcurrentHashMap;

public class CacheMap {

	private static ConcurrentHashMap<Object,Object> cache = new ConcurrentHashMap<Object,Object>();
	
	private static CacheMap cacheInstance = new CacheMap();
	private CacheMap(){
		
	}
	
	public synchronized static CacheMap getInstance(){
		return cacheInstance;
	}
	
	public static Object put(Object o){
		return cache.putIfAbsent(o, o);
	}
	
	public static Object get(Object o){
		return cache.get(o);
	}
}
