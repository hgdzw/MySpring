package cn.dzw.springframwork.context.support;

import cn.dzw.springframwork.beans.factory.support.DefaultListableBeanFactory;
import cn.dzw.springframwork.beans.factory.xml.XmlBeanDefinitionReader;

/**
 * @Classname AbstractXmlApplicationContext
 * @Description 加载xml 的抽象类
 * @Date 2022/10/24 17:25
 * @Created by dongzhiwei
 */
public abstract class AbstractXmlApplicationContext extends AbstractRefreshableApplicationContext {


    @Override
    protected void loadBeanDefinitions(DefaultListableBeanFactory beanFactory) {
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(beanFactory, this);
        //获取方式是不同的
        String[] configLocations = getConfigLocations();
        if (null != configLocations) {
            reader.loadBeanDefinitions(configLocations);
        }
    }

    protected abstract String[] getConfigLocations();
}
