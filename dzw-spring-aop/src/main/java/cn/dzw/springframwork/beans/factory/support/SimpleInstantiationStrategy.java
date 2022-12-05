package cn.dzw.springframwork.beans.factory.support;

import cn.dzw.springframwork.beans.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Classname SimpleInstantiationStrategy
 * @Description JDK 实例化
 * @Date 2022/8/26 15:06
 * @Created by dongzhiwei
 */
public class SimpleInstantiationStrategy implements InstantiationStrategy {

    @Override
    public Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor ctor, Object[] args) {
        Class clazz = beanDefinition.getBeanClass();
        try {
            if (null != ctor) {
                return clazz.getDeclaredConstructor(ctor.getParameterTypes()).newInstance(args);
            } else {
                return clazz.getDeclaredConstructor().newInstance();
            }
        } catch (Exception e) {

        }


        return null;
    }
}
