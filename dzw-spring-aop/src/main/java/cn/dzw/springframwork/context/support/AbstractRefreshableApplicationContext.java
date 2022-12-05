package cn.dzw.springframwork.context.support;

import cn.dzw.springframwork.beans.factory.ConfigurableListableBeanFactory;
import cn.dzw.springframwork.beans.factory.support.DefaultListableBeanFactory;

/**
 * @Classname AbstractRefreshableApplicationContext
 * @Description 实现父级的 重要 refresh 方法的类
 * @Date 2022/10/24 17:15
 * @Created by dongzhiwei
 */
public abstract class AbstractRefreshableApplicationContext extends AbstractApplicationContext {

    private DefaultListableBeanFactory beanFactory;


    @Override
    protected void refreshBeanFactory() {
        DefaultListableBeanFactory beanFactory = createBeanFactory();
        //这个不确定怎么加载 可以是注解 或者 xml 所有是个抽象方法
        loadBeanDefinitions(beanFactory);
        this.beanFactory = beanFactory;
    }

    /**
     * 加载beanDefinition
     * @param beanFactory
     */
    protected abstract void loadBeanDefinitions(DefaultListableBeanFactory beanFactory);

    private DefaultListableBeanFactory createBeanFactory(){
        return new DefaultListableBeanFactory();
    }

    @Override
    protected ConfigurableListableBeanFactory getBeanFactory() {
        return beanFactory;
    }
}
