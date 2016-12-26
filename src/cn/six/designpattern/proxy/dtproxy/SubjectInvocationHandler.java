package cn.six.designpattern.proxy.dtproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class SubjectInvocationHandler implements InvocationHandler {

	private RealSubject realsub;

	public SubjectInvocationHandler(RealSubject realsub) {
		super();
		this.realsub = realsub;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("==动态代理前=");
		method.invoke(realsub, args);
		System.out.println("==动态代理后=");
		return null;
	}

}
