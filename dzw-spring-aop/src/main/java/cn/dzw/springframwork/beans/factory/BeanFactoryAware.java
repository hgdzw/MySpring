package cn.dzw.springframwork.beans.factory;

/**
 * @Classname BeanFactoryAware
 * @Description 继承就能拥有 beanFactory
 * @Date 2022/11/1 15:14
 * @Created by dongzhiwei
 */
public interface BeanFactoryAware  extends Aware{

    /**
     * 设置bean factory 进去
     * @param beanFactory
     */
    void setBeanFactory(BeanFactory beanFactory);

}
