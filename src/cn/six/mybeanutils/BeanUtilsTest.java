package cn.six.mybeanutils;


import java.util.Date;

import org.apache.commons.beanutils.BeanUtils;
import org.junit.Test;

import cn.six.test.User;

public class BeanUtilsTest {
	@Test
	public void testName() throws Exception {
		User user  =new User();
		user.setId(1);
		user.setUserCode("wys");
		user.setLoginDate(new Date());
		User user2 = (User) BeanUtils.cloneBean(user);
		System.out.println(user);
		System.out.println(user2);
	}
}
