package cn.dzw.springframwork.beans.factory.support;

import main.java.cn.dzw.springframwork.factory.config.BeanDefinition;

import java.lang.reflect.Constructor;

/**
 * @Classname InstantiationStrategy
 * @Description 实例化策略接口
 * @Date 2022/8/26 15:04
 * @Created by dongzhiwei
 */
public interface InstantiationStrategy {


    Object instantiate(BeanDefinition beanDefinition, String beanName, Constructor
            ctor, Object[] args);
}
