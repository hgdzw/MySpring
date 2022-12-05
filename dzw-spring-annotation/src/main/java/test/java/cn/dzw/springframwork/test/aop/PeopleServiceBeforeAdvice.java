package test.java.cn.dzw.springframwork.test.aop;

import cn.dzw.springframwork.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

/**
 * @Classname PeopleServiceBeforeAdvice
 * @Description 自定义拦截方法
 * @Date 2022/11/23 11:49
 * @Created by dongzhiwei
 */
public class PeopleServiceBeforeAdvice implements MethodBeforeAdvice {


    @Override
    public void before(Method method, Object[] args, Object target) {
        System.out.println("拦截方法了" + method.getName());
    }
}
