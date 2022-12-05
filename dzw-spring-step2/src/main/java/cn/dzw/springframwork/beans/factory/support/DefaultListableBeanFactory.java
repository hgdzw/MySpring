package cn.dzw.springframwork.beans.factory.support;

import main.java.cn.dzw.springframwork.factory.config.BeanDefinition;

import java.util.HashMap;
import java.util.Map;

/**
 * @Classname DefaultListableBeanFactory
 * @Description 核心类实现
 * @Date 2022/8/26 14:13
 * @Created by dongzhiwei
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String, BeanDefinition> beanDefinitionMap = new HashMap<String, BeanDefinition>();


    @Override
    protected BeanDefinition getBeanDefinition(String beanName) {
        return beanDefinitionMap.get(beanName);
    }

    @Override
    public void registryBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        beanDefinitionMap.put(beanName, beanDefinition);
    }
}
