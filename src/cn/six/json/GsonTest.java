package cn.six.json;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import cn.six.test.Bean;

import com.google.gson.Gson;

/**
 * 
 * @ClassName: GsonTest
 * @Description: TODO(这里用一句话描述这个类的作用)
 * @author A18ccms a18ccms_gmail_com
 * @date 2017年7月11日 下午9:42:47
 *
 */
public class GsonTest {

	SysLogTool sysLog = new SysLogTool(true);
	
	@Test
	public void JsonStrToBean() {
		String str = "{\"id\":11,\"name\":\"ce shi\"}";
		System.out.println(str);
		Gson gson = new Gson();
		Bean fromJson = gson.fromJson(str, Bean.class);
		System.out.println(fromJson);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Test
	public void testMapToJson() throws Exception {
		Map map = new HashMap();
		map.put("name", "json");
		map.put("bool", Boolean.TRUE);
		map.put("int", new Integer(1));
		map.put("arr", new String[] { "a", "b" });
		map.put("func", "function(i){ return this.arr[i]; }");
		Gson gson = new Gson();
		String json = gson.toJson(map);
		System.out.println(json);
	}

	@Test
	public void testListToJson() throws Exception {
		String[] arr = { "a", "b", "c" };
		List<String> asList = Arrays.asList(arr);
		Gson gson = new Gson();
		String json = gson.toJson(arr);
		String json2 = gson.toJson(asList);
		System.out.println(json);
		System.out.println(json2);
	}

	@Test
	public void testBeanToJson() throws Exception {
		Bean bean = new Bean();
		bean.setId(new Long(11));
		bean.setName("ce shi");
		Gson gson = new Gson();
		String json = gson.toJson(bean);
		System.out.println(json);
	}

	@Test
	public void testJsonForArray() throws Exception {
		Gson gson = new Gson();
		int[] ints={1,2,3,4,5};
		String json = gson.toJson(ints);
		sysLog.log(json);
		
		
	}
	
	
	//基本类型 序列化 反序列化
	@SuppressWarnings("unused")
	@Test
	public void testJson() throws Exception {// Serialization
		Gson gson = new Gson();
		gson.toJson(1); // ==> 1
		gson.toJson("abcd"); // ==> "abcd"
		gson.toJson(new Long(10)); // ==> 10
		int[] values = { 1 };
		gson.toJson(values); // ==> [1]

		// Deserialization
		int one = gson.fromJson("1", int.class);
		Integer one2 = gson.fromJson("1", Integer.class);
		Long two = gson.fromJson("1", Long.class);
		Boolean b = gson.fromJson("false", Boolean.class);
		String str = gson.fromJson("\"abc\"", String.class);
		String[] anotherStr = gson.fromJson("[\"abc\"]", String[].class);
	}
}
