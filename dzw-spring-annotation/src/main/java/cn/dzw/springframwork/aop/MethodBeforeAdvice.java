package cn.dzw.springframwork.aop;

import java.lang.reflect.Method;

/**
 * @Classname MethodBeforeAdvice
 * @Description 前置拦截器链
 *          实现了这个接口的 就会被执行这个方法
 * @Date 2022/11/23 11:16
 * @Created by dongzhiwei
 */
public interface MethodBeforeAdvice  extends BeforeAdvice{

    /**
     * 拦截器链的前置方法
     * @param method
     * @param args
     * @param target
     */
    void before(Method method, Object[] args, Object target);
}
