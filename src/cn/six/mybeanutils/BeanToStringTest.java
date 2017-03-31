package cn.six.mybeanutils;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

/**
 * 测试beantostring 工具类
 */
public class BeanToStringTest {

	@Test
	public void beanToString() throws Exception {
		TestBean testBean = initBean();
		String beanToString = BeanToString.beanToString(testBean);
		System.out.println(beanToString);
	}

	@Test
	public void beanToJson() throws Exception {
		TestBean testBean = initBean();
		String beanToString = BeanToString.beanToJson(testBean);
		System.out.println(beanToString);

	}

	public TestBean initBean() {
		TestBean testBean = new TestBean();
		testBean.setBeanName("ts");
		testBean.setBeanYear(12);
		testBean.setList(Arrays.asList(new String[] { "1", "2" }));
		HashMap<String, String> hashMap = new HashMap<String, String>();
		hashMap.put("a", "a1");
		hashMap.put("b", "a2");
		hashMap.put("c", "c3");
		testBean.setMap(hashMap);
		return testBean;
	}

}
