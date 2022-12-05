package cn.dzw.springframwork.context.support;

import cn.dzw.springframwork.beans.factory.ConfigurableListableBeanFactory;
import cn.dzw.springframwork.beans.factory.config.BeanFactoryPostProcessor;
import cn.dzw.springframwork.beans.factory.config.BeanPostProcessor;
import cn.dzw.springframwork.context.ApplicationEvent;
import cn.dzw.springframwork.context.ApplicationListener;
import cn.dzw.springframwork.context.ConfigurableApplicationContext;
import cn.dzw.springframwork.context.event.ApplicationEventMulticaster;
import cn.dzw.springframwork.context.event.ContextRefreshedEvent;
import cn.dzw.springframwork.context.event.SimpleApplicationEventMulticaster;
import cn.dzw.springframwork.core.io.DefaultResourceLoader;
import test.java.cn.dzw.springframwork.test.event.MyEvent;

import java.util.List;
import java.util.Map;

/**
 * @Classname AbstractApplicationContext
 * @Description 抽象核心类
 * @Date 2022/10/24 14:48
 * @Created by dongzhiwei
 */
public abstract class AbstractApplicationContext extends DefaultResourceLoader implements ConfigurableApplicationContext {

    private ApplicationEventMulticaster applicationEventMulticaster;

    final String APPLICATION_EVENT_MULTICASTER_BEAN_NAME = "applicationEventMulticaster";

    @Override
    public void refresh() {
        // 1. 创建bean factory  加载beanDefine
        refreshBeanFactory();

        //2 获取 beanFactory
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();

        //加载 自己写的 ApplicationContextAwareProcessor 因为用户不会帮你写进xml自动加载
        beanFactory.addBeanPostProcessor(new ApplicationContextAwareProcessor(this));

        //处理 实例化之前的 bean ， beanFactoryPostProcess
        invokeBeanFactoryPostProcessors(beanFactory);

        // 注册 beanPostProcess 的bean ，并实例化
        registerBeanPostProcessors(beanFactory);

        //实例化

        // 处理实例化之后的bean

        //这里事件处理
        //注册事件处理器
        initApplicationEventMulticaster();

        //注册事件监听器
        registerListener();

        //容器完成事件
        finishRefresh();
    }

    /**
     * 容器完成事件
     */
    private void finishRefresh() {
        pushEvent(new ContextRefreshedEvent(this));
    }

    public void pushEvent(MyEvent applicationEvent) {

        applicationEventMulticaster.multicastEvent(applicationEvent);
    }

    /**
     * 注册事件监听器
     */
    private void registerListener() {
        Map<String, ApplicationListener> beans = getBeansOfType(ApplicationListener.class);
        for (Map.Entry<String, ApplicationListener> entry : beans.entrySet()) {
            applicationEventMulticaster.addApplicationListener(entry.getValue());
        }
    }

    protected  void initApplicationEventMulticaster(){
        ConfigurableListableBeanFactory beanFactory = getBeanFactory();
        applicationEventMulticaster = new SimpleApplicationEventMulticaster();
        beanFactory.registerSingleton(APPLICATION_EVENT_MULTICASTER_BEAN_NAME, applicationEventMulticaster);

    }

    private void registerBeanPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanPostProcessor.class);
        if (null != beansOfType) {
            for (BeanPostProcessor value : beansOfType.values()) {
                beanFactory.addBeanPostProcessor(value);
            }
        }
    }

    private void invokeBeanFactoryPostProcessors(ConfigurableListableBeanFactory beanFactory){
        Map<String, BeanFactoryPostProcessor> beansOfType = beanFactory.getBeansOfType(BeanFactoryPostProcessor.class);
        if (null != beansOfType) {
            for (BeanFactoryPostProcessor value : beansOfType.values()) {
                value.postProcessBeanFactory(beanFactory);
            }
        }
    }

    protected abstract ConfigurableListableBeanFactory getBeanFactory();


    protected abstract void refreshBeanFactory();


    @Override
    public <T> Map<String, T> getBeansOfType(Class<T> type) {
        return getBeanFactory().getBeansOfType(type);
    }

    @Override
    public List<String> getBeanDefinitionNames() {
        return getBeanFactory().getBeanDefinitionNames();
    }

    @Override
    public Object getBean(String name) {
        return getBeanFactory().getBean(name);
    }

    @Override
    public Object getBean(String beanName, Object... args) {
        return getBeanFactory().getBean(beanName, args);
    }

    @Override
    public <T>  T getBean(String beanName, Class<T> requiredType) {
        return getBeanFactory().getBean(beanName, requiredType);
    }
}
