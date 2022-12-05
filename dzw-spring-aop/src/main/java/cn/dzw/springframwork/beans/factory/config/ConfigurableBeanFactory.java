package cn.dzw.springframwork.beans.factory.config;

import cn.dzw.springframwork.beans.factory.BeanFactory;
import cn.dzw.springframwork.beans.factory.HierarchicalBeanFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Classname ConfigurableBeanFactory
 * @Description 配置类的bean
 * @Date 2022/10/22 15:59
 * @Created by dongzhiwei
 */
public interface ConfigurableBeanFactory extends HierarchicalBeanFactory,SingletonBeanRegistry {

    String SCOPE_SINGLETON = "singleton";

    String SCOPE_PROTOTYPE = "prototype";

    /**
     * 将 自定义的 BeanPostProcessor 加到list 中
     * @param beanPostProcessor
     */
    void addBeanPostProcessor(BeanPostProcessor beanPostProcessor);
}
