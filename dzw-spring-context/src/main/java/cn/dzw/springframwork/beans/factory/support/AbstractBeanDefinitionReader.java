package cn.dzw.springframwork.beans.factory.support;

import cn.dzw.springframwork.core.io.DefaultResourceLoader;
import cn.dzw.springframwork.core.io.ResourceLoader;

/**
 * @Classname AbstractBeanDefinitionReader
 * @Description 抽象的bean 定义
 * @Date 2022/8/30 14:39
 * @Created by dongzhiwei
 */
public abstract class AbstractBeanDefinitionReader implements BeanDefinitionReader  {

    private ResourceLoader resourceLoader;

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public AbstractBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this(new DefaultResourceLoader(),beanDefinitionRegistry);
    }

    public AbstractBeanDefinitionReader(ResourceLoader resourceLoader, BeanDefinitionRegistry beanDefinitionRegistry) {
        this.resourceLoader = resourceLoader;
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    @Override
    public ResourceLoader getResourceLoader() {
        return resourceLoader;
    }

    public BeanDefinitionRegistry getBeanDefinitionRegistry() {
        return beanDefinitionRegistry;
    }
}
