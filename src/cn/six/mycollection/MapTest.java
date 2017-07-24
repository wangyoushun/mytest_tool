package cn.six.mycollection;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Test;

public class MapTest {

	@Test
	public void testName() throws Exception {
		Map<String, String> map = new HashMap<String, String>();
		String string = map.get("a");
		
		System.out.println(string);
	}
	
	
	class bb {
		Integer a;

		public void set(Integer a) {
			this.a = a;
		}
	}

	// test weakHashMap
	@Test
	public void testweakHashMap() {
		WeakHashMap<bb, Integer> weakHashMap = new WeakHashMap<bb, Integer>();

		Integer a = 2;
		Integer b = 3;
		Integer c = 4;
		// weakHashMap.put(a, c);
		// weakHashMap.put(b, c);
		// weakHashMap.put(c, c);
		bb bb = new bb();
		bb.set(a);
		weakHashMap.put(bb, c);
		bb = null;
		// bb.set(null);
		// a=null;
		// c = null;
		System.gc();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(weakHashMap);
		Iterator<Entry<bb, Integer>> iterator = weakHashMap.entrySet()
				.iterator();
		while (iterator.hasNext()) {
			Entry<bb, Integer> next = iterator.next();
			System.out.println(next);
			System.out.println(next.getKey() + "--" + next.getValue());
		}
	}

	/**
	 * test linkedHashMap
	 */
	@Test
	public void linkedHashMap() {
		LinkedHashMap<String, String> linkedHashMap = new LinkedHashMap<String, String>(
				16, 0.75f, true); // 按照访问顺序排序
		// LinkedHashMap<String,String> linkedHashMap = new
		// LinkedHashMap<String,String>(); //按照插入顺序排序
		linkedHashMap.put("a", "a");
		linkedHashMap.put("b", "b");
		linkedHashMap.put("c", "c");

		for (Entry<String, String> s : linkedHashMap.entrySet()) {
			System.out.println(s);
		}

		Iterator<String> iterator = linkedHashMap.keySet().iterator();
		while (iterator.hasNext()) {
			String next = iterator.next();
			System.out.println(linkedHashMap.get(next));
		}

	}

	/**
	 * test my hashMap
	 */
	@Test
	public void testmymap() {
		MyHashMap<String, String> myHashMap = new MyHashMap<String, String>();
		myHashMap.put("a", "a");
		System.out.println(myHashMap);
		System.out.println(myHashMap.size());

		Map<String, String> map = new HashMap<String, String>(100);
		System.out.println(map.size());
	}

	/**
	 * test map size modCount 1000 
	 * entrySet size 1000 
	 * loadFactor=0.75 
	 * table 2048
	 * threshold table*loadFactor=1536
	 */
	@Test
	public void testMapSize() {
		Map<String, String> map = new HashMap<String, String>();
		for (int i = 0; i < 1537; i++) {
			map.put(i + "", i + "");
		}
		map.put("12", "bb");
		System.out.println(map);
	}

	@Test
	public void mapToObject() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("id", 1);
		map.put("ab_cd_ed_gh_li_jk_lm_no_pq_rs_tu_vwa_xy_z", "wys");
		map.put("ab_cd_ed_gh_li_jk_lm_no_pq_rs_tu_vwa_xy", "wys");
		map.put("ab_cd_ed_gh_li_jk_lm_no_pq_rs_tu_vwa", "wys");
		map.put("ab_cd_ed_gh_li_jk_lm_no_pq_r", "wys");
		map.put("ab_cd_ed_gh_li_jk_lm_no_pq", "wys");
		map.put("ab_cd_ed_gh_li_jk_lm_no", "wys");
		map.put("ab_cd_ed_gh_li_jk", "wys");
		map.put("ab_cd_ed_gh_li_j", "wys");
		map.put("ab_cd_ed_g", "wys");
		map.put("ab_cd", "wys");

		Set<String> keySet = map.keySet();
		long start1 = System.currentTimeMillis();
		for (String key : keySet) {
			String keyN = TouFeng(key);
			// System.out.println(keyN);

			long end1 = System.currentTimeMillis();
			System.out.println(end1 - start1);

			long start2 = System.currentTimeMillis();
			for (String key1 : keySet) {
				String key2 = underlineToCamel(key1);
				// System.out.println(key2);
			}
		}

	}

	public String underlineToCamel(String param) {
		char ch = '_';
		if (param == null || "".equals(param.trim())) {
			return "";
		}
		int len = param.length();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++) {
			char c = param.charAt(i);
			if (c == ch) {
				if (++i < len) {
					sb.append(Character.toUpperCase(param.charAt(i)));
				}
			} else {
				sb.append(c);
			}
		}
		return sb.toString();
	}

	private String TouFeng(String key) {
		StringBuilder sb = new StringBuilder();
		String[] split = key.trim().split("_");
		// System.out.println(Arrays.toString(split));
		sb.append(split[0]);
		for (int i = 1; i < split.length; i++) {
			sb.append(toProperCase(split[i]));
		}

		return sb.toString();
	}

	private String toProperCase(String string) {
		return string.substring(0, 1).toUpperCase() + string.substring(1);
	}

	@Test
	public void testMaptoString() {
		Map<String, String> map = new HashMap<String, String>();
		map.put("a", "111");
		map.put("b", "22");
		System.out.println(map);
		List<Object> list = new ArrayList<Object>();
		list.add(map);
		System.out.println(list);

	}

	@Test
	public void testSumMap() {
		Map<String, Integer> map1 = new HashMap<String, Integer>();
		Map<String, Integer> map2 = new HashMap<String, Integer>();

		map1.put("a", 3);
		map1.put("b", 1);

		Map<String, Integer> map3 = new HashMap<String, Integer>(map1);
		map2.put("a", 1);
		map2.put("b", 1);

		map3.put("a", 1);

		map1.get("a");
		map1.putAll(map2);
		System.out.println(map1);
		System.out.println(map2);
		System.out.println(map3);
	}

}
