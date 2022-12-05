package cn.dzw.springframwork.beans.factory.config;

import cn.dzw.springframwork.beans.factory.BeanFactory;

/**
 * @Classname AutowireCapableBeanFactory
 * @Description 自动化完成工厂配置的接口
 * @Date 2022/10/22 14:48
 * @Created by dongzhiwei
 */
public interface AutowireCapableBeanFactory extends BeanFactory {

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessBeforeInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsBeforeInitialization(String beanName, Object existingBean);

    /**
     * 执行 BeanPostProcessors 接口实现类的 postProcessorsAfterInitialization 方法
     *
     * @param existingBean
     * @param beanName
     * @return
     */
    Object applyBeanPostProcessorsAfterInitialization(Object existingBean, String beanName);

}
