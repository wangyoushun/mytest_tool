package cn.six.json;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.junit.Test;

import cn.six.test.Bean;

/**
 * json-lib 测试json
 *
 */
public class JsonTest {

	@Test
	public void testStringToJson() {
		String temp = "{a=[{aa=1}],b=2}";
		JSONObject fromObject = JSONObject.fromObject(temp);
		JSONArray jsonArray = fromObject.getJSONArray("a");
		System.out.println(jsonArray);
	}

	// Collection对象转换成JSON
	@Test
	public void testListToJson() {
		List<String> list = new ArrayList<String>();
		list.add("first");
		list.add("second");
		JSONArray jsonArray = JSONArray.fromObject(list);
		System.out.println(jsonArray);
		// prints ["first","second"]
	}

	// 数组转json
	@Test
	public void testArrayToJson() {
		int[] a = { 1, 2, 3, 4 };
		JSONArray fromObject = JSONArray.fromObject(a);
		System.out.println(fromObject);
	}

	// 字符串json转换成json， 根据情况是用JSONArray或JSONObject
	@Test
	public void testJsonStrToJSON() {
		JSONArray jsonArray = JSONArray.fromObject("['json','is','easy']");
		System.out.println(jsonArray);
		// prints ["json","is","easy"]
	}

	// Map转换成json， 是用jsonObject
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testMapToJson() {
		Map map = new HashMap();
		map.put("name", "json");
		map.put("bool", Boolean.TRUE);
		map.put("int", new Integer(1));
		map.put("arr", new String[] { "a", "b" });
		map.put("func", "function(i){ return this.arr[i]; }");

		JSONObject jsonObject = JSONObject.fromObject(map);
		System.out.println(jsonObject);
	}

	// bean ==> json
	// 实体类要用public修饰
	@Test
	public void testBeanToJson() {
		Bean bean = new Bean();
		bean.setId(new Long(11));
		bean.setName("ce shi");

		List<Object> arrayList = new ArrayList<>();
		arrayList.add(bean);

		JSONArray fromObject = JSONArray.fromObject(arrayList);
		System.out.println(fromObject);
	}

	@Test
	public void JsonStrToBean() {
		String str = "{\"id\":11,\"name\":\"ce shi\"}";
		System.out.println(str);

		JSONObject fromObject = JSONObject.fromObject(str);
		System.out.println(fromObject);
		System.out.println(fromObject instanceof JSONObject);
	}

}
