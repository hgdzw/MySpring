package cn.dzw.springframwork.beans.factory.support;

import main.java.cn.dzw.springframwork.factory.BeanFactory;
import main.java.cn.dzw.springframwork.factory.config.BeanDefinition;

/**
 * @Classname AbstractBeanFactory
 * @Description 抽象 bean 工厂
 * @Date 2022/8/26 11:26
 * @Created by dongzhiwei
 */
abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    /**
     * 创建bean
     */
    protected abstract Object createBean(String beanName, BeanDefinition beanDefinition, Object... args);

    @Override
    public Object getBean(String beanName) {
        //获取 单例bean 获取不到就创建
        return doGetBean(beanName, new Object());
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return doGetBean(beanName, args);
    }


    public Object doGetBean(String beanName, Object... args) {
        Object singleton = getSingleton(beanName);
        if (null != singleton) {
            return singleton;
        }
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        return createBean(beanName, beanDefinition,args);
    }


    /**
     * 获取bean 定义
     * @param beanName
     * @return
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);

}
