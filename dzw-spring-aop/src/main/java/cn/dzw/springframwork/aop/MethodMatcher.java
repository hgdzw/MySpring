package cn.dzw.springframwork.aop;

import java.lang.reflect.Method;

/**
 * @Classname MethodMatcher
 * @Description 方法的过滤
 * @Date 2022/11/7 16:01
 * @Created by dongzhiwei
 */
public interface MethodMatcher {


    /**
     * 方法匹配
     * @param method 当前方法
     * @param targetClass 目标类
     * @return
     */
    boolean methodMatcher(Method method,Class<?> targetClass);





}
