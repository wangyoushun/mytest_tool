package cn.six.designpattern.proxy.dtproxy;

import java.lang.reflect.Proxy;

/**
 * 动态代理
 * 最大的好处是接口中声明的所有方法都被转移到调用处理器一个集中的方法中处理
 * @author 有顺
 *
 */
public class Main {

	public static void main(String[] args) {
		RealSubject realSubject = new RealSubject();
		SubjectInvocationHandler handler = new SubjectInvocationHandler(
				realSubject);
		Subject sub = (Subject) Proxy.newProxyInstance(realSubject.getClass().getClassLoader(),
				realSubject.getClass().getInterfaces(), handler);
		sub.sysop();
	}
}
