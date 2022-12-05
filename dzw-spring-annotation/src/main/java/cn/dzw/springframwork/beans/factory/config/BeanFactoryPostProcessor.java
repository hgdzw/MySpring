package cn.dzw.springframwork.beans.factory.config;

import cn.dzw.springframwork.beans.factory.ConfigurableListableBeanFactory;

/**
 * @Classname BeanFactoryPostProcessor
 * @Description 前置处理器 在 bean实例化之前
 * @Date 2022/10/19 16:33
 * @Created by dongzhiwei
 */
public interface BeanFactoryPostProcessor {

    /**
     * 在所有beanfaction 加载完成之后， 实例化对象之前 提供修改 beanfaction 属性的功能
     */
    void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory);


}
