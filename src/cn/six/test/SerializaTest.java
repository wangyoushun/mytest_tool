package cn.six.test;

import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * 测试序列化， common-lang包
* @ClassName: SerializaTest 
* @Description: TODO 
* @author iwantfly 
* @date 2016年3月29日 下午2:02:11 
*
 */
public class SerializaTest {

	public static void main(String[] args) {
		Bean bean = new Bean();
		bean.setId(1L);
		bean.setName("111");
		
//		提供拷贝
		Bean clone = (Bean) SerializationUtils.clone(bean);
		System.out.println(bean);
		System.out.println(clone);
		
		bean.setBean(clone);
		
//		提供tostring
		String reflectionToString = ToStringBuilder.reflectionToString(bean);
		System.out.println(reflectionToString);
	}
}
