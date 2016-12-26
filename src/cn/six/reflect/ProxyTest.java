package cn.six.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyTest {

	public static void main(String[] args) {
		MyInvocationHandler handler = new MyInvocationHandler();
		IPersion p= (IPersion) Proxy.newProxyInstance(IPersion.class.getClassLoader(),new Class[]{IPersion.class}, handler);
		p.say();
	}
}

class MyInvocationHandler implements InvocationHandler{

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
	//	method.invoke(proxy, null);
		return null;
	}
	
}
