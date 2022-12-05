package test.java.cn.dzw.springframwork.test.bean;

import cn.dzw.springframwork.beans.PropertyValue;
import cn.dzw.springframwork.beans.PropertyValues;
import cn.dzw.springframwork.beans.factory.ConfigurableListableBeanFactory;
import cn.dzw.springframwork.beans.factory.config.BeanDefinition;
import cn.dzw.springframwork.beans.factory.config.BeanFactoryPostProcessor;

/**
 * @Classname MyUserFactoryPostProcess
 * @Description TODO
 * @Date 2022/10/25 17:42
 * @Created by dongzhiwei
 */
public class MyUserFactoryPostProcess implements BeanFactoryPostProcessor {


    /**
     * 在 实例化之前
     * @param beanFactory
     * @return
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) {
        BeanDefinition userService = beanFactory.getBeanDefinition("userService");
        PropertyValues propertyValues = userService.getPropertyValues();
        propertyValues.addPropertyValue(new PropertyValue("uId", "1003"));
//        userService
    }
}
