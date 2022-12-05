package cn.dzw.springframwork.beans.factory.support;

import main.java.cn.dzw.springframwork.factory.config.BeanDefinition;

/**
 * @Classname BeanDefinitionRegistry
 * @Description bean 的注册
 * @Date 2022/8/26 14:15
 * @Created by dongzhiwei
 */
public interface BeanDefinitionRegistry {


    void registryBeanDefinition(String beanName, BeanDefinition beanDefinition);


}
