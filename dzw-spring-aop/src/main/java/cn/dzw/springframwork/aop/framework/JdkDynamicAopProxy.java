package cn.dzw.springframwork.aop.framework;

import cn.dzw.springframwork.aop.AdvisedSupport;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Classname JdkDynamicAopProxy
 * @Description jdk 动态代理的实现
 * @Date 2022/11/9 16:59
 * @Created by dongzhiwei
 */
public class JdkDynamicAopProxy implements AopProxy, InvocationHandler {

    private AdvisedSupport advised;


    public JdkDynamicAopProxy(AdvisedSupport advised) {
        this.advised = advised;
    }

    @Override
    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),advised.getTargetSource().getTargetClass(),this);
    }

    /**
     * 为了 Proxy.newProxyInstance 的入参 才继承的
     * @param proxy
     * @param method
     * @param args
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 判断方法 然后执行用户传进来的方法
        if (advised.getMethodMatcher().methodMatcher(method, advised.getTargetSource().getTarget().getClass())) {
            return advised.getMethodInterceptor().invoke(new ReflectiveMethodInvocation(advised.getTargetSource().getTarget(), method, args));
        }
        return method.invoke(advised.getTargetSource().getTarget(), args);
    }
}
